package us.ampre.rets.client.models;

import us.ampre.rets.client.GetObjectIterator;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Streaming iterator for a non-multipart GetObject response.
 * Returns SingleObjectResponse instances that do NOT copy the underlying InputStream,
 * allowing callers to stream directly from the transport without buffering into memory.
 */
public final class NonMultipartGetObjectStreamingIterator implements GetObjectIterator<SingleObjectResponse> {
    private boolean exhausted;
    private final Map<String, String> headers;
    private final InputStream fileBackedStream;

    public NonMultipartGetObjectStreamingIterator(Map<String, String> headers, InputStream in) {
        this.exhausted = false;
        this.headers = Objects.requireNonNull(headers, "headers");
        Objects.requireNonNull(in, "in");
        try {
            final java.nio.file.Path tmp = us.ampre.rets.client.utils.InputStreamUtil.copyStreamToTempFile(in, "rets-object");
            FileInputStream fis = new FileInputStream(tmp.toFile());
            this.fileBackedStream = new FilterInputStream(fis) {
                @Override
                public void close() throws IOException {
                    try {
                        super.close();
                    } finally {
                        try {
                            java.nio.file.Files.deleteIfExists(tmp);
                        } catch (IOException ignored) {
                        }
                    }
                }
            };
        } catch (IOException e) {
            throw new RuntimeException("Failed to read input stream", e);
        }
    }

    @Override
    public void close() throws IOException {
        // No action; file will be removed when the returned stream is closed
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNext() {
        return this.exhausted == false;
    }

    @Override
    public SingleObjectResponse next() {
        if (this.exhausted)
            throw new NoSuchElementException("Stream exhausted");

        this.exhausted = true;
        try {
            // Create SingleObjectResponse without copying the InputStream (streaming mode)
            return new SingleObjectResponse(this.headers, this.fileBackedStream, null, false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
