package lisp.eval;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;
import lisp.exception.LispException;
import lisp.exception.NumberRequiredException;
import lisp.reader.Lexer;
import lisp.reader.Reader;
import lisp.reader.Token;

import lisp.exception.WrongNumberOfArgumentsException;
import lisp.exception.NaturalNumberRequiredException;
import lisp.exception.NumberRequiredException;


/**
 * 
 * 組込み手続き Sqrt のテスト
 * @author bp15030
 * @author bp15046
 * 
 *
 */



public class SqrtTest {
	@Test
	// (sqrt 121)
	public void テスト1 () {
		Sqrt test = new Sqrt();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(121),EmptyList.getInstance());
		double value = 11.0;
	  
		try {
			Dbl actual = (Dbl)test.apply(sexp);
			Dbl expected = Dbl.valueOf(value);
			assertThat(actual, is(instanceOf(Dbl.class)));
			
			double actual1 = ((Dbl)actual).getValue();
			double expected1 = expected.getValue();
			assertThat(actual1, is(expected1));
		} catch(Exception e) {
			fail("Exception thrown");
		}
		
	}
	
	
	@Test
	// (sqrt 121 4)
	public void テスト2 () {
		Sqrt test = new Sqrt();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(121),ConsCell.getInstance(Int.valueOf(4),EmptyList.getInstance()));
		try {
			// この場合、NumberRequiredExceotionが投げられるのでそれを受け取って何もしなければよい
			SExpression actual = test.apply(sexp);
		} catch (WrongNumberOfArgumentsException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
				
	}

	

	@Test
	// (sqrt -2)
	public void テスト3 () {
		Sqrt test = new Sqrt();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(-2),EmptyList.getInstance());
		try {
			// この場合、NumberRequiredExceotionが投げられるのでそれを受け取って何もしなければよい
			SExpression actual = test.apply(sexp);
		} catch (NaturalNumberRequiredException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
				
	}
}
