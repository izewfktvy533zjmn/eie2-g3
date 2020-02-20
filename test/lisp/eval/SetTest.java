package lisp.eval;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.SyntaxErrorException;

/**
 * @author BP15043
 * @author bp15046
 */

public class SetTest {
	@Test
	// (define x 1)
	// (set! x 2)
	public void テスト1 () {
		Environment env = new Environment(null);
		Define test1 = new Define();
		Set test2 = new Set();
		String value = "x";
		
		SExpression sexp1 = ConsCell.getInstance(Symbol.getInstance(value), ConsCell.getInstance(Int.valueOf(1), EmptyList.getInstance()));
		SExpression sexp2 = ConsCell.getInstance(Symbol.getInstance(value), ConsCell.getInstance(Int.valueOf(2), EmptyList.getInstance()));
		
		try {
			test1.apply(sexp1, env);
			SExpression actual2 = test2.apply(sexp2, env);
			Symbol expected = Symbol.getInstance(value);
			assertThat(actual2, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	// (set! 3 z)
	public void テスト2 () {
		Environment env = new Environment(null);
		Set test = new Set();
		String z = "z";
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(3), ConsCell.getInstance(Symbol.getInstance(z), EmptyList.getInstance()));
	
		try {
			SExpression actual = test.apply(sexp, env);
		} catch(SyntaxErrorException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	// (define x 1)
	// (set! x 2 3)
	public void テスト3 () {
		Environment env = new Environment(null);
		Define test1 = new Define();
		Set test2 = new Set();
		String value = "x";
		
		SExpression sexp1 = ConsCell.getInstance(Symbol.getInstance(value), ConsCell.getInstance(Int.valueOf(1), EmptyList.getInstance()));
		SExpression sexp2 = ConsCell.getInstance(Symbol.getInstance(value), ConsCell.getInstance(Int.valueOf(2), ConsCell.getInstance(Int.valueOf(3), EmptyList.getInstance())));
	
		try {
			test1.apply(sexp1, env);
			SExpression actual = test2.apply(sexp2, env);
			Symbol expected = Symbol.getInstance(value);
			assertThat(actual, is(expected));
		} catch (SyntaxErrorException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	//副作用
	// (define x 1)
	// (set! x 2)
	public void テスト4 () {
		Environment env = new Environment(null);
		Define test1 = new Define();
		Set test2 = new Set();
		String value = "x";
			
		SExpression sexp1 = ConsCell.getInstance(Symbol.getInstance(value), ConsCell.getInstance(Int.valueOf(1), EmptyList.getInstance()));
		SExpression sexp2 = ConsCell.getInstance(Symbol.getInstance(value), ConsCell.getInstance(Int.valueOf(2), EmptyList.getInstance()));
			
		try {
			test1.apply(sexp1, env);
			SExpression actual2 = test2.apply(sexp2, env);
			Symbol expected = Symbol.getInstance(value);
			
			
			Int actual1 = (Int)env.get(Symbol.getInstance(value));
			Int expected1 = Int.valueOf(2);
			assertThat(actual1, is(expected1));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
}
