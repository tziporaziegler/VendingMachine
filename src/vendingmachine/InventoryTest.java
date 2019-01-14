package vendingmachine;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

public class InventoryTest {

	@Test
	public void testLoadToString() throws IOException {
		Inventory invent = new Inventory();
		invent.load("inventory.txt");
		Assert.assertEquals("A01 Candy Bar @ $1.55 x 5\nB02 Chips @ $1.30 x 3\nC03 Pretzels @ $1.00 x 1\n"
				+ "D04 Nuts @ $2.25 x 10\nE05 Gum @ $1.75 x 20\n", invent.toString());
	}

	@Test
	public void testLoadIOException() {
		Inventory invent = new Inventory();
		try {
			invent.load("invent.txt");
			Assert.fail("IO Exception should be thrown here");
		} catch (IOException e) {
		}
	}

	@Test
	public void testGet() throws IOException {
		Inventory invent = new Inventory();
		invent.load("inventory.txt");
		Assert.assertNull(invent.get("A00"));
		Assert.assertNotNull(invent.get("A01"));
	}

	@Test
	public void testAddNewItem() throws IOException {
		Inventory invent = new Inventory();
		invent.load("inventory.txt");
		invent.add(new Item("F06", "bananas", .30, 5));
		Assert.assertEquals("bananas", invent.get("F06").getName());
	}

	@Test
	public void testAddMoreOfItem() throws IOException {
		Inventory invent = new Inventory();
		invent.load("inventory.txt");
		invent.add(new Item("A01", "Candy Bar", 1.55, 3));
		Assert.assertEquals(8, invent.get("A01").getQuantity());
	}

	@Test
	public void testIsEmptyAndRemoveOne() throws IOException {
		Inventory invent = new Inventory();
		invent.load("inventory.txt");
		Assert.assertFalse(invent.isEmpty("C03"));
		invent.removeOne("C03");
		Assert.assertTrue(invent.isEmpty("C03"));
	}
}
