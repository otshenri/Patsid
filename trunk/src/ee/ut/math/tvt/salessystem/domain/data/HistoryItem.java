
package ee.ut.math.tvt.salessystem.domain.data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


public class HistoryItem implements DisplayableItem, Serializable {
	
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "purchase_date")
	private Date date0;
	@Column(name = "total")
	private double sum;
	@OneToMany(mappedBy = "historyItem")
	private List<SoldItem> items;
	
	private int state;
	private static long nextId;
	private DateFormat time;
	private DateFormat date;

	public void setItems(List<SoldItem> purchase) {
		this.items = purchase;
	}

	public List<SoldItem> getItems() {
		return this.items;
	}

	public String getDate() {
		
		String s = date.format(date0);
		return s;
	}

	public void setDate(DateFormat date) {
		this.date = date;
	}

	public String getTime() {
		
		String s = time.format(date0);
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
		this.date0 = Calendar.getInstance().getTime();
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