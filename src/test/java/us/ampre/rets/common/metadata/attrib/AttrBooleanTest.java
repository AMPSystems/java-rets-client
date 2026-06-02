package us.ampre.rets.common.metadata.attrib;

import org.junit.jupiter.api.Test;
import us.ampre.rets.common.metadata.AttrType;
import us.ampre.rets.common.metadata.MetaParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AttrBooleanTest extends AttrTypeTest {

    @Test
    void testBoolean() throws Exception {
        String[] trues = {"true", "1", "TrUe", "Y"};
        String[] falses = {"false", "FALSE", "0", "", "N"};
        String[] exceptions = {"weird", "#(*&", "2", "falze"};

        AttrType parser = new AttrBoolean();
        assertEquals(Boolean.class, parser.getType());
        for (String input : trues) {
            boolean value = ((Boolean) parser.parse(input, true)).booleanValue();
            assertTrue(value, "Expected true return for " + input);
        }
        for (String input : falses) {
            boolean value = ((Boolean) parser.parse(input, true)).booleanValue();
            assertFalse(value, "Expected false return for " + input);
        }
        for (String input : exceptions) {
            assertParseException(parser, input);
        }
    }

    @Test
    void testBooleanOutput() throws MetaParseException {
        AttrBoolean parser = new AttrBoolean();
        Boolean output = parser.parse("true", true);
        assertEquals("1", parser.render(output));
        output = parser.parse("false", true);
        assertEquals("0", parser.render(output));
    }
}