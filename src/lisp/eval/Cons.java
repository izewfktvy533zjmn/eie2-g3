package lisp.eval;



import lisp.exception.WrongNumberOfArgumentsException;



/**
 * 組込み手続き : cons
 * @author bp15046
 */



public class Cons extends Subroutine {
	@Override
	SExpression apply(SExpression sexp) throws WrongNumberOfArgumentsException {
		// 引数sexpがConsCellである場合
		if (sexp instanceof ConsCell) {
			ConsCell tmp = (ConsCell)sexp;

			SExpression tmpCar1 = tmp.getCar();
			SExpression tmpCdr1 = tmp.getCdr();

			// 引数sexpのcdrがConsCellである場合
			if (tmpCdr1 instanceof ConsCell) {
				SExpression tmpCar2 = ((ConsCell)tmpCdr1).getCar();
				SExpression tmpCdr2 = ((ConsCell)tmpCdr1).getCdr();

				// 引数sexpのcdrのcdrがEmptyListである場合
				if (tmpCdr2 instanceof EmptyList) {
					return ConsCell.getInstance(tmpCar1, tmpCar2);
				}
				// 引数sexpのcdrのcdrがEmptyListでない場合
				else {
					throw new WrongNumberOfArgumentsException();
				}
			}
			// 引数sexpのcdrがConsCellでない場合
			else {
				throw new WrongNumberOfArgumentsException();
			}

		}
		// 引数sexpがConsCellでない場合
		else {
			throw new WrongNumberOfArgumentsException();
		}
	}



	@Override
	public String toString() {
		return "#<sub cons>";
	}



}
