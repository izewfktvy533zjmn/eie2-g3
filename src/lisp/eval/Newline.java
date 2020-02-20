package lisp.eval;

import lisp.exception.ListRequiredException;



/**
 *
 * 組込み手続き newline
 * @author bp15046
 *
 */



public class Newline extends Subroutine {
	@Override
	SExpression apply(SExpression sexp) throws ListRequiredException {
		if ( !(sexp instanceof EmptyList) ) {
			throw new ListRequiredException();
		}

		System.out.println();

		return Undef.getInstance();
	}



	@Override
	public String toString() {
		return "#<subr newline>";
	}



}

