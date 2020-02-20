package lisp.eval;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.ListRequiredException;

/**
 * 
 * 組込み手続き append のテスト
 * @author bp15046
 *
 */



public class AppendTest {
	@Test
	// (append '(a b) '(c d)))
	public void テスト1 () {
		Append test = new Append();
		
		SExpression arg1 = ConsCell.getInstance(Symbol.getInstance("a"), ConsCell.getInstance(Symbol.getInstance("b"), EmptyList.getInstance()));
		SExpression arg2 = ConsCell.getInstance(Symbol.getInstance("c"), ConsCell.getInstance(Symbol.getInstance("d"), EmptyList.getInstance()));
		SExpression sexp = ConsCell.getInstance(arg1, ConsCell.getInstance(arg2, EmptyList.getInstance()));

		
		try {
			SExpression actual = test.apply(sexp);
			SExpression expected = ConsCell.getInstance(Symbol.getInstance("a"), ConsCell.getInstance(Symbol.getInstance("b"), ConsCell.getInstance(Symbol.getInstance("c"), ConsCell.getInstance(Symbol.getInstance("d"), EmptyList.getInstance()))));
			assertThat(actual, is(instanceOf(ConsCell.class)));
			
			// a
			SExpression actual1 = ((ConsCell)actual).getCar();
			SExpression expected1 = ((ConsCell)expected).getCar();
			assertThat(actual1, is(expected1));

			actual = ((ConsCell)actual).getCdr();
			expected = ((ConsCell)expected).getCdr();
			assertThat(actual, is(instanceOf(ConsCell.class)));
			
			// b
			SExpression actual2 = ((ConsCell)actual).getCar();
			SExpression expected2 = ((ConsCell)expected).getCar();
			assertThat(actual2, is(expected2));

			actual = ((ConsCell)actual).getCdr();
			expected = ((ConsCell)expected).getCdr();
			assertThat(actual, is(instanceOf(ConsCell.class)));
			
			// c
			SExpression actual3 = ((ConsCell)actual).getCar();
			SExpression expected3 = ((ConsCell)expected).getCar();
			assertThat(actual3, is(expected3));

			actual = ((ConsCell)actual).getCdr();
			expected = ((ConsCell)expected).getCdr();
			assertThat(actual, is(instanceOf(ConsCell.class)));
			
			// d
			SExpression actual4 = ((ConsCell)actual).getCar();
			SExpression expected4 = ((ConsCell)expected).getCar();
			assertThat(actual4, is(expected4));

			actual = ((ConsCell)actual).getCdr();
			expected = ((ConsCell)expected).getCdr();
			assertThat(actual, is(instanceOf(EmptyList.class)));
			
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
		
		
	@Test
	public void テスト2() {
		Append test = new Append();
			
		SExpression sexp = (ConsCell.getInstance(Symbol.getInstance("a"), ConsCell.getInstance(Symbol.getInstance("b"), EmptyList.getInstance())));
			
			
		try {
			SExpression actual = test.apply(sexp);
				
		} catch (ListRequiredException e) {
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
				

	
}
