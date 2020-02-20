package lisp.eval;

import lisp.exception.NumberRequiredException;



/**
 *
 * 組込み手続き : =
 * @author bp15046
 *
 */



public class Equal extends Subroutine {

	@Override
	SExpression apply(SExpression sexp) throws NumberRequiredException {
		// 引数がリストでない場合は例外を投げる
		if ( !(sexp instanceof ConsCell) ) {
			throw new NumberRequiredException();
		}
		
		ConsCell tmpSexp = (ConsCell)sexp;
		
		// 引数が一つしかない場合は例外を投げる
		if (tmpSexp.getCdr() instanceof EmptyList) {
			throw new NumberRequiredException();
		}

		// 比較用変数
		SExpression comp = tmpSexp.getCar();

		while (true) {		
			SExpression tmpSexpCar = tmpSexp.getCar();
			SExpression tmpSexpCdr = tmpSexp.getCdr();

			// 引数を評価したものがIntの場合
			if (tmpSexpCar instanceof Int) {
				// 比較用変数を評価したものがIntの場合
				if (comp instanceof Int) {
					// 比較用変数とtmpSexoCarの値が等しくない場合、#fを返す
					if ( !( ((Int)comp).getValue() == ((Int)tmpSexpCar).getValue() ) ) {
						return Bool.valueOf(false);
					}
				}
				// 比較用変数を評価したものがDblの場合
				else if (comp instanceof Dbl) {
					// 比較用変数とtmpSexoCarの値が等しくない場合、#fを返す
					if ( !( ((Dbl)comp).getValue() == (double)(((Int)tmpSexpCar).getValue()) ) ) {
						return Bool.valueOf(false);
					}
				}
				// 評価したものが数値でない場合は例外を投げる
				else {
					throw new NumberRequiredException();
				}
			}
			
			// 引数を評価したものがDblの場合
			else if (tmpSexpCar instanceof Dbl) {
				// 比較用変数を評価したものがIntの場合
				if (comp instanceof Int) {
					// 比較用変数とtmpSexoCarの値が等しくない場合、#fを返す
					if ( !( (double)(((Int)comp).getValue()) == ((Dbl)tmpSexpCar).getValue() ) ) {
						return Bool.valueOf(false);
					}
				}
				// 比較用変数を評価したものがDblの場合
				else if (comp instanceof Dbl) {
					// 比較用変数とtmpSexoCarの値が等しくない場合、#fを返す
					if ( (((Dbl)comp).getValue()).compareTo(((Dbl)tmpSexpCar).getValue()) != 0 )  {
						return Bool.valueOf(false);
					}
				}
				// 評価したものが数値でない場合は例外を投げる
				else {
					throw new NumberRequiredException();
				}
			}
			
			// 評価したものが数値でない場合は例外を投げる
			else {
				throw new NumberRequiredException();
			}
			

			// 引数がなくなったときループを終了
			if (tmpSexpCdr instanceof EmptyList) {
				break;
			}
			
			// 引数がまだある場合
			else {
				comp = tmpSexpCar;
				tmpSexp = (ConsCell)tmpSexpCdr;
			}
		
		}

		// ループを抜けた場合、引数が全て等しいので#tを返す
		return Bool.valueOf(true);
	}



	@Override
	public String toString() {
		return "#<subr =>";	
	}

}
