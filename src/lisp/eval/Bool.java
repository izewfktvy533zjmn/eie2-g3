package lisp.eval;

/**
 * 真理値
 * @author tetsuya
 *
 */
public class Bool implements SExpression {
	private static final Bool TRUE = new Bool(true);
	private static final Bool FALSE = new Bool(false);

	private boolean value;

	public boolean isValid() {
		return value;
	}
	
	public static Bool valueOf(boolean value) {
		if (value == true) {
			return TRUE;
		}
		return FALSE;
	}
	
	private Bool(boolean value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		if (this.value == true) {
			return "#t";
		}
		return "#f";
	}
}
