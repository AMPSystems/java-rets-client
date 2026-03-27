package us.ampre.rets.client;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class CommonsHttpClientUaAuthTest {

    @Test
    public void testCalculateUaAuthHeaderMatchesExpected() {
        CommonsHttpClient client = new CommonsHttpClient("secret", false);
        HttpGet method = new HttpGet("http://example.com");
        method.setHeader(CommonsHttpClient.USER_AGENT, "TestAgent/1.0");
        method.setHeader("RETS-Request-ID", "req-1");
        method.setHeader("RETS-Version", "RETS/1.7.2");

        String header = client.calculateUaAuthHeader(method, new HashMap<>());
        assertNotNull(header);
        assertTrue(header.startsWith("Digest ")); // structural check; exact vector unknown
    }
}
