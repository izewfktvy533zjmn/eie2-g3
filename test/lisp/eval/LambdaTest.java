package lisp.eval;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import org.junit.Test;

import lisp.exception.WrongNumberOfArgumentsException;

/**
 * @author BP15043
 * @author bp15046
 */

public class LambdaTest {
	@Test
	// (lambda (x y) x)
	public void テスト1 () {
		Environment env = new Environment(null);
		Lambda test = new Lambda();
		String x = "x";
		String y = "y";
		
		SExpression sexp = ConsCell.getInstance(ConsCell.getInstance(Symbol.getInstance(x), ConsCell.getInstance(Symbol.getInstance(y), EmptyList.getInstance())), ConsCell.getInstance(Symbol.getInstance(x), EmptyList.getInstance()));
	
		try {
			SExpression actual = test.apply(sexp, env);
			assertThat(actual, is(instanceOf(Closure.class)));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	// ((lambda (x y) 1 2) 1)
	public void テスト2 () {
		Environment env = new Environment(null);
		Lambda test1 = new Lambda();
		String x = "x";
		String y = "y";
		
		SExpression sexp = ConsCell.getInstance(ConsCell.getInstance(Symbol.getInstance(x), ConsCell.getInstance(Symbol.getInstance(y), EmptyList.getInstance())), ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Int.valueOf(2), EmptyList.getInstance())));
		SExpression dummyArgs = ConsCell.getInstance(Symbol.getInstance(x), ConsCell.getInstance(Symbol.getInstance(y), EmptyList.getInstance()));
		SExpression body = ConsCell.getInstance(Int.valueOf(1), ConsCell.getInstance(Int.valueOf(2), EmptyList.getInstance()));
		
		try {
			test1.apply(sexp, env);
			Closure test2 = new Closure(env,dummyArgs ,body);
            SExpression actual = test2.apply(ConsCell.getInstance(Int.valueOf(1), EmptyList.getInstance()));
		} catch(WrongNumberOfArgumentsException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	// 副作用
	// (lambda (x y) x)
		public void テスト3 () {
			Environment env = new Environment(null);
			Lambda test = new Lambda();
			String x = "x";
			String y = "y";
			
			SExpression sexp = ConsCell.getInstance(ConsCell.getInstance(Symbol.getInstance(x), ConsCell.getInstance(Symbol.getInstance(y), EmptyList.getInstance())), ConsCell.getInstance(Symbol.getInstance(x), EmptyList.getInstance()));
		
			try {
				SExpression actual = test.apply(sexp, env);
		
				
				Environment actual1 = ((Closure)actual).getEnv();
				Environment expected1 = env;
				assertThat(actual1, is(expected1));
				
				
				ConsCell tmp_actual = (ConsCell)((Closure)actual).getDummyArgs();
				ConsCell tmp_expected = ConsCell.getInstance(Symbol.getInstance("x"), ConsCell.getInstance(Symbol.getInstance("y"), EmptyList.getInstance()));
				
				Symbol actual2 = (Symbol)tmp_actual.getCar();
				Symbol expected2 = (Symbol)tmp_expected.getCar();
				assertThat(actual2, is(expected2));
				
				tmp_actual = (ConsCell)tmp_actual.getCdr();
				tmp_expected = (ConsCell)tmp_expected.getCdr();
				
				Symbol actual3 = (Symbol)tmp_actual.getCar();
				Symbol expected3 = (Symbol)tmp_expected.getCar();
				assertThat(actual3, is(expected3));
				
				EmptyList actual4 = (EmptyList)tmp_actual.getCdr();
				EmptyList expected4 = (EmptyList)tmp_expected.getCdr();
				assertThat(actual4, is(expected4));
				
			
				tmp_actual = (ConsCell)((Closure)actual).getBody();
				tmp_expected = ConsCell.getInstance(Symbol.getInstance("x"), EmptyList.getInstance());
				
				Symbol actual5 = (Symbol)tmp_actual.getCar();
				Symbol expected5 = (Symbol)tmp_expected.getCar();
				assertThat(actual5, is(expected5));
				
				EmptyList actual6 = (EmptyList)tmp_actual.getCdr();
				EmptyList expected6 = (EmptyList)tmp_expected.getCdr();
				assertThat(actual6, is(expected6));
			} catch(Exception e) {
				fail("Exception thrown");
			}
		}
}
