package lisp.eval;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.InvalidApplicationException;
import lisp.exception.ListRequiredException;

/**
 * 
 * 組込み手続き map のテスト
 * @author bp15046
 *
 */

public class MapTest {
	@Test
	// (map * '(1 2 3) '(4 5 6))
	public void テスト1 () {
		Map test = new Map();
		Multi proc = new Multi();
		
		SExpression arg1 = ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Int.valueOf(2), ConsCell.getInstance(Int.valueOf(3), EmptyList.getInstance())));
		SExpression arg2 = ConsCell.getInstance(Int.valueOf(4), ConsCell.getInstance(Int.valueOf(5), ConsCell.getInstance(Int.valueOf(6), EmptyList.getInstance())));
		SExpression sexp = ConsCell.getInstance(proc, ConsCell.getInstance(arg1, ConsCell.getInstance(arg2, EmptyList.getInstance())));
		
		
		try {
			SExpression actual = test.apply(sexp);
			SExpression expected = ConsCell.getInstance(Int.valueOf(4), ConsCell.getInstance(Int.valueOf(10), ConsCell.getInstance(Int.valueOf(18), EmptyList.getInstance())));
			assertThat(actual, is(instanceOf(ConsCell.class)));
			
			// 4
			SExpression actual_tmp = ((ConsCell)actual).getCar();
			SExpression expected_tmp = ((ConsCell)expected).getCar();
			assertThat(actual_tmp, is(expected_tmp));
			
			actual = ((ConsCell)actual).getCdr();
			expected = ((ConsCell)expected).getCdr();
			assertThat(actual, is(instanceOf(ConsCell.class)));
			
			// 10
			actual_tmp = ((ConsCell)actual).getCar();
			expected_tmp = ((ConsCell)expected).getCar();
			assertThat(actual_tmp, is(expected_tmp));
			
			actual = ((ConsCell)actual).getCdr();
			expected = ((ConsCell)expected).getCdr();
			assertThat(actual, is(instanceOf(ConsCell.class)));
			
			// 18
			actual_tmp = ((ConsCell)actual).getCar();
			expected_tmp = ((ConsCell)expected).getCar();
			assertThat(actual_tmp, is(expected_tmp));
			
			// ()
			actual_tmp = ((ConsCell)actual).getCdr();
			expected_tmp = ((ConsCell)expected).getCdr();
			assertThat(actual_tmp, is(expected_tmp));
			
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
	
	
	
	@Test
	// (map '(1 2 3) '(4 5 6))
	public void テスト2 () {
		Map test = new Map();
		
		SExpression arg1 = ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Int.valueOf(2), ConsCell.getInstance(Int.valueOf(3), EmptyList.getInstance())));
		SExpression arg2 = ConsCell.getInstance(Int.valueOf(4), ConsCell.getInstance(Int.valueOf(5), ConsCell.getInstance(Int.valueOf(6), EmptyList.getInstance())));
		SExpression sexp = ConsCell.getInstance(arg1, ConsCell.getInstance(arg2, EmptyList.getInstance()));
		
		
		try {
			SExpression actual = test.apply(sexp);
		} catch (InvalidApplicationException e) {
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
	
	
	
	@Test
	// (map * 1 2)
	public void テスト3 () {
		Map test = new Map();
		Multi proc = new Multi();
		
		SExpression arg1 = ConsCell.getInstance(Int.valueOf(1), EmptyList.getInstance());
		SExpression arg2 = ConsCell.getInstance(Int.valueOf(2), EmptyList.getInstance());
		SExpression sexp = ConsCell.getInstance(proc, ConsCell.getInstance(arg1, ConsCell.getInstance(arg2, EmptyList.getInstance())));
		
		try {
			SExpression actual = test.apply(sexp);
		} catch (ListRequiredException e){
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

}
