package lisp.eval;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.SyntaxErrorException;


/**
 * 
 * 組込み手続き List のテスト
 * @author bp15030
 * @author bp15046
 *
 */



public class ListTest {
	@Test
	// (list 'a 'b 'c 'd)
	public void テスト1 () {
		List test = new List();
		
		SExpression sexp = ConsCell.getInstance(Symbol.getInstance("a"), ConsCell.getInstance(Symbol.getInstance("b"), ConsCell.getInstance(Symbol.getInstance("c"),ConsCell.getInstance(Symbol.getInstance("d"), EmptyList.getInstance()))));
		
		
		try {
			SExpression actual = test.apply(sexp);
			SExpression expected = sexp;
			assertThat(actual, is(sexp));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
		
		
		
	@Test
	// (list . 'a)
	public void テスト2 () {
		List test = new List();
			
		SExpression sexp = Symbol.getInstance("a");
			
			
		try {
			SExpression actual = test.apply(sexp);
		} catch (SyntaxErrorException e) {
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
}

	