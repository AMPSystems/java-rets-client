package us.ampre.rets.client.models;

import org.apache.commons.lang3.StringUtils;
import us.ampre.rets.client.GetObjectIterator;
import us.ampre.rets.client.SinglePartInputStream;
import us.ampre.rets.client.NonMultipartGetObjectResponseIterator;
import us.ampre.rets.client.exceptions.RetsRuntimeException;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Iterator implementation for parsing multipart RETS GetObject responses.
 * Each iteration yields a SingleObjectResponse representing a part in the response.
 *
 * @param <T> The type of SingleObjectResponse
 */
public class GetObjectResponseIterator<T extends SingleObjectResponse> implements GetObjectIterator<T> {
    public static final char CR = '\r';
    public static final char LF = '\n';
    public static final String EOL = CR + "" + LF;
    public static final String BS = "--";

    private final PushbackInputStream multipartStream;
    private final String boundary;
    private Boolean hasNext;

    /**
     * Creates a new iterator for parsing a multipart RETS GetObject response.
     * If the response does not contain a boundary, returns an empty iterator.
     *
     * @param response The RETS GetObjectResponse to iterate over
     * @param streamBufferSize The buffer size for reading the response stream
     * @return A GetObjectIterator for iterating over SingleObjectResponse objects
     * @throws Exception if an error occurs during iterator creation
     */
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
            return new GetObjectResponseIterator(response, boundary, streamBufferSize);

        // Fallback: try treating the whole response as a single non-multipart object
        try {
            final NonMultipartGetObjectResponseIterator nm = new NonMultipartGetObjectResponseIterator(response.getHeaders(), response.getInputStream());
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

    /**
     * Constructs an iterator for a multipart RETS GetObject response.
     *
     * @param response The RETS GetObjectResponse to iterate over
     * @param boundary The multipart boundary string
     * @param streamBufferSize The buffer size for reading the response stream
     */
    private GetObjectResponseIterator(GetObjectResponse response, String boundary, int streamBufferSize) {
        this.boundary = boundary;

        BufferedInputStream input = new BufferedInputStream(response.getInputStream(), streamBufferSize);
        this.multipartStream = new PushbackInputStream(input, BS.length() + this.boundary.length() + EOL.length());
    }

    /**
     * Returns {@code true} if the iterator has more elements (parts) to read.
     * Caches the result to avoid redundant stream reads.
     *
     * @return {@code true} if there is another part, {@code false} otherwise
     * @throws RetsRuntimeException if an I/O error occurs
     */
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

    /**
     * Returns the next SingleObjectResponse in the multipart stream.
     * Resets the cached hasNext state.
     *
     * @return The next SingleObjectResponse
     * @throws NoSuchElementException if no more elements exist
     * @throws RetsRuntimeException if an error occurs while reading the next part
     */
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
        return (T) new SingleObjectResponse(headers, new SinglePartInputStream(this.multipartStream, BS + this.boundary));
    }

    // TODO find existing library to do this
    /**
     * Reads the next line from the multipart stream, handling CR/LF and pushback for non-standard line endings.
     *
     * @return The next line as a String, or {@code null} if the end of stream is reached
     * @throws IOException if an I/O error occurs
     */
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