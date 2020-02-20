package lisp.exception;

/**
 * 入力が尽きたことを表す例外
 * @author tetsuya
 *
 */
@SuppressWarnings("serial")
public class EndOfFileException extends LispException {
	public EndOfFileException() {
		super("");
	}
	public EndOfFileException(String msg) {
		super(msg);
	}
}
