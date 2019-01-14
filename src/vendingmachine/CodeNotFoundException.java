package vendingmachine;

public class CodeNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public CodeNotFoundException() {
		super("Code Not Found");
	}
}
