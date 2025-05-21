package us.ampre.rets.client;

import org.junit.jupiter.api.Test;

import java.io.Reader;
import java.io.StringReader;

import us.ampre.rets.client.exceptions.InvalidReplyCodeException;
import us.ampre.rets.client.exceptions.RetsException;

import static org.junit.jupiter.api.Assertions.*;

class StreamingSearchResultProcessorTest {

	protected SearchResultProcessor createProcessor(InvalidReplyCodeHandler invalidReplyCodeHandler) {
		StreamingSearchResultProcessor streamingSearchResultProcessor = new StreamingSearchResultProcessor(1, 0);
		if (invalidReplyCodeHandler != null)
			streamingSearchResultProcessor.setInvalidRelyCodeHandler(invalidReplyCodeHandler);
		return streamingSearchResultProcessor;
	}

	SearchResultSet runSearchTest(String input) throws RetsException {
		return runSearchTest(input, InvalidReplyCodeHandler.FAIL);
	}

	SearchResultSet runSearchTest(String input, InvalidReplyCodeHandler invalidReplyCodeHandler) throws RetsException {
		SearchResultProcessor processor = createProcessor(invalidReplyCodeHandler);
		Reader source = new StringReader(input);
		return processor.parse(source);
	}

	@Test
	void testSmallResult() throws RetsException {
		SearchResultSet result = runSearchTest(SearchResultHandlerTest.GOOD_SMALL_TEST);
		String[] columns = result.getColumns();
		assertNotNull(columns);
		assertEquals(1, columns.length, "column headers count wrong");
		assertEquals("Column1", columns[0], "bad column header");

		if (result.getCount() != -1)
			assertEquals(1, result.getCount(), "wrong row count");

		assertTrue(result.hasNext(), "iterator should have more");
		String[] row = result.next();

		assertEquals(1, row.length, "wrong row width");
		assertEquals("Data1", row[0], "wrong row data");

		assertFalse(result.hasNext(), "rows should be exhausted");
		assertFalse(result.isMaxRows(), "max rows wrong");
		assertTrue(result.isComplete(), "search not complete");
	}

	@Test
	void testEarlyCallToIsMaxRows() throws RetsException {
		SearchResultSet result = runSearchTest(SearchResultHandlerTest.ALL_TAGS_TEST);
		assertThrows(IllegalStateException.class, result::isMaxRows, "Should throw illegal state exception");
	}

	@Test
	void testAllTags() throws RetsException {
		SearchResultSet result = runSearchTest(SearchResultHandlerTest.ALL_TAGS_TEST);
		assertEquals(100, result.getCount(), "extended count wrong");

		assertTrue(result.hasNext(), "iterator should have more");
		String[] row = result.next();
		assertNotNull(row, "row 0 is null");
		assertEquals(1, row.length, "wrong number of row[0] elements");
		assertEquals("Data1", row[0], "wrong row[0] data");

		assertTrue(result.hasNext(), "iterator should have more");
		row = result.next();
		assertNotNull(row, "row 1 is null");
		assertEquals(1, row.length, "wrong number of row[1] elements");
		assertEquals("Data2", row[0], "wrong row[1] data");

		assertFalse(result.hasNext(), "rows should be exhausted");
		assertTrue(result.isComplete(), "search not complete");
		assertTrue(result.isMaxRows(), "max rows not set");
	}

	@Test
	void testReplyCode20208() throws RetsException {
		SearchResultSet result = runSearchTest(SearchResultHandlerTest.MAXROWS_REPLYCODE);
		assertEquals(100, result.getCount(), "extended count wrong");

		assertTrue(result.hasNext(), "iterator should have more");
		String[] row = result.next();
		assertNotNull(row, "row 0 is null");
		assertEquals(1, row.length, "wrong number of row[0] elements");
		assertEquals("Data1", row[0], "wrong row[0] data");

		assertTrue(result.hasNext(), "iterator should have more");
		row = result.next();
		assertNotNull(row, "row 1 is null");
		assertEquals(1, row.length, "wrong number of row[1] elements");
		assertEquals("Data2", row[0], "wrong row[1] data");

		assertFalse(result.hasNext(), "rows should be exhausted");
		assertTrue(result.isComplete(), "search not complete");
		assertTrue(result.isMaxRows(), "max rows not set");
	}

	@Test
	void testReplyCode20201WithColumns() throws RetsException {
		SearchResultSet result = runSearchTest(SearchResultHandlerTest.EMPTY_REPLYCODE_WITH_COLUMNS_TAG);
		assertFalse(result.hasNext(), "iterator should be empty");
	}

	@Test
	void testReplyCode20201WithoutColumns() throws RetsException {
		SearchResultSet result = runSearchTest(SearchResultHandlerTest.EMPTY_REPLYCODE);
		assertFalse(result.hasNext(), "iterator should be empty");
	}

	@Test
	void testEarlyException() throws RetsException {
		assertThrows(InvalidReplyCodeException.class, () -> {
			SearchResultSet result = runSearchTest(SearchResultHandlerTest.EARLY_ERROR_TEST);
			while (result.hasNext())
				result.next();
		}, "Expected an Invalid ReplyCodeException");
	}

	@Test
	void testLateException() throws RetsException {
		assertThrows(InvalidReplyCodeException.class, () -> {
			SearchResultSet result = runSearchTest(SearchResultHandlerTest.LATE_ERROR_TEST);
			while (result.hasNext())
				result.next();
		}, "Expected an Invalid ReplyCodeException");
	}

	@Test
	void testEarlyExceptionWithTrap() throws RetsException {
		assertThrows(InvalidReplyCodeException.class, () -> {
			SearchResultSet result = runSearchTest(SearchResultHandlerTest.EARLY_ERROR_TEST,
					new TestInvalidReplyCodeHandler());
			while (result.hasNext())
				result.next();
		}, "Expected an Invalid ReplyCodeException");
	}

	@Test
	void testLateExceptionWithTrap() throws RetsException {
		TestInvalidReplyCodeHandler testInvalidReplyCodeHandler = new TestInvalidReplyCodeHandler();
		SearchResultSet result = runSearchTest(SearchResultHandlerTest.LATE_ERROR_TEST, testInvalidReplyCodeHandler);
		while (result.hasNext())
			result.next();

		assertEquals(SearchResultHandlerTest.LATE_ERROR_CODE, testInvalidReplyCodeHandler.getReplyCode());
	}

	@Test
	void testTimeout() throws Exception {
		int timeout = 100;
		SearchResultProcessor processor = new StreamingSearchResultProcessor(1, timeout);
		Reader source = new StringReader(SearchResultHandlerTest.ALL_TAGS_TEST);
		SearchResultSet result = processor.parse(source);

		assertThrows(RetsException.class, () -> {
			Thread.sleep(timeout * 10);
			result.hasNext();
		}, "Should fail since timeout should have been reached");
	}

	@Test
	void testIONotEatenException() throws RetsException {
		SearchResultProcessor processor = new StreamingSearchResultProcessor(100);

		IOFailReader ioExceptionStream = new IOFailReader(new StringReader(SearchResultHandlerTest.ALL_TAGS_TEST));
		ioExceptionStream.setFailRead(true);

		SearchResultSet resultSet = processor.parse(ioExceptionStream);

		assertThrows(RetsException.class, () -> {
			while (resultSet.hasNext())
				resultSet.next();
		}, "Expection an IOException to be thrown during stream reading.");
	}
}