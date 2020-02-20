package lisp.eval;

/**
 * 空リスト
 * @author tetsuya
 *
 */
public class EmptyList implements SExpression {
	private static final EmptyList emptyList = new EmptyList();

	public static EmptyList getInstance() {
		return emptyList;
	}

	private EmptyList() {
	}

	@Override
	public String toString() {
		return "()";
	}
}
