package lisp.eval;

import lisp.exception.ListRequiredException;



/**
 *
 * 組込み手続き write
 * @author bp15046
 *
 */



public class Write extends Subroutine {
	@Override
	SExpression apply(SExpression sexp) throws ListRequiredException {
		//System.out.println("Write | sexp : " + sexp);
		
		if (sexp instanceof EmptyList) {
			System.out.print(" ");
			
			return Undef.getInstance();
		}
		
		if ( !(sexp instanceof ConsCell) ) {
			throw new ListRequiredException();
		}
		
		//System.out.print("\033[01;31m" + ((ConsCell)sexp).getCar() + "\033[0m");
		System.out.print(((ConsCell)sexp).getCar());

		return Undef.getInstance();
	}



	@Override
	public String toString() {
		return "#<subr write>";
	}



}

