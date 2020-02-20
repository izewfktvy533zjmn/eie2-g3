package lisp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileReader;

import lisp.eval.Add;
import lisp.eval.Append;
import lisp.eval.Canvas;
import lisp.eval.Car;
import lisp.eval.Cdr;
import lisp.eval.Cons;
import lisp.eval.ConsCell;
import lisp.eval.Cos;
import lisp.eval.Define;
import lisp.eval.Div;
import lisp.eval.Drawline;
import lisp.eval.Environment;
import lisp.eval.Eq;
import lisp.eval.Equal;
import lisp.eval.Evaluator;
import lisp.eval.Exit;
import lisp.eval.If;
import lisp.eval.Lambda;
import lisp.eval.Let;
import lisp.eval.List;
import lisp.eval.Map;
import lisp.eval.Multi;
import lisp.eval.Newline;
import lisp.eval.Quote;
import lisp.eval.SExpression;
import lisp.eval.Set;
import lisp.eval.Sin;
import lisp.eval.Sqrt;
import lisp.eval.Sub;
import lisp.eval.Symbol;
import lisp.eval.Write;

import lisp.exception.EndOfFileException;
import lisp.exception.ExitException;
import lisp.exception.InvalidApplicationException;
import lisp.exception.LispException;
import lisp.exception.ListRequiredException;
import lisp.exception.ReadErrorException;
import lisp.exception.SyntaxErrorException;
import lisp.exception.PairRequiredException;

import lisp.reader.Reader;



public class Main {
	static void printGreetingMessage() {
		System.out.println("\033[01;34m*************************************");
		System.out.println("");
		System.out.println("              G-3  Lisp              ");
		System.out.println("");
		System.out.println("*************************************\033[0m");
	}



	public static void main(String[] args) throws IOException {
		// 環境の生成
		Environment env = new Environment(null);
		// 特殊形式を環境に束縛させる
	  	env.define(Symbol.getInstance("define"), new Define());
	  	env.define(Symbol.getInstance("if"), new If());
      	env.define(Symbol.getInstance("lambda"), new Lambda());
      	env.define(Symbol.getInstance("let"), new Let());
	  	env.define(Symbol.getInstance("quote"), new Quote());
	  	env.define(Symbol.getInstance("set!"), new Set());
      	// 組込み手続きを環境に束縛させる
      	env.define(Symbol.getInstance("+"), new Add());
      	env.define(Symbol.getInstance("-"), new Sub());
      	env.define(Symbol.getInstance("*"), new Multi());
      	env.define(Symbol.getInstance("/"), new Div());
      	env.define(Symbol.getInstance("="), new Equal());
      	env.define(Symbol.getInstance("append"), new Append());
      	env.define(Symbol.getInstance("canvas"), new Canvas());
      	env.define(Symbol.getInstance("car"), new Car());
      	env.define(Symbol.getInstance("cdr"), new Cdr());
	    env.define(Symbol.getInstance("cons"), new Cons());
		env.define(Symbol.getInstance("cos"), new Cos());
		env.define(Symbol.getInstance("drawline"), new Drawline());
      	env.define(Symbol.getInstance("eq?"), new Eq());
		env.define(Symbol.getInstance("exit"), new Exit());
		env.define(Symbol.getInstance("list"), new List());
		env.define(Symbol.getInstance("newline"), new Newline());
      	env.define(Symbol.getInstance("sin"), new Sin());
      	env.define(Symbol.getInstance("sqrt"), new Sqrt());
		env.define(Symbol.getInstance("write"), new Write());
		
		env.define(Symbol.getInstance("map"), new Map());


		// 引数がある場合、バッチ処理を実行
		if (args.length >= 1) {
			File file = new File(args[0]);
			
			// 引数に指定されたファイルが見つからない場合はエラーを表示して終了
			if ( !file.isFile() ) {
				System.out.println("cannot find " + args[0]);
				return;
			}
			// 引数に指定されたファイルが読み込めない場合はエラーを表示して終了
			if ( !file.canRead() ) {
				System.out.println("cannot read " + args[0]);
				return;
			}
			// 引数に指定されたファイルがscmファイルではない場合はエラーを表示して終了
			if ( !file.getPath().endsWith(".scm") ) {
				System.out.println("not scm-file");
				return;
			}


			FileReader fileReader = new FileReader(file);
			BufferedReader in = new BufferedReader(fileReader);
			Reader reader = new Reader(in);


			try {
				while(true) {
					try {
						SExpression exp = reader.read();
			
			
			
						//System.out.println("\033[01;32mMain | S Expression : " + exp + "\033[0m");
			

						Evaluator.eval(exp, env);
					
					} catch (EndOfFileException e) {
						break;
					} catch (ExitException e) {
						System.exit(0);
					} catch (LispException e) {
						System.out.println("\033[01;33m" + e.getMessage() + "\033[0m");
					}
				}
			} catch (Exception e) {
				System.out.println("\033[01;33m" + e + "\033[0m");
			}

			in.close();
		}



		// 引数がない場合、インタプリタを実行
		else {
			printGreetingMessage();

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			Reader reader = new Reader(in);
		

			// REPL(Read-Eval-Print-Loop)
			try {
				while(true) {
					try {
						System.out.print("\033[01;34mLisp> \033[0m");
						SExpression exp = reader.read();
			
			
					
						//System.out.println("\033[01;32mMain | S Expression : " + exp + "\033[0m");
			
			
					
						SExpression value = Evaluator.eval(exp, env);
						System.out.println("\033[01;31m" + value + "\033[0m");
					} catch (EndOfFileException e) {
						break;
					} catch (ExitException e) {
						System.exit(0);
					} catch (LispException e) {
						System.out.println("\033[01;33m" + e.getMessage() + "\033[0m");
					}
				}
			} catch (Exception e) {
				System.out.println("\033[01;33m" + e + "\033[0m");
			}

			in.close();
		}
	


	}



}
