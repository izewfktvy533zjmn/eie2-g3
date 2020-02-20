package lisp.eval;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.PairRequiredException;


/**
 * 
 * 組込み手続き car のテスト
 * @author bp15046
 *
 */



public class CarTest {
	@Test
	public void テスト1 () {
		Car test = new Car();
		
		SExpression sexp = ConsCell.getInstance(ConsCell.getInstance(Int.valueOf(1), Int.valueOf(2)), EmptyList.getInstance());
		
		
		try {
			SExpression actual = test.apply(sexp);
			SExpression expected = Int.valueOf(1);
			
			assertThat(actual, is(expected));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
	
	
	
	@Test
	public void テスト2 () {
		Car test = new Car();
		
		SExpression sexp = ConsCell.getInstance(Symbol.getInstance("a"), EmptyList.getInstance());
		
		
		try {
			SExpression actual = test.apply(sexp);
		} catch (PairRequiredException e) {
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

}
