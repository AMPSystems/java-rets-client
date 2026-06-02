package us.ampre.rets.client;

import org.junit.jupiter.api.Test;
import us.ampre.rets.client.exceptions.InvalidArgumentException;
import us.ampre.rets.client.exceptions.RetsException;

import static org.junit.jupiter.api.Assertions.*;

class GetMetadataRequestTest {

	@Test
	void testGetMetadataRequestSimple() throws RetsException {
		GetMetadataRequest request = new GetMetadataRequest("SYSTEM", "*");
		request.setUrl("http://rets.test:6103/getMetadata");
		assertFalse(request.isCompactFormat());
		assertTrue(request.isStandardXmlFormat());
		assertNull(request.getStandardXmlVersion());
		assertEquals("http://rets.test:6103/getMetadata", request.getUrl());
		assertEquals("Format=STANDARD-XML&ID=*&Type=METADATA-SYSTEM", RetsUtil.urlDecode(request.getHttpParameters()));
	}

	@Test
	void testGetMetadataRequestMultipleIds() throws RetsException {
		GetMetadataRequest request = new GetMetadataRequest("UPDATE_TYPE", new String[] { "ActiveAgent", "ACTAGT", "Change_ACTAGT" });
		request.setCompactFormat();

		assertTrue(request.isCompactFormat());
		assertFalse(request.isStandardXmlFormat());
		assertEquals("Format=COMPACT&ID=ActiveAgent:ACTAGT:Change_ACTAGT&Type=METADATA-UPDATE_TYPE", RetsUtil.urlDecode(request.getHttpParameters()));
	}

	@Test
	void testInvalidGetMetadataRequests() {
		assertThrows(InvalidArgumentException.class, () -> {
			new GetMetadataRequest("SYSTEM", "Blah");
		});

		assertThrows(InvalidArgumentException.class, () -> {
			new GetMetadataRequest("RESOURCE", "Blah");
		});

		assertThrows(InvalidArgumentException.class, () -> {
			new GetMetadataRequest("RESOURCE", new String[0]);
		});
	}
}