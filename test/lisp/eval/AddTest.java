package lisp.eval;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.NumberRequiredException;


/**
 * 
 * 組込み手続き + のテスト
 * @author bp15046
 *
 */



public class AddTest {
	@Test
	// (+ 1 2)
	public void テスト1 () {
		Add test = new Add();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Int.valueOf(2), EmptyList.getInstance()));
		int value = 3;
		
		try {
			SExpression actual = test.apply(sexp);
			Int expected = Int.valueOf(value);
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
		
		
	}
	

	
	@Test
	// (+ 1 2 3)
	public void テスト2 () {
		Add test = new Add();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Int.valueOf(2), ConsCell.getInstance(Int.valueOf(3), EmptyList.getInstance())));
		int value = 6;
		
		try {
			SExpression actual = test.apply(sexp);
			Int expected = Int.valueOf(value);
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
		
		
	}
	
	@Test
	// (+ 1 2.5)
	public void テスト3 () {
		Add test = new Add();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Dbl.valueOf(2.5),EmptyList.getInstance()));
		double value = 3.5;
		
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
	// (+ 1 (2 3))
	public void テスト4 () {
		Add test = new Add();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(
				ConsCell.getInstance(Int.valueOf(2), ConsCell.getInstance(Int.valueOf(3), EmptyList.getInstance())), EmptyList.getInstance()));
		
		try {
			// この場合、NumberRequiredExceotionが投げられるのでそれを受け取って何もしなければよい
			SExpression actual = test.apply(sexp);
		} catch (NumberRequiredException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
		
		
	}
	
}
