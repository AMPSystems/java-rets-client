package us.ampre.rets.common.metadata.attrib;

import org.junit.jupiter.api.Test;
import us.ampre.rets.common.metadata.AttrType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttrEnumTest extends AttrTypeTest {

	@Test
	void testEnum() throws Exception {
		String[] values = { "One", "Two", "Three" };
		AttrType parser = new AttrEnum(values);
		for (String value : values) {
			assertEquals(value, parser.render(parser.parse(value, true)));
		}
		assertParseException(parser, "Four");
		assertParseException(parser, "");
		assertParseException(parser, "three");
	}
}