package lisp.eval;

import lisp.exception.LispException;



/**
 *
 * 組み込み手続き
 * @author tetsuya
 * @author bp15046 
 *
 */



abstract class Subroutine implements SExpression {
   abstract SExpression apply(SExpression sexp) throws LispException;
}
