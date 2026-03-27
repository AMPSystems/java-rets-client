package us.ampre.rets.client.models;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.hc.core5.http.HeaderElement;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.message.BasicHeaderValueParser;
import org.apache.hc.core5.http.message.ParserCursor;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import us.ampre.rets.client.GetObjectIterator;
import us.ampre.rets.client.NonMultipartGetObjectResponseIterator;
import us.ampre.rets.client.ReplyCode;
import us.ampre.rets.client.exceptions.RetsException;
import us.ampre.rets.client.utils.InputStreamUtil;
import us.ampre.rets.common.util.CaseInsensitiveTreeMap;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Represents the response from a RETS GetObject request.
 * <p>
 * Handles both multipart and non-multipart responses, parses headers,
 * and provides iterators for accessing individual objects.
 * Handles error responses and exposes reply codes and messages.
 * </p>
 *
 * <b>Note:</b> This class is <b>not thread-safe</b> and should be used in a single-threaded context.
 * The headers are stored in a case-insensitive map.
 * The input stream may be consumed for XML error handling.
 * The iterator returned is single-use; calling {@code iterator()} or {@code iterator(int)} more than once will throw an exception.
 *
 * @author Ampre Chris Hailey
 */
public class GetObjectResponse {
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private static final GetObjectIterator<SingleObjectResponse> EMPTY_OBJECT_RESPONSE_ITERATOR =
            new GetObjectIterator<>() {
                public boolean hasNext() {
                    return false;
                }

                public SingleObjectResponse next() {
                    throw new NoSuchElementException();
                }

                public void close() { /* no op */ }

                public void remove() { /* no op */ }
            };

    private final Map<String, String> headers;
    private final InputStream inputStream;
    private final boolean isMultipart;
    private boolean emptyResponse;
    private boolean exhausted;
    private int replyCode = -99;
    private String replyText;
    private SingleObjectResponse errorResponse;

    /**
     * Constructs a GetObjectResponse from headers and input stream.
     * Handles multipart and XML error responses.
     *
     * @param headers the response headers (case-insensitive, must not be null)
     * @param in      the input stream of the response (must not be null)
     * @throws RetsException        if the response is malformed or contains an error
     * @throws NullPointerException if headers or in is null
     */
    public GetObjectResponse(Map<String, String> headers, InputStream in) throws RetsException {
        this.emptyResponse = false;
        this.exhausted = false;
        this.headers = new CaseInsensitiveTreeMap<>(Objects.requireNonNull(headers, "headers"));
        this.isMultipart = getType() != null && getType().contains("multipart");
        Objects.requireNonNull(in, "inputStream");

        boolean isXml = getType() != null && getType().contains("text/xml");
        byte[] xmlBytes = null;
        try {
            if (isXml) {
                // Small XML error responses are safe to buffer so we can parse them.
                // Read bytes so we can create independent InputStreams for parsing and later use.
                java.io.ByteArrayInputStream copied = InputStreamUtil.copyStream(in);
                xmlBytes = copied.readAllBytes();
                this.inputStream = new ByteArrayInputStream(xmlBytes);
            } else {
                // For non-XML (regular) responses, create an independent stream immediately so the
                // original InputStream passed to the constructor may be consumed by callers without
                // affecting this response. Small streams are buffered in-memory; large/unknown
                // streams are copied to a temp file.
                try {
                    boolean useInMemory = false;
                    try {
                        if (in instanceof java.io.ByteArrayInputStream) {
                            useInMemory = true;
                        } else {
                            int avail = in.available();
                            useInMemory = (avail > 0 && avail <= 64 * 1024);
                        }
                    } catch (IOException ignored) {
                        // If available() fails, treat as large and copy to temp file
                        useInMemory = false;
                    }

                    if (useInMemory) {
                        java.io.ByteArrayInputStream bais = InputStreamUtil.copyStream(in);
                        byte[] data = bais.readAllBytes();
                        this.inputStream = new ByteArrayInputStream(data);
                    } else {
                        final java.nio.file.Path tmp = InputStreamUtil.copyStreamToTempFile(in, "rets-object");
                        java.io.FileInputStream fis = new java.io.FileInputStream(tmp.toFile());
                        this.inputStream = new java.io.FilterInputStream(fis) {
                            @Override
                            public void close() throws IOException {
                                try {
                                    super.close();
                                } finally {
                                    try {
                                        java.nio.file.Files.deleteIfExists(tmp);
                                    } catch (IOException ignored) {
                                    }
                                }
                            }
                        };
                    }
                } catch (IOException e) {
                    throw new RetsException("Failed to copy input stream", e);
                }
            }
        } catch (IOException e) {
            throw new RetsException("Failed to copy input stream", e);
        }

        boolean containsContentId = headers.containsKey(SingleObjectResponse.CONTENT_ID);
        boolean nonMultiPartXmlWithoutContentId = this.isMultipart == false && isXml && containsContentId == false;
        boolean multiPartXml = this.isMultipart && isXml;

        // Handle XML error responses
        if (multiPartXml || nonMultiPartXmlWithoutContentId) {
            try {
                this.emptyResponse = true;
                SAXBuilder builder = new SAXBuilder();
                Document mDocument = builder.build(xmlBytes != null ? new ByteArrayInputStream(xmlBytes) : getInputStream());
                Element root = mDocument.getRootElement();
                if ("RETS".equals(root.getName())) {
                    replyCode = NumberUtils.toInt(root.getAttributeValue("ReplyCode"));
                    replyText = root.getAttributeValue("ReplyText");
                    if (ReplyCode.SUCCESS.equals(replyCode) == true) return;
                    if (ReplyCode.SUCCESS.equals(replyCode) == false) {
                        errorResponse = new SingleObjectResponse(headers, null, replyText);
                    }
                    return;
                }
                throw new RetsException("Malformed response [multipart=" + this.isMultipart + ", content-type=text/xml]. " +
                        "Content id did not exist in response and response was not valid RETS response.");
            } catch (JDOMException | IOException e) {
                try {
                    errorResponse = new SingleObjectResponse(headers, null, e.getMessage());
                } catch (IOException ex) {
                    throw new RetsException(ex);
                }
                throw new RetsException(e);
            }
        }
    }

