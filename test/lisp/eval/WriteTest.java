package lisp.eval;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;



/**
 * 
 * 組込み手続き write のテスト
 * @author bp15046
 *
 */



public class WriteTest {

	@Test
	// (write 1)
	public void テスト1 () {
		
		Write test = new Write();
		SExpression sexp = ConsCell.getInstance(Int.valueOf(1), EmptyList.getInstance());
	
		try {
			SExpression actual = test.apply(sexp);
			Undef expected = Undef.getInstance();
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
}
