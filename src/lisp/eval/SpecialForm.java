package lisp.eval;

import lisp.exception.LispException;



/**
 *
 * 特殊形式
 * @author tetsuya
 * @author bp15046
 *
 */



abstract class SpecialForm implements SExpression {
   abstract SExpression apply(SExpression sexp, Environment env) throws LispException;
}
