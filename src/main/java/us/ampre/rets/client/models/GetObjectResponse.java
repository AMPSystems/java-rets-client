package us.ampre.rets.client.models;

import lombok.Getter;
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
import us.ampre.rets.client.*;
import us.ampre.rets.client.exceptions.RetsException;
import us.ampre.rets.common.util.CaseInsensitiveTreeMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Represents the response from a RETS GetObject request.
 * <p>
 * Handles both multipart and non-multipart responses, parses headers,
 * and provides iterators for accessing individual objects.
 * Handles error responses and exposes reply codes and messages.
 * </p>
 *
 * This class is not thread-safe and should be used in a single-threaded context.
 *
 * @author: Ampre Chris Hailey
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
    @Getter
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
     * @param headers the response headers
     * @param in      the input stream of the response
     * @throws RetsException if the response is malformed or contains an error
     */
    public GetObjectResponse(Map<String, String> headers, InputStream in) throws RetsException {
        this.emptyResponse = false;
        this.exhausted = false;
        this.headers = new CaseInsensitiveTreeMap<>(headers);
        this.isMultipart = getType() != null && getType().contains("multipart");
        this.inputStream = in;

        boolean isXml = getType() != null && getType().contains("text/xml");
        boolean containsContentId = headers.containsKey(SingleObjectResponse.CONTENT_ID);
        boolean nonMultiPartXmlWithoutContentId = this.isMultipart == false && isXml && containsContentId == false;
        boolean multiPartXml = this.isMultipart && isXml;

        // Handle XML error responses
        if (multiPartXml || nonMultiPartXmlWithoutContentId) {
            try {
                this.emptyResponse = true;
                SAXBuilder builder = new SAXBuilder();
                Document mDocument = builder.build(in);
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
                errorResponse = new SingleObjectResponse(headers, null, e.getMessage());
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
    private static String unescapeBoundary(String boundaryValue) {
        if (boundaryValue.startsWith("\""))
            boundaryValue = boundaryValue.substring(1);
        if (boundaryValue.endsWith("\""))
            boundaryValue = boundaryValue.substring(0, boundaryValue.length() - 1);
        return boundaryValue;
    }

    /**
     * Returns the reply code and text from the RETS response, formatted as "<code>-<text>".
     * This is useful for logging or error reporting.
     *
     * @return reply code and text as a string, or null if not available
     */
    public String getReplyText() {
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
     * <p>
     * If the Content-Type header is missing, returns {@code null}.
     * If the header is present but malformed, or the boundary parameter is missing,
     * throws an {@link IllegalArgumentException}.
     * </p>
     *
     * @return the boundary string, or {@code null} if Content-Type is not set
     * @throws IllegalArgumentException if the Content-Type header is malformed or missing the boundary parameter
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
                return GetObjectResponseIterator.createIterator(this, DEFAULT_BUFFER_SIZE);
            } catch (Exception e) {
                throw new RetsException("Error creating multipart GetObjectIterator", e);
            }
        }
        return new NonMultipartGetObjectResponseIterator(this.headers, this.inputStream);
    }
}