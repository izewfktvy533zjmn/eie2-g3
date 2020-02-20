package lisp.eval;

import java.util.HashMap;
import java.util.Map;

import lisp.exception.InvalidApplicationException;


/**
 * 環境
 * @author tetsuya
 * @author bp15046
 */
public class Environment {
   private final Map<Symbol, SExpression> frame = new HashMap<>();
   private final Environment parent;


   public Environment(Environment parent) {
      this.parent = parent;
   }


   private static Environment find(Environment env, Symbol symbol) throws InvalidApplicationException {
      for (Environment e = env; e != null; e = e.parent) {
         if (e.frame.containsKey(symbol)) {
            return e;
         }
      }
      throw new InvalidApplicationException();
   }


   public SExpression get(Symbol symbol) throws InvalidApplicationException {
      try {
         return find(this, symbol).frame.get(symbol);
      } catch (InvalidApplicationException e) {
         throw e;
      }
   }


   public SExpression set(Symbol symbol, SExpression sexp) throws InvalidApplicationException {
      try {
         find(this, symbol).frame.put(symbol, sexp);
         return (SExpression)symbol;
      } catch (InvalidApplicationException e) {
         throw e;
      }
   }


   public SExpression define(Symbol symbol, SExpression sexp) {
      frame.put(symbol, sexp);
      return (SExpression)symbol;
   }


}
