package us.ampre.rets.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginRequestTest {

	@Test
	void testGetUrl() {
		LoginRequest req = new LoginRequest();
		req.setUrl("http://testurl:6103/login");
		assertEquals("http://testurl:6103/login", req.getUrl());
	}

	@Test
	void testSetBrokerCode() {
		LoginRequest req = new LoginRequest();
		req.setUrl("http://testurl:6103/login");
		req.setBrokerCode(null, "branch");
		assertEquals("http://testurl:6103/login", req.getUrl());
		req.setBrokerCode("broker", null);
		assertEquals("http://testurl:6103/login", req.getUrl());
		assertEquals("BrokerCode=broker", req.getHttpParameters());
		req.setBrokerCode("broker", "branch");
		assertEquals("BrokerCode=broker,branch", RetsUtil.urlDecode(req.getHttpParameters()));
	}
}