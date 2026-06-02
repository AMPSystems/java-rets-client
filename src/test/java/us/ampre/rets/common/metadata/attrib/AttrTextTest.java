package us.ampre.rets.common.metadata.attrib;

import org.junit.jupiter.api.Test;
import us.ampre.rets.common.metadata.AttrType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttrTextTest extends AttrTypeTest {

	@Test
	void testAttrText() throws Exception {
		AttrType parser = new AttrText(0, 10);
		String[] good = { "\r\n\t", "eabc\rdefg" };
		String[] bad = { (char) 7 + "", (char) 1 + "", "12345678901" };

		assertEquals(String.class, parser.getType());

		for (String s : good) {
			assertEquals(s, parser.parse(s, true));
		}

		for (String s : bad) {
			assertParseException(parser, s);
		}
	}
}
