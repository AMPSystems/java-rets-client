package us.ampre.rets.client;

import org.junit.jupiter.api.Test;
import us.ampre.rets.client.models.SingleObjectResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class NonMultipartGetObjectResponseIteratorTests {

    @Test
    void testNormalIteration() throws IOException {
        var headers = Collections.singletonMap("Content-Type", "image/jpeg");
        var data = new byte[]{1, 2, 3};
        var in = new ByteArrayInputStream(data);

        var iterator = new NonMultipartGetObjectResponseIterator(headers, in);

        assertTrue(iterator.hasNext());
        SingleObjectResponse response = iterator.next();
        assertNotNull(response);

        // Compare contents, not instance
        byte[] expected = data;
        byte[] actual = response.getInputStream().readAllBytes();
        assertArrayEquals(expected, actual);

        assertFalse(iterator.hasNext());
        iterator.close();
    }

    @Test
    void testNextAfterExhaustionThrows() {
        var iterator = new NonMultipartGetObjectResponseIterator(Collections.emptyMap(), new ByteArrayInputStream(new byte[0]));
        iterator.next(); // first call is fine
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testRemoveThrows() {
        var iterator = new NonMultipartGetObjectResponseIterator(Collections.emptyMap(), new ByteArrayInputStream(new byte[0]));
        assertThrows(UnsupportedOperationException.class, iterator::remove);
    }

    @Test
    void testEmptyStream() throws IOException {
        var headers = Collections.singletonMap("Content-Type", "image/jpeg");
        var in = new ByteArrayInputStream(new byte[0]);

        var iterator = new NonMultipartGetObjectResponseIterator(headers, in);

        assertTrue(iterator.hasNext());
        SingleObjectResponse response = iterator.next();
        assertNotNull(response);

        // Verify empty stream
        assertEquals(0, response.getInputStream().readAllBytes().length);

        assertFalse(iterator.hasNext());
        iterator.close();
    }

    @Test
    void testNullHeadersThrows() {
        var in = new ByteArrayInputStream(new byte[0]);
        assertThrows(NullPointerException.class, () -> new NonMultipartGetObjectResponseIterator(null, in));
    }
}