package lisp.eval;

import lisp.exception.PairRequiredException;




/**
 * 組込み手続き : car
 * @author bp15046
 */



public class Car extends Subroutine {
	@Override
	SExpression apply(SExpression sexp) throws PairRequiredException {
		if (sexp instanceof ConsCell) {
			ConsCell tmp = (ConsCell)sexp;
			
			if (tmp.getCdr() instanceof EmptyList) {
				SExpression tmpSexp = tmp.getCar();
				
				if (!(tmpSexp instanceof ConsCell)) {
					throw new PairRequiredException();
				}
				else {
					return ((ConsCell)tmpSexp).getCar();
				}
			}
			else {
				throw new PairRequiredException();
			}
		}
		else {
			throw new PairRequiredException();
		}
	}
	
	

	@Override 
	public String toString() {
		return "#<subr car>";
	}
}
