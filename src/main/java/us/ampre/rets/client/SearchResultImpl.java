package us.ampre.rets.client;

import java.util.*;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.LogFactory;
/**
 * Concrete Implementation of SearchResult interface
 *
 */
@Slf4j
public class SearchResultImpl implements SearchResult, SearchResultCollector {

	@Getter
	private String[] columnNames;
	@Getter
	private Hashtable<String, Integer> columnMap;
	@Setter
	private int count;
	@Getter
	private List<String[]> rows;
	@Getter
	@Setter
	private boolean maxRows;
	@Getter
	@Setter
	private boolean complete;

	public SearchResultImpl() {
		this.count = 0;
		this.rows = new ArrayList<String[]>();
		this.maxRows = false;
		this.complete = false;
	}

	public String[] getColumns() {
		return this.columnNames;
	}

	public int getCount() {
		if (this.count > 0) {
			return this.count;
		}
		return this.rows.size();
	}
	
	public int getRowCount() {
		return this.rows.size();
	}

	public void setColumns(String[] columns) {
		// save the columns
		this.columnNames = columns;
		// create a hashmap of where each column is
		int i = 0;
		columnMap = new Hashtable<>();
		for (String column: columnNames) {
			columnMap.put(column, i);
			i++;
		}
	}

	public boolean addRow(String[] row) {
		if (row.length > this.columnNames.length) {
			throw new IllegalArgumentException(String.format("Invalid number of result columns: got %s, expected %s",row.length, this.columnNames.length));
		}
		if (row.length < this.columnNames.length) {
			LogFactory.getLog(SearchResultCollector.class).warn(String.format("Row %s: Invalid number of result columns:  got %s, expected ",this.rows.size(), row.length, this.columnNames.length));
		}
		return this.rows.add(row);
	}

	public String[] getRow(int idx) {
		if (idx >= this.rows.size()) {
			throw new NoSuchElementException();
		}
		return this.rows.get(idx);
	}

	public Iterator iterator() {
		return this.rows.iterator();
	}

	public void setMaxRows() {
		setMaxRows(true);
	}

	public void setComplete() {
		setComplete(true);
	}

}
