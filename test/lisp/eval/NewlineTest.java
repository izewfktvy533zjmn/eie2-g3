package lisp.eval;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;



/**
 * 
 * 組込み手続き + のテスト
 * @author bp15046
 *
 */



public class NewlineTest {

	@Test
	// (newline)
	public void テスト1 () {
		
		Newline test = new Newline();
		SExpression sexp = EmptyList.getInstance();
	
		try {
			SExpression actual = test.apply(sexp);
			Undef expected = Undef.getInstance();
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
}
