package us.ampre.rets.client;

import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;
import us.ampre.rets.client.exceptions.InvalidReplyCodeException;
import us.ampre.rets.client.exceptions.RetsException;

import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class SearchResultHandlerTest {

	SearchResult runSearchTest(String input) throws RetsException {
		return runSearchTest(input, InvalidReplyCodeHandler.FAIL);
	}

	SearchResult runSearchTest(String input, InvalidReplyCodeHandler invalidReplyCodeHandler) throws RetsException {
		SearchResultImpl res = new SearchResultImpl();
		SearchResultHandler h = new SearchResultHandler(res, invalidReplyCodeHandler, CompactRowPolicy.DEFAULT);
		InputSource source = new InputSource(new StringReader(input));
		h.parse(source);
		return res;
	}

	@Test
	void testSmallResult() throws RetsException {
		SearchResult result = runSearchTest(GOOD_SMALL_TEST);
		assertTrue(result.isComplete(), "search not complete");
		String[] columns = result.getColumns();
		assertNotNull(columns);
		assertEquals(1, columns.length, "column headers count wrong");
		assertEquals("Column1", columns[0], "bad column header");
		assertEquals(1, result.getCount(), "wrong row count");
		String[] row = result.getRow(0);
		assertEquals(1, row.length, "wrong row width");
		assertEquals("Data1", row[0], "wrong row data");
		assertFalse(result.isMaxRows(), "max rows wrong");
	}

	@Test
	void testAllTags() throws RetsException {
		SearchResult result = runSearchTest(ALL_TAGS_TEST);
		assertTrue(result.isComplete(), "search not complete");
		assertEquals(100, result.getCount(), "extended count wrong");
		assertTrue(result.isMaxRows(), "max rows not set");
		String[] row = result.getRow(0);
		assertNotNull(row, "row 0 is null");
		assertEquals(1, row.length, "wrong number of row[0] elements");
		assertEquals("Data1", row[0], "wrong row[0] data");
		row = result.getRow(1);
		assertNotNull(row, "row 1 is null");
		assertEquals(1, row.length, "wrong number of row[1] elements");
		assertEquals("Data2", row[0], "wrong row[1] data");
	}

	@Test
	void testReplyCode20208() throws RetsException {
		SearchResult result = runSearchTest(MAXROWS_REPLYCODE);
		assertTrue(result.isComplete(), "search not complete");
		assertEquals(100, result.getCount(), "extended count wrong");
		assertTrue(result.isMaxRows(), "max rows not set");
		String[] row = result.getRow(0);
		assertNotNull(row, "row 0 is null");
		assertEquals(1, row.length, "wrong number of row[0] elements");
		assertEquals("Data1", row[0], "wrong row[0] data");
		row = result.getRow(1);
		assertNotNull(row, "row 1 is null");
		assertEquals(1, row.length, "wrong number of row[1] elements");
		assertEquals("Data2", row[0], "wrong row[1] data");
	}

	@Test
	void testReplyCode20201WithColumns() throws RetsException {
		SearchResult result = runSearchTest(EMPTY_REPLYCODE_WITH_COLUMNS_TAG);
		assertFalse(result.iterator().hasNext(), "iterator should be empty");
	}

	@Test
	void testReplyCode20201WithoutColumns() throws RetsException {
		SearchResult result = runSearchTest(EMPTY_REPLYCODE);
		assertFalse(result.iterator().hasNext(), "iterator should be empty");
	}

	@Test
	void testEarlyException() {
		Exception exception = assertThrows(InvalidReplyCodeException.class, () -> {
			runSearchTest(EARLY_ERROR_TEST);
		});
		// Optionally, assert on exception message if needed
	}

	@Test
	void testLateException() {
		Exception exception = assertThrows(InvalidReplyCodeException.class, () -> {
			runSearchTest(LATE_ERROR_TEST);
		});
		// Optionally, assert on exception message if needed
	}

	@Test
	void testEarlyExceptionWithTrap() {
		Exception exception = assertThrows(InvalidReplyCodeException.class, () -> {
			runSearchTest(EARLY_ERROR_TEST, new TestInvalidReplyCodeHandler());
		});
		// Optionally, assert on exception message if needed
	}

	@Test
	void testLateExceptionWithTrap() throws RetsException {
		TestInvalidReplyCodeHandler testInvalidReplyCodeHandler = new TestInvalidReplyCodeHandler();
		runSearchTest(LATE_ERROR_TEST, testInvalidReplyCodeHandler);
		assertEquals(LATE_ERROR_CODE, testInvalidReplyCodeHandler.getReplyCode());
	}

	public static final String CRLF = "\r\n";

	public static final String GOOD_SMALL_TEST = "<RETS ReplyCode=\"0\" " + "ReplyText=\"Success\">" + CRLF
			+ "<DELIMITER value=\"09\"/>" + CRLF + "<COLUMNS>\tColumn1\t</COLUMNS>" + CRLF + "<DATA>\tData1\t</DATA>"
			+ CRLF + "</RETS>" + CRLF;

	public static final String ALL_TAGS_TEST = "<RETS ReplyCode=\"0\" " + "ReplyText=\"Success\">" + CRLF
			+ "<COUNT Records=\"100\"/>" + CRLF + "<DELIMITER value=\"09\"/>" + CRLF + "<COLUMNS>\tColumn1\t</COLUMNS>"
			+ CRLF + "<DATA>\tData1\t</DATA>" + CRLF + "<DATA>\tData2\t</DATA>" + CRLF + "<MAXROWS/>" + "</RETS>"
			+ CRLF;

	public static final String EARLY_ERROR_TEST = "<RETS ReplyCode=\"20203\" " + "ReplyText=\"Misc search Error\">"
			+ CRLF + "</RETS>" + CRLF;

	public static final int LATE_ERROR_CODE = 20203;

	public static final String LATE_ERROR_TEST = "<RETS ReplyCode=\"0\" " + "ReplyText=\"Success\">" + CRLF
			+ "<COUNT Records=\"100\"/>" + CRLF + "<DELIMITER value=\"09\"/>" + CRLF + "<COLUMNS>\tColumn1\t</COLUMNS>"
			+ CRLF + "<DATA>\tData1\t</DATA>" + CRLF + "<DATA>\tData2\t</DATA>" + CRLF + "<RETS-STATUS ReplyCode=\""
			+ LATE_ERROR_CODE + "\" ReplyText=\"Misc Error\"/>" + "</RETS>" + CRLF;

	public static final String MAXROWS_REPLYCODE = "<RETS ReplyCode=\"20208\" " + "ReplyText=\"Success\">" + CRLF
			+ "<COUNT Records=\"100\"/>" + CRLF + "<DELIMITER value=\"09\"/>" + CRLF + "<COLUMNS>\tColumn1\t</COLUMNS>"
			+ CRLF + "<DATA>\tData1\t</DATA>" + CRLF + "<DATA>\tData2\t</DATA>" + CRLF + "<MAXROWS/>" + "</RETS>"
			+ CRLF;

	public static final String EMPTY_REPLYCODE = "<RETS ReplyCode=\"20201\" " + "ReplyText=\"No Records Found\">"
			+ CRLF + "</RETS>" + CRLF;

	public static final String EMPTY_REPLYCODE_WITH_COLUMNS_TAG = "<RETS ReplyCode=\"20201\" "
			+ "ReplyText=\"No Records Found\">" + CRLF + "<DELIMITER value=\"09\"/>" + CRLF
			+ "<COLUMNS>\tColumn1\t</COLUMNS>" + CRLF + "</RETS>" + CRLF;
}