package lisp.eval;

/**
 * 浮動小数点値
 * @author tetsuya
 *
 */
public class Dbl implements SExpression {
	private Double value;

	public Double getValue() {
		return value;
	}

	public static Dbl valueOf(Double value) {
		return new Dbl(value);
	}
	
	private Dbl(Double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value.toString();
	}
	
	@Override
	public int hashCode() {
		return this.value.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if ((obj instanceof Int) == false) {
			return false;
		}
		return this.value.equals(((Dbl)obj).getValue());
	}
}
