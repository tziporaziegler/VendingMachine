package vendingmachine;

public class NotEnoughPaidException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotEnoughPaidException() {
		super("Not Enough Paid");
	}
}
