package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
	
	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {
		// Let's assume we have checked and found out that the buyer is underaged and
		// cannot buy chupa-chups
		throw new VerificationFailedException("Underaged!");
		// XXX - Save purchase
	}

	public void cancelCurrentPurchase() throws VerificationFailedException {				
		// XXX - Cancel current purchase
	}
	

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	public List<StockItem> loadWarehouseState() {
        // XXX mock implementation
        List<StockItem> dataset = new ArrayList<StockItem>();

        StockItem chips = new StockItem(1l, "Lays chips", "Potato chips", 11.0, 5);
        StockItem chupaChups = new StockItem(2l, "Chupa-chups", "Sweets", 8.0, 8);
        StockItem frankfurters = new StockItem(3l, "Frankfurters", "Beer sauseges", 15.0, 12);
        StockItem beer = new StockItem(4l, "Free Beer", "Student's delight", 0.0, 100);

        dataset.add(chips);
        dataset.add(chupaChups);
        dataset.add(frankfurters);
        dataset.add(beer);
       
        
        
        ObjectInputStream ois;
        try {
                ois = new ObjectInputStream(new FileInputStream("stockitems.data"));
               
                StockItem obj = null;
                   while ((obj = (StockItem)ois.readObject()) != null) {
                        dataset.add(obj);
                    }
                   ois.close();
        } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                //e.printStackTrace();
        }
       
        return dataset;
}

	@Override
	public List<HistoryItem> getHistoryItems() {
		// TODO Auto-generated method stub
		return null;
	}
}
