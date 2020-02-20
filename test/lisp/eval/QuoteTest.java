package lisp.eval;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.SyntaxErrorException;

/**
 * @author BP15043
 */

public class QuoteTest {
	@Test
	// (quote x)
	public void テスト1 () {
		Environment env = new Environment(null);
		Quote test = new Quote();
		String value = "x";
		
		SExpression sexp = ConsCell.getInstance(Symbol.getInstance(value), EmptyList.getInstance());
	
		try {
			SExpression actual = test.apply(sexp, env);
			Symbol expected = Symbol.getInstance(value);
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	@Test
	// (quote x x)
	public void テスト2 () {
		Environment env = new Environment(null);
		Quote test = new Quote();
		String x = "x";
		
		SExpression sexp = ConsCell.getInstance(Symbol.getInstance(x), ConsCell.getInstance(Symbol.getInstance(x), EmptyList.getInstance()));
	
		try {
			SExpression actual = test.apply(sexp, env);
		} catch(SyntaxErrorException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
}
