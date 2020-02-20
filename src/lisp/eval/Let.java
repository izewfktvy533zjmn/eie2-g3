package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.ListRequiredException;
import lisp.exception.WrongNumberOfArgumentsException;



/**
 *
 * 特殊形式 let
 * @author bp15046
 *
 */



public class Let extends SpecialForm {

	@Override
	SExpression apply(SExpression sexp, Environment env) throws ListRequiredException, WrongNumberOfArgumentsException, LispException {


		
		//System.out.println("Let | sexp : " + sexp);


		
		// 引数がConsCellではない場合、例外を投げる
		if ( !(sexp instanceof ConsCell) ) {
			throw new WrongNumberOfArgumentsException();
		}

		// 第一引数を取得
		SExpression arg1 = ((ConsCell)sexp).getCar();



		//System.out.println("Let | arg1 : " + arg1);


		
		// 第一引数がConsCellではない場合、例外を投げる
		if ( !(arg1 instanceof ConsCell) ) {
			throw new ListRequiredException();
		}

		// 第二引数を取得
		SExpression arg2 = ((ConsCell)sexp).getCdr();


		
		//System.out.println("Let | arg2 : " + arg2);


		
		// 第二引数たConsCellではない場合、例外を投げる
		if ( !(arg2 instanceof ConsCell) ) {
			throw new WrongNumberOfArgumentsException();
		}


		/*
		 * letをlambdaに書き直す
		 */

		
		// 記号"lambda"を作成
		Symbol symbol_lambda = Symbol.getInstance("lambda");
		
		
		/*  仮引数と実引数を作成 */
		

		// 第一引数の第一要素を取得
		SExpression tmp = ((ConsCell)arg1).getCar();


		
		//System.out.println("Let | tmp : " + tmp);


		
		// 第一引数の第一要素がConsCellではない場合、例外を投げる
		if ( !(tmp instanceof ConsCell) ) {
			throw new ListRequiredException();
		}

		// 第一引数の第一要素の右側を取得
		SExpression tmp_right = ((ConsCell)tmp).getCar();
		// 第一引数の第一要素の左側を取得
		SExpression tmp_left =  ((ConsCell)tmp).getCdr();



		//System.out.println("Let | tmp_right : " + tmp_right);
		//System.out.println("Let | tmp_left  : " + tmp_left);


		
		// 第一引数の第一要素の左側がConsCellではない場合、例外を投げる
		if ( !(tmp_left instanceof ConsCell) ) {
			throw new ListRequiredException();
		}
		
		
		// 仮引数の作成
		SExpression dummyArgs = ConsCell.getInstance(tmp_right, EmptyList.getInstance());
		// 実引数の作成
		SExpression realArgs = tmp_left;



		//System.out.println("Let | dummyArgs : " + dummyArgs);
		//System.out.println("Let | realArgs  : " + realArgs);


		
		// 仮引数の終端を取得	
		SExpression end_dummyArgs = dummyArgs;
		// 実引数の終端を取得
		SExpression end_realArgs = realArgs;
		
		
		
		//System.out.println("Let | end_dummyArgs : " + end_dummyArgs);
		//System.out.println("Let | end_realArgs  : " + end_realArgs);
		

		
		// 第一引数の更新
		arg1 = ((ConsCell)arg1).getCdr() ;
		
		
		
		//System.out.println("Let | arg1 : " + arg1);



		//第二要素以降はループ内で処理する
		while(true) {
			// 第一引数がEmptyListの場合、ループを抜ける
			if (arg1 instanceof EmptyList) {
				break;
			}
			
			// 第一引数の第*要素を取得
			tmp = ((ConsCell)arg1).getCar();
			

			
			//System.out.println("Let | tmp : " + tmp);


			
			// 第一引数の第*要素がConsCellではない場合、例外を投げる
			if ( !(tmp instanceof ConsCell) ) {
				throw new ListRequiredException();
			}

			// 第一引数の第*要素の右側を取得
			tmp_right = ((ConsCell)tmp).getCar();
			// 第一引数の第*要素の左側を取得
			tmp_left =  ((ConsCell)tmp).getCdr();



			//System.out.println("Let | tmp_right : " + tmp_right);
			//System.out.println("Let | tmp_left  : " + tmp_left);

		
		
			// 第一引数の第*要素の左側がConsCellではない場合、例外を投げる
			if ( !(tmp_left instanceof ConsCell) ) {
				throw new ListRequiredException();
			}
		
			// 第一引数の第*要素の左側(ConsCell)のcdrがEmptyListではない場合、例外を投げる
			if ( !(((ConsCell)tmp_left).getCdr() instanceof EmptyList) ) {
				throw new ListRequiredException();
			}
			
			// 仮引数の作成
			SExpression dummy_arg = ConsCell.getInstance(tmp_right, EmptyList.getInstance());
			// 実引数の作成
			SExpression real_arg = tmp_left;
			
			
			
			//System.out.println("Let | dummy_arg : " + dummy_arg);
			//System.out.println("Let | real_arg  : " + real_arg);
			
			
			
			// 仮引数の追加
			((ConsCell)end_dummyArgs).setCdr(dummy_arg);
			// 実引数の追加
			((ConsCell)end_realArgs).setCdr(real_arg);
		
			// 仮引数の終端を取得	
			end_dummyArgs = dummy_arg;
			// 実引数の終端を取得
			end_realArgs = real_arg;
			
			
			
			//System.out.println("Let | end_dummyArgs : " + end_dummyArgs);
			//System.out.println("Let | end_realArgs  : " + end_realArgs);
			

			
			//System.out.println("Let | dummyArgs : " + dummyArgs);
			//System.out.println("Let | realArgs  : " + realArgs);



			// 第一引数の更新
			arg1 = ((ConsCell)arg1).getCdr();
			
			

			//System.out.println("Let | arg1 : " + arg1);

			

		}

		// 本体の作成
		SExpression body = arg2;



		//System.out.println("Let | dummyArgs : " + dummyArgs);
		//System.out.println("Let | realArgs  : " + realArgs);
		//System.out.println("Let | body      : " + body); 

		

		// lambda式の作成
		SExpression lambda_sexp = ConsCell.getInstance(ConsCell.getInstance(symbol_lambda, ConsCell.getInstance(dummyArgs, body)), realArgs);

		// lambda式の評価を返す
		try {
			return Evaluator.eval(lambda_sexp, env);
		} catch (LispException e) {
			throw e;
		}


	}



	@Override
	public String toString() {
		return "#<syntax let>";
	}



}
