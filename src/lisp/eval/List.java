package lisp.eval;

import lisp.exception.SyntaxErrorException;

/**
 *組み込み手続き : list
 * @author bp15043
 */

public class List extends Subroutine {

@Override
    SExpression apply(SExpression sexp) throws SyntaxErrorException {
    if (sexp instanceof ConsCell) {
	return (ConsCell)sexp;
    }
    else {
	throw new SyntaxErrorException();
    }
}

@Override 
    public String toString() {
    return "#<subr list>";
}

}
			 
