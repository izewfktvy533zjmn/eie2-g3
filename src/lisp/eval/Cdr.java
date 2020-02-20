package lisp.eval;

import lisp.exception.PairRequiredException;



/**
 * 組込み手続き : cdr
 * @author bp15046
 */



public class Cdr extends Subroutine {
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
					return ((ConsCell)tmpSexp).getCdr();
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
		return "#<subr cdr>";
	}
}
