package ee.ut.math.tvt;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;



public class StockItemTest {

	private StockItem test;
	
	
	@Before
	public void setUp() {
		test = new StockItem((long) 5, "Testing", "Testing", 15,10);
	}
	
	
	@Test
	public void testClone() {
		StockItem comparisonItem = (StockItem) test.clone();
		// Checking each field individually
		boolean testboolean1 = (test.getId() == comparisonItem.getId());
		boolean testboolean2 = test.getName().equals(comparisonItem.getName());
		boolean testboolean3 = test.getDescription().equals(comparisonItem.getDescription());
		boolean testboolean4 = test.getPrice() == comparisonItem.getPrice();
		boolean testboolean5 = test.getQuantity() == comparisonItem.getQuantity();
		
		assertTrue(testboolean1 && testboolean2 && testboolean3 && testboolean4 && testboolean5);
	}

	@Test
	public void testGetColumn() {
		assertEquals((long)5,test.getColumn(0));
		assertEquals("Testing",test.getColumn(1));
		assertEquals((double) 15,test.getColumn(2));
		assertEquals(10,test.getColumn(3));
		
	}
}
