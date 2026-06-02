package us.ampre.rets.client;

/**
 * Interface for a setting properties of a result from a query (used by SearchResultHandler)
 */

public interface SearchResultCollector {

	public void setCount(int count);

	public void setColumns(String[] columns);

	public boolean addRow(String[] row);

	public void setMaxRows();

	public void setComplete();
}
