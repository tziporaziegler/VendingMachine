package vendingmachine;

import java.io.IOException;
import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Test;

public class VendingMachineTest {

	@Test
	public void pay() {
		DecimalFormat formatter = new DecimalFormat("$#,##0.00");
		VendingMachine vend = new VendingMachine(new Inventory(), new Money(10, 10, 10, 10));
		Assert.assertEquals("$14.00", formatter.format(vend.getBank().getTotal()));
		vend.pay(new Money(2, 5, 6, 3));
		Assert.assertEquals("$18.00", formatter.format(vend.getBank().getTotal()));
		Assert.assertEquals("$4.00", formatter.format(vend.getPaid().getTotal()));
	}

	@Test
	public void buy() throws IOException, CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException {
		DecimalFormat formatter = new DecimalFormat("$#,##0.00");
		Inventory inv = new Inventory();
		inv.load("inventory.txt");
		VendingMachine vend = new VendingMachine(inv, new Money(10, 10, 10, 10));
		vend.pay(new Money(2, 0, 0, 0));
		int oldQty = inv.get("A01").getQuantity();
		Assert.assertEquals("$0.45", formatter.format(vend.buy("A01").getTotal()));
		Assert.assertEquals(0, vend.getPaid().getTotal(), 0);
		Assert.assertEquals(oldQty - 1, inv.get("A01").getQuantity(), 0);
	}

	@Test
	public void buy2() throws IOException, CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException {
		DecimalFormat formatter = new DecimalFormat("$#,##0.00");
		VendingMachine vend = new VendingMachine(new Inventory(), new Money(10, 10, 10, 10));
		vend.getInventory().load("inventory.txt");
		vend.pay(new Money(2, 0, 0, 0));
		Assert.assertEquals("$0.70", formatter.format(vend.buy("B02").getTotal()));
	}

	@Test
	public void buy3() throws IOException, CodeNotFoundException, NotEnoughPaidException, NotEnoughChangeException {
		DecimalFormat formatter = new DecimalFormat("$#,##0.00");
		VendingMachine vend = new VendingMachine(new Inventory(), new Money(0, 0, 0, 30));
		vend.getInventory().load("inventory.txt");
		vend.pay(new Money(2, 0, 0, 0));
		Assert.assertEquals("$0.70", formatter.format(vend.buy("B02").getTotal()));
	}

	@Test
	public void testBuyCodeNotFoundException() throws IOException, NotEnoughPaidException, NotEnoughChangeException {
		VendingMachine vend = new VendingMachine(new Inventory(), new Money(10, 10, 10, 10));
		vend.getInventory().load("inventory.txt");
		try {
			vend.buy("B22");
			Assert.fail("CodeNotFoundException should be thrown here");
		} catch (CodeNotFoundException e) {
		}
	}

	@Test
	public void testBuyNotEnoughPaidException() throws IOException, CodeNotFoundException, NotEnoughChangeException {
		VendingMachine vend = new VendingMachine(new Inventory(), new Money(10, 10, 10, 10));
		vend.getInventory().load("inventory.txt");
		try {
			vend.pay(new Money(1, 0, 0, 1));
			vend.buy("B02");
			Assert.fail("NotEnoughPaidException should be thrown here");
		} catch (NotEnoughPaidException e) {
		}
	}

	@Test
	public void testBuyNotEnoughChangeException() throws IOException, CodeNotFoundException, NotEnoughPaidException {
		VendingMachine vend = new VendingMachine(new Inventory(), new Money(1, 1, 1, 1));
		vend.getInventory().load("inventory.txt");
		try {
			vend.pay(new Money(2, 0, 1, 1));
			vend.buy("B02");
			Assert.fail("NotEnoughChangeException should be thrown here");
		} catch (NotEnoughChangeException e) {
		}
	}

	@Test
	public void testBuyNotEnoughChangeException2() throws IOException, CodeNotFoundException, NotEnoughPaidException {
		VendingMachine vend = new VendingMachine(new Inventory(), new Money(10, 0, 0, 0));
		vend.getInventory().load("inventory.txt");
		try {
			vend.pay(new Money(10, 0, 0, 0));
			vend.buy("B02");
			Assert.fail("NotEnoughChangeException should be thrown here");
		} catch (NotEnoughChangeException e) {
		}
	}
}