package us.ampre.rets.client;

import us.ampre.rets.client.exceptions.RetsException;

/**
 * Iterator style interface for processing the results
 * of a RETS search a single time.  Information about the
 * search can be retrieved once processing is complete by
 * calling the getInfo() method.
 * 
 * @author jrayburn
 */
public interface SearchResultSet extends SearchResultInfo {
	public String[] next() throws RetsException;

	public boolean hasNext() throws RetsException;
}
