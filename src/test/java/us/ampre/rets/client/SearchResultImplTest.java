package us.ampre.rets.client;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class SearchResultImplTest {

	@Test
	void testSearchResult() {
		String[] cols = { "Column1", "Column2" };
		String[] row1 = { "Data1x1", "Data1x2" };
		String[] row2 = { "Data2x1", "Data2x2" };
		String[] row2alt = { "", "" };
		row2alt[0] = row2[0];
		row2alt[1] = row2[1];
		SearchResultImpl result = new SearchResultImpl();
		result.setCount(5);
		result.setColumns(cols);
		result.addRow(row1);
		result.addRow(row2);
		result.setMaxRows();
		result.setComplete();
		org.junit.jupiter.api.Assertions.assertEquals(5, result.getCount(), "setCount wrong");
		assertTrue(result.isComplete(), "isComplete not set");
		assertTrue(result.isMaxRows(), "isMaxrows not set");
		assertArrayEquals(cols, result.getColumns(), "columns mangled");
		assertArrayEquals(row1, result.getRow(0), "row 1 mangled");
		assertArrayEquals(row2alt, result.getRow(1), "row 2 mangled");
		assertThrows(NoSuchElementException.class, () -> result.getRow(2),
				"getting invalid row 2 should have thrown NoSuchElementException");
	}

	@Test
	void testMinimumSearchResult() {
		String[] cols = { "col1" };
		String[] row = { "row1" };
		SearchResultImpl result = new SearchResultImpl();
		result.setColumns(cols);
		result.addRow(row);
		result.setComplete();
		org.junit.jupiter.api.Assertions.assertEquals(1, result.getCount(), "row count wrong");
		assertTrue(result.isComplete(), "isComplete wrong");
		assertFalse(result.isMaxRows(), "isMaxrows wrong");
	}
}