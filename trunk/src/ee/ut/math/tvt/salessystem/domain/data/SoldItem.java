package ee.ut.math.tvt.salessystem.domain.data;

import javax.persistence.*;


/**
 * Already bought StockItem. SoldItem duplicates name and price for preserving history. 
 */
@Entity
@Table(name="SOLDITEM")
public class SoldItem implements Cloneable, DisplayableItem {
	 @Id
     @Column(name = "id")
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	 
     // @ManyToOne

     
     public SoldItem() {
		super();
	}

	@ManyToOne
     @JoinColumn(name = "stockitem_id", nullable = true)
     private StockItem stockItem;
     
     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "sale_id", nullable = true)
     private HistoryItem historyItem2;
     
     @Column(name = "name")
     private String name;
     
     @Column(name = "quantity")
     private Integer quantity;

     @Column(name = "itemprice")
     private double price;


	public SoldItem(StockItem stockItem, int quantity) {
        this.stockItem = stockItem;
        this.name = stockItem.getName();
        this.price = stockItem.getPrice();
        this.id = stockItem.getId();
        this.quantity = quantity;
        
    }
	
    public void setHistoryItem(HistoryItem historyItem) {
		this.historyItem2 = historyItem;
	}
	
    
    
    public Long getId() {
    	 return stockItem.getId();
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getSum() {
        return price * ((double) quantity);
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
    }
    
}
