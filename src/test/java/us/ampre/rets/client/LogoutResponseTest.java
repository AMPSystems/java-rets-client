package us.ampre.rets.client;

import org.junit.jupiter.api.Test;
import us.ampre.rets.client.exceptions.RetsException;

import static org.junit.jupiter.api.Assertions.*;
import static us.ampre.rets.client.RetsTestCase.getResource;

class LogoutResponseTest {

	// TODO: FIX THESE
    /*
    @Test
    void testValidLogoutResponse10() throws RetsException {
        LogoutResponse response = new LogoutResponse();
        response.parse(getResource("logout_valid10.xml"), RetsVersion.RETS_10);
        assertEquals("1000", response.getSeconds());
        assertEquals("$20.00", response.getBillingInfo());
        assertEquals("Good Bye", response.getLogoutMessage());
    }

    @Test
    void testValidLogoutResponse() throws RetsException {
        LogoutResponse response = new LogoutResponse();
        response.parse(getResource("logout_valid15.xml"), RetsVersion.RETS_15);
        assertEquals("1000", response.getSeconds());
        assertEquals("$20.00", response.getBillingInfo());
        assertEquals("Good Bye", response.getLogoutMessage());
    }

    @Test
    void testLowerCaseKeys() throws RetsException {
        LogoutResponse response = new LogoutResponse();
        response.parse(getResource("logout_lower_case.xml"), RetsVersion.RETS_15);
        assertEquals("1000", response.getSeconds());
        assertEquals("$20.00", response.getBillingInfo());
        assertEquals("Good Bye", response.getLogoutMessage());
    }
    */

	@Test
	void testStrictLowerCaseKeys() {
		LogoutResponse response = new LogoutResponse();
		response.setStrict(true);
		Exception exception = assertThrows(RetsException.class, () -> {
			response.parse(getResource("logout_lower_case.xml"), RetsVersion.RETS_15);
		});
		// Optionally, assert on exception message if needed
	}

	// TODO: FIX THIS.
    /*
    @Test
    void testLogoutNoEquals() throws RetsException {
        LogoutResponse response = new LogoutResponse();
        response.parse(getResource("logout_no_equals.xml"), RetsVersion.RETS_15);
        assertNull(response.getSeconds());
        assertNull(response.getBillingInfo());
        assertNull(response.getLogoutMessage());
    }
    */
}