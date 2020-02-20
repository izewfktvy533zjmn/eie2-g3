package lisp.eval;



import lisp.exception.DblRequiredException;



/**
 * 組込み手続き : sin
 * @author bp15046
 */



public class Sin extends Subroutine {
	@Override
	SExpression apply(SExpression sexp) throws DblRequiredException {
		
		// 引数sexpがConsCellである場合
		if (sexp instanceof ConsCell) {

			// 引数sexpのcdrがEmptyListである場合
			if (((ConsCell)sexp).getCdr() instanceof EmptyList) {

				// 引数sexpのcarがDblである場合
				if (((ConsCell)sexp).getCar() instanceof Dbl) {
					double tmp = ((Dbl)(((ConsCell)sexp).getCar())).getValue();
					tmp = Math.sin(tmp);

					return Dbl.valueOf(tmp);
				}
				else {
					throw new DblRequiredException();
				}
			}
			// 引数sexpのcdrがEmptyListでない場合
			else {
				throw new DblRequiredException();
			}
		}
		// sexpがConsCellでない場合
		else {
			throw new DblRequiredException();
		}

	}
	
	@Override 
	public String toString() {
		return "#<subr sin>";
	}
}
