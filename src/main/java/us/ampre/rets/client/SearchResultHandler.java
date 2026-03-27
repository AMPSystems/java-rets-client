package us.ampre.rets.client;

import lombok.extern.slf4j.Slf4j;
import org.xml.sax.*;
import us.ampre.rets.client.exceptions.InvalidReplyCodeException;
import us.ampre.rets.client.exceptions.RetsException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Parses RETS search results XML and forwards extracted data to a {@link SearchResultCollector}.
 *
 * <p>This {@link org.xml.sax.ContentHandler} / {@link org.xml.sax.ErrorHandler} implementation
 * processes RETS-specific elements such as {@code RETS}, {@code RETS-STATUS}, {@code COUNT},
 * {@code DELIMITER}, {@code COLUMNS}, {@code DATA}, and {@code MAXROWS}. It enforces server
 * reply codes via {@link InvalidReplyCodeHandler} and applies a {@link CompactRowPolicy}
 * to determine whether rows should be accepted.
 *
 * <p>Instances are not thread-safe and should be used for a single parsing operation.
 *
 * @author Chris Hailey
 */
@Slf4j
public class SearchResultHandler implements ContentHandler, ErrorHandler {
    private static final SAXParserFactory FACTORY = SAXParserFactory.newInstance();

    private int dataCount;
    private final SearchResultCollector collector;
    private StringBuffer currentEntry;
    private String delimiter;
    private Locator locator;
    private String[] columns;
    private final InvalidReplyCodeHandler invalidReplyCodeHandler;
    private final CompactRowPolicy compactRowPolicy;

    /**
     * Creates a SearchResultHandler that forwards parsed rows to the given collector.
     *
     * @param r the collector to receive parsed rows; must not be null
     */
    public SearchResultHandler(SearchResultCollector r) {
        this(r, InvalidReplyCodeHandler.FAIL, CompactRowPolicy.DEFAULT);
    }

    /**
     * Creates a handler with explicit reply-code handling and row policy.
     *
     * @param r the collector to receive parsed rows; must not be null
     * @param invalidReplyCodeHandler strategy for handling non-success reply codes; must not be null
     * @param badRowPolicy policy to decide whether malformed compact rows should be included
     */
    public SearchResultHandler(SearchResultCollector r, InvalidReplyCodeHandler invalidReplyCodeHandler, CompactRowPolicy badRowPolicy) {
        this.compactRowPolicy = badRowPolicy;
        if (r == null)
            throw new NullPointerException("SearchResultCollector must not be null");

        if (invalidReplyCodeHandler == null)
            throw new NullPointerException("InvalidReplyCodeHandler must not be null");

        if (badRowPolicy == null)
            throw new NullPointerException("BadRowPolicy must not be null");

        this.collector = r;
        this.dataCount = 0;
        this.invalidReplyCodeHandler = invalidReplyCodeHandler;
    }

