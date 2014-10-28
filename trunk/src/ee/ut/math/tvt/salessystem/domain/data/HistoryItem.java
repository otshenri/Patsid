
package ee.ut.math.tvt.salessystem.domain.data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class HistoryItem implements DisplayableItem, Serializable {
	
	
	private static final long serialVersionUID = 1L;


	private static long nextId;
	private long id;
	private DateFormat time;
	private DateFormat date;
	private double sum;
	private List<SoldItem> items;
	private int state;

	public void setItems(List<SoldItem> purchase) {
		this.items = purchase;
	}

	public List<SoldItem> getItems() {
		return this.items;
	}

	public String getDate() {
		Date time2 = Calendar.getInstance().getTime();
		String s = date.format(time2);
		return s;
	}

	public void setDate(DateFormat date) {
		this.date = date;
	}

	public String getTime() {
		Date time2 = Calendar.getInstance().getTime();
		String s = time.format(time2);
		return s;
	}

	public void setTime(DateFormat time) {
		this.time = time;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/**
	 * Constructor for HistoryItem

	 */
	public HistoryItem(double sum, List<SoldItem> purchase) {
		this.id = nextId++;
		this.time = new SimpleDateFormat("hh:mm a");
		this.date = new SimpleDateFormat("dd-MM-yyyy");
		this.sum = sum;
		this.items = purchase;
	}

	//kas neid vaja yldse praegu ?
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}