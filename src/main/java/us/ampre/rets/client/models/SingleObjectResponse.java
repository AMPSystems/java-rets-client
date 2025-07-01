package us.ampre.rets.client.models;

import lombok.Getter;
import lombok.Setter;
import us.ampre.rets.client.utils.InputStreamUtil;
import us.ampre.rets.common.util.CaseInsensitiveTreeMap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a single object returned from a RETS server, including its headers,
 * content stream, and an optional error message if the response indicates an error.
 * <p>
 * The headers are stored in a case-insensitive map.
 * The input stream is always copied to ensure independence from the original stream.
 * If an error occurs during retrieval, the error message will be set and the input stream may be null.
 * </p>
 */
public class SingleObjectResponse {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String LOCATION = "Location";
    public static final String CONTENT_DESCRIPTION = "Content-Description";
    public static final String OBJECT_ID = "Object-ID";
    public static final String CONTENT_ID = "Content-ID";

    @Getter
    private final Map<String, String> headers;

    @Getter
    @Setter
    private InputStream inputStream;
    @Getter
    private final String errorMessage;

    /**
     * Constructs a SingleObjectResponse with headers, content stream, and optional error message.
     * <p>
     * The input stream is fully copied to ensure this instance has an independent stream.
     * </p>
     *
     * @param headers      the response headers (case-insensitive, must not be null)
     * @param inputStream  the input stream for the object content, or null if error
     * @param errorMessage the error message if present, or null if successful
     * @throws IOException              if copying the input stream fails
     * @throws NullPointerException     if headers is null
     */
    public SingleObjectResponse(Map<String, String> headers, InputStream inputStream, String errorMessage) throws IOException {
        this.headers = new CaseInsensitiveTreeMap<>(Objects.requireNonNull(headers, "headers"));
        if (inputStream != null) {
            this.inputStream = InputStreamUtil.copyStream(inputStream);
        } else {
            this.inputStream = null;
        }
        this.errorMessage = errorMessage;
    }

    /**
     * Constructs a successful SingleObjectResponse with headers and content stream.
     * <p>
     * The input stream is fully copied to ensure this instance has an independent stream.
     * </p>
     *
     * @param headers     the response headers (case-insensitive, must not be null)
     * @param inputStream the input stream for the object content
     * @throws IOException              if copying the input stream fails
     * @throws NullPointerException     if headers is null
     */
    public SingleObjectResponse(Map<String, String> headers, InputStream inputStream) throws IOException {
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