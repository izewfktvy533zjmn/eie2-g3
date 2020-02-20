package lisp.eval;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.NumberRequiredException ;


/**
 * 
 * 組込み手続き = のテスト
 * @author bp15046
 *
 */


public class EqualTest {
	@Test
	// (= 1 2)
	public void テスト1 () {
		Equal test = new Equal();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(1),ConsCell.getInstance(Int.valueOf(2), EmptyList.getInstance()));
	
		try {
			SExpression actual = test.apply(sexp);
			Bool expected = Bool.valueOf(false);
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}

	}
	
	
	
	@Test
	// (= 1 1 1)
	public void テスト2 () {
		Equal test = new Equal();
		
		SExpression sexp = ConsCell.getInstance(Dbl.valueOf(1.0), ConsCell.getInstance(Dbl.valueOf(1.0), ConsCell.getInstance(Dbl.valueOf(1.0), EmptyList.getInstance())));
	
		try {
			SExpression actual = test.apply(sexp);
			Bool expected = Bool.valueOf(true);
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}

	}
	
	
	
	@Test
	// (= 1.0 1.0)
	public void テスト3 () {
		Equal test = new Equal();
		
		SExpression sexp = ConsCell.getInstance(Dbl.valueOf(1.0), ConsCell.getInstance(Dbl.valueOf(1.0), EmptyList.getInstance()));
	
		try {
			SExpression actual = test.apply(sexp);
			Bool expected = Bool.valueOf(true);
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}

	}

	
	
	@Test
	// (= 'a 'a)
	public void テスト4 () {
		Equal test = new Equal();
		
		SExpression sexp = ConsCell.getInstance(Symbol.getInstance("a"), ConsCell.getInstance(Symbol.getInstance("a"), EmptyList.getInstance()));
	
		try {
			SExpression actual = test.apply(sexp);
		} catch (NumberRequiredException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}

	}
}
