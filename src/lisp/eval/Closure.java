package lisp.eval;



import lisp.exception.InvalidApplicationException;
import lisp.exception.LispException;
import lisp.exception.WrongNumberOfArgumentsException;



/**
 * クロージャ
 * @author tetsuya
 * @author bp15046
 */



public class Closure implements SExpression {
	// 手続きを作成しいたときの現在のフレームへのポインタ
	private Environment env;
	// 仮引数
	private SExpression dummyArgs;
	// 手続き本体
	private SExpression body;


	
	public Closure(Environment env) {
		this.env = env;
	}
	
	
	public Closure(Environment env, SExpression dummyArgs, SExpression body) {
		this.env = env;
		this.dummyArgs = dummyArgs;
		this.body = body;
	}


	public Environment getEnv() {
		return this.env;
	}


	public SExpression getDummyArgs() {
		return this.dummyArgs;
	}


	public SExpression getBody() {
		return this.body;
	}


	public SExpression apply(SExpression realArgs) throws InvalidApplicationException, LispException, WrongNumberOfArgumentsException {
		// 仮引数と実引数との対応を記録するフレームを作る
		Environment tmpEnv = new Environment(this.env);
		
		SExpression tmpDummyArgs = this.dummyArgs;
		SExpression tmpRealArgs = realArgs;

		if (tmpDummyArgs instanceof ConsCell) {
			tmpDummyArgs = (ConsCell)tmpDummyArgs;

			if ( !(tmpRealArgs instanceof ConsCell) ) {
				throw new InvalidApplicationException();
			}

			tmpRealArgs = (ConsCell)tmpRealArgs;
		
			//System.out.println("Closure | tmpDummyArgs is ConsCell");	
			//System.out.println("Closure | tmpDummyArgs : " + tmpDummyArgs);
			//System.out.println("Closure | tmpRealArgs : " + tmpRealArgs);
		}
		else {
			//System.out.println("Closure | tmpDummyArgs is not ConsCell");	
			//System.out.println("Closure | tmpDummyArgs : " + tmpDummyArgs);
			//System.out.println("Closure | tmpRealArgs  : " + tmpRealArgs);
			//System.out.println("Closure | this.body    : " + this.body);

			// 仮引数と実引数がEmpytListの場合は本体を評価して返す。
			if (tmpDummyArgs instanceof EmptyList) {
				if (tmpRealArgs instanceof EmptyList) {
					SExpression tmpBody = this.body;
					SExpression tmp;

					try {
						
						while(true) {
							if ( !(tmpBody instanceof ConsCell) ) {
								Evaluator.eval(tmpBody, tmpEnv);
							}

							else if ( ((ConsCell)tmpBody).getCdr() instanceof EmptyList ) {
								tmp = ((ConsCell)tmpBody).getCar();
								break;
							}
							else {
								tmp = ((ConsCell)tmpBody).getCar();
								Evaluator.eval(tmp, tmpEnv);
								tmpBody = ((ConsCell)tmpBody).getCdr();
							}
						}

					
						return Evaluator.eval(tmp, tmpEnv);	

					} catch (LispException e) {
						throw e;
					}
				}
				else {
					throw new InvalidApplicationException();
				}
			}

			// 仮引数と本体から取得したS式が記号ではないので例外を投げる
			if ( !(tmpDummyArgs instanceof Symbol) ) {
				throw new InvalidApplicationException();
			}

			// 作成したフレーム(tmpEnv)の中で仮引数のS式を実引数のS式に束縛する
			tmpEnv.define((Symbol)tmpDummyArgs, tmpRealArgs);
			
			try {
				// 作成したフレーム(tmpEnv)を現在のフレームとして、
				// クロージャー(手続き)の本体(thid.body)を評価したもの
				// クロージャー(手続き)の評価値としてを返す
			
				//System.out.println("this.body : " + this.body);
			
				SExpression ret = Evaluator.eval(this.body, tmpEnv);
			
				//System.out.println("ret : " + ret);
			
				return ret;
			}
			catch (LispException e){
				throw e;
			}
			
		}

		while (true) {
			// 仮引数がらS式を取得
			SExpression tmpSymbol = ((ConsCell)tmpDummyArgs).getCar();
			// 実引数からS式を取得
			SExpression tmpSexp = ((ConsCell)tmpRealArgs).getCar();
			

			// 仮引数から取得したS式が記号ではない場合
			if (!(tmpSymbol instanceof Symbol)) {
				// 仮引数から取得したS式がドット対である場合
				if ( !(((ConsCell)tmpSymbol).getCdr() instanceof EmptyList) ) {
					
					//System.out.println("Closure | not Symbol tmpSymbol : " + tmpSymbol);
					
					// ドット対を取得
					SExpression dot = tmpSymbol;
					

					if ( !(dot instanceof ConsCell) ) {
						throw new InvalidApplicationException();
					}


					// ドット対のcarを取得
					SExpression dotCar = ((ConsCell)dot).getCar();
					// ドット対のcdrを取得
					SExpression dotCdr = ((ConsCell)dot).getCdr();
					
					//System.out.println("Closure | dotCar : " + dotCar);
					//System.out.println("Closure | dotCdr : " + dotCdr);
					
					// ドット対のcarとcdrがどちらもSymbolの場合
					if ( (dotCar instanceof Symbol) && (dotCdr instanceof Symbol) ) {
						// 作成したフレーム(tmpEnv)の中で仮引数のS式を実引数のS式に束縛する
						tmpEnv.define((Symbol)dotCar, tmpSexp);
						
						//System.out.println("Closure | defined tmpSymbol : " + tmpSymbol);
						//System.out.println("Closure | defined tmpSexp : " + tmpSexp);
						
						tmpSexp = ((ConsCell)tmpRealArgs).getCdr();
						tmpEnv.define((Symbol)dotCdr, tmpSexp);
						
						//System.out.println("Closure | defined tmpSymbol : " + tmpSymbol);
						//System.out.println("Closure | defined tmpSexp : " + tmpSexp);
						
						break;
					}
					else {
						throw new InvalidApplicationException();
					}
				}
				// 仮引数から取得したS式がドット対でない場合、例外を投げる
				else {
					throw new InvalidApplicationException();
				}
			}
			

			// 作成したフレーム(tmpEnv)の中で仮引数のS式を実引数のS式に束縛する
			tmpEnv.define((Symbol)tmpSymbol, tmpSexp);
			
			//System.out.println("Closure | defined tmpSymbol : " + tmpSymbol);
			//System.out.println("Closure | defined tmpSexp : " + tmpSexp);
		

			// 仮引数と実引数がこれ以上ない場合ループを抜ける
			if ( (((ConsCell)tmpDummyArgs).getCdr() instanceof EmptyList) && (((ConsCell)tmpRealArgs).getCdr() instanceof EmptyList) ) {
				break;
			}
			// 仮引数と実引数の個数が対応しないので例外を投げる
			else if ( !(((ConsCell)tmpDummyArgs).getCdr() instanceof EmptyList) && !(((ConsCell)tmpRealArgs).getCdr() instanceof EmptyList) ) {
				// 仮引数と実引数の読み進め
				tmpDummyArgs = ((ConsCell)tmpDummyArgs).getCdr();
				tmpRealArgs = ((ConsCell)tmpRealArgs).getCdr();
			}
			// 仮引数と実引数の個数が対応しないので例外を投げる
			else {
				throw new WrongNumberOfArgumentsException();
			}
		}
		
		// 作成したフレーム(tmpEnv)を現在のフレームとして、
		// クロージャー(手続き)の本体(thid.body)を評価したもの
		// クロージャー(手続き)の評価値としてを返す
			
		//System.out.println("this.body : " + this.body);
		
		SExpression tmpBody = this.body;
		SExpression tmp;

		try {
				
			while(true) {
				if ( !(tmpBody instanceof ConsCell) ) {
					Evaluator.eval(tmpBody, tmpEnv);
				}

				else if ( ((ConsCell)tmpBody).getCdr() instanceof EmptyList ) {
					tmp = ((ConsCell)tmpBody).getCar();
					break;
				}
				else {
					tmp = ((ConsCell)tmpBody).getCar();
					Evaluator.eval(tmp, tmpEnv);
					tmpBody = ((ConsCell)tmpBody).getCdr();
				}
			}

					
			return Evaluator.eval(tmp, tmpEnv);	

		} catch (LispException e) {
			throw e;
		}
	}



	@Override
	public String toString() {
		String str = "";
		SExpression tmp = this.dummyArgs;

		while (true) {
			if (tmp instanceof EmptyList) {
				return "#<closure (#f)>";
			}
			
			str += ((ConsCell)tmp).getCar();
			
			if (((ConsCell)tmp).getCdr() instanceof EmptyList) {
				break;
			}
			else {
				str += " ";
				tmp = ((ConsCell)tmp).getCdr();
			}
		}
		
		return "#<closure (#f " + str + ")>";
	}

}
