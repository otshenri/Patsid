package ee.ut.math.tvt.salessystem.ui.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;


/**
 * Purchase history model.
 */
public abstract class HistoryTableModel extends SalesSystemTableModel<HistoryItem> {
	private static final long serialVersionUID = 1L;
	private SalesSystemModel model;

	private static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

	public HistoryTableModel(SalesSystemModel model) {
		super(new String[] { "Id", "Time", "Sum", "Client" });
		this.model = model;
	}

	@Override
	protected Object getColumnValue(HistoryItem sale, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return sale.getId();
		case 1:
			return DATE_FORMAT.format(sale.getEndDate());
		case 2:
			return sale.getSum();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final HistoryItem sale : getTableRows()) {
			buffer.append(sale.getId() + "\t");
			buffer.append(sale.getSum() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}//a

	@Override
	public List<HistoryItem> getTableRows() {
		return model.getDomainController().getAllHistoryItems();
	}
}