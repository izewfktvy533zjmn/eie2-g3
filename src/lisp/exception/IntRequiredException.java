package lisp.exception;



/**
 *
 * Intオブジェクトが得られなかった場合の例外
 * @author bp15046
 *
 */



@SuppressWarnings("serial")
public class IntRequiredException extends LispException{
	private static final String prefix = "Int Required Exception";
	public IntRequiredException() {
		super(prefix);
	}
}
