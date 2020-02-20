package lisp.eval;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.SyntaxErrorException;

/**
 * @author BP15043
 */

public class IfTest {
	@Test
	// (if #t 1 2)
	public void テスト1 () {
		Environment env = new Environment(null);
		If test = new If();
		int value = 1;
		
		SExpression sexp = ConsCell.getInstance(Bool.valueOf(true),ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Int.valueOf(2), EmptyList.getInstance())));
	
		try {
			SExpression actual = test.apply(sexp, env);
			Int expected = Int.valueOf(value);
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	@Test
	// (if #t 1 2 3)
	public void テスト2 () {
		Environment env = new Environment(null);
		If test = new If();
		
		SExpression sexp = ConsCell.getInstance(Bool.valueOf(true),ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Int.valueOf(2), ConsCell.getInstance(Int.valueOf(3), EmptyList.getInstance()))));
	
		try {
			SExpression actual = test.apply(sexp, env);
		} catch(SyntaxErrorException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	@Test
	// (if #f 1 2)
	public void テスト3 () {
		Environment env = new Environment(null);
		If test = new If();
		int value = 2;
		
		SExpression sexp = ConsCell.getInstance(Bool.valueOf(false),ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Int.valueOf(2), EmptyList.getInstance())));
	
		try {
			SExpression actual = test.apply(sexp, env);
			Int expected = Int.valueOf(value);
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
}
