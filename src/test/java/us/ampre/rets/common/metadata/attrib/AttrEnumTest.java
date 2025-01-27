package us.ampre.rets.common.metadata.attrib;

import us.ampre.rets.common.metadata.AttrType;

public class AttrEnumTest extends AttrTypeTest {
	public void testEnum() throws Exception {
		String[] values = { "One", "Two", "Three" };
		AttrType parser = new AttrEnum(values);
		for (int i = 0; i < values.length; i++) {
			String value = values[i];
			assertEquals(value, parser.render(parser.parse(value,true)));
		}
		assertParseException(parser, "Four");
		assertParseException(parser, "");
		assertParseException(parser, "three");
	}
}
