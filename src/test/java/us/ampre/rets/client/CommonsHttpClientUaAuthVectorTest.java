package us.ampre.rets.client;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CommonsHttpClientUaAuthVectorTest {

    @Test
    public void testUaAuthVectorMatchesSpecComputation() {
        String userAgent = "TestAgent/1.2";
        String password = "p@ssw0rd";
        String requestId = "req-123";
        String sessionId = "sess-456";
        String retsVersion = "RETS/1.7.2";

        CommonsHttpClient client = new CommonsHttpClient(password, false);
        HttpGet method = new HttpGet("http://example.com");
        method.setHeader(CommonsHttpClient.USER_AGENT, userAgent);
        method.setHeader("RETS-Request-ID", requestId);
        method.setHeader("RETS-Version", retsVersion);

        Map<String, String> cookies = new HashMap<>();
        cookies.put("RETS-Session-ID", sessionId);

        // Compute expected per spec:
        String a1 = DigestUtils.md5Hex(String.format("%s:%s", userAgent, password));
        String pieces = String.format("%s:%s:%s:%s", a1, requestId, sessionId, retsVersion);
        String expected = "Digest " + DigestUtils.md5Hex(pieces);

        String actual = client.calculateUaAuthHeader(method, cookies);

        assertEquals(expected, actual);
    }
}
