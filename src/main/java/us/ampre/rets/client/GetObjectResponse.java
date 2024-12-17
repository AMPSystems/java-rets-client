package us.ampre.rets.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.NoSuchElementException;

import lombok.Getter;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.message.BasicHeaderValueParser;
import org.apache.hc.core5.http.message.ParserCursor;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import us.ampre.rets.common.util.CaseInsensitiveTreeMap;

public class GetObjectResponse {
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private final static GetObjectIterator<SingleObjectResponse> EMPTY_OBJECT_RESPONSE_ITERATOR =
            new GetObjectIterator<>() {
                public boolean hasNext() {
                    return false;
                }

                public SingleObjectResponse next() {
                    throw new NoSuchElementException();
                }

                public void close() {
                    /* no op */
                }

                public void remove() {
                    /* no op */
                }
            };

    private final Map<String, String> headers;
    @Getter
    private final InputStream inputStream;
    private final boolean isMultipart;
    private boolean emptyResponse;
    private final boolean exhausted;
    private int replyCode = -99;
    private String replyText;

    public String getReplyText() {
        return replyCode + "-" + replyText;
    }

    public boolean isSuccessful() {
        return replyCode == -99;
    }

    public GetObjectResponse(Map<String, String> headers, InputStream in) throws RetsException {
        this.emptyResponse = false;
        this.exhausted = false;
        this.headers = new CaseInsensitiveTreeMap<>(headers);
        this.isMultipart = getType().contains("multipart");
        this.inputStream = in;

        boolean isXml = getType().contains("text/xml");
        boolean containsContentId = headers.containsKey(SingleObjectResponse.CONTENT_ID);
        boolean nonMultiPartXmlWithoutContentId = !this.isMultipart && isXml && !containsContentId;
        boolean multiPartXml = this.isMultipart && isXml;

        if (multiPartXml || nonMultiPartXmlWithoutContentId) {
            try {
                this.emptyResponse = true;
                SAXBuilder builder = new SAXBuilder();
                Document mDocument = builder.build(in);
                Element root = mDocument.getRootElement();
                if (root.getName().equals("RETS")) {
                    replyCode = NumberUtils.toInt(root.getAttributeValue("ReplyCode"));
                    replyText = root.getAttributeValue("ReplyText");
                    if (ReplyCode.SUCCESS.equals(replyCode)) return;
                    if (ReplyCode.NO_OBJECT_FOUND.equals(replyCode)) return;

                    throw new InvalidReplyCodeException(replyCode, replyText);
                }
                throw new RetsException("Malformed response [multipart=" + this.isMultipart + ", content-type=text/xml]. " +
                        "Content id did not exist in response and response was not valid RETS response.");
            } catch (JDOMException | IOException e) {
                throw new RetsException(e);
            }
        }
    }

    public String getType() {
        return this.headers.get(HttpHeaders.CONTENT_TYPE);
    }

    public String getBoundary() {
        String contentTypeValue = getType();
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
            return null;
        }
        return unescapeBoundary(boundary);
    }

    private static String unescapeBoundary(String boundaryValue) {
        if (boundaryValue.startsWith("\""))
            boundaryValue = boundaryValue.substring(1);
        if (boundaryValue.endsWith("\""))
            boundaryValue = boundaryValue.substring(0, boundaryValue.length() - 1);
        return boundaryValue;
    }

    public <T extends SingleObjectResponse> GetObjectIterator<T> iterator() throws RetsException {
        return iterator(DEFAULT_BUFFER_SIZE);
    }

    public <T extends SingleObjectResponse> GetObjectIterator<T> iterator(int bufferSize) throws RetsException {
        if (this.exhausted)
            throw new RetsException("Response was exhausted - cannot request iterator a second time");

        if (this.emptyResponse)
            return (GetObjectIterator<T>) EMPTY_OBJECT_RESPONSE_ITERATOR;

        if (this.isMultipart) {
            try {
                return GetObjectResponseIterator.createIterator(this, bufferSize);
            } catch (Exception e) {
                throw new RetsException("Error creating multipart GetObjectIterator", e);
            }
        }
        return new NonMultipartGetObjectResponseIterator(this.headers, this.inputStream);
    }

}

/**
 * Used to implement GetObjectIterator for a non-multipart response.
 */
final class NonMultipartGetObjectResponseIterator implements GetObjectIterator {
    private boolean exhausted;
    private final Map<String, String> headers;
    private final InputStream inputStream;

    public NonMultipartGetObjectResponseIterator(Map<String, String> headers, InputStream in) {
        this.exhausted = false;
        this.headers = headers;
        this.inputStream = in;
    }

    public void close() throws IOException {
        this.inputStream.close();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public boolean hasNext() {
        return !this.exhausted;
    }

    public SingleObjectResponse next() {
        if (this.exhausted)
            throw new NoSuchElementException("Stream exhausted");

        this.exhausted = true;
        return new SingleObjectResponse(this.headers, this.inputStream);
    }
}