    /**
     * SAX callback invoked when a start element is encountered during parsing.
     * <p>
     * Recognized element names: {@code RETS}, {@code RETS-STATUS}, {@code COUNT}, {@code DELIMITER},
     * {@code COLUMNS}, {@code DATA}, and {@code MAXROWS}. Reply codes are validated and may
     * cause an {@link SAXException} if invalid and configured to fail.
     *
     * @param uri namespace URI, may be empty
     * @param localName local (unprefixed) name
     * @param qName qualified name (with prefix)
     * @param atts attributes attached to the element
     * @throws SAXException on parsing errors or invalid reply codes
     */
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        String name = localName;
        if (localName.isEmpty()) {
            name = qName;
        }
        switch (name) {
            case "RETS", "RETS-STATUS" -> {
                String rawReplyCode = atts.getValue("ReplyCode");
                log.debug("Rets ReplyCode = [{}]", rawReplyCode);
                try {
                    int replyCode = Integer.parseInt(rawReplyCode);
                    String replyText = atts.getValue("ReplyText");
                    if (replyCode > 0) {
                        try {
                            if (ReplyCode.MAXIMUM_RECORDS_EXCEEDED.equals(replyCode))
                                return;

                            if (ReplyCode.NO_RECORDS_FOUND.equals(replyCode))
                                return;

                            if (name.equals("RETS"))
                                this.invalidReplyCodeHandler.invalidRetsReplyCode(replyCode);
                            else
                                this.invalidReplyCodeHandler.invalidRetsStatusReplyCode(replyCode);
                        } catch (InvalidReplyCodeException e) {
                            String text = atts.getValue("ReplyText");
                            e.setRemoteMessage(text);
                            throw new SAXException(e);
                        }
                    }
                } catch (NumberFormatException e) {
                    throw new SAXParseException("Invalid ReplyCode '" + rawReplyCode + "'", this.locator);
                }
                return;
            }
            case "COUNT" -> {
                String s = atts.getValue("Records");
                if (s == null) {
                    s = atts.getValue("", "Records");
                    if (s == null) {
                        throw new SAXParseException("COUNT tag has no Records " + "attribute", this.locator);
                    }
                }
                int i = Integer.parseInt(s, 10);
                this.collector.setCount(i);
                return;
            }
            case "DELIMITER" -> {
                String s = atts.getValue("value");
                if (s == null) {
                    s = atts.getValue("", "value");
                    if (s == null) {
                        throw new RuntimeException("Invalid Delimiter");
                    }
                }
                int i = Integer.parseInt(s, 16);
                this.delimiter = "" + (char) i;
                return;
            }
            case "COLUMNS", "DATA" -> {
                this.currentEntry = new StringBuffer();
                return;
            }
            case "MAXROWS" -> {
                this.collector.setMaxRows();
                return;
            }
        }
        // Unknown tag. danger, will.
        log.warn("Unknown tag: {}, qName = {}", name, qName);

    }

    /**
     * SAX callback for character data. When inside a {@code COLUMNS} or {@code DATA} element
     * the characters are appended to an internal buffer for later processing.
     *
     * @param ch character array
     * @param start start offset
     * @param length number of characters
     */
    public void characters(char[] ch, int start, int length) {
        if (this.currentEntry != null) {
            this.currentEntry.append(ch, start, length);
        }
    }

    public void ignorableWhitespace(char[] ch, int start, int length) {
        // we ignore NOZINK!
        characters(ch, start, length);
    }

    /**
     * Splits a compact-format input string using the configured delimiter.
     * <p>
     * The compact format uses a delimiter character (set by a {@code DELIMITER} tag). The input
     * string is expected to start with the delimiter. This method preserves empty fields and
     * does not use {@link String#split} to avoid token boundary losses.
     *
     * @param input the compact-format line to split
     * @return array of field values (never null)
     * @throws SAXParseException if the delimiter is not set or the input is malformed
     */
    private String[] split(String input) throws SAXParseException {
        if (this.delimiter == null) {
            throw new SAXParseException("Invalid compact format - DELIMITER not specified", this.locator);
        }
        if (!input.startsWith(this.delimiter)) {
            throw new SAXParseException("Invalid compact format", this.locator);
        }
        StringTokenizer tkn = new StringTokenizer(input, this.delimiter, true);
        List<String> list = new LinkedList<>();
        tkn.nextToken(); // junk the first element
        String last = null;
        while (tkn.hasMoreTokens()) {
            String next = tkn.nextToken();
            if (next.equals(this.delimiter)) {
                if (last == null) {
                    list.add("");
                } else {
                    last = null;
                }
            } else {
                list.add(next);
                last = next;
            }
        }
        return list.toArray(new String[0]);
    }

    /**
     * SAX callback for end of element. When ending {@code COLUMNS} the collected column names are set
     * on the collector; when ending {@code DATA} the row is split and passed to the collector subject
     * to the compact row policy.
     *
     * @param uri namespace URI
     * @param localName local name
     * @param qName qualified name
     * @throws SAXParseException for format errors
     */
    public void endElement(String uri, String localName, String qName) throws SAXParseException {
        String name = localName;
        if (name.isEmpty()) {
            name = qName;
        }
        if (name.equals("COLUMNS") || name.equals("DATA")) {
            String[] contents = split(this.currentEntry.toString());
            if (name.equals("COLUMNS")) {
                this.collector.setColumns(contents);
                this.columns = contents;
            } else {
                if (this.compactRowPolicy.apply(this.dataCount, this.columns, contents)) {
                    this.dataCount++;
                    this.collector.addRow(contents);
                }
            }
            this.currentEntry = null;
        }
    }

    public void startDocument() {
        log.trace("Start document");
    }

    public void endDocument() {
        log.trace("Document ended");
        this.collector.setComplete();
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {

    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {

    }

    public void processingInstruction(String target, String data) throws SAXException {
        throw new SAXException("processing instructions not supported: " + "target=" + target + ", data=" + data);
    }

    public void skippedEntity(String name) throws SAXException {
        throw new SAXException("skipped entities not supported: name=" + name);
    }

    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    public void error(SAXParseException e) throws SAXException {
        throw e;
    }

    public void fatalError(SAXParseException e) throws SAXException {
        throw e;
    }

    public void warning(SAXParseException e) {
        log.warn("an error occurred while parsing.  Attempting to continue", e);
    }


    /**
     * Parses the given SAX {@link InputSource} using the handler's configuration.
     *
     * @param src the input source to parse
     * @throws RetsException on parse errors
     */
    public void parse(InputSource src) throws RetsException {
        parse(src, null);
    }

    /**
     * created in order to pass the charset to the parser for proper encoding
     *
     */

    /**
     * Parses the provided input stream using an optional charset for encoding.
     * The stream will be closed when parsing completes.
     *
     * @param str the input stream to parse; must not be null
     * @param charset the character set to set on the {@link InputSource} if encoding is not present
     * @throws RetsException on parse errors
     */
    public void parse(InputStream str, String charset) throws RetsException {
        parse(new InputSource(str), charset);
        try {
            str.close();
        } catch (IOException e) {
            throw new RetsException(e);
        }
    }

    /**
     * Pareses given source with the given charset
     */
    /**
     * Parses the given {@link InputSource} using the provided charset when necessary.
     * This method configures a SAX parser and delegates to this handler.
     *
     * @param src the input source to parse
     * @param charset optional charset to apply if the source has no encoding
     * @throws RetsException on parsing errors
     */
    public void parse(InputSource src, String charset) throws RetsException {
        String encoding = src.getEncoding();
        if (encoding == null && (charset != null)) {
            encoding = charset;
            log.trace("Charset from headers:{}. Setting as correct encoding for parsing", charset);
            src.setEncoding(encoding);
        }
        try {
            SAXParser p = FACTORY.newSAXParser();
            XMLReader r = p.getXMLReader();
            r.setContentHandler(this);
            r.setErrorHandler(this);
            r.parse(src);

        } catch (SAXException se) {
            if (se.getException() != null && se.getException() instanceof RetsException) {
                throw (RetsException) se.getException();
            }
            throw new RetsException(se);
        } catch (Exception e) {
            log.error("An exception occurred.", e);
            throw new RetsException(e);

        }
    }
}
