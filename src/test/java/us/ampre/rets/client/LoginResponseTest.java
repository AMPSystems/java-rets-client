package us.ampre.rets.client;

import org.junit.jupiter.api.Test;
import us.ampre.rets.client.exceptions.RetsException;

import static org.junit.jupiter.api.Assertions.*;

class LoginResponseTest extends RetsTestCase {

    /*
    @Test
    void testValidLoginResponse15() throws RetsException {
        LoginResponse response = new LoginResponse();
        response.parse(getResource("login_valid15.xml"), RetsVersion.RETS_15);
        assertEquals("B123, BO987", response.getBroker(), "Checking broker");
        assertEquals("Joe T. Schmoe", response.getMemberName(), "Checking member name");
        assertEquals("1.00.000", response.getMetadataVersion(), "Checking metadata version");
        assertEquals("1.00.000", response.getMinMetadataVersion(), "Checking min metadata version");
        assertEquals("A123,5678,1,A123", response.getUserInformation(), "Checking user information");
        assertNull(response.getOfficeList(), "Checking office list");
        assertEquals("44.21", response.getBalance(), "Checking balance");
        assertEquals(60, response.getSessionTimeout(), "Checking timeout");
        assertNull(response.getPasswordExpiration(), "Checking password expiration");

        CapabilityUrls urls = response.getCapabilityUrls();
        assertEquals("http://rets.test:6103/get", urls.getActionUrl());
        assertEquals("http://rets.test:6103/changePassword", urls.getChangePasswordUrl());
        assertEquals("http://rets.test:6103/getObjectEx", urls.getGetObjectUrl());
        assertEquals("http://rets.test:6103/login", urls.getLoginUrl());
        assertNull(urls.getLoginCompleteUrl());
        assertEquals("http://rets.test:6103/logout", urls.getLogoutUrl());
        assertEquals("http://rets.test:6103/search", urls.getSearchUrl());
        assertEquals("http://rets.test:6103/getMetadata", urls.getGetMetadataUrl());
        assertNull(urls.getUpdateUrl());
    }

    @Test
    void testValidLoginResponse10() throws RetsException {
        LoginResponse response = new LoginResponse();
        response.parse(getResource("login_valid10.xml"), RetsVersion.RETS_10);
        assertEquals("B123, BO987", response.getBroker(), "Checking broker");
        assertEquals("Joe T. Schmoe", response.getMemberName(), "Checking member name");
        assertEquals("1.00.000", response.getMetadataVersion(), "Checking metadata version");
        assertEquals("1.00.000", response.getMinMetadataVersion(), "Checking min metadata version");
        assertEquals("A123,5678,1,A123", response.getUserInformation(), "Checking user information");
        assertNull(response.getOfficeList(), "Checking office list");
        assertEquals("44.21", response.getBalance(), "Checking balance");
        assertEquals(60, response.getSessionTimeout(), "Checking timeout");
        assertNull(response.getPasswordExpiration(), "Checking password expiration");

        CapabilityUrls urls = response.getCapabilityUrls();
        assertEquals("http://rets.test:6103/get", urls.getActionUrl());
        assertEquals("http://rets.test:6103/changePassword", urls.getChangePasswordUrl());
        assertEquals("http://rets.test:6103/getObjectEx", urls.getGetObjectUrl());
        assertEquals("http://rets.test:6103/login", urls.getLoginUrl());
        assertNull(urls.getLoginCompleteUrl());
        assertEquals("http://rets.test:6103/logout", urls.getLogoutUrl());
        assertEquals("http://rets.test:6103/search", urls.getSearchUrl());
        assertEquals("http://rets.test:6103/getMetadata", urls.getGetMetadataUrl());
        assertNull(urls.getUpdateUrl());
    }

    @Test
    void testLowerCaseKeys() throws RetsException {
        LoginResponse response = new LoginResponse();
        response.parse(getResource("login_lower_case.xml"), RetsVersion.RETS_15);
        assertEquals("B123, BO987", response.getBroker(), "Checking broker");
        assertEquals("Joe T. Schmoe", response.getMemberName(), "Checking member name");
        assertEquals("1.00.000", response.getMetadataVersion(), "Checking metadata version");
        assertEquals("1.00.000", response.getMinMetadataVersion(), "Checking min metadata version");
        assertEquals("A123,5678,1,A123", response.getUserInformation(), "Checking user information");
        assertNull(response.getOfficeList(), "Checking office list");
        assertEquals("44.21", response.getBalance(), "Checking balance");
        assertEquals(60, response.getSessionTimeout(), "Checking timeout");
        assertNull(response.getPasswordExpiration(), "Checking password expiration");

        CapabilityUrls urls = response.getCapabilityUrls();
        assertEquals("http://rets.test:6103/get", urls.getActionUrl());
        assertEquals("http://rets.test:6103/changePassword", urls.getChangePasswordUrl());
        assertEquals("http://rets.test:6103/getObjectEx", urls.getGetObjectUrl());
        assertEquals("http://rets.test:6103/login", urls.getLoginUrl());
        assertNull(urls.getLoginCompleteUrl());
        assertEquals("http://rets.test:6103/logout", urls.getLogoutUrl());
        assertEquals("http://rets.test:6103/search", urls.getSearchUrl());
        assertEquals("http://rets.test:6103/getMetadata", urls.getGetMetadataUrl());
        assertNull(urls.getUpdateUrl());
    }
    */

	@Test
	void testStrictLowerCaseKeys() {
		LoginResponse response = new LoginResponse();
		response.setStrict(true);
		Exception exception = assertThrows(RetsException.class, () -> {
			response.parse(getResource("login_lower_case.xml"), RetsVersion.RETS_15);
		});
		// Optionally, assert on exception message if needed
	}
}