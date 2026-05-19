package us.ampre.rets.client;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RetsHttpResponseImplCookieParsingTest {

    @Test
    public void testParseSetCookieHeaderPreservesEqualsAndQuoted() {
        String header1 = "MYCOOKIE=val=with=equals; Path=/; HttpOnly";
        Map<String, String> parsed1 = RetsHttpResponseImpl.parseSetCookieHeader(header1);
        assertEquals("val=with=equals", parsed1.get("MYCOOKIE"));

        String header2 = "QUOTED=\"val;with;semicolons\"; Path=/; Secure";
        Map<String, String> parsed2 = RetsHttpResponseImpl.parseSetCookieHeader(header2);
        assertEquals("val;with;semicolons", parsed2.get("QUOTED"));
    }
}
