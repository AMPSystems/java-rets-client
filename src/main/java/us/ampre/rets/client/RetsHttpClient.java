package us.ampre.rets.client;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class RetsHttpClient implements AutoCloseable {

    public static final String SESSION_ID_COOKIE = "RETS-Session-ID";
    public static final String LOGIN_SESSION_ID = "0";

    protected final CloseableHttpClient httpClient;
    protected final Map<String, String> defaultHeaders;

    protected RetsHttpClient() {
        this.httpClient = HttpClients.createDefault();
        this.defaultHeaders = new HashMap<>();
    }

    public abstract void setUserCredentials(String userName, String password);

    public RetsHttpResponseImpl doRequest(String httpMethod, RetsHttpRequest request) throws RetsException {
        HttpUriRequestBase httpRequest = createHttpRequest(httpMethod, request);

        for (Map.Entry<String, String> entry : defaultHeaders.entrySet()) {
            if (entry.getValue() != null) {
                httpRequest.addHeader(entry.getKey(), entry.getValue());
            }
        }

        try (CloseableHttpResponse response = httpClient.execute(httpRequest)) {
            return new RetsHttpResponseImpl(response);
        } catch (IOException e) {
            throw new RetsException(e);
        }
    }

    private HttpUriRequestBase createHttpRequest(String httpMethod, RetsHttpRequest request) {
        return new HttpGet(request.getUrl());
    }

    public void addDefaultHeader(String name, String value) {
        if (value == null) {
            defaultHeaders.remove(name);
        } else {
            defaultHeaders.put(name, value);
        }
    }

    @Override
    public void close() {
        try {
            httpClient.close();
        } catch (IOException e) {
            System.err.println("Error closing HttpClient: " + e.getMessage());
        }
    }
}
