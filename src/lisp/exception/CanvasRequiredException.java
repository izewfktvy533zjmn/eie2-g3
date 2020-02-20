package lisp.exception;



/**
 *
 * Fundオブジェクトが得られなかった場合の例外
 * @author bp15046
 *
 */



@SuppressWarnings("serial")
public class CanvasRequiredException extends LispException{
	private static final String prefix = "Canvas Required Exception";
	public CanvasRequiredException() {
		super(prefix);
	}
}
