package us.ampre.rets.client.models;

import org.junit.jupiter.api.Test;
import us.ampre.rets.client.models.SingleObjectResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SingleObjectResponseTest {

	@Test
	void testCaseInsensitiveHeaders() throws IOException {
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-type", "1");
		headers.put("location", "2");
		headers.put("Object-Id", "3");
		headers.put("content-id", "4");
		headers.put("CONTENT-DESCRIPTION", "5");

		SingleObjectResponse res = new SingleObjectResponse(headers, null);
		assertEquals("1", res.getType());
		assertEquals("2", res.getLocation());
		assertEquals("3", res.getObjectID());
		assertEquals("4", res.getContentID());
		assertEquals("5", res.getDescription());
	}

	@Test
	void testInputStreamIsCopiedAndIndependent() throws IOException {
		byte[] data = {10, 20, 30};
		var original = new java.io.ByteArrayInputStream(data);

		SingleObjectResponse res = new SingleObjectResponse(new HashMap<>(), original);

		// Read from original, which should not affect res's stream
		original.read(); // advances original's position
		byte[] fromRes = res.getInputStream().readAllBytes();
		// Should still get all data from res's stream
		assertArrayEquals(data, fromRes);
	}

	@Test
	void testMultipleResponsesHaveIndependentStreams() throws IOException {
		byte[] data = {1, 2, 3, 4};
		var original = new java.io.ByteArrayInputStream(data);

		SingleObjectResponse res1 = new SingleObjectResponse(new HashMap<>(), original);
		SingleObjectResponse res2 = new SingleObjectResponse(new HashMap<>(), new java.io.ByteArrayInputStream(data));

		// Read from res1's stream
		res1.getInputStream().read();
		// res2's stream should still have all data
		byte[] fromRes2 = res2.getInputStream().readAllBytes();
		assertArrayEquals(data, fromRes2);
	}
}