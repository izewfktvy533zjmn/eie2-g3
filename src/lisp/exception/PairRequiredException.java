package lisp.exception;



/**
 *
 * ConsCellオブジェクトでドット対が得られなかった場合の例外
 * @author bp15046
 *
 */



@SuppressWarnings("serial")
public class PairRequiredException extends LispException{
	private static final String prefix = "Pair Required Exception";
	public PairRequiredException() {
		super(prefix);
	}
}
