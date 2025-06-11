package us.ampre.rets.client;

import us.ampre.rets.client.models.SingleObjectResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Used to implement GetObjectIterator for a non-multipart response.
 */
public final class NonMultipartGetObjectResponseIterator implements GetObjectIterator {
    private boolean exhausted;
    private final Map<String, String> headers;
    private final InputStream inputStream;

    public NonMultipartGetObjectResponseIterator(Map<String, String> headers, InputStream in) {
        this.exhausted = false;
        this.headers = headers;
        this.inputStream = in;
    }

    @Override
    public void close() throws IOException {
        this.inputStream.close();
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
        return new SingleObjectResponse(this.headers, this.inputStream);
    }
}
