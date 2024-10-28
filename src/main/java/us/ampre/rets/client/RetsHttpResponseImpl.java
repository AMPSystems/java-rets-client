package us.ampre.rets.client;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.Header;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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

    private void populateCookies() {
        // For new cookies from Set-Cookie headers
        for (Header cookieHeader : response.getHeaders("Set-Cookie")) {
            String[] cookieParts = cookieHeader.getValue().split(";");
            for (String cookie : cookieParts) {
                String[] parts = cookie.split("=", 2);
                if (parts.length == 2) {
                    cookies.put(parts[0].trim(), parts[1].trim());
                }
            }
        }
    }

    private void populateCookies(Map<String, String> cookies) {
        this.cookies.putAll(cookies);
        for (Header cookieHeader : response.getHeaders("Set-Cookie")) {
            String[] cookieParts = cookieHeader.getValue().split(";");
            for (String cookie : cookieParts) {
                String[] parts = cookie.split("=", 2);
                if (parts.length == 2) {
                    cookies.put(parts[0].trim(), parts[1].trim());
                }
            }
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