package lisp.exception;



/**
 *
 * 手続きが得られなかった場合の例外
 * @author bp15046
 *
 */



@SuppressWarnings("serial")
public class InvalidApplicationException extends LispException {
	private static final String prefix = "Invalid Application Exception";
	public InvalidApplicationException() {
		super(prefix);
	}
}
