package us.ampre.rets.client;

import org.junit.jupiter.api.Test;
import us.ampre.rets.client.GetObjectIterator;
import us.ampre.rets.client.models.GetObjectResponse;
import us.ampre.rets.client.models.SingleObjectResponse;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MultipartBoundaryToleranceTest {

    @Test
    void testDetectBoundaryWhenMissingFromHeader() throws Exception {
        final String BOUNDARY = "detectme";
        final String PART = "hello";
        final byte[] MULTIPART_BODY = ("--" + BOUNDARY + "\r\nContent-Type: text/plain\r\nContent-ID: one\r\nObject-ID: 1\r\n\r\n" + PART + "\r\n--" + BOUNDARY + "--").getBytes();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "multipart/parallel"); // missing boundary param
        GetObjectResponse response = new GetObjectResponse(headers, new ByteArrayInputStream(MULTIPART_BODY));
        GetObjectIterator<SingleObjectResponse> it = GetObjectResponseIterator.createIterator(response, 1024);

        assertTrue(it.hasNext());
        SingleObjectResponse part = it.next();
        assertEquals("one", part.getContentID());
        assertEquals(PART, new String(part.getInputStream().readAllBytes()));
        it.close();
    }

    @Test
    void testTolerantContentTypeParsing() throws Exception {
        final String BOUNDARY = "xboundary";
        final String PART = "data";
        final byte[] MULTIPART_BODY = ("--" + BOUNDARY + "\r\nContent-Type: text/plain\r\nContent-ID: one\r\nObject-ID: 1\r\n\r\n" + PART + "\r\n--" + BOUNDARY + "--").getBytes();

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "Multipart/Parallel; foo=bar; boundary=\"" + BOUNDARY + "\"; something=else");
        GetObjectResponse response = new GetObjectResponse(headers, new ByteArrayInputStream(MULTIPART_BODY));
        GetObjectIterator<SingleObjectResponse> it = GetObjectResponseIterator.createIterator(response, 1024);

        assertTrue(it.hasNext());
        SingleObjectResponse part = it.next();
        assertEquals("one", part.getContentID());
        assertEquals(PART, new String(part.getInputStream().readAllBytes()));
        it.close();
    }
}
