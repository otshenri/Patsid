package ee.ut.math.tvt.salessystem.domain.controller.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import ee.ut.math.tvt.salessystem.domain.exception.VerificationFailedException;
import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

/**
 * Implementation of the sales domain controller.
 */
public class SalesDomainControllerImpl implements SalesDomainController {
	
	private Session session = HibernateUtil.currentSession();
	
	public void submitCurrentPurchase(List<SoldItem> goods) throws VerificationFailedException {

	}
	public void submitCurrentPurchase2(HistoryItem e) throws VerificationFailedException {
		
		session.getTransaction().begin();
		for (SoldItem soldItem : e.getItems()) {
			soldItem.setHistoryItem(e);
			changeStockItemQuantity(soldItem);
			session.save(soldItem);
			
		}
		session.save(e);
		session.getTransaction().commit();
	}
	
	@SuppressWarnings("unchecked")
	public void changeStockItemQuantity(SoldItem soldItem) {
		long id_a = soldItem.getStockItem().getId();
		List<Integer> b = session.createQuery(
				"select quantity from StockItem where id ="
						+ Long.toString(id_a)).list();
		int difference = b.get(0) - soldItem.getQuantity();
		session.createQuery(
				"update StockItem set quantity = "
						+ Integer.toString(difference) + " where id = "
						+ Long.toString(id_a)).executeUpdate();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<HistoryItem> loadHistory() {
		List<HistoryItem> dataset = session.createQuery("from HistoryItem").list();
		return dataset;
	}

	public void cancelCurrentPurchase() throws VerificationFailedException {				
		// XXX - Cancel current purchase
	}
	

	public void startNewPurchase() throws VerificationFailedException {
		// XXX - Start new purchase
	}

	@SuppressWarnings("unchecked")
	public List<StockItem> loadWarehouseState() {
        // XXX mock implementation
		List<StockItem> dataset = new ArrayList();
		dataset = session.createQuery("from StockItem").list();
       
        return dataset;
}
	public void addToWarehouse(StockItem stockItem) {
		session.getTransaction().begin();
		session.save(stockItem);
		session.getTransaction().commit();
		}

	@Override
	public List<HistoryItem> getHistoryItems() {
		// TODO Auto-generated method stub
		return null;
	}
	public void endSession() {
		HibernateUtil.closeSession();
	}
}
