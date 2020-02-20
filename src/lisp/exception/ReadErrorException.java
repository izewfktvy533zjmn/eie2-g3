package lisp.exception;



/**
 *
 * 読み込めず評価できない場合の例外
 * @author bp15046
 *
 */



@SuppressWarnings("serial")
public class ReadErrorException extends LispException {
	private static final String prefix = "Read Error Exception";
	public ReadErrorException() {
		super(prefix);
	}
}
