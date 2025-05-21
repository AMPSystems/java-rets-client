package us.ampre.rets.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CaseInsensitiveTreeMapTest {

	private CaseInsensitiveTreeMap map;

	@BeforeEach
	void setUp() {
		this.map = new CaseInsensitiveTreeMap();
	}

	@AfterEach
	void tearDown() {
		this.map = null;
	}

	@Test
	void testGetPut() {
		this.map.put("A", "X");
		assertEquals("X", this.map.get("A"));
		assertEquals("X", this.map.get("a"));

		this.map.put("a", "Y");
		assertEquals("Y", this.map.get("a"));
		assertEquals("Y", this.map.get("A"));

		assertEquals(1, this.map.size());
	}

	@Test
	void testContainsKey() {
		this.map.put("A", "X");
		assertTrue(this.map.containsKey("A"));
		assertTrue(this.map.containsKey("a"));
	}

	@Test
	void testClone() {
		Map otherMap = new HashMap();
		otherMap.put("A", "X");
		otherMap.put("a", "Y");
		assertEquals(2, otherMap.size());

		CaseInsensitiveTreeMap newCitm = new CaseInsensitiveTreeMap(otherMap);
		assertEquals(1, newCitm.size());
		// no guarantee of *which* value we'll get, just that they'll be equal
		assertEquals(this.map.get("a"), this.map.get("A"));
	}
}