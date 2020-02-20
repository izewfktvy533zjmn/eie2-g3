package lisp.eval;

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
 * 組込み手続き - のテスト
 * @author bp15102
 *
 */



public class SubTest {
	@Test
	// (- 2 1)
	public void テスト1 () {
		Sub test = new Sub();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(2), ConsCell.getInstance(Int.valueOf(1), EmptyList.getInstance()));
		int value = 1;
		
		try {
			SExpression actual = test.apply(sexp);
			Int expected = Int.valueOf(value);
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
		
		
	}
	

	
	@Test
	// (- 3 1 2)
	public void テスト2 () {
		Sub test = new Sub();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(3), ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Int.valueOf(2), EmptyList.getInstance())));
		int value = 0;
		
		try {
			SExpression actual = test.apply(sexp);
			Int expected = Int.valueOf(value);
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
		
		
	}
	
	
	
	@Test
	// (- 3 (1 2))
	public void テスト3 () {
		Sub test = new Sub();
		
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
