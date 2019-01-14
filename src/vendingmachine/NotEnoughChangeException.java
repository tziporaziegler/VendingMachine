package vendingmachine;

public class NotEnoughChangeException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotEnoughChangeException() {
		super("Not Enough Change");
	}
}
