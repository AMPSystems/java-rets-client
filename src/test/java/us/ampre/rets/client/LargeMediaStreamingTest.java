package us.ampre.rets.client;

import org.junit.jupiter.api.Test;
import us.ampre.rets.client.models.SingleObjectResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LargeMediaStreamingTest {

    @Test
    void testNonMultipartLargeStreamToTempFile() throws IOException {
        Map<String, String> headers = Collections.singletonMap("Content-Type", "application/octet-stream");
        InputStream largeStream = new InputStream() {
            private long remaining = 52L * 1024 * 1024; // 52 MB

            @Override
            public int read() throws IOException {
                if (remaining-- <= 0) return -1;
                return 0;
            }
        };

        var iterator = new NonMultipartGetObjectResponseIterator(headers, largeStream);
        assertTrue(iterator.hasNext());
        SingleObjectResponse response = iterator.next();
        assertNotNull(response);

        // Read a small portion to verify streaming works without forcing the iterator to buffer the whole file in memory
        int first = response.getInputStream().read();
        assertEquals(0, first);
        response.getInputStream().close();

        assertFalse(iterator.hasNext());
        iterator.close();
    }
}
