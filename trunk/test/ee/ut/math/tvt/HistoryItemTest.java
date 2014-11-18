package ee.ut.math.tvt;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class HistoryItemTest {
	private ArrayList<SoldItem> items;
	private StockItem testitem;
	private StockItem testitem2;
	private HistoryItem test;

	@Before
	public void setUp() {
		testitem = new StockItem((long) 5, "Testing", "Testing", 15);
		testitem2 = new StockItem((long) 6, "Testing2", "Testing2", 15);

	}

	@Test
	public void testAddSoldItem() {
		items = new ArrayList<SoldItem>();
		ArrayList<SoldItem> items2 = new ArrayList<SoldItem>();
		
		items.add(new SoldItem(testitem, 10));
		items.add(new SoldItem(testitem2, 10));
		
		test = new HistoryItem(items);
		
		items2 = (ArrayList<SoldItem>) test.getItems();

		assertTrue(items.equals(items2));
	}

	@Test
	public void testGetSumWithNoItems() {
		items = new ArrayList<SoldItem>();
		test = new HistoryItem(items);
		assertTrue(test.getSum() == 0);
	}

	@Test
	public void testGetSumWithOneItem() {
		items = new ArrayList<SoldItem>();
		items.add(new SoldItem(testitem, 10));
		test = new HistoryItem(items);

		assertTrue(test.getSum() == 150);
	}

	@Test
	public void testGetSumWithMultipleItems() {
		items = new ArrayList<SoldItem>();
		items.add(new SoldItem(testitem, 10));
		items.add(new SoldItem(testitem2, 10));
		test = new HistoryItem(items);
		assertTrue(test.getSum() == 300);
	}
}