    /**
     * Removes quotes from the boundary value if present.
     * Used internally to normalize the boundary string for multipart parsing.
     *
     * @param boundaryValue the boundary string
     * @return the unescaped boundary string
     */
    public static String unescapeBoundary(String boundaryValue) {
        if (boundaryValue == null) return null;
        if (boundaryValue.startsWith("\""))
            boundaryValue = boundaryValue.substring(1);
        if (boundaryValue.endsWith("\""))
            boundaryValue = boundaryValue.substring(0, boundaryValue.length() - 1);
        return boundaryValue;
    }

    /**
     * Returns the reply code and text from the RETS response, formatted as "<code>-<text>".
     * Returns null if replyText is not available.
     *
     * @return reply code and text as a string, or null if not available
     */
    public String getReplyText() {
        if (replyText == null) return null;
        return replyCode + "-" + replyText;
    }

    /**
     * Returns true if the reply code indicates a successful response (i.e., the server reported no error).
     * Success is determined by the reply code matching {@link ReplyCode#SUCCESS} or -99 (custom).
     *
     * @return true if the response is successful, false otherwise
     */
    public boolean isSuccessful() {
        return replyCode == -99 || ReplyCode.SUCCESS.equals(replyCode);
    }

    /**
     * Returns the Content-Type of the response, as provided in the headers.
     *
     * @return the Content-Type header value, or null if not present
     */
    public String getType() {
        return this.headers.get(HttpHeaders.CONTENT_TYPE);
    }

