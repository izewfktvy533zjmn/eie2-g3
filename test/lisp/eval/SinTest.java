package lisp.eval;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
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

import lisp.exception.DblRequiredException;


/**
 * 
 * 組込み手続き Sin のテスト
 * @author bp15030
 * @author bp15046
 *
 *
 */



public class SinTest {
	@Test
	// (sin 1.57)
	public void テスト1 () {
		Sin test = new Sin();
		
		SExpression sexp = ConsCell.getInstance(Dbl.valueOf(1.57),EmptyList.getInstance());
		double value = 0.9999996829318346;
	  

;
		
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
	// (sin 90)
	public void テスト2 () {
		Sin test = new Sin();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(90),EmptyList.getInstance());
		try {
			// この場合、NumberRequiredExceotionが投げられるのでそれを受け取って何もしなければよい
			SExpression actual = test.apply(sexp);
		} catch (DblRequiredException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
		
		
	}
	
}
