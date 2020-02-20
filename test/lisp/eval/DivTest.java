package lisp.eval;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.NumberRequiredException;
import lisp.exception.DivisionException;


/**
 * 
 * 組込み手続き / のテスト
 * @author bp15102
 * @author bp15046
 *
 */



public class DivTest {
	@Test
	// (/ 4 2)
	public void テスト1 () {
		Div test = new Div();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(4), ConsCell.getInstance(Int.valueOf(2), EmptyList.getInstance()));
		double value = 2;
		
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
	// (/ 3 2)
	public void テスト2 () {
		Div test = new Div();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(3), ConsCell.getInstance(Int.valueOf(2), EmptyList.getInstance()));
		double value = 1.5;
		
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
	// (/ 3 (1 2))
	public void テスト3 () {
		Div test = new Div();
		
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
	
	
	@Test
	// (/ 1 0)
	public void テスト4 () {
		Div test = new Div();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Int.valueOf(0), EmptyList.getInstance()));
		
		try {
			SExpression actual = test.apply(sexp);
		} catch (DivisionException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
		
		
	}

}
