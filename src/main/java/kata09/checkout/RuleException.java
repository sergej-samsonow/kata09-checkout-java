package kata09.checkout;

public class RuleException extends Exception {

	private static final long serialVersionUID = 1L;

	public RuleException(Exception e) {
		super(e);
	}

	public RuleException(String message) {
		super(message);
	}

}
