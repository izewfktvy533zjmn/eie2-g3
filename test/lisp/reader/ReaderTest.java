package lisp.reader;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import lisp.eval.Bool;
import lisp.eval.ConsCell;
import lisp.eval.Dbl;
import lisp.eval.EmptyList;
import lisp.eval.Int;
import lisp.eval.Symbol;
import lisp.exception.LispException;

public class ReaderTest {
	@Test
	public void テスト1 () {
		String input = "1";
		int value = 1;
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			Int actual = (Int)reader.read();
			Int expected = Int.valueOf(value);
			assertThat(actual, is(expected));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void テスト2 () {
		String input = "1.1";
		double value = 1.1;
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			Dbl actual = (Dbl)reader.read();
			Dbl expected = Dbl.valueOf(value);
			assertThat(actual, is(instanceOf(Dbl.class)));
			
			double actual1 = actual.getValue();
			double expected1 = expected.getValue();
			assertThat(actual1, is(expected1));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}
	

	@Test
	public void テスト3 () {
		String input = "a";
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			Symbol actual = (Symbol)reader.read();
			Symbol expected = Symbol.getInstance(input);
			assertThat(actual, is(expected));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void テスト4 () {
		String input = "#t";
		boolean value = true;
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			Bool actual = (Bool)reader.read();
			Bool expected = Bool.valueOf(value);
			assertThat(actual, is(expected));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void テスト5 () {
		String input = "#f";
		boolean value = false;
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			Bool actual = (Bool)reader.read();
			Bool expected = Bool.valueOf(value);
			assertThat(actual, is(expected));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void テスト6 () {
		String input = "'(tmp)";
		boolean value = false;
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			ConsCell actual = (ConsCell)reader.read();
			ConsCell expected = ConsCell.getInstance(Symbol.getInstance("quote"), ConsCell.getInstance(ConsCell.getInstance(Symbol.getInstance("tmp"), EmptyList.getInstance()), EmptyList.getInstance()));
			
			Symbol actual1 = (Symbol)actual.getCar();
			Symbol expected1 = (Symbol)expected.getCar();
			assertThat(actual1, is(expected1));
			
			actual = (ConsCell)actual.getCdr();
			expected = (ConsCell)expected.getCdr();
			
			EmptyList actual2 = (EmptyList)actual.getCdr();
			EmptyList expected2 = (EmptyList)expected.getCdr();
			assertThat(actual2, is(expected2));
			
			actual = (ConsCell)actual.getCar();
			expected = (ConsCell)expected.getCar();
			
			Symbol actual3 = (Symbol)actual.getCar();
			Symbol expected3 = (Symbol)expected.getCar();
			assertThat(actual3, is(expected3));
			
			EmptyList actual4 = (EmptyList)actual.getCdr();
			EmptyList expected4 = (EmptyList)expected.getCdr();
			assertThat(actual4, is(expected4));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void テスト7 () {
		String input = "()";
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			EmptyList actual = (EmptyList)reader.read();
			EmptyList expected = EmptyList.getInstance();
			assertThat(actual, is(expected));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void テスト8 () {
		String car = "a";
		String cdr = "b";
		String input = "("+car+" . "+cdr+")";
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			ConsCell cell = (ConsCell)reader.read();
			Symbol actual = (Symbol)cell.getCar();
			Symbol expected = Symbol.getInstance(car);
			assertThat(actual, is(expected));
			
			actual = (Symbol)cell.getCdr();
			expected = Symbol.getInstance(cdr);
			assertThat(actual, is(expected));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void テスト9 () {
		String car = "a";
		String cdr_car = "b";
		String cdr_cdr = "()";
		String input = "("+car+" . ("+cdr_car+ " . "+cdr_cdr+"))";
		BufferedReader br = new BufferedReader(new StringReader(input));
		Reader reader = new Reader(br);
		try {
			ConsCell actual = (ConsCell)reader.read();
			ConsCell expected = ConsCell.getInstance(Symbol.getInstance(car), ConsCell.getInstance(Symbol.getInstance(cdr_car), EmptyList.getInstance()));
			
			//a
			Symbol actual1 = (Symbol)actual.getCar();
			Symbol expected1= (Symbol)expected.getCar();
			assertThat(actual1, is(expected1));
			
			actual = (ConsCell)actual.getCdr();
			expected = (ConsCell)expected.getCdr();
			
			// b
			Symbol actual2 = (Symbol)actual.getCar();
			Symbol expected2= (Symbol)expected.getCar();
			assertThat(actual2, is(expected2));
			
			// ()
			EmptyList actual3 = (EmptyList)actual.getCdr();
			EmptyList expected3= (EmptyList)expected.getCdr();
			assertThat(actual3, is(expected3));
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (LispException e) {
			fail(e.getMessage());
		} catch (ClassCastException e) {
			fail(e.getMessage());
		}
	}


}
