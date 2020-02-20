package lisp.eval;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.IntRequiredException;
import lisp.exception.WrongNumberOfArgumentsException;


/**
 * 
 * 組込み手続き canvas のテスト
 * @author bp15046
 *
 */

public class CanvasTest {
	@Test
	// (canvas 500 500)
	public void テスト1 () {
		Canvas test = new Canvas();
		SExpression sexp = ConsCell.getInstance(Int.valueOf(500), ConsCell.getInstance(Int.valueOf(500), EmptyList.getInstance()));
		
		try {
			SExpression actual = test.apply(sexp);
			assertThat(actual, is(instanceOf(Fund.class)));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
	
	
	
	@Test
	// (canvas 500.5 500)
	public void テスト2 () {
		Canvas test = new Canvas();
		SExpression sexp = ConsCell.getInstance(Dbl.valueOf(500.5), ConsCell.getInstance(Int.valueOf(500), EmptyList.getInstance()));
		
		try {
			SExpression actual = test.apply(sexp);
			assertThat(actual, is(instanceOf(Fund.class)));
		} catch (IntRequiredException e) {
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
	
	
	
	@Test
	// (canvas 500 500 500)
	public void テスト3 () {
		Canvas test = new Canvas();
		SExpression sexp = ConsCell.getInstance(Int.valueOf(500), ConsCell.getInstance(Int.valueOf(500), ConsCell.getInstance(Int.valueOf(500), EmptyList.getInstance())));
		
		try {
			SExpression actual = test.apply(sexp);
		} catch (WrongNumberOfArgumentsException e) {
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
	

}
