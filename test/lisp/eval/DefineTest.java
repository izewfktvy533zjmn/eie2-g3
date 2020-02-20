package lisp.eval;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.SyntaxErrorException;

/**
 * @author BP15043
 * @author bp15046
 */

public class DefineTest {
	@Test
	// (define x 1)
	public void テスト1 () {
		Environment env = new Environment(null);
		Define test = new Define();
		String value = "x";
		
		SExpression sexp = ConsCell.getInstance(Symbol.getInstance(value), ConsCell.getInstance(Int.valueOf(1), EmptyList.getInstance()));
	
		try {
			SExpression actual = test.apply(sexp, env);
			Symbol expected = Symbol.getInstance(value);
			assertThat(actual, is(expected));
			
			//副作用
			Int actual1 = (Int)env.get(Symbol.getInstance(value));
			Int expected1 = Int.valueOf(1);
			assertThat(actual1, is(expected1));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	// (define 1 z)
	public void テスト2 () {
		Environment env = new Environment(null);
		Define test = new Define();
		String z = "z";
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Symbol.getInstance(z), EmptyList.getInstance()));
	
		try {
			SExpression actual = test.apply(sexp, env);
		} catch(SyntaxErrorException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}

	@Test
	// (define x 1 2)
	public void テスト3 () {
		Environment env = new Environment(null);
		Define test = new Define();
		String value = "x";
		
		SExpression sexp = ConsCell.getInstance(Symbol.getInstance(value), ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Int.valueOf(2), EmptyList.getInstance())));
	
		try {
			SExpression actual = test.apply(sexp, env);
			Symbol expected = Symbol.getInstance(value);
			assertThat(actual, is(expected));
		} catch(SyntaxErrorException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	// 副作用
	// (define x 1)
	public void テスト4 () {
		Environment env = new Environment(null);
		Define test = new Define();
		String value = "x";
		
		SExpression sexp = ConsCell.getInstance(Symbol.getInstance(value), ConsCell.getInstance(Int.valueOf(1), EmptyList.getInstance()));
	
		try {
			SExpression actual = test.apply(sexp, env);
			Symbol expected = Symbol.getInstance(value);
			Int actual1 = (Int)env.get(Symbol.getInstance(value));
			Int expected1 = Int.valueOf(1);
			assertThat(actual1, is(expected1));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
}
