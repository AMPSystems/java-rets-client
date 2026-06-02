package us.ampre.rets.client;

import us.ampre.rets.client.exceptions.RetsException;

import java.io.InputStream;
import java.io.Reader;

/**
 * Interface for parsing results from a RETS query/search
 */
public interface SearchResultProcessor {
	public SearchResultSet parse(InputStream in) throws RetsException;

	public SearchResultSet parse(Reader in) throws RetsException;
}
