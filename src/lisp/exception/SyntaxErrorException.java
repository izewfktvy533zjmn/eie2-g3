package lisp.exception;

/**
 * 構文上の誤りを表す例外
 * @author tetsuya
 * @author bp15030
 */
@SuppressWarnings("serial")
public class SyntaxErrorException extends LispException {
   private static final String prefix = "Syntax Error Exception";
   public SyntaxErrorException() {
      super(prefix);
   }
}
