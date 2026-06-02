package us.ampre.rets.common.metadata.attrib;

import us.ampre.rets.common.metadata.MetadataTestCase;
import us.ampre.rets.common.metadata.AttrType;
import us.ampre.rets.common.metadata.MetaParseException;

import static org.junit.jupiter.api.Assertions.fail;

public abstract class AttrTypeTest extends MetadataTestCase {
	protected void assertParseException(AttrType attrib, String input) throws Exception {
		attrib.parse(input, false);
		try {
			attrib.parse(input, true);
			fail("Expected MetaParseException, got no exception for input \"" + input + "\"");
		} catch (MetaParseException e) {
			// success
		}
	}
}