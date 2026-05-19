package us.ampre.rets.client;

import org.apache.hc.core5.http.message.BasicHeaderValueParser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GetObjectBoundaryTest {

    @Test
    public void testUnescapeBoundaryQuotes() {
        String quoted = "\"myBoundary\"";
        String unescaped = us.ampre.rets.client.models.GetObjectResponse.unescapeBoundary(quoted);
        assertEquals("myBoundary", unescaped);
    }

}
