package lisp.exception;



/**
 *
 * 組込み手続きexitが呼ばれたときの例外
 * @author bp15046
 *
 */



@SuppressWarnings("serial")
public class ExitException extends LispException{
	private static final String prefix = "";
	public ExitException() {
		super(prefix);
	}
}
