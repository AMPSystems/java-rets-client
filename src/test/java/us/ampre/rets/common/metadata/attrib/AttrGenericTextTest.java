package us.ampre.rets.common.metadata.attrib;

import org.junit.jupiter.api.Test;
import us.ampre.rets.common.metadata.AttrType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttrGenericTextTest extends AttrTypeTest {

	@Test
	void testAttrGeneric() throws Exception {
		AttrType parser = new AttrGenericText(0, 10, "abcdefg");

		assertEquals("aaaaa", parser.parse("aaaaa", true));
		assertEquals("abcdefg", parser.parse("abcdefg", true));
		assertEquals("", parser.parse("", true));

		assertParseException(parser, "abcdefG");
		assertParseException(parser, "A");
		assertParseException(parser, "abcdefgabcd");
	}
}