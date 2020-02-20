package lisp.eval;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.WrongNumberOfArgumentsException;


/**
 * 
 * 組込み手続き eq? のテスト
 * @author bp15068
 *
 */



public class EqTest {
	@Test
	// (eq? 'a 'b)
	public void テスト1 () {
		Eq test = new Eq();
		String a = "a";
		String b = "b";
		boolean value = false;
		
		SExpression sexp = ConsCell.getInstance(Symbol.getInstance(a),ConsCell.getInstance(Symbol.getInstance(b), EmptyList.getInstance()));
	
		try {
			SExpression actual = test.apply(sexp);
			Bool expected = Bool.valueOf(value);
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
		
		
	}
	
	@Test
	// (eq? 'a 'a)
	public void テスト2 () {
		Eq test = new Eq();
		String a = "a";
		boolean value = true;
		
		SExpression sexp = ConsCell.getInstance(Symbol.getInstance(a),ConsCell.getInstance(Symbol.getInstance(a), EmptyList.getInstance()));
		
		try {
			SExpression actual = test.apply(sexp);
			Bool expected = Bool.valueOf(value);
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
		
		
	}
	
	
	@Test
	// (eq? '(a b) '(a b))
	public void テスト3 () {
		Eq test = new Eq();
		String a = "a";
		String b = "b";
		boolean value = false;
		
		ConsCell.getInstance(ConsCell.getInstance(Symbol.getInstance(a),ConsCell.getInstance(Symbol.getInstance(b),EmptyList.getInstance())),EmptyList.getInstance());
		
		SExpression sexp = ConsCell.getInstance(ConsCell.getInstance(ConsCell.getInstance(Symbol.getInstance(a),ConsCell.getInstance(Symbol.getInstance(b),EmptyList.getInstance())),EmptyList.getInstance()) ,
				                                ConsCell.getInstance(ConsCell.getInstance(ConsCell.getInstance(Symbol.getInstance(a),ConsCell.getInstance(Symbol.getInstance(b),EmptyList.getInstance())),EmptyList.getInstance()), EmptyList.getInstance()));
				
		try {
			SExpression actual = test.apply(sexp);
			Bool expected = Bool.valueOf(value);
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
		
		
	}
	
	
	@Test
	// (eq? 'a 'a 'a)
	public void テスト4 () {
		Eq test = new Eq();
		String a = "a";
		
		SExpression sexp = ConsCell.getInstance(Symbol.getInstance(a),ConsCell.getInstance(Symbol.getInstance(a),ConsCell.getInstance(Symbol.getInstance(a), EmptyList.getInstance())));
		
		try {
			// この場合、WrongNumberOfArgumentsExceptionが投げられるのでそれを受け取って何もしなければよい
			SExpression actual = test.apply(sexp);
		} catch (WrongNumberOfArgumentsException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
		
		
	}
	
}

