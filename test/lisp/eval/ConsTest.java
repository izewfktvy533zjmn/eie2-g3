package lisp.eval;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.WrongNumberOfArgumentsException;



/**
 * 
 * 組込み手続き / のテスト
 * @author bp15102
 * @author bp15046
 *
 */



public class ConsTest {
	@Test
	// (cons 'a 'b)
	public void テスト1 () {
		Cons test = new Cons();
		String a = "a";
		String b = "b";
		
		SExpression sexp = ConsCell.getInstance(Symbol.getInstance(a),ConsCell.getInstance(Symbol.getInstance(b),EmptyList.getInstance()));
						
	    SExpression expected = ConsCell.getInstance(Symbol.getInstance(a) ,Symbol.getInstance(b));
		
		try {
			SExpression actual = test.apply(sexp);
			assertThat(actual, is(instanceOf(ConsCell.class)));
			
			// a
			SExpression actual1 = ((ConsCell)actual).getCar();
			SExpression expected1 = ((ConsCell)expected).getCar();
			assertThat(actual1, is(expected1));
			
			actual1 = ((ConsCell)actual).getCdr();
			expected1 = ((ConsCell)expected).getCdr();
			assertThat(actual1, is(expected1));
		} catch(Exception e) {
			fail("Exception thrown");
		}
		
		
	}
	

	
	
	@Test
	// (cons 'a 'b 'c)
	public void テスト2 () {
		Cons test = new Cons();
		String a = "a";
		String b = "b";
		String c = "c";
		SExpression sexp = ConsCell.getInstance(Symbol.getInstance(a),ConsCell.getInstance(Symbol.getInstance(b),ConsCell.getInstance(Symbol.getInstance(c),EmptyList.getInstance())));
		
		try {
			// この場合、WrongNumberOfArgumentsExceptionが投げられるのでそれを受け取って何もしなければよい
			SExpression actual = test.apply(sexp);
		} catch (WrongNumberOfArgumentsException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
		
		
	}
	
}