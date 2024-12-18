package us.ampre.rets.client;

import java.util.Map;
import java.io.InputStream;

/**
 * Interface for retrieving useful header fields from a RETS HTTP response
 */

public interface RetsHttpResponse {
    int getResponseCode() throws RetsException;

    Map<String, String> getHeaders() throws RetsException;

    String getHeader(String hdr) throws RetsException;

    String getCookie(String cookie) throws RetsException;

    String getCharset() throws RetsException;

    InputStream getInputStream() throws RetsException;

    Map<String, String> getCookies() throws RetsException;

}