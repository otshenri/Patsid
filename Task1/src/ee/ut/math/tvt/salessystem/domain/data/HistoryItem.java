
package ee.ut.math.tvt.salessystem.domain.data;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "HISTORYITEM")
public class HistoryItem implements DisplayableItem {
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "purchase_date")
	private Date date0;
	@Column(name = "total")
	private double sum;

	 @OneToMany(mappedBy = "historyItem2")
     private List<SoldItem> purchase;
	
	@Transient
	private int state;
	@Transient
	private static long nextId;


	public void setItems(List<SoldItem> purchase) {
		this.purchase = purchase;
	}

	public List<SoldItem> getItems() {
		return this.purchase;
	}

	public String getDate() {
		DateFormat date = new SimpleDateFormat("dd-MM-yyyy");
		String s = date.format(date0);
		return s;
	}
		

	public String getTime() {
		DateFormat time = new SimpleDateFormat("hh:mm a");
		String s = time.format(date0);
		return s;
	}


	public HistoryItem() {
		super();
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
		this.date0 = Calendar.getInstance().getTime();
		this.sum = sum;
		this.purchase = purchase;
	}
	
	public HistoryItem(List<SoldItem> purchase) {
		this.id = nextId++;
		
		this.date0 = Calendar.getInstance().getTime();
		for (SoldItem soldItem : purchase) {
			this.sum += soldItem.getSum();
			}
		this.purchase = purchase;
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