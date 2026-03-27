package us.ampre.rets.client;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.Header;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.net.HttpCookie;

import java.io.ByteArrayInputStream;

import org.apache.hc.core5.http.io.entity.EntityUtils;
import us.ampre.rets.common.util.CaseInsensitiveTreeMap;

public class RetsHttpResponseImpl implements RetsHttpResponse, AutoCloseable {
    private final CloseableHttpResponse response;
    @Getter
    private Map<String, String> headers = new HashMap<>();
    @Getter
    private final Map<String, String> cookies = new HashMap<>();
    private final byte[] raw;

    public RetsHttpResponseImpl(CloseableHttpResponse response, Map<String, String> cookies) throws IOException {
        this.response = response;
        this.raw = readResponseBody(response);
        populateHeaders();
        populateCookies(cookies);
    }

    public RetsHttpResponseImpl(CloseableHttpResponse response) throws IOException {
        this.response = response;
        this.raw = readResponseBody(response);
        populateHeaders();
        populateCookies();
    }

    private byte[] readResponseBody(CloseableHttpResponse response) throws IOException {
        if (response.getEntity() != null) {
            return EntityUtils.toByteArray(response.getEntity());
        }
        return new byte[0];
    }

    private void populateHeaders() {
        this.headers = new CaseInsensitiveTreeMap<>();
        for (Header header : this.response.getHeaders()) {
            this.headers.put(header.getName(), header.getValue());
        }
    }

    // Robust Set-Cookie parsing using java.net.HttpCookie with a safe fallback
    public static Map<String, String> parseSetCookieHeader(String headerValue) {
        Map<String, String> result = new CaseInsensitiveTreeMap<>();
        if (headerValue == null) return result;
        // If header contains quoted values, use manual parser to preserve embedded semicolons
        if (headerValue.indexOf('"') >= 0) {
            // fall through to manual parse below
        } else {
            // Try the standard parser when no quotes are present
            try {
                List<HttpCookie> httpCookies = HttpCookie.parse(headerValue);
                for (HttpCookie cookie : httpCookies) {
                    result.put(cookie.getName(), cookie.getValue());
                }
                return result;
            } catch (IllegalArgumentException ignored) {
                // Fall through to manual parsing if the header is malformed
            }
        }

        // Manual parse that supports quoted values containing semicolons
        int len = headerValue.length();
        int i = 0;
        while (i < len) {
            // Skip whitespace and separators
            while (i < len && (headerValue.charAt(i) == ' ' || headerValue.charAt(i) == ';')) i++;
            if (i >= len) break;
            int nameStart = i;
            while (i < len && headerValue.charAt(i) != '=' && headerValue.charAt(i) != ';') i++;
            if (i >= len) break;
            String name = headerValue.substring(nameStart, i).trim();
            String value = "";
            if (i < len && headerValue.charAt(i) == '=') {
                i++; // skip '='
                if (i < len && headerValue.charAt(i) == '"') {
                    // Quoted value: read until closing quote
                    i++; // skip opening quote
                    int valStart = i;
                    while (i < len) {
                        if (headerValue.charAt(i) == '"') {
                            value = headerValue.substring(valStart, i);
                            i++; // skip closing quote
                            break;
                        }
                        i++;
                    }
                    if (i >= len && value.isEmpty()) {
                        // Unterminated quote: take rest
                        value = headerValue.substring(valStart);
                    }
                    // Skip remaining attributes until next semicolon
                    while (i < len && headerValue.charAt(i) != ';') i++;
                } else {
                    int valStart = i;
                    while (i < len && headerValue.charAt(i) != ';') i++;
                    value = headerValue.substring(valStart, i).trim();
                }
            } else {
                // Attribute without value: skip until semicolon
                while (i < len && headerValue.charAt(i) != ';') i++;
                continue;
            }
            if (!name.isEmpty()) result.put(name, value);
        }
        return result;
    }

    private void populateCookies() {
        // For new cookies from Set-Cookie headers
        for (Header cookieHeader : response.getHeaders("Set-Cookie")) {
            try {
                Map<String, String> parsed = parseSetCookieHeader(cookieHeader.getValue());
                this.cookies.putAll(parsed);
            } catch (Exception e) {
                // fallback to naive parsing
                String[] cookieParts = cookieHeader.getValue().split(";");
                for (String cookie : cookieParts) {
                    String[] parts = cookie.split("=", 2);
                    if (parts.length == 2) {
                        cookies.put(parts[0].trim(), parts[1].trim());
                    }
                }
            }
        }
    }

    private void populateCookies(Map<String, String> cookies) {
        this.cookies.putAll(cookies);
        for (Header cookieHeader : response.getHeaders("Set-Cookie")) {
            Map<String, String> parsed = parseSetCookieHeader(cookieHeader.getValue());
            cookies.putAll(parsed);
        }
    }

    @Override
    public int getResponseCode() {
        return response.getCode();
    }

    public String getHeader(String header) {
        return this.headers.get(header);
    }


    public String getCookie(String cookie) {
        return this.cookies.get(cookie);
    }

    public String getCharset() {
        String contentType = StringUtils.trimToEmpty(this.getHeader(CommonsHttpClient.CONTENT_TYPE)).toLowerCase();
        String[] split = StringUtils.split(contentType, ";");
        if (split == null) return null;

        for (String s : split) {
            String sLower = s.toLowerCase().trim();
            boolean b = sLower.startsWith("charset=");
            if (b) {
                return StringUtils.substringAfter(s, "charset=");
            }
        }
        return null;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(raw);
    }

    @Override
    public void close() throws IOException {
        response.close();
    }
}