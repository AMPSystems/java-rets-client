package us.ampre.rets.client;

import us.ampre.rets.client.models.SingleObjectResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Iterator for a non-multipart GetObject response.
 * <p>
 * This iterator is single-use and will return at most one {@link SingleObjectResponse}.
 * The entire input stream is eagerly read into memory during construction.
 * </p>
 */
public final class NonMultipartGetObjectResponseIterator implements GetObjectIterator {
    private boolean exhausted;
    private final Map<String, String> headers;
    private final byte[] data;

    /**
     * Constructs a new iterator for a non-multipart response.
     * The provided input stream is fully read into memory and closed.
     *
     * @param headers response headers
     * @param in      input stream containing the response body (will be fully read)
     * @throws NullPointerException if headers or in is null
     * @throws RuntimeException     if reading the stream fails
     */
    public NonMultipartGetObjectResponseIterator(Map<String, String> headers, InputStream in) {
        this.exhausted = false;
        this.headers = Objects.requireNonNull(headers, "headers");
        Objects.requireNonNull(in, "in");
        try {
            this.data = toByteArray(in);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read input stream", e);
        }
    }

    /**
     * No-op. The stream is already read and closed during construction.
     */
    @Override
    public void close() throws IOException {
        // Nothing to close, as the stream is already read
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
            return new SingleObjectResponse(this.headers, new ByteArrayInputStream(this.data));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] temp = new byte[8192];
        int bytesRead;
        while ((bytesRead = in.read(temp)) != -1) {
            buffer.write(temp, 0, bytesRead);
        }
        return buffer.toByteArray();
    }
}
