package us.ampre.rets.client;

import us.ampre.rets.client.models.SingleObjectResponse;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Iterator for a non-multipart GetObject response.
 * <p>
 * This iterator is single-use and will return at most one {@link SingleObjectResponse}.
 * Instead of eagerly reading the entire input stream into memory, the stream is copied
 * to a temporary file and the returned SingleObjectResponse exposes a FileInputStream
 * that deletes the temp file when closed.
 * </p>
 */
public final class NonMultipartGetObjectResponseIterator implements GetObjectIterator {
    private boolean exhausted;
    private final Map<String, String> headers;
    private final InputStream fileBackedStream;

    /**
     * Constructs a new iterator for a non-multipart response.
     * The provided input stream is copied to a temporary file and closed.
     *
     * @param headers response headers
     * @param in      input stream containing the response body (will be copied)
     * @throws NullPointerException if headers or in is null
     * @throws RuntimeException     if reading the stream fails
     */
    public NonMultipartGetObjectResponseIterator(Map<String, String> headers, InputStream in) {
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

    /**
     * No-op. The stream is file-backed and will be deleted when the returned InputStream is closed.
     */
    @Override
    public void close() throws IOException {
        // No action; file will be removed when the SingleObjectResponse's stream is closed
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
            return new SingleObjectResponse(this.headers, this.fileBackedStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
