package vendingmachine;

public class Money {

	private int numDollars;
	private int numQuarters;
	private int numDimes;
	private int numNickles;

	public Money() {
		numDollars = 0;
		numQuarters = 0;
		numDimes = 0;
		numNickles = 0;
	}

	public Money(int numDollars, int numQuarters, int numDimes, int numNickels) {
		this.numDollars = numDollars;
		this.numQuarters = numQuarters;
		this.numDimes = numDimes;
		this.numNickles = numNickels;
	}

	public void add(Money money) {
		numDollars = numDollars + money.getNumDollars();
		numQuarters = numQuarters + money.getNumQuarters();
		numDimes = numDimes + money.getNumDimes();
		numNickles = numNickles + money.getNumNickles();
	}

	public Money remove(double amount) throws NotEnoughChangeException {
		Money change = new Money();
		double aamount = round(amount, 2);
		while (aamount >= .05) {
			while (aamount >= 1 && getNumDollars() > 0) {
				this.setNumDollars(getNumDollars() - 1);
				change.setNumDollars(change.getNumDollars() + 1);
				aamount = round(aamount -= 1, 2);
			}
			while (aamount >= .25 && getNumQuarters() > 0) {
				this.setNumQuarters(getNumQuarters() - 1);
				change.setNumQuarters(change.getNumQuarters() + 1);
				aamount = round(aamount -= .25, 2);
			}
			while (aamount >= .1 && getNumDimes() > 0) {
				this.setNumDimes(getNumDimes() - 1);
				change.setNumDimes(change.getNumDimes() + 1);
				aamount = round(aamount -= .1, 2);
			}
			while (aamount >= .05) {
				if (getNumNickles() == 0) {
					throw new NotEnoughChangeException();
				}
				this.setNumNickles(getNumNickles() - 1);
				change.setNumNickles(change.getNumNickles() + 1);
				aamount = round(aamount -= .05, 2);
			}
		}
		return change;
	}

	public double getTotal() {
		double total = 0.0;
		for (int i = 0; i < getNumDollars(); i++) {
			total += 1;
		}
		for (int i = 0; i < getNumQuarters(); i++) {
			total += .25;
		}
		for (int i = 0; i < getNumDimes(); i++) {
			total += .1;
		}
		for (int i = 0; i < getNumNickles(); i++) {
			total += .05;
		}
		return total;
	}

	public int getNumDollars() {
		return numDollars;
	}

	public void setNumDollars(int numDollars) {
		this.numDollars = numDollars;
	}

	public int getNumQuarters() {
		return numQuarters;
	}

	public void setNumQuarters(int numQuarters) {
		this.numQuarters = numQuarters;
	}

	public int getNumNickles() {
		return numNickles;
	}

	public void setNumNickles(int numnickels) {
		this.numNickles = numnickels;
	}

	public int getNumDimes() {
		return numDimes;
	}

	public void setNumDimes(int numDimes) {
		this.numDimes = numDimes;
	}

	// help counteract the inexactness of doubles by
	// rounding to the amount of place values after the decimal that enter
	public double round(double value, int places) {
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}
