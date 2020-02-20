package lisp.eval;

import lisp.exception.WrongNumberOfArgumentsException;
import lisp.exception.NaturalNumberRequiredException;
import lisp.exception.NumberRequiredException;



/**
 *
 * 組込み手続き sqrt
 * @author bp15046
 *
 */



public class Sqrt extends Subroutine {
	@Override
	SExpression apply(SExpression sexp) throws WrongNumberOfArgumentsException, NaturalNumberRequiredException, NumberRequiredException {
		// 引数がConsCellでない場合、例外を投げる
		if ( !(sexp instanceof ConsCell) ){
			throw new WrongNumberOfArgumentsException();
		}

		/*
		 * 以下、引数がConsCell
		 */

		// 引数のcdrがEmptyListでない場合、例外を投げる
		if ( !( ((ConsCell)sexp).getCdr() instanceof EmptyList) ) {
			throw new WrongNumberOfArgumentsException();
		}

		/*
		 * 引数を受け取り、平方根を計算して返す
		 */

		// 引数がInt
		if ( ((ConsCell)sexp).getCar() instanceof Int ) {
			Int tmpInt = (Int)((ConsCell)sexp).getCar();
			int tmp_int = tmpInt.getValue();

			if (tmp_int < 0) {
				throw new NaturalNumberRequiredException();
			}

			return Dbl.valueOf(Math.sqrt(tmp_int));
		}
		// 引数がDbl
		else if ( ((ConsCell)sexp).getCar() instanceof Dbl ) {
			Dbl tmpDbl = (Dbl)((ConsCell)sexp).getCar();
			double tmp_double = tmpDbl.getValue();
			
			if (tmp_double < 0) {
				throw new NaturalNumberRequiredException();
			}

			return Dbl.valueOf(Math.sqrt(tmp_double));
		}
		// 引数が上記以外の場合、例外を投げる
		else {
			throw new NumberRequiredException();
		}

	}



	@Override
	public String toString() {
		return "#<subr sqrt>";
	}



}




