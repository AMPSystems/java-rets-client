package us.ampre.rets.client.models;

import org.junit.jupiter.api.Test;
import us.ampre.rets.client.GetObjectIterator;
import us.ampre.rets.client.models.GetObjectResponse;
import us.ampre.rets.client.models.GetObjectResponseIterator;
import us.ampre.rets.client.models.SingleObjectResponse;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class GetObjectResponseIteratorTests {

    private static final String BOUNDARY = "testboundary";
    private static final String PART1 = "data1";
    private static final String PART2 = "data2";
    private static final byte[] MULTIPART_BODY = (
            "--" + BOUNDARY + "\r\nContent-Type: text/plain\r\nContent-ID: one\r\nObject-ID: 1\r\n\r\n" + PART1 +
                    "\r\n--" + BOUNDARY + "\r\nContent-Type: text/plain\r\nContent-ID: two\r\nObject-ID: 2\r\n\r\n" + PART2 +
                    "\r\n--" + BOUNDARY + "--"
    ).getBytes();

    @Test
    void testIterateMultipart() throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "multipart/parallel; boundary=" + BOUNDARY);
        GetObjectResponse response = new GetObjectResponse(headers, new ByteArrayInputStream(MULTIPART_BODY));
        GetObjectIterator<SingleObjectResponse> it = GetObjectResponseIterator.createIterator(response, 1024);

        assertTrue(it.hasNext());
        SingleObjectResponse part1 = it.next();
        assertEquals("one", part1.getContentID());
        assertEquals(PART1, new String(part1.getInputStream().readAllBytes()));

        assertTrue(it.hasNext());
        SingleObjectResponse part2 = it.next();
        assertEquals("two", part2.getContentID());
        assertEquals(PART2, new String(part2.getInputStream().readAllBytes()));

        assertFalse(it.hasNext());
        it.close();
    }

    @Test
    void testNextThrowsWhenEmpty() throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "multipart/parallel; boundary=" + BOUNDARY);
        GetObjectResponse response = new GetObjectResponse(headers, new ByteArrayInputStream(new byte[0]));
        GetObjectIterator<SingleObjectResponse> it = GetObjectResponseIterator.createIterator(response, 1024);

        assertFalse(it.hasNext());
        assertThrows(NoSuchElementException.class, it::next);
        it.close();
    }

    @Test
    void testRemoveThrows() throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "multipart/parallel; boundary=" + BOUNDARY);
        GetObjectResponse response = new GetObjectResponse(headers, new ByteArrayInputStream(MULTIPART_BODY));
        GetObjectIterator<SingleObjectResponse> it = GetObjectResponseIterator.createIterator(response, 1024);

        assertThrows(UnsupportedOperationException.class, it::remove);
        it.close();
    }
}