package ee.ut.math.tvt;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.HistoryModel;

public class HistoryModelTest {
	HistoryModel hm;
	HistoryItem hi;
	List<SoldItem> sold;
	SoldItem test1;
	SoldItem test2;
	StockItem test3;
	StockItem test4;
	
	@Before
	public void setUp() {
		sold = new ArrayList();
		
		hm = new HistoryModel();
		
		test3 =new StockItem((long) 10, "Testing11", "Testing11", 16,11);
		
		test4 = new StockItem((long) 11, "Testing22", "Testing22", 15,10);
		
		test1 = new SoldItem(test3, 4);
		
		test2 = new SoldItem(test4, 5);
		
		sold.add(test1);
		
		sold.add(test2);
		
		hi = new HistoryItem(sold);
		
	}
	
	@Test
	public void testGetColumnValue() {
		
		assertEquals(hi.getDate(),hm.getColumnValue(hi, 0));
		assertEquals(hi.getTime(),hm.getColumnValue(hi, 1));
		assertEquals(hi.getSum(),hm.getColumnValue(hi, 2));
		
		
	}
	
}