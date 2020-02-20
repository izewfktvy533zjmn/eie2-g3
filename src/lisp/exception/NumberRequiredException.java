package lisp.exception;



/**
 *
 * 数値が得られなかった場合の例外
 * @author bp15046
 *
 */



@SuppressWarnings("serial")
public class NumberRequiredException extends LispException{
	private static final String prefix = "Number Required Exception";
	public NumberRequiredException() {
		super(prefix);
	}
}
