package lisp.eval;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.ExitException;
import lisp.exception.WrongNumberOfArgumentsException;



/**
 * 
 * 組込み手続き + のテスト
 * @author bp15046
 *
 */



public class ExitTest {
	@Test
	// (exit)
	public void テスト1 () {
		Exit test = new Exit();
		
		SExpression sexp = EmptyList.getInstance();
		
		try {
			SExpression actual = test.apply(sexp);
		} catch (ExitException e) {
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

	
	@Test
	// (exit 1)
	public void テスト2 () {
		Exit test = new Exit();
		
		SExpression sexp = ConsCell.getInstance(Int.valueOf(1), EmptyList.getInstance());

		
		try {
			SExpression actual = test.apply(sexp);
		} catch (WrongNumberOfArgumentsException e) {
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
}
