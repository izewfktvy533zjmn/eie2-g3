package lisp.eval;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.CanvasRequiredException;
import lisp.exception.ListRequiredException;
import lisp.exception.NumberRequiredException;

/**
 * 
 * 組込み手続き drawline のテスト
 * @author bp15046
 *
 */

public class DrawlineTest {
	@Test
	// (drawline Fundオブジェクト '(((0 0) (100 100))))
	public void テスト1 () {
		Drawline test = new Drawline();
		
		Fund fund = new Fund(500, 500);
		SExpression sp = ConsCell.getInstance(Int.valueOf(0), ConsCell.getInstance(Int.valueOf(0), EmptyList.getInstance()));
		SExpression ep = ConsCell.getInstance(Int.valueOf(100), ConsCell.getInstance(Int.valueOf(100), EmptyList.getInstance()));
		SExpression keiro = ConsCell.getInstance(sp, ConsCell.getInstance(ep, EmptyList.getInstance()));
		keiro = ConsCell.getInstance(keiro, EmptyList.getInstance());
		SExpression sexp = ConsCell.getInstance(fund, ConsCell.getInstance(keiro, EmptyList.getInstance()));
		
		
		try {
			SExpression actual = test.apply(sexp);
			assertThat(actual, is(instanceOf(Undef.class)));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
	
	
	
	@Test
	// (drawline Fundオブジェクト '(((0 0) (100.0 100.0))))
	public void テスト2 () {
		Drawline test = new Drawline();
		
		Fund fund = new Fund(500, 500);
		SExpression sp = ConsCell.getInstance(Int.valueOf(0), ConsCell.getInstance(Int.valueOf(0), EmptyList.getInstance()));
		SExpression ep = ConsCell.getInstance(Dbl.valueOf(100.0), ConsCell.getInstance(Dbl.valueOf(100.0), EmptyList.getInstance()));
		SExpression keiro = ConsCell.getInstance(sp, ConsCell.getInstance(ep, EmptyList.getInstance()));
		keiro = ConsCell.getInstance(keiro, EmptyList.getInstance());
		SExpression sexp = ConsCell.getInstance(fund, ConsCell.getInstance(keiro, EmptyList.getInstance()));
		
		
		try {
			SExpression actual = test.apply(sexp);
			assertThat(actual, is(instanceOf(Undef.class)));
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
	
	
	
	@Test
	// (drawline 1 '(((0 0) (100 100))))
	public void テスト3 () {
		Drawline test = new Drawline();
		
		SExpression sp = ConsCell.getInstance(Int.valueOf(0), ConsCell.getInstance(Int.valueOf(0), EmptyList.getInstance()));
		SExpression ep = ConsCell.getInstance(Int.valueOf(100), ConsCell.getInstance(Int.valueOf(100), EmptyList.getInstance()));
		SExpression keiro = ConsCell.getInstance(sp, ConsCell.getInstance(ep, EmptyList.getInstance()));
		keiro = ConsCell.getInstance(keiro, EmptyList.getInstance());
		SExpression sexp = ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(keiro, EmptyList.getInstance()));
		
		try {
			SExpression actual = test.apply(sexp);
		} catch (CanvasRequiredException e) {
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
	
	
	
	@Test
	// (drawline Fundオブジェクト '(((x y) (100 100))))
	public void テスト4 () {
		Drawline test = new Drawline();
		
		Fund fund = new Fund(500, 500);
		SExpression sp = ConsCell.getInstance(Symbol.getInstance("x"), ConsCell.getInstance(Symbol.getInstance("y"), EmptyList.getInstance()));
		SExpression ep = ConsCell.getInstance(Int.valueOf(100), ConsCell.getInstance(Int.valueOf(100), EmptyList.getInstance()));
		SExpression keiro = ConsCell.getInstance(sp, ConsCell.getInstance(ep, EmptyList.getInstance()));
		keiro = ConsCell.getInstance(keiro, EmptyList.getInstance());
		SExpression sexp = ConsCell.getInstance(fund, ConsCell.getInstance(keiro, EmptyList.getInstance()));
		
		
		try {
			SExpression actual = test.apply(sexp);
		} catch (NumberRequiredException e) {
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}
	
	
	
	@Test
	// (drawline Fundオブジェクト)
	public void テスト5 () {
		Drawline test = new Drawline();
		
		Fund fund = new Fund(500, 500);
		//SExpression sp = ConsCell.getInstance(Int.valueOf(0), ConsCell.getInstance(Int.valueOf(0), EmptyList.getInstance()));
		//SExpression keiro = ConsCell.getInstance(sp, EmptyList.getInstance());
		//keiro = ConsCell.getInstance(keiro, EmptyList.getInstance());
		SExpression sexp = ConsCell.getInstance(fund,EmptyList.getInstance());
		
		
		try {
			SExpression actual = test.apply(sexp);
		} catch (ListRequiredException e) {
		//} catch (NumberRequiredException e) {
		} catch (Exception e) {
			fail("Exception thrown");
		}
	}

}
