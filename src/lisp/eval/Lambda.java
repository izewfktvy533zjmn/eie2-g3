package lisp.eval;



import lisp.exception.SyntaxErrorException;



/**
 * 特殊形式 : lambda
 * @author bp15046
 */



public class Lambda extends SpecialForm {
	@Override
	SExpression apply(SExpression sexp, Environment env) throws SyntaxErrorException {
		// applyの引数がConsCellの場合
		if (sexp instanceof ConsCell) {
			
			//System.out.println("Lambda | sexp is ConsCell");
			
			ConsCell tmp = (ConsCell)sexp;
			
			//System.out.println("Lambda | sexp : " + sexp);

			SExpression tmpCar = tmp.getCar();
			SExpression tmpCdr = tmp.getCdr();

			//System.out.println("Lambda | tmpCar : " + tmpCar);
			//System.out.println("Lambda | tmpCdr : " + tmpCdr);
			
			// 特殊形式lambdaの第一引数(手続き)がリストである場合
			if (tmpCar instanceof ConsCell) {
				
				//System.out.println("Lambda | tmpCar is ConsCell");
				//System.out.println("Lambda | tmpCar :" + tmpCar);
				
				// 特殊形式lambdaの第二引数(本体)がリストである場合
				if (tmpCdr instanceof ConsCell) {
					
					//System.out.println("Lambda | tmpCdr is ConsCell");
					//System.out.println("Lambda | tmpCdr :" + tmpCdr);
					
					ConsCell tmpCdr_tmp = (ConsCell)tmpCdr;

					SExpression tmpCdr_tmpCar = tmpCdr_tmp.getCar();
					SExpression tmpCdr_tmpCdr = tmpCdr_tmp.getCdr();

					// クロージャーを作成してそのクロージャーを返す
					Closure closure =  new Closure(env, tmpCar, tmpCdr);
					return closure;
					
					/*
					// 特殊形式lambdaの第二引数(本体)の要素が一つの場合
					if (tmpCdr_tmpCdr instanceof EmptyList) {
						
						//System.out.println("Lambda | tmpCdr_tmpCdr is EmptyList");
						
						//System.out.println("Lambda | tmpCdr_tmpCar" + tmpCdr_tmpCar);
						
						// クロージャーを作成してそのクロージャーを返す
						Closure closure =  new Closure(env, tmpCar, tmpCdr_tmpCar);
							
						//System.out.println("Lambda | closure.getEnv : " + closure.getEnv());
						//System.out.println("Lambda | closure.getDummyArgs : " + closure.getDummyArgs());
						//System.out.println("Lambda | closure.getBody : " + closure.getBody());
						
						return closure;
					}
					// 特殊形式lambdaの第二引数引数(本体)の要素が二つ以上の場合、
					// 最後の要素を本体とする
					else {
						// tmpCdr_tmpCdrの最後のcdrがEmptyListになるまでループ
						SExpression loop = tmpCdr_tmpCdr;
						while (true) {

							if ( ((ConsCell)loop).getCdr() instanceof EmptyList) {
								break;
							}
							else {
								loop = ((ConsCell)loop).getCdr();
							}
						}
						
						tmpCdr_tmpCdr = ((ConsCell)loop).getCdr();
						tmpCdr_tmpCar = ((ConsCell)loop).getCar();

						//System.out.println("Lambda | tmpCdr_tmpCdr is EmptyList");
						
						//System.out.println("Lambda | tmpCdr_tmpCar" + tmpCdr_tmpCar);
						
						// クロージャーを作成してそのクロージャーを返す
						Closure closure =  new Closure(env, tmpCar, tmpCdr_tmpCar);
							
						//System.out.println("Lambda | closure.getEnv : " + closure.getEnv());
						//System.out.println("Lambda | closure.getDummyArgs : " + closure.getDummyArgs());
						//System.out.println("Lambda | closure.getBody : " + closure.getBody());
						
						return closure;
					}
					*/

				}
				// sexpの第二引数がリストでない場合
				else {
					throw new SyntaxErrorException();
				}
			}
			// sexpの第一引数がリストでない場合
			else {
				// EmptyListの場合
				if (tmpCar instanceof EmptyList) {
					// クロージャーを作成してそのクロージャーを返す
					//System.out.println(tmpCar);
					//System.out.println(tmpCdr);
					
					
					Closure closure =  new Closure(env, tmpCar, tmpCdr);
					
					
					//System.out.println("Lambda | closure.getEnv : " + closure.getEnv());
					//System.out.println("Lambda | closure.getDummyArgs : " + closure.getDummyArgs());
					//System.out.println("Lambda | closure.getBody : " + closure.getBody());
					
					return closure;
				}
				
				// 0個以上の引数
				if ( !(((ConsCell)tmpCdr).getCdr() instanceof EmptyList) ) {
					throw new SyntaxErrorException();
				}

				// クロージャーを作成してそのクロージャーを返す
				Closure closure =  new Closure(env, tmpCar, ((ConsCell)tmpCdr).getCar());
						
				//System.out.println("Lambda | closure.getEnv : " + closure.getEnv());
				//System.out.println("Lambda | closure.getDummyArgs : " + closure.getDummyArgs());
				//System.out.println("Lambda | closure.getBody : " + closure.getBody());
						
				return closure;
			}

		}
		// applyの引数がConsCellでない場合
		else {
			throw new SyntaxErrorException();
		}
	}

	

	@Override
	public String toString() {
		return "#<syntax lambda>";
	}

}
