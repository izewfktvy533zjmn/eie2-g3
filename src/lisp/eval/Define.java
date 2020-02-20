package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;



/**
 * 特殊形式 : define
 * @author bp15030
 * @author bp15046
 */



public class Define extends SpecialForm {
   
   @Override
   SExpression apply(SExpression sexp, Environment env) throws SyntaxErrorException, LispException {
	
	   if(sexp instanceof ConsCell) {
		   //System.out.println("Define | sexp is ConsCell");
		   //System.out.println("Define | sexp : " + sexp);
		   
		   ConsCell tmp = (ConsCell)sexp;
		   
		   // 特殊形式defineの引数をcarとcdrに分ける
		   SExpression tmpCar = tmp.getCar();
		   SExpression tmpCdr = tmp.getCdr();
		   
		   // 第一引数(tmpCar)が記号の場合
		   if(tmpCar instanceof Symbol) {
			   //System.out.println("Define | tmpCar is Symbol : " + tmpCar);
			   
			   // 第一引数(tmpCar)を記号に置き換える
			   Symbol tmp_car = (Symbol)tmpCar;
			   
			   //System.out.println("Define | tmpCdr : " + tmpCdr);
			   
			   // 第二引数(tmpCdr)を評価し、第二引数を第一引数に束縛した結果を返す
			   try {
				   
				   if ( !((((ConsCell)tmpCdr).getCdr()) instanceof EmptyList) ) {
					   throw new SyntaxErrorException();
				   }
				   
				   tmpCdr = ((ConsCell)tmpCdr).getCar();
				   
				   //System.out.println("Define | tmpCdr : " + tmpCdr);
				   
				   SExpression evaled_tmpCdr = Evaluator.eval(tmpCdr, env);
				   
				   //System.out.println("Define | evaled_tmpCdr : " + evaled_tmpCdr);
				   

				   return env.define(tmp_car, evaled_tmpCdr);
			   
			   } catch (LispException e) {
				   throw e;
			   }
			   
		   }
		   // 第一引数が記号でない場合、クロージャーを作成して第一引数のcarに
		   // 作成したクロージャーを束縛する
		   // 入力例 : (define (add x y) (+ x y))
		   else {
			   
			   //System.out.println("Define | first argument is ConsCell");
			   // 第一引数がリストである場合
			   if (tmpCar instanceof ConsCell) {

				   //System.out.println("Define | first argument is ConsCell");

				   /*
				    * 束縛される記号と手続きの仮引数のリストの
					* 束縛される記号の部分(リストのcar)を取得する。
				    * この取得したものが記号でない場合は例外をスロー
				    * 記号である場合はその後、作成されるであろうクロージャーを
				    * その記号に束縛した結果を返す
					*/
				   
				   // 束縛される記号と手続きの仮引数のリストを取得
				   // 入力例からすると (add x y) が取得される
				   SExpression args1 = ((ConsCell)tmp).getCar();
				   
				   // 手続きの本体を取得
				   // 入力例からすると (+ x y) が取得される
				   SExpression args2 = ((ConsCell)tmp).getCdr();


				   //System.out.println("Define | args1 : " + args1);
				   //System.out.println("Define | args2 : " + args2);
				   

				   // 束縛される記号と手続きの仮引数のリストから束縛される記号を取得
				   SExpression args1_symbol = ((ConsCell)args1).getCar();
				   // 束縛される記号と手続きの仮引数のリストから手続きの仮引数を取得
				   SExpression args1_dummyArgs = ((ConsCell)args1).getCdr();
				   				   
				   // 束縛される記号が記号でない場合、例外を投げる
				   if ( !(args1_symbol instanceof Symbol) ) {
		   				throw new SyntaxErrorException();
				   }
				   
				   // 束縛される記号が記号でない場合、記号として確保
				   Symbol symbol = (Symbol)args1_symbol;
				   //System.out.println("Define | symbol : " + symbol);
	

				   /*
				    * lambda式を作成してEvaluatorで評価したものを
				    * 束縛される記号に束縛した結果を返す
					*/
	
					
					// 記号"lambda"を作成
					SExpression lambdaExpCar = Symbol.getInstance("lambda");

					// lambda式を作成
					SExpression lambdaExpCdr = ConsCell.getInstance(args1_dummyArgs, args2);
					SExpression lambdaExp = ConsCell.getInstance(lambdaExpCar, lambdaExpCdr);

					//System.out.println("Define | lambda exp : " + lambdaExp);
					
					try {
						// 作成したlambda式を評価
						SExpression evaled_lambdaExp = Evaluator.eval(lambdaExp, env);
					
						//System.out.println("Define | evaled lambda exp : " + evaled_lambdaExp);

					
						// 束縛される記号に作成したlambda式の評価結果を束縛したか結果を返す
						return env.define(symbol, evaled_lambdaExp);
					
					} catch (LispException e) {
						throw e;
					}
			   }
			   // 第一引数が記号でもリストでもない場合
			   // 入力例 : (define #t 1)
			   else {
				   throw new SyntaxErrorException();
			   }

		   }
		   
		   
	   }
	   else {
		   throw new SyntaxErrorException();
	   }
	   
   }
   

   
   @Override 
   public String toString() {
      return "#<syntax define>";
   }

}