    /**
     * Extracts the multipart boundary from the Content-Type header.
     *
     * This method is tolerant: it will attempt to parse standard boundary parameters,
     * fall back to a manual extraction if the header is nonstandard, and finally
     * attempt a best-effort scan of the response stream for lines starting with "--" when possible.
     * If no boundary can be determined, {@code null} is returned.
     *
     * @return the boundary string, or {@code null} if none could be determined
     */
    public String getBoundary() {
        String contentTypeValue = getType();
        if (contentTypeValue == null) return null;
        BasicHeaderValueParser parser = new BasicHeaderValueParser();
        HeaderElement[] contentTypeElements;

        try {
            ParserCursor cursor = new ParserCursor(0, contentTypeValue.length());
            contentTypeElements = parser.parseElements(contentTypeValue, cursor);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not parse Content-Type header value: " + contentTypeValue, e);
        }

        if (contentTypeElements.length != 1) {
            throw new IllegalArgumentException("Multipart response appears to have a bad Content-Type header value: " + contentTypeValue);
        }

        // Extract the boundary parameter from the single HeaderElement
        String boundary = null;
        for (NameValuePair param : contentTypeElements[0].getParameters()) {
            if ("boundary".equalsIgnoreCase(param.getName())) {
                boundary = param.getValue();
                break;
            }
        }
        if (boundary == null) {
            throw new IllegalArgumentException("Missing boundary in Content-Type header: " + contentTypeValue);
        }
        return unescapeBoundary(boundary);
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    /**
     * Returns an iterator for accessing individual objects in the response.
     * <p>
     * Note: This method can only be called once per response. Subsequent calls will throw a {@link RetsException}.
     * In case of an error response, the iterator will return a {@link SingleObjectResponse} containing error details.
     * </p>
     *
     * @param <T> type extending SingleObjectResponse
     * @return a GetObjectIterator for the response
     * @throws RetsException if the response is exhausted or malformed
     */
    public <T extends SingleObjectResponse> GetObjectIterator<T> iterator() throws RetsException {
        return iterator(DEFAULT_BUFFER_SIZE);
    }

    /**
     * Returns an iterator for accessing individual objects in the response with a custom buffer size.
     * <p>
     * Note: This method can only be called once per response. Subsequent calls will throw a {@link RetsException}.
     * In case of an error response, the iterator will return a {@link SingleObjectResponse} containing error details.
     * </p>
     *
     * @param bufferSize the buffer size for reading
     * @param <T>        type extending SingleObjectResponse
     * @return a GetObjectIterator for the response
     * @throws RetsException if the response is exhausted or malformed
     */
    public <T extends SingleObjectResponse> GetObjectIterator<T> iterator(int bufferSize) throws RetsException {
        // Probe for empty stream without consuming data
        try {
            if (this.inputStream == null) {
                throw new RetsException("Empty input stream");
            }
            if (this.inputStream.markSupported()) {
                this.inputStream.mark(1);
                int first = this.inputStream.read();
                if (first == -1) {
                    throw new RetsException("Empty input stream");
                }
                this.inputStream.reset();
            }
        } catch (IOException e) {
            throw new RetsException("Failed to probe input stream", e);
        }

        if (this.exhausted)
            throw new RetsException("Response was exhausted - cannot request iterator a second time");
        this.exhausted = true;

        if (this.errorResponse != null) {
            return new GetObjectIterator<>() {
                private boolean returned = false;

                @Override
                public boolean hasNext() {
                    return returned == false;
                }

                @Override
                public T next() {
                    if (returned) {
                        throw new NoSuchElementException("No more elements");
                    }
                    returned = true;
                    return (T) errorResponse;
                }

                @Override
                public void close() {
                    // No-op
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }

        if (this.emptyResponse)
            return (GetObjectIterator<T>) EMPTY_OBJECT_RESPONSE_ITERATOR;

        if (this.isMultipart) {
            try {
                return GetObjectResponseIterator.createIterator(
                        this,
                        bufferSize
                );
            } catch (Exception e) {
                throw new RetsException("Error creating multipart GetObjectIterator", e);
            }
        }
        // For non-multipart, return a single-use iterator that yields the full response as a
        // single SingleObjectResponse. The constructor ensured the response has an independent
        // InputStream (either memory-backed or file-backed) so this iterator can safely expose it.
        final InputStream singleStream = this.getInputStream();
        return new GetObjectIterator<>() {
            private boolean returned = false;

            @Override
            public boolean hasNext() {
                return returned == false;
            }

            @Override
            public T next() {
                if (returned) throw new NoSuchElementException("No more elements");
                returned = true;
                try {
                    return (T) new SingleObjectResponse(headers, singleStream);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void close() throws IOException {
                try { singleStream.close(); } catch (IOException ignored) {}
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }
}
