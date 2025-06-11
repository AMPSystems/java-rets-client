package us.ampre.rets.common.metadata.attrib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import us.ampre.rets.common.metadata.AttrType;
import us.ampre.rets.common.metadata.MetaParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttrVersionTest extends AttrTypeTest {
	private AttrType mParser;

	@BeforeEach
	void setUp() {
		this.mParser = new AttrVersion();
	}

	@Test
	void testAttrVersion() throws Exception {
		assertEquals(Integer.class, this.mParser.getType());
		assertVersionEquals(10500005, "1.5.5");
		assertVersionEquals(123456789, "12.34.56789");
		assertVersionEquals(0, "0.0.0");
		assertParseException("1.1.1.1");
		assertParseException("1.1");
		assertParseException("123456789");
	}

	private void assertParseException(String input) throws Exception {
		assertParseException(this.mParser, input);
	}

	private void assertVersionEquals(int expected, String input) throws MetaParseException {
		Integer i = (Integer) this.mParser.parse(input, true);
		assertEquals(expected, i.intValue());
	}
}