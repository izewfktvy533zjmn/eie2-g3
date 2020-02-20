package lisp.reader;

import java.io.BufferedReader;
import java.io.IOException;

import lisp.eval.Bool;
import lisp.eval.ConsCell;
import lisp.eval.EmptyList;
import lisp.eval.SExpression;
import lisp.eval.Int;
import lisp.eval.Dbl;
import lisp.eval.Symbol;
import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;



/**
 * 構文解析器
 * @author tetsuya
 * @author bp15046
 */



public class Reader {
	/**
	 * 字句解析器
	 */
	private Lexer lexer;

	/**
	 * 先読みの字句
	 */
    private Token token = null;

    /**
     * 括弧の入れ子レベル
     * 式を読み終わった時にnestingLevelが0ならば、そこまでの式を評価する。
     */
    private int nestingLevel = 0;
    
    /**
     * コンストラクタ
     * @param in 入力
     */
	public Reader(BufferedReader in) {
		this.lexer = new Lexer(in);
	}

	/**
	 * <pre>
	 * {@literal <S式>} ::= {@literal <整数値>} | {@literal <記号>} | {@literal <真理値>} | '(' ({@literal <S式>} '.' {@literal <S式>})? ')' 
	 * </pre>
	 * @return S式
	 * @throws LispException 
	 * @throws IOException 
	 */
	SExpression sExpression() throws IOException, LispException {
		// 整数値
		if (this.token.getKind() == Token.Kind.NUMBER) {
			int value = this.token.getIntValue();
			if (this.nestingLevel != 0) { // 式が未完成
				this.token = this.lexer.getNextToken();
				//System.out.println("Reader | get next token");
				//System.out.println("Reader | this.token : " + this.token);
			}
			return Int.valueOf(value);
		}
		// 浮動小数点数値
		if (this.token.getKind() == Token.Kind.REALNUMBER) {
			double value = this.token.getDoubleValue();
			if (this.nestingLevel != 0) { // 式が未完成
				this.token = this.lexer.getNextToken();
				//System.out.println("Reader | get next token");
				//System.out.println("Reader | this.token : " + this.token);
			}
			return Dbl.valueOf(value);
		}
		// 記号
		if (this.token.getKind() == Token.Kind.SYMBOL) {
			String value = this.token.getSymbolValue();
			if (this.nestingLevel != 0) { // 式が未完成
				this.token = this.lexer.getNextToken();
				//System.out.println("Reader | get next token");
				//System.out.println("Reader | this.token : " + this.token);
			}
			return Symbol.getInstance(value);
		}
		// 真理値
		if (this.token.getKind() == Token.Kind.BOOLEAN) {
			boolean value = this.token.getBooleanValue();
			if (this.nestingLevel != 0) { // 式が未完成
				this.token = this.lexer.getNextToken();
				//System.out.println("Reader | get next token");
				//System.out.println("Reader | this.token : " + this.token);
			}
			return Bool.valueOf(value);
		}
		// クォート
		if (this.token.getKind() == Token.Kind.QUOTE) {
			this.token = this.lexer.getNextToken();
			SExpression tmp = sExpression();
			tmp =  ConsCell.getInstance(tmp, EmptyList.getInstance());
			
			return ConsCell.getInstance(Symbol.getInstance("quote"), tmp);
		}
		// '(' ')' or '(' <S式> . <S式> ')'
		if (this.token.getKind() == Token.Kind.LEFTPAR) {
			this.nestingLevel++;
			this.token = this.lexer.getNextToken();
			// 空リスト
			if (this.token.getKind() == Token.Kind.RIGHTPAR) {
				this.nestingLevel--;
				if (this.nestingLevel != 0) { // 式が未完成
					this.token = this.lexer.getNextToken();
					//System.out.println("Reader | get next token");
					//System.out.println("Reader | this.token : " + this.token);
				}
				return EmptyList.getInstance();
			}
			
			//System.out.println("Reader | sExpression");
			
			// car
			SExpression car = sExpression();

			//System.out.println("Reader | car : " + car);


				
			// '.'でない場合
			if (this.token.getKind() != Token.Kind.DOT) {
				
				// 要素が一つのリスト
				if (this.token.getKind() == Token.Kind.RIGHTPAR) {
					this.nestingLevel--;
			
					if (this.nestingLevel != 0) { // 式が未完成
						this.token = this.lexer.getNextToken();
						//System.out.println("Reader | get next token");
						//System.out.println("Reader | this.token : " + this.token);
					}
					
					return ConsCell.getInstance(car, EmptyList.getInstance());				
				}

				// ループ内でcdrを保存しておくためのリストを作成
				ConsCell list = ConsCell.getInstance(EmptyList.getInstance(), EmptyList.getInstance());
				
				while (true) {
					//System.out.println("Reader | LOOP this.token :" + this.token);
					//System.out.println("Reader | sExpression");
					//System.out.println("Reader | list : " + list);
			
					// cdr
					SExpression cdr = sExpression();
					
					//System.out.println("Reader | LOOP cdr : " + cdr);
					
					// S式のループが終わった後の処理
					if (this.token.getKind() == Token.Kind.RIGHTPAR) {
						cdr = ConsCell.getInstance(cdr, EmptyList.getInstance());
						//System.out.println("Reader | LOOP TOKEN KIND RIGHTPAR cdr : " + cdr);
						//System.out.println(car);
					
						ConsCell tmpList = list;
						
					    //System.out.println(tmpList);	
						
						if (tmpList.getCar() instanceof EmptyList && tmpList.getCdr() instanceof EmptyList){
							//System.out.println("OK");
							//System.out.println("Readr | tmpList : " + tmpList);

							list = (ConsCell)cdr;
							
							this.nestingLevel--;			
							if (this.nestingLevel != 0) { // 式が未完成
								this.token = this.lexer.getNextToken();
								//System.out.println("Reader | get next token");
								//System.out.println("Reader | this.token : " + this.token);
							}
							
							return ConsCell.getInstance(car, list);
						}
						
						// 現在のcdrをlistの最後のcdrに保存するための処理
						while (true) {
							if (tmpList.getCdr() instanceof EmptyList) {
								break;
							}
							else {
								tmpList = (ConsCell)tmpList.getCdr();
								//System.out.println("Readr | LOOP tmpList : " + tmpList);
							}
						}

						//System.out.println("Reader | LOOP END list : " + list);
						//System.out.println("Reader | LOOP END tmpList : " + tmpList);
						
						if (tmpList.getCar() instanceof EmptyList){
							//System.out.println("Readr | tmpList : " + tmpList);

							tmpList.setCdr(cdr);

							
							this.nestingLevel--;			
							if (this.nestingLevel != 0) { // 式が未完成
								this.token = this.lexer.getNextToken();
								//System.out.println("Reader | get next token");
								//System.out.println("Reader | this.token : " + this.token);
							}
							
							return ConsCell.getInstance(car, list.getCdr());
						}
						else {
							tmpList.setCdr(cdr);
						
							//System.out.println("Reader | LOOP END seted list : " + list);
						
							this.nestingLevel--;			
						
							if (this.nestingLevel != 0) { // 式が未完成
								this.token = this.lexer.getNextToken();
								//System.out.println("Reader | get next token");
								//System.out.println("Reader | this.token : " + this.token);
							}
						
							return ConsCell.getInstance(car, list.getCdr());
						}
					}
					// S式のループの後にドットが来る場合
					else if (this.token.getKind() == Token.Kind.DOT) {
						
						SExpression tmpCar = cdr;

						this.token = this.lexer.getNextToken();

						//System.out.println("Reader | sExpression");
			
						// cdr
						SExpression tmpCdr = sExpression();
			
						if (this.token.getKind() != Token.Kind.RIGHTPAR) {
							throw new SyntaxErrorException();
						}
						
						/*
						this.nestingLevel--;			
						
						if (this.nestingLevel != 0) { // 式が未完成
							this.token = this.lexer.getNextToken();
							//System.out.println("Reader | get next token");
							//System.out.println("Reader | this.token : " + this.token);
						}
						*/
						
						cdr = ConsCell.getInstance(tmpCar, tmpCdr);
						//cdr = ConsCell.getInstance(cdr, EmptyList.getInstance());
						
						ConsCell tmpList = list;
						
						// 現在のcdrをlistの最後のcdrに保存するための処理
						while (true) {
							if (tmpList.getCdr() instanceof EmptyList) {
								break;
							}
							else {
								tmpList = (ConsCell)tmpList.getCdr();
							}
						}
						
						if (tmpList.getCar() instanceof EmptyList){
							list = (ConsCell)cdr;
							
							this.nestingLevel--;			
							if (this.nestingLevel != 0) { // 式が未完成
								this.token = this.lexer.getNextToken();
							}
							
							return ConsCell.getInstance(car, list);
						}
						else {
							tmpList.setCdr(cdr);
							
							this.nestingLevel--;			
						
							if (this.nestingLevel != 0) { // 式が未完成
								this.token = this.lexer.getNextToken();
							}
						
							return ConsCell.getInstance(car, list.getCdr());
						}

					}
					// S式のループが続く場合
					else {
						// cdrを保存しておく必要がある
						// 現在のcdrをlistの最後のcdrに保存
						ConsCell tmpList = list;
						while (true) {
							if (tmpList.getCdr() instanceof EmptyList) {
								//System.out.println("Reader | tmpList.getCdr() : " + tmpList.getCdr());
			
								break;
							}
							else {
								tmpList = (ConsCell)tmpList.getCdr();
							}
						}
						
						//System.out.println("Reader | LOOP list : " + tmpList);
						tmpList.setCdr(ConsCell.getInstance(cdr, EmptyList.getInstance()));
						//System.out.println("Reader | LOOP seted list : " + tmpList);
					}

				}
			
			}
			// '.'である場合
			else {
				this.token = this.lexer.getNextToken();
			
				//System.out.println("Reader | sExpression");
			
				// cdr
				SExpression cdr = sExpression();
			
				if (this.token.getKind() != Token.Kind.RIGHTPAR) {
					throw new SyntaxErrorException();
				}
			
				this.nestingLevel--;			
				if (this.nestingLevel != 0) { // 式が未完成
					this.token = this.lexer.getNextToken();
					//System.out.println("Reader | get next token");
					//System.out.println("Reader | this.token : " + this.token);
				}
				
				return ConsCell.getInstance(car, cdr);
			}

		}
		throw new SyntaxErrorException();
	}

	public SExpression read() throws IOException, LispException {
		this.nestingLevel = 0;
		this.token = this.lexer.getNextToken();
		return sExpression();
	}
}
