package lisp.exception;



/**
 *
 * 0除算の場合の例外
 * @author bp15030
 *
 */


@SuppressWarnings("serial")
public class DivisionException extends LispException{
	private static final String prefix = "Division Exception";
	public DivisionException() {
		super(prefix);
	}
}



