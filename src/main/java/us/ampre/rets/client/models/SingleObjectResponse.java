package us.ampre.rets.client.models;

import lombok.Getter;
import us.ampre.rets.common.util.CaseInsensitiveTreeMap;

import java.io.InputStream;
import java.util.Map;

/**
 * Represents a single object returned from a RETS server, including its headers,
 * content stream, and an optional error message if the response indicates an error.
 * <p>
 * This class provides access to common RETS object headers and the binary content.
 * If an error occurs during retrieval, the error message will be set and the input stream may be null.
 * </p>
 */
public class SingleObjectResponse {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String LOCATION = "Location";
    public static final String CONTENT_DESCRIPTION = "Content-Description";
    public static final String OBJECT_ID = "Object-ID";
    public static final String CONTENT_ID = "Content-ID";

    private final Map<String, String> headers;

    @Getter
    private final InputStream inputStream;
    @Getter
    private final String errorMessage;

    /**
     * Constructs a SingleObjectResponse with headers, content stream, and optional error message.
     *
     * @param headers      the response headers (case-insensitive)
     * @param inputStream  the input stream for the object content, or null if error
     * @param errorMessage the error message if present, or null if successful
     */
    public SingleObjectResponse(Map<String, String> headers, InputStream inputStream, String errorMessage) {
        this.headers = new CaseInsensitiveTreeMap<>(headers);
        this.inputStream = inputStream;
        this.errorMessage = errorMessage;
    }

    /**
     * Constructs a successful SingleObjectResponse with headers and content stream.
     *
     * @param headers     the response headers (case-insensitive)
     * @param inputStream the input stream for the object content
     */
    public SingleObjectResponse(Map<String, String> headers, InputStream inputStream) {
        this(headers, inputStream, null);
    }

    /**
     * @return the value of the Content-Type header, or null if not present
     */
    public String getType() {
        return headers.get(CONTENT_TYPE);
    }

    /**
     * @return the value of the Content-ID header, or null if not present
     */
    public String getContentID() {
        return headers.get(CONTENT_ID);
    }

    /**
     * @return the value of the Object-ID header, or null if not present
     */
    public String getObjectID() {
        return headers.get(OBJECT_ID);
    }

    /**
     * @return the value of the Content-Description header, or null if not present
     */
    public String getDescription() {
        return headers.get(CONTENT_DESCRIPTION);
    }

    /**
     * @return the value of the Location header, or null if not present
     */
    public String getLocation() {
        return headers.get(LOCATION);
    }

}