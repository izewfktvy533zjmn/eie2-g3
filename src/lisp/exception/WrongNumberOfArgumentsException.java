package lisp.exception;



/**
 *
 * 引数の個数が間違っている場合の例外
 * @author bp15046
 *
 */



@SuppressWarnings("serial")
public class WrongNumberOfArgumentsException extends LispException {
	private static final String prefix = "Wrong Number Of Arguments Exception";
	public WrongNumberOfArgumentsException() {
		super(prefix);
	}
}
