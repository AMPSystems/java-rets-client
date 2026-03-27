package us.ampre.rets.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


import org.apache.commons.lang3.math.NumberUtils;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Document;
import org.jdom.input.SAXBuilder;
import us.ampre.rets.client.exceptions.InvalidReplyCodeException;
import us.ampre.rets.client.exceptions.RetsException;
import us.ampre.rets.common.metadata.MetaObject;
import us.ampre.rets.common.metadata.JDomCompactBuilder;
import us.ampre.rets.common.metadata.MetadataException;
import us.ampre.rets.common.metadata.JDomStandardBuilder;

/**
 * Parses RETS GetMetadata XML responses and exposes parsed {@link us.ampre.rets.common.metadata.MetaObject} array.
 *
 * <p>The constructor parses the input stream and dispatches to compact or standard
 * metadata builders depending on the {@code compact} flag. When the server returns
 * a "no metadata found" reply code, an empty array is returned.
 *
 * <p>Instances are immutable once constructed.
 *
 * @author Chris Hailey
 */
public class GetMetadataResponse {
    private MetaObject[] mMetadataObjs;

    /**
     * Parses metadata from the provided stream.
     *
     * @param stream the input stream containing the RETS metadata XML
     * @param compact true to parse compact metadata format, false for standard format
     * @param isStrict builder strictness to apply during parsing
     * @throws RetsException on parse errors or invalid reply codes
     */
    public GetMetadataResponse(InputStream stream, boolean compact, boolean isStrict) throws RetsException {
        try {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(stream);
            Element retsElement = document.getRootElement();
            if (!retsElement.getName().equals("RETS")) {
                throw new RetsException("Expecting RETS");
            }
            int replyCode = NumberUtils.toInt(retsElement.getAttributeValue("ReplyCode"));
            if (ReplyCode.SUCCESS.equals(replyCode)) {
                if (compact) {
                    handleCompactMetadata(document, isStrict);
                } else {
                    handleStandardMetadata(document, isStrict);
                }
            } else if (ReplyCode.NO_METADATA_FOUND.equals(replyCode)) {
                // No metadata is not an exceptional case
                handleNoMetadataFound(retsElement);
            } else {
                InvalidReplyCodeException e = new InvalidReplyCodeException(replyCode);
                e.setRemoteMessage(retsElement.getAttributeValue("ReplyText"));
                throw e;
            }
        } catch (JDOMException | IOException e) {
            throw new RetsException(e);
        }
    }

    private void handleNoMetadataFound(Element retsElement) throws RetsException {
        List<?> children = retsElement.getChildren();
        if (children.size() != 0) {
            throw new RetsException("Expecting 0 children when results");
        }
        this.mMetadataObjs = new MetaObject[0];
    }

    private void handleCompactMetadata(Document document, boolean isStrict) throws RetsException {
        try {
            JDomCompactBuilder builder = new JDomCompactBuilder();
            builder.setStrict(isStrict);
            this.mMetadataObjs = builder.parse(document);
        } catch (MetadataException e) {
            throw new RetsException(e);
        }
    }

    private void handleStandardMetadata(Document document, boolean isStrict) throws RetsException {
        try {
            JDomStandardBuilder builder = new JDomStandardBuilder();
            builder.setStrict(isStrict);
            this.mMetadataObjs = builder.parse(document);
        } catch (MetadataException e) {
            throw new RetsException(e);
        }
    }

    /**
     * Returns the parsed metadata objects. May be an empty array if no metadata was found.
     *
     * @return an array of {@link MetaObject} instances (never null)
     */
    public MetaObject[] getMetadata() {
        return this.mMetadataObjs;
    }

}
