package lisp.eval;

import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;



/**
 *
 * 特殊形式 : if
 * @author bp15046
 *
 */



public class If extends SpecialForm {

	@Override
	SExpression apply(SExpression sexp, Environment env) throws SyntaxErrorException, LispException {
		// 引数がConsCellでない場合、例外を投げる
		if ( !(sexp instanceof ConsCell) ) {
			throw new SyntaxErrorException();
		}
		// 引数にthen-elseがない場合、例外を投げる
		if ( ((ConsCell)sexp).getCdr() instanceof EmptyList ) {
			throw new SyntaxErrorException();
		}


		// 引数predicateを取得する
		SExpression predicate = ((ConsCell)sexp).getCar();
		// 引数then-elseを取得する
		SExpression tmp = ((ConsCell)sexp).getCdr();
		// 引数thenを取得する
		SExpression arg_then = ((ConsCell)tmp).getCar();
		// 引数elseを取得する
		SExpression arg_else = ((ConsCell)tmp).getCdr();
		if (arg_else instanceof ConsCell) {
			if ( ((ConsCell)arg_else).getCdr() instanceof EmptyList) {
				arg_else = ((ConsCell)arg_else).getCar();
			}
			// 引数が多すぎる場合、例外を投げる
			else {
				throw new SyntaxErrorException();
			}
		}
		// 引数elseがない場合は未定義値とする
		else if (arg_else instanceof EmptyList) {
			arg_else = Undef.getInstance();
		}
		else {
			throw new SyntaxErrorException();
		}

		//System.out.println("If | predicate : " + predicate);
		//System.out.println("If | arg_then  : " + arg_then);
		//System.out.println("If | arg_else  : " + arg_else);
		
		// 引数predicateを評価する
		try {
			predicate = Evaluator.eval(predicate, env);
		
			// 引数predicateの評価値が真理値の場合
			if (predicate instanceof Bool) {
				// #tの場合
				if ( ((Bool)predicate).isValid() ){
					return Evaluator.eval(arg_then, env);
				}
				// #fの場合
				else {
					return Evaluator.eval(arg_else, env);
				}
			}
			// 引数predicateの評価値が真理値でない場合
			else {
				return Evaluator.eval(arg_then, env);
			}
		}catch (LispException e) {
			throw e;
		}
	}




	@Override
	public String toString(){
		return "#<syntax if>";
	}

}
