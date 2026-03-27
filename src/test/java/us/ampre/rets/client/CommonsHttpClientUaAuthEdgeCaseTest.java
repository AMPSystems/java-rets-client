package us.ampre.rets.client;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CommonsHttpClientUaAuthEdgeCaseTest {

    @Test
    public void testEmptyRetsRequestIdIsTreatedAsEmpty() {
        String userAgent = "EdgeAgent/1.0";
        String password = "secret";
        String sessionId = "sess-999";
        String retsVersion = "RETS/1.7.2";

        CommonsHttpClient client = new CommonsHttpClient(password, false);
        HttpGet method = new HttpGet("http://example.com");
        method.setHeader(CommonsHttpClient.USER_AGENT, userAgent);
        method.setHeader("RETS-Version", retsVersion);
        // RETS-Request-ID intentionally not set

        Map<String, String> cookies = new HashMap<>();
        cookies.put("RETS-Session-ID", sessionId);

        String secretHash = DigestUtils.md5Hex(String.format("%s:%s", userAgent, password));
        String pieces = String.format("%s:%s:%s:%s", secretHash, "", sessionId, retsVersion);
        String expected = "Digest " + DigestUtils.md5Hex(pieces);

        String actual = client.calculateUaAuthHeader(method, cookies);
        assertEquals(expected, actual);
    }

    @Test
    public void testEmptySessionIdIsTreatedAsEmpty() {
        String userAgent = "EdgeAgent/1.0";
        String password = "secret";
        String requestId = "req-edge";
        String retsVersion = "RETS/1.7.2";

        CommonsHttpClient client = new CommonsHttpClient(password, false);
        HttpGet method = new HttpGet("http://example.com");
        method.setHeader(CommonsHttpClient.USER_AGENT, userAgent);
        method.setHeader("RETS-Request-ID", requestId);
        method.setHeader("RETS-Version", retsVersion);

        Map<String, String> cookies = new HashMap<>();
        // session id intentionally absent

        String secretHash = DigestUtils.md5Hex(String.format("%s:%s", userAgent, password));
        String pieces = String.format("%s:%s:%s:%s", secretHash, requestId, "", retsVersion);
        String expected = "Digest " + DigestUtils.md5Hex(pieces);

        String actual = client.calculateUaAuthHeader(method, cookies);
        assertEquals(expected, actual);
    }

    @Test
    public void testMissingRetsVersionIsTreatedAsEmpty() {
        String userAgent = "EdgeAgent/1.0";
        String password = "secret";
        String requestId = "req-edge2";
        String sessionId = "sess-777";

        CommonsHttpClient client = new CommonsHttpClient(password, false);
        HttpGet method = new HttpGet("http://example.com");
        method.setHeader(CommonsHttpClient.USER_AGENT, userAgent);
        method.setHeader("RETS-Request-ID", requestId);
        // RETS-Version intentionally not set

        Map<String, String> cookies = new HashMap<>();
        cookies.put("RETS-Session-ID", sessionId);

        String secretHash = DigestUtils.md5Hex(String.format("%s:%s", userAgent, password));
        String pieces = String.format("%s:%s:%s:%s", secretHash, requestId, sessionId, "");
        String expected = "Digest " + DigestUtils.md5Hex(pieces);

        String actual = client.calculateUaAuthHeader(method, cookies);
        assertEquals(expected, actual);
    }
}
