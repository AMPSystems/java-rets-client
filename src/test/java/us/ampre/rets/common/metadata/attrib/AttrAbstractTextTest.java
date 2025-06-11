package us.ampre.rets.common.metadata.attrib;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import us.ampre.rets.common.metadata.AttrType;
import us.ampre.rets.common.metadata.MetaParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AttrAbstractTextTest extends AttrTypeTest {
	AttrType mShort, mLong;

	@BeforeEach
	void setUp() {
		this.mShort = new AttrAbstractText(1, 10) {
			@Override
			protected void checkContent(String value) throws MetaParseException {
				// no-op
			}
		};
		this.mLong = new AttrAbstractText(10, 20) {
			@Override
			protected void checkContent(String value) throws MetaParseException {
				// no-op
			}
		};
	}

	@Test
	void testAttrAbstractText() throws MetaParseException {
		assertEquals(String.class, this.mShort.getType());
		String test = "short";
		assertEquals(test, this.mShort.parse(test, true));
	}

	@Test
	void testTooLong() throws Exception {
		assertParseException(this.mShort, "way too long");
	}

	@Test
	void testTooShort() throws Exception {
		assertParseException(this.mLong, "short");
	}
}