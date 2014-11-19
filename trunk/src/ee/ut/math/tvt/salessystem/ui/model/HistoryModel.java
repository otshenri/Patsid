
package ee.ut.math.tvt.salessystem.ui.model;

import java.util.NoSuchElementException;

import org.apache.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;



public class HistoryModel extends SalesSystemTableModel<HistoryItem> {

	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(HistoryModel.class);

	public HistoryModel() {
		super(new String[] { "Date", "Time", "Sum" });
	}

	@Override
	public Object getColumnValue(HistoryItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getDate();
		case 1:
			return item.getTime();
		case 2:
			return item.getSum();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	//add new history item
	public void addItem(final HistoryItem historyItem) {
		rows.add(historyItem);
		log.debug("Added new item: " + historyItem.getDate());
		fireTableDataChanged();
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final HistoryItem historyItem : rows) {
			buffer.append(historyItem.getTime() + "\t");
			buffer.append(historyItem.getDate() + "\t");
			buffer.append(historyItem.getSum() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}

}