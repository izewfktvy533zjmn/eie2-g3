package lisp.eval;

import lisp.exception.DblRequiredException;



/**
 * 組込み手続き : cos
 * @author bp15043
 * @author bp15046
 */



public class Cos extends Subroutine {
	@Override
	SExpression apply(SExpression sexp) throws DblRequiredException {
		
		// 引数sexpはConsCell
		if (sexp instanceof ConsCell) {

			// 引数sexpのcdrはEmptyList
			if (((ConsCell)sexp).getCdr() instanceof EmptyList) {

				// 引数sexpのcarがDbl
				if (((ConsCell)sexp).getCar() instanceof Dbl) {
					double tmp = ((Dbl)(((ConsCell)sexp).getCar())).getValue();
					tmp = Math.cos(tmp);
					return Dbl.valueOf(tmp);
				}
				/*
				else if (((ConsCell)sexp).getCar() instanceof Int) {
				    double tmp = (double)((Int)(((ConsCell)sexp).getCar())).getValue();
					tmp = Math.cos(tmp);
					return Dbl.valueOf(tmp);
				}
				*/
				else {
					throw new DblRequiredException();
				}
			}
			// 引数sexpのcdrがEmptyListでない
			else {
				throw new DblRequiredException();
			}
		}
		// sexpがConsCellでない
		else {
			throw new DblRequiredException();
		}

	}
	


	@Override 
	public String toString() {
		return "#<subr cos>";
	}

}

