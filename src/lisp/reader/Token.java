package lisp.reader;



/**
  * 字句
  * @author tetsuya
  * @author bp15046
  *
  */



public class Token {
	public enum Kind {
	    NUMBER, // 数値
	    REALNUMBER, // 浮動小数点数値
		BOOLEAN, // 真理値
	    SYMBOL, // 記号
	    LEFTPAR, // 左括弧
	    RIGHTPAR, // 右括弧
	    DOT, // ドット
		QUOTE // クォート
	}

	private Kind kind;
	private int intValue;
	private double doubleValue;
	private boolean booleanValue;
	private String symbol;
	
	Token(Kind kind) {
		this.kind = kind;
	}
	Token(int value) {
		this.kind = Kind.NUMBER;
		this.intValue = value;
	}
	Token(double value) {
		this.kind = Kind.REALNUMBER;
		this.doubleValue = value;
	}
	Token(boolean value) {
		this.kind = Kind.BOOLEAN;
		this.booleanValue = value;
	}
	Token(String value) {
		this.kind = Kind.SYMBOL;
		this.symbol = value;
	}
	Kind getKind() {
		return this.kind;
	}
	int getIntValue() {
		return this.intValue;
	}
	double getDoubleValue() {
		return this.doubleValue;
	}
	boolean getBooleanValue() {
		return this.booleanValue;
	}
	String getSymbolValue() {
		return this.symbol;
	}
	@Override
	public String toString() {
		// 数値
		if (this.kind == Kind.NUMBER) {
			return "Token (Number, " + this.intValue + ")";
		}
		// 浮動小数点数値
		if (this.kind == Kind.REALNUMBER) {
			return "Token (RealNumber, " + this.doubleValue + ")";
		}
		// 真理値
		if (this.kind == Kind.BOOLEAN) {
			return "Token (Boolean, " + this.booleanValue + ")";
		}
		// 記号
		if (this.kind == Kind.SYMBOL) {
			return "Token (Symbol, " + this.symbol + ")";
		}
		// 左括弧
		if (this.kind == Kind.LEFTPAR) {
			return "Token (LeftPar)";
		}
		// 右括弧
		if (this.kind == Kind.RIGHTPAR) {
			return "Token (RightPar)";
		}
		// ドット
		if (this.kind == Kind.DOT) {
			return "Token (Dot)";
		}
		// クォート
		if (this.kind == Kind.QUOTE) {
			return "Token (QUOTE)";
		}
		return "Token (Unknown)";
	}
}
