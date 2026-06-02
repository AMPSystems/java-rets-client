package us.ampre.rets.client.models;

import org.apache.commons.lang3.StringUtils;
import us.ampre.rets.client.GetObjectIterator;
import us.ampre.rets.client.SinglePartInputStream;
import us.ampre.rets.client.exceptions.RetsRuntimeException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Streaming iterator implementation for parsing multipart RETS GetObject responses.
 * Each iteration yields a SingleObjectResponse backed by the raw InputStream (no copying).
 */
public class GetObjectResponseStreamingIterator<T extends SingleObjectResponse> implements GetObjectIterator<T> {
    public static final char CR = '\r';
    public static final char LF = '\n';
    public static final String EOL = CR + "" + LF;
    public static final String BS = "--";

    private final PushbackInputStream multipartStream;
    private final String boundary;
    private Boolean hasNext;

    public static <T extends SingleObjectResponse> GetObjectIterator<T> createIterator(final GetObjectResponse response, int streamBufferSize) throws Exception {
        String boundary = null;
        try {
            boundary = response.getBoundary();
        } catch (IllegalArgumentException iae) {
            // Header was malformed or missing boundary - attempt best-effort detection from stream
            InputStream in = response.getInputStream();
            if (in != null && in.markSupported()) {
                try {
                    int detectionLimit = Math.max(streamBufferSize, 65536);
                    in.mark(detectionLimit);
                    byte[] buf = new byte[detectionLimit];
                    int read = in.read(buf);
                    if (read > 0) {
                        String sample = new String(buf, 0, read);
                        String[] lines = sample.split("\\r?\\n");
                        for (String line : lines) {
                            String t = line.trim();
                            if (t.startsWith("--")) {
                                String token = t.substring(2);
                                if (token.endsWith("--")) token = token.substring(0, token.length() - 2);
                                token = token.trim();
                                if (!token.isEmpty()) {
                                    boundary = token;
                                    break;
                                }
                            }
                        }
                    }
                    in.reset();
                } catch (IOException ignored) {
                    try { in.reset(); } catch (IOException e) { /* ignore */ }
                }
            }
        }

        if (boundary != null)
            return new GetObjectResponseStreamingIterator(response, boundary, streamBufferSize);

        // Fallback: try treating the whole response as a single non-multipart object
        try {
            final NonMultipartGetObjectStreamingIterator nm = new NonMultipartGetObjectStreamingIterator(response.getHeaders(), response.getInputStream());
            return new GetObjectIterator<>() {
                private boolean returned = false;

                @Override
                public void close() throws IOException {
                    nm.close();
                }

                @Override
                public boolean hasNext() {
                    return nm.hasNext();
                }

                @Override
                public T next() {
                    return (T) nm.next();
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException("");
                }
            };
        } catch (Exception e) {
            // If even fallback fails, return an empty iterator that will close the underlying stream on close
            return new GetObjectIterator<>() {

                public void close() throws IOException {
                    response.getInputStream().close();
                }

                public boolean hasNext() {
                    return false;
                }

                public T next() {
                    throw new NoSuchElementException();
                }

                public void remove() {
                    throw new UnsupportedOperationException("");
                }
            };
        }
    }

    private GetObjectResponseStreamingIterator(GetObjectResponse response, String boundary, int streamBufferSize) {
        this.boundary = boundary;

        BufferedInputStream input = new BufferedInputStream(response.getInputStream(), streamBufferSize);
        this.multipartStream = new PushbackInputStream(input, BS.length() + this.boundary.length() + EOL.length());
    }

    @Override
    public boolean hasNext() {
        if (this.hasNext != null)
            return this.hasNext;

        try {
            this.hasNext = this.getHaveNext();
            return this.hasNext;
        } catch (IOException e) {
            throw new RetsRuntimeException(e);
        }
    }

    @Override
    public T next() {
        if (this.hasNext() == false)
            throw new NoSuchElementException();

        this.hasNext = null;
        try {
            return getNext();
        } catch (Exception e) {
            throw new RetsRuntimeException(e);
        }
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() throws IOException {
        this.multipartStream.close();
    }

    private boolean getHaveNext() throws IOException {
        String line;
        while ((line = this.readLine()) != null) {
            if (line.equals(BS + this.boundary))
                return true;
            if (line.equals(BS + this.boundary + BS))
                return false;
        }
        return false;
    }

    private T getNext() throws Exception {
        Map<String, String> headers = new HashMap<>();
        String header;
        while (StringUtils.isNotEmpty(header = this.readLine())) {
            int nvSeperatorIndex = header.indexOf(':');
            if (nvSeperatorIndex == -1) {
                headers.put(header, "");
            } else {
                String name = header.substring(0, nvSeperatorIndex);
                String value = header.substring(nvSeperatorIndex + 1).trim();
                headers.put(name, value);
            }
        }
        // NOTE: create SingleObjectResponse WITHOUT copying the input stream (streaming mode)
        return (T) new SingleObjectResponse(headers, new SinglePartInputStream(this.multipartStream, BS + this.boundary), null, false);
    }

    private String readLine() throws IOException {
        boolean eolReached = false;
        StringBuilder line = new StringBuilder();
        int currentChar = -1;
        while (eolReached == false && (currentChar = this.multipartStream.read()) != -1) {
            eolReached = (currentChar == CR || currentChar == LF);
            if (eolReached == false)
                line.append((char) currentChar);
        }

        if (currentChar == -1 && line.length() == 0)
            return null;

        if (currentChar == CR) {
            int nextChar = this.multipartStream.read();
            if (nextChar != LF)
                this.multipartStream.unread(new byte[]{(byte) nextChar});
        }

        return line.toString();
    }
}
