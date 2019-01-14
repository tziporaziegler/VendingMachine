package vendingmachine;

import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Test;

public class MoneyTest {

	@Test
	public void testGetTotal() {
		DecimalFormat formatter = new DecimalFormat("$#,##0.00");
		Money money = new Money(1, 1, 1, 1);
		Assert.assertEquals("$1.40", formatter.format(money.getTotal()));
		money = new Money(2, 2, 2, 2);
		Assert.assertEquals("$2.80", formatter.format(money.getTotal()));
	}

	@Test
	public void testRemove() throws NotEnoughChangeException {
		Money money = new Money(10, 10, 10, 10);
		money.remove(.2);
		Assert.assertEquals(8, money.getNumDimes(), 0.0);
		money.remove(.25);
		Assert.assertEquals(9, money.getNumQuarters(), 0.0);
	}

	@Test
	public void testRemoveNickel() throws NotEnoughChangeException {
		Money money = new Money(10, 10, 10, 10);
		money.remove(.3);
		Assert.assertEquals(9, money.getNumQuarters(), 0.0);
		Assert.assertEquals(9, money.getNumNickles(), 0.0);
	}

	@Test
	public void testAdd() {
		DecimalFormat formatter = new DecimalFormat("$#,##0.00");
		Money money = new Money();
		money.add(new Money(10, 10, 10, 10));
		Assert.assertEquals("$14.00", formatter.format(money.getTotal()));
		money = new Money();
		Assert.assertEquals(0, money.getTotal(), 0);
	}

	@Test
	public void testRemoveThrowsNotEnoughChangeException() {
		Money money = new Money(0, 0, 0, 0);
		try {
			money.remove(1.00);
			// fail if reach point because means exception not thrown
			Assert.fail("NotENoughChangeException should be thrown here");
		} catch (NotEnoughChangeException e) {
			e.printStackTrace();
		}
	}
}