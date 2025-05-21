package us.ampre.rets.common.metadata.attrib;

import org.junit.jupiter.api.Test;
import us.ampre.rets.common.metadata.AttrType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttrPlaintextTest extends AttrTypeTest {

	@Test
	void testPlaintext() throws Exception {
		AttrType parser = new AttrPlaintext(0, 10);

		assertEquals(String.class, parser.getType());
		String[] good = { "%17a", "!%@$", "90785", ")!(*%! ", "" };
		String[] bad = { "\r\n", "\t", new String(new char[] { (char) 7 }) };

		for (String s : good) {
			assertEquals(s, parser.parse(s, true));
		}

		for (String s : bad) {
			assertParseException(parser, s);
		}

		AttrType parser2 = new AttrPlaintext(10, 20);
		assertParseException(parser2, "1");
		assertParseException(parser2, "123456789012345678901");
	}
}