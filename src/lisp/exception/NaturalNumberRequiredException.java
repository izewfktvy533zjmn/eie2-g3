package lisp.exception;



/**
 *
 * 自然数の値が得られなかった場合の例外
 * @author bp15046
 *
 */



@SuppressWarnings("serial")
public class NaturalNumberRequiredException extends LispException{
	private static final String prefix = "Natural Number Required Exception";
	public NaturalNumberRequiredException() {
		super(prefix);
	}
}
