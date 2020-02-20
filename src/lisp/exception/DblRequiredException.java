package lisp.exception;



/**
 *
 * Dblオブジェクトが得られなかった場合の例外
 * @author bp15046
 *
 */



@SuppressWarnings("serial")
public class DblRequiredException extends LispException{
	private static final String prefix = "Dbl Required Exception";
	public DblRequiredException() {
		super(prefix);
	}
}
