package us.ampre.rets.client.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import us.ampre.rets.client.exceptions.RetsException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GetObjectResponseTests {

    private Map<String, String> headers;

    @BeforeEach
    void setUp() {
        headers = new HashMap<>();
    }

    @Test
    void testNonMultipartSuccessResponse() throws Exception {
        headers.put("Content-Type", "image/jpeg");
        InputStream in = new ByteArrayInputStream(new byte[]{1, 2, 3});
        GetObjectResponse response = new GetObjectResponse(headers, in);

        assertTrue(response.isSuccessful());
        assertEquals("image/jpeg", response.getType());
        assertThrows(IllegalArgumentException.class, response::getBoundary);
        assertNotNull(response.iterator());
    }

    @Test
    void testXmlErrorResponseSetsReplyCodeAndText() throws Exception {
        headers.put("Content-Type", "text/xml");
        String xml = "<RETS ReplyCode=\"20403\" ReplyText=\"No Object Found\"/>";
        InputStream in = new ByteArrayInputStream(xml.getBytes());
        GetObjectResponse response = new GetObjectResponse(headers, in);

        assertFalse(response.isSuccessful());
        assertTrue(response.getReplyText().contains("20403"));
        assertTrue(response.getReplyText().contains("No Object Found"));
        assertNotNull(response.iterator());
    }

    @Test
    void testMultipartBoundaryExtraction() throws Exception {
        headers.put("Content-Type", "multipart/parallel; boundary=\"abc123\"");
        InputStream in = new ByteArrayInputStream(new byte[0]);
        GetObjectResponse response = new GetObjectResponse(headers, in);

        assertTrue(response.getType().contains("multipart"));
        assertEquals("abc123", response.getBoundary());
    }

    @Test
    void testIteratorExhaustionThrows() throws Exception {
        headers.put("Content-Type", "image/jpeg");
        InputStream in = new ByteArrayInputStream(new byte[]{1, 2, 3});
        GetObjectResponse response = new GetObjectResponse(headers, in);

        response.iterator();
        assertThrows(RetsException.class, response::iterator);
    }

    @Test
    void testMalformedXmlThrowsRetsException() {
        headers.put("Content-Type", "text/xml");
        String badXml = "<RETS ReplyCode=\"20403\""; // malformed
        InputStream in = new ByteArrayInputStream(badXml.getBytes());

        assertThrows(RetsException.class, () -> new GetObjectResponse(headers, in));
    }

    @Test
    void testMalformedContentTypeThrows() throws RetsException {
        headers.put("Content-Type", "multipart/parallel; boundary");
        InputStream in = new ByteArrayInputStream(new byte[0]);
        GetObjectResponse response = new GetObjectResponse(headers, in);

        assertThrows(IllegalArgumentException.class, response::getBoundary);
    }

    @Test
    void testEmptyResponseIterator() throws Exception {
        headers.put("Content-Type", "text/xml");
        String xml = "<RETS ReplyCode=\"0\" ReplyText=\"Success\"/>";
        InputStream in = new ByteArrayInputStream(xml.getBytes());
        GetObjectResponse response = new GetObjectResponse(headers, in);

        assertTrue(response.isSuccessful());
        assertNotNull(response.iterator());
    }

    @Test
    void testInputStreamIsCopiedAndIndependent() throws Exception {
        headers.put("Content-Type", "image/jpeg");
        byte[] data = {10, 20, 30};
        ByteArrayInputStream inputForResponse = new ByteArrayInputStream(data);
        ByteArrayInputStream inputForVerification = new ByteArrayInputStream(data);

        GetObjectResponse response = new GetObjectResponse(headers, inputForResponse);

        // Exhaust the original stream after construction
        while (inputForResponse.read() != -1) { /* read to EOF */ }
        assertEquals(-1, inputForResponse.read());

        // The response's stream should still have all the original data
        var iterator = response.iterator();
        assertTrue(iterator.hasNext());
        SingleObjectResponse obj = iterator.next();
        byte[] fromResponse = obj.getInputStream().readAllBytes();

        // Compare to the original data
        byte[] originalData = inputForVerification.readAllBytes();
        assertArrayEquals(originalData, fromResponse);
    }

    @Test
    void testMultipleResponsesHaveIndependentStreams() throws Exception {
        headers.put("Content-Type", "image/jpeg");
        byte[] data = {1, 2, 3, 4};

        GetObjectResponse response1 = new GetObjectResponse(headers, new ByteArrayInputStream(data));
        GetObjectResponse response2 = new GetObjectResponse(headers, new ByteArrayInputStream(data));

        var it1 = response1.iterator();
        var it2 = response2.iterator();

        // Read one byte from response1's stream
        assertTrue(it1.hasNext());
        SingleObjectResponse obj1 = it1.next();
        obj1.getInputStream().read();

        // response2's stream should still have all data
        assertTrue(it2.hasNext());
        SingleObjectResponse obj2 = it2.next();
        byte[] fromRes2 = obj2.getInputStream().readAllBytes();
        assertArrayEquals(data, fromRes2);
    }
}