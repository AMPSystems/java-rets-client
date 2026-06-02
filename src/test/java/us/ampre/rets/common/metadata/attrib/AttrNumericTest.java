package us.ampre.rets.common.metadata.attrib;

import org.junit.jupiter.api.Test;
import us.ampre.rets.common.metadata.AttrType;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttrNumericTest extends AttrTypeTest {

	@Test
	void testNumeric() throws Exception {
		AttrType parser = new AttrNumeric();
		assertEquals(Integer.class, parser.getType());
		int[] values = { 1, 100, 99999, 12345, 67890 };
		for (int expected : values) {
			String input = Integer.toString(expected);
			Object o = parser.parse(input, true);
			int output = ((Integer) o).intValue();
			assertEquals(expected, output);
		}
		assertParseException(parser, "0x99");
		assertParseException(parser, "0AF");
		assertParseException(parser, "0L");
	}
}