package lisp.exception;

@SuppressWarnings("serial")
public class UnboundVariableException extends LispException {
	private static final String prefix = "Unbound Variable Exception";
	public UnboundVariableException() {
		super(prefix);
	}

}
