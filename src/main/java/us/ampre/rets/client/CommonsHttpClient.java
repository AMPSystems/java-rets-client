package us.ampre.rets.client;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.cookie.BasicCookieStore;
import org.apache.hc.client5.http.cookie.Cookie;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.util.Timeout;
import us.ampre.rets.common.util.CaseInsensitiveTreeMap;

@Slf4j
public class CommonsHttpClient extends RetsHttpClient {
    private static final int DEFAULT_TIMEOUT = 300000;
    private static final String RETS_VERSION = "RETS-Version";
    private static final String RETS_SESSION_ID = "RETS-Session-ID";
    private static final String RETS_REQUEST_ID = "RETS-Request-ID";
    public static final String USER_AGENT = "User-Agent";
    public static final String RETS_UA_AUTH_HEADER = "RETS-UA-Authorization";
    private static final String ACCEPT_ENCODING = "Accept-Encoding";
    public static final String CONTENT_ENCODING = "Content-Encoding";
    public static final String DEFLATE_ENCODINGS = "gzip,deflate";
    public static final String CONTENT_TYPE = "Content-Type";

    private final ConcurrentHashMap<String, String> defaultHeaders;
    private CloseableHttpClient httpClient;
    private final BasicCookieStore cookieStore;
    private final String userAgentPassword;

    public CommonsHttpClient() {
        this(null, true);
    }

    public CommonsHttpClient(String userAgentPassword, boolean gzip) {
        this.userAgentPassword = userAgentPassword;
        this.defaultHeaders = new ConcurrentHashMap<>();

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(Timeout.ofMilliseconds(DEFAULT_TIMEOUT))
                .setResponseTimeout(Timeout.ofMilliseconds(DEFAULT_TIMEOUT))
                .build();

        this.cookieStore = new BasicCookieStore();
        this.httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();

        if (gzip) {
            this.addDefaultHeader(ACCEPT_ENCODING, DEFLATE_ENCODINGS);
        }
    }

    @Override
    public void setUserCredentials(String userName, String password) {
        BasicCredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(null, -1), new UsernamePasswordCredentials(userName, password.toCharArray()));
        this.httpClient = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();
    }

    @Override
    public RetsHttpResponseImpl doRequest(String httpMethod, RetsHttpRequest request) throws RetsException {
        return "GET".equalsIgnoreCase(httpMethod) ? this.doGet(request) : this.doPost(request);
    }

    public RetsHttpResponseImpl doGet(RetsHttpRequest request) throws RetsException {
        String url = request.getUrl();
        String args = request.getHttpParameters();
        if (args != null) {
            url = url + "?" + args;
        }
        log.debug("URL = [{}]", url);
        HttpGet method = new HttpGet(url);
        return execute(method, request.getHeaders());
    }

    public RetsHttpResponseImpl doPost(RetsHttpRequest request) throws RetsException {
        String url = request.getUrl();
        String body = request.getHttpParameters();
        if (body == null) body = "";
        HttpPost method = new HttpPost(url);
        method.setEntity(new StringEntity(body, StandardCharsets.UTF_8));
        method.setHeader(CONTENT_TYPE, "application/x-www-form-urlencoded");
        return execute(method, request.getHeaders());
    }

    protected RetsHttpResponseImpl execute(final HttpUriRequestBase method, Map<String, String> headers) throws RetsException {
        try {
            for (Map.Entry<String, String> entry : this.defaultHeaders.entrySet()) {
                method.setHeader(entry.getKey(), entry.getValue());
            }

            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    method.setHeader(entry.getKey(), entry.getValue());
                }
            }

            if (this.userAgentPassword != null) {
                method.setHeader(RETS_UA_AUTH_HEADER, calculateUaAuthHeader(method, getCookies()));
            }

            try (CloseableHttpResponse response = this.httpClient.execute(method)) {
                int statusCode = response.getCode();
                String reasonPhrase = response.getReasonPhrase();
                if (statusCode != HttpStatus.SC_OK) {
                    throw new InvalidHttpStatusException(statusCode, reasonPhrase);
                }
                return new RetsHttpResponseImpl(response, getCookies());
            }
        } catch (IOException e) {
            throw new RetsException(e);
        }
    }

    @Override
    public synchronized void addDefaultHeader(String key, String value) {
        this.defaultHeaders.put(key, value);
        if (value == null) {
            this.defaultHeaders.remove(key);
        }
    }

    protected Map<String, String> getCookies() {
        Map<String, String> cookieMap = new CaseInsensitiveTreeMap<>();
        for (Cookie cookie : this.cookieStore.getCookies()) {
            cookieMap.put(cookie.getName(), cookie.getValue());
        }
        return cookieMap;
    }

    protected String calculateUaAuthHeader(HttpUriRequestBase method, Map<String, String> cookies) {
        final String userAgent = this.getHeaderValue(method, USER_AGENT);
        final String requestId = this.getHeaderValue(method, RETS_REQUEST_ID);
        final String sessionId = cookies.get(RETS_SESSION_ID);
        final String retsVersion = this.getHeaderValue(method, RETS_VERSION);
        String secretHash = DigestUtils.md5Hex(String.format("%s:%s", userAgent, this.userAgentPassword));
        String pieces = String.format("%s:%s:%s:%s", secretHash, StringUtils.trimToEmpty(requestId), StringUtils.trimToEmpty(sessionId), retsVersion);
        return String.format("Digest %s", DigestUtils.md5Hex(pieces));
    }

    protected String getHeaderValue(HttpUriRequestBase method, String key) {
        Header requestHeader = method.getFirstHeader(key);
        if (requestHeader == null) return null;
        return requestHeader.getValue();
    }
}
