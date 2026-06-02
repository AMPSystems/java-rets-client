package us.ampre.rets.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RetsVersionTest {

	@Test
	void testEquals() {
		assertEquals(RetsVersion.RETS_10, new RetsVersion(1, 0), "Checking 1.0");
		assertEquals(RetsVersion.RETS_15, new RetsVersion(1, 5), "Checking 1.5");
		assertEquals(RetsVersion.RETS_17, new RetsVersion(1, 7), "Checking 1.7");
		assertEquals(RetsVersion.RETS_1_7_2, new RetsVersion(1, 7, 2, 0), "Checking 1.7.2");
		assertEquals(RetsVersion.RETS_1_7_2, new RetsVersion(1, 7, 2, 0), "Checking revision support");
		assertFalse(RetsVersion.RETS_15.equals(new RetsVersion(1, 5, 0, 1)), "Checking draft support");
		assertFalse(RetsVersion.RETS_15.equals(new RetsVersion(1, 5, 1)), "Checking backwards compatible draft support");
	}

	@Test
	void testToString() {
		assertEquals("RETS/1.0", RetsVersion.RETS_10.toString(), "Checking toString() 1.0");
		assertEquals("RETS/1.5", RetsVersion.RETS_15.toString(), "Checking toString() 1.5");
		assertEquals("RETS/1.7", RetsVersion.RETS_17.toString(), "Checking toString() 1.7");
		assertEquals("RETS/1.7.2", RetsVersion.RETS_1_7_2.toString(), "Checking toString() 1.7.2");
		assertEquals("RETS/1.5d1", new RetsVersion(1, 5, 1).toString(), "Checking toString() backward compatible draft without revision");
		assertEquals("RETS/1.7.2d1", new RetsVersion(1, 7, 2, 1).toString(), "Checking toString() revision with draft");
	}
}