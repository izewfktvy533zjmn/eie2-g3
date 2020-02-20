package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.ReadErrorException;



/**
 * 評価器
 * @author tetsuya
 * @author bp15046
 */



public class Evaluator {
	/**
	 * 引数の環境の下で引数のS式を評価する。
	 * @param sexp S式
	 * @param env 環境
	 * @return 評価値(S式)
	 */
	public static SExpression eval(SExpression sexp, Environment env) throws LispException, ReadErrorException {
		// 整数値
		if (sexp instanceof Int) {
			Int tmp = (Int)sexp;

			return tmp;
		}
		// 浮動小数点数値
		else if (sexp instanceof Dbl) {
       		Dbl tmp = (Dbl)sexp;

			return tmp;
		}
		// 真理値
		else if (sexp instanceof Bool) {
			Bool tmp = (Bool)sexp;

			return tmp;
		}
		// 空リスト
		else if (sexp instanceof EmptyList) {
			EmptyList tmp = (EmptyList)sexp;

			return tmp;
		}
		// 未定義値
		else if (sexp instanceof Undef) {
			Undef tmp = (Undef)sexp;

			return tmp;
		}
		// 記号
		else if (sexp instanceof Symbol) {
			Symbol tmpSymbol = (Symbol)sexp;
			
			// 環境に束縛されているか否か
			try {
            	SExpression tmpSexp = env.get(tmpSymbol);

            	// 束縛値が手続きの場合
            	if (tmpSexp instanceof Subroutine) {
               		Subroutine sub = (Subroutine) tmpSexp;
               		return sub;
            	}
            	
				// 束縛値が特殊形式の場合
            	else if (tmpSexp instanceof SpecialForm) {
               		SpecialForm spf = (SpecialForm)tmpSexp;
               		return spf;
            	}
				// それ以外はそのまま返す
            	else {
               		return tmpSexp;
            	}
			} catch (LispException e) {
            	throw e;
         	}
		}
		// S式
		else if (sexp instanceof ConsCell) {
			//System.out.println("Evaluator | SExpression : " + sexp);
			
			ConsCell tmp = (ConsCell)sexp;
			// carとcdrに分ける
			SExpression car = tmp.getCar();
			SExpression cdr = tmp.getCdr();

			//System.out.println("Evaluator | car : " + car);
			//System.out.println("Evaluator | cdr : " + cdr);
			
			
			// carを評価
			SExpression tmpCar = eval(car, env);
			
			//System.out.println("Evaluator | tmpCar : " + tmpCar);
			
			// carが手続きの場合、cdrを評価してからその評価結果を手続きの引数にする
			if (tmpCar instanceof Subroutine) {
				Subroutine sub = (Subroutine)tmpCar;
				
				/* 下記よりcdrの個々の要素を評価する */
				
				SExpression tmpCdr = cdr;
				SExpression evaledCdr;
				SExpression element;
				SExpression evaled_element;
				SExpression end_list;
				SExpression tmp_list;
				
				
				// 第一要素がConsCellではない場合
				if ( !(tmpCdr instanceof ConsCell) ) {
					evaledCdr = eval(tmpCdr, env);
					//System.out.println("Evaluator | evaledCdr : " + evaledCdr);

					try {
						return sub.apply(evaledCdr);
					} catch (LispException e) {
						throw e;
					}
					
				}
				// 第一要素がConsCellの場合
				else {	
					element = ((ConsCell)tmpCdr).getCar();
					tmpCdr = ((ConsCell)tmpCdr).getCdr();

					//System.out.println("Evaluator | element : " + element);

					evaled_element = eval(element, env);
					
					//System.out.println("Evaluator | evaled element : " + evaled_element);
				}

				
				evaledCdr = ConsCell.getInstance(evaled_element, EmptyList.getInstance());
				end_list = evaledCdr;


				// 第二要素以降はループ内で処理
				
				while(true) {
					if (tmpCdr instanceof EmptyList) {
						break;
					}

					if ( !(tmpCdr instanceof ConsCell) ) {
						evaled_element = eval(tmpCdr, env);

				
						//System.out.println("Evaluator | evaled element : " + evaled_element);


						((ConsCell)end_list).setCdr(ConsCell.getInstance(evaled_element, EmptyList.getInstance()));
						

						break;
					}
					else {
						element = ((ConsCell)tmpCdr).getCar();
						tmpCdr = ((ConsCell)tmpCdr).getCdr();

					
						//System.out.println("Evaluator | element : " + element);

						
						evaled_element = eval(element, env);

					
						//System.out.println("Evaluator | evaled element : " + evaled_element);


						tmp_list = ConsCell.getInstance(evaled_element, EmptyList.getInstance());
						((ConsCell)end_list).setCdr(tmp_list);
						end_list = tmp_list;
					}
				}


				//System.out.println("Evaluator | evaledCdr : " + evaledCdr);


				try {
					return sub.apply(evaledCdr);
				} catch (LispException e) {
					throw e;
				}
			}
			// carが特殊形式の場合、cdrを評価せづに特殊形式の引数にする
			else if (tmpCar instanceof SpecialForm){
				SExpression tmpCdr = cdr;
				SpecialForm spf = (SpecialForm)tmpCar;
				
				try {
					return spf.apply(tmpCdr, env);
				} catch (LispException e) {
					throw e;
				}
			}
			// tmpCarがクロージャーの場合
			else if (tmpCar instanceof Closure) {
				//System.out.println("Evaluator | Closure");
				Closure closure = (Closure)tmpCar;
			
				/* 下記よりcdrの個々の要素を評価する */
				
				SExpression tmpCdr = cdr;
				SExpression evaledCdr;
				SExpression element;
				SExpression evaled_element;
				SExpression end_list;
				SExpression tmp_list;
				
				
				// 第一要素がConsCellではない場合
				if ( !(tmpCdr instanceof ConsCell) ) {
					evaledCdr = eval(tmpCdr, env);
					//System.out.println("Evaluator | evaledCdr : " + evaledCdr);

					try {
						return closure.apply(evaledCdr);
					} catch (LispException e) {
						throw e;
					}
					
				}
				// 第一要素がConsCellの場合
				else {	
					element = ((ConsCell)tmpCdr).getCar();
					tmpCdr = ((ConsCell)tmpCdr).getCdr();

					//System.out.println("Evaluator | element : " + element);

					evaled_element = eval(element, env);
					
					//System.out.println("Evaluator | evaled element : " + evaled_element);
				}

				
				evaledCdr = ConsCell.getInstance(evaled_element, EmptyList.getInstance());
				end_list = evaledCdr;


				// 第二要素以降はループ内で処理
				
				while(true) {
					if (tmpCdr instanceof EmptyList) {
						break;
					}

					if ( !(tmpCdr instanceof ConsCell) ) {
						evaled_element = eval(tmpCdr, env);

				
						//System.out.println("Evaluator | evaled element : " + evaled_element);


						((ConsCell)end_list).setCdr(ConsCell.getInstance(evaled_element, EmptyList.getInstance()));
						

						break;
					}
					else {
						element = ((ConsCell)tmpCdr).getCar();
						tmpCdr = ((ConsCell)tmpCdr).getCdr();

					
						//System.out.println("Evaluator | element : " + element);

						
						evaled_element = eval(element, env);

					
						//System.out.println("Evaluator | evaled element : " + evaled_element);


						tmp_list = ConsCell.getInstance(evaled_element, EmptyList.getInstance());
						((ConsCell)end_list).setCdr(tmp_list);
						end_list = tmp_list;
					}
				}


				//System.out.println("Evaluator | evaledCdr : " + evaledCdr);


				try {
					return closure.apply(evaledCdr);
				} catch (LispException e) {
					throw e;
				}
			}
			// carがそれ以外の場合
			else {

				throw new ReadErrorException();
			}
		}
		else {
			throw new ReadErrorException();
		}
	}

}

