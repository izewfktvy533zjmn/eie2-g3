package lisp.eval;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;
import lisp.exception.LispException;
import lisp.reader.Lexer;
import lisp.reader.Reader;
import lisp.reader.Token;

import lisp.exception.NumberRequiredException;

/**
 * 
 * 組込み手続き * のテスト
 * @author bp15030
 * @author bp15046
 *
 */

public class MultiTest {
	@Test
	// (* 1 2 3 4)
	public void テスト1 () {
		
		Multi test = new Multi();
		SExpression sexp = ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Int.valueOf(2), ConsCell.getInstance(Int.valueOf(3),ConsCell.getInstance(Int.valueOf(4), EmptyList.getInstance()))));
		int value = 24;
	
		try {
			SExpression actual = test.apply(sexp);
			Int expected = Int.valueOf(value);
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	@Test
	// (* 1 2.5)
	public void テスト2 () {
		Multi test = new Multi();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Dbl.valueOf(2.5),EmptyList.getInstance()));
		double value = 2.5;
		
		try {
			SExpression actual = test.apply(sexp);
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
	// (* 3 '(1 2))
	public void テスト3() {
		Multi test = new Multi();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(3), ConsCell.getInstance(
				ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Int.valueOf(2), EmptyList.getInstance())), EmptyList.getInstance()));
		
		try {
			// この場合、NumberRequiredExceotionが投げられるのでそれを受け取って何もしなければよい
			SExpression actual = test.apply(sexp);
		} catch (NumberRequiredException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
		
		
	}
}
