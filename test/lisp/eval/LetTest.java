package lisp.eval;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.InvalidApplicationException;
import lisp.exception.ListRequiredException;
import lisp.exception.WrongNumberOfArgumentsException;

/**
 * @author BP15043
 * @author bp15046
 */

public class LetTest {
	@Test
	// (let ((x 10) (y 20)) (+ x y))
	public void テスト1 () {
		Environment env = new Environment(null);
		env.define(Symbol.getInstance("+"), new Add());
		Let test = new Let();
		String x = "x";
		String y = "y";
		String add = "+";
		
		SExpression sexp2 = ConsCell.getInstance(
				ConsCell.getInstance(
						ConsCell.getInstance(Symbol.getInstance(x), ConsCell.getInstance(Int.valueOf(10), EmptyList.getInstance())), 
						ConsCell.getInstance(ConsCell.getInstance(Symbol.getInstance(y), ConsCell.getInstance(Int.valueOf(20), EmptyList.getInstance())), EmptyList.getInstance())), 
							ConsCell.getInstance(ConsCell.getInstance(Symbol.getInstance(add), ConsCell.getInstance(Symbol.getInstance(x), ConsCell.getInstance(Symbol.getInstance(y), EmptyList.getInstance()))), EmptyList.getInstance()));
	
		SExpression tmp1 = ConsCell.getInstance(Symbol.getInstance("x"), ConsCell.getInstance(Int.valueOf(10), EmptyList.getInstance()));
		SExpression tmp2 = ConsCell.getInstance(Symbol.getInstance("y"), ConsCell.getInstance(Int.valueOf(20), EmptyList.getInstance()));
		SExpression tmp3 = ConsCell.getInstance(tmp1, ConsCell.getInstance(tmp2, EmptyList.getInstance()));
		SExpression tmp4 = ConsCell.getInstance(Symbol.getInstance("+"), ConsCell.getInstance(Symbol.getInstance("x"), ConsCell.getInstance(Symbol.getInstance("y"), EmptyList.getInstance())));
	
		SExpression tmp5 = ConsCell.getInstance(tmp3, ConsCell.getInstance(tmp4, EmptyList.getInstance()));
		SExpression tmp = ConsCell.getInstance(tmp5, EmptyList.getInstance());
		
		
		try {
			Int actual = (Int)test.apply(tmp, env);
			Int expected = Int.valueOf(30);
			assertThat(actual, is(expected));
		} catch (InvalidApplicationException e) {
			fail("Error");
		} catch (ListRequiredException e) {
		} catch (WrongNumberOfArgumentsException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
}
