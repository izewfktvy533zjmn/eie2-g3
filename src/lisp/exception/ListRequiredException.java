package lisp.exception;



/**
 *
 * ConsCellオブジェクトでリストが得られなかった場合の例外
 * @author bp15046
 *
 */



@SuppressWarnings("serial")
public class ListRequiredException extends LispException{
	private static final String prefix = "List Required Exception";
	public ListRequiredException() {
		super(prefix);
	}
}
