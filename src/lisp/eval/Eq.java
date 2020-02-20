package lisp.eval;

import lisp.exception.WrongNumberOfArgumentsException;



/**
 *
 * 組込み手続き eq
 * @author bp15046
 *
 */



public class Eq extends Subroutine {
	@Override
	SExpression apply(SExpression sexp) throws WrongNumberOfArgumentsException {
		
		// 引数がConsCellではない場合は例外を投げる
		if ( !(sexp instanceof ConsCell) ) {
			throw new WrongNumberOfArgumentsException();
		}
		
		// 第一引数と第二引数以降に分ける
		SExpression arg1 = ((ConsCell)sexp).getCar();
		SExpression arg2 = ((ConsCell)sexp).getCdr();
		
		// ドット対に場合は例外を投げる
		if ( !(arg2 instanceof ConsCell) ) {
			throw new WrongNumberOfArgumentsException();
		}
		
		// 第三引数以降がある場合は例外を投げる
		if ( !(((ConsCell)arg2).getCdr() instanceof EmptyList) ) {
			throw new WrongNumberOfArgumentsException();
		}
		
		// 第二引数を取得
		arg2 = ((ConsCell)arg2).getCar();


		//System.out.println("Eq | arg1 : " + arg1);
		//System.out.println("Eq | arg2 : " + arg2);


		if (arg1.equals(arg2)) {
			return Bool.valueOf(true);
		}
		else {
			return Bool.valueOf(false);	
		}

	}



	@Override
	public String toString() {
		return "#<subr eq?>";
	}



}

