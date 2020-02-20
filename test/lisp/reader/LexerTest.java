package lisp.reader;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;
import lisp.exception.LispException;
import lisp.exception.SyntaxErrorException;



/**
 * 
 * @author bp15046
 *
 */
public class LexerTest {
	
	@Test
	public void テスト01 () {
		BufferedReader br = new BufferedReader(new StringReader("　"));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
		} catch (SyntaxErrorException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	
	
	@Test
	public void テスト02 () {
		BufferedReader br = new BufferedReader(new StringReader("\r1"));
		Lexer lexer = new Lexer(br);
		try {
			Token.Kind actual = lexer.getNextToken().getKind();
			Token.Kind expected = Token.Kind.NUMBER;
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト03 () {
		BufferedReader br = new BufferedReader(new StringReader("\n1"));
		Lexer lexer = new Lexer(br);
		try {
			Token.Kind actual = lexer.getNextToken().getKind();
			Token.Kind expected = Token.Kind.NUMBER;
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト04 () {
		BufferedReader br = new BufferedReader(new StringReader("\t1"));
		Lexer lexer = new Lexer(br);
		try {
			Token.Kind actual = lexer.getNextToken().getKind();
			Token.Kind expected = Token.Kind.NUMBER;
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト05 () {
		BufferedReader br = new BufferedReader(new StringReader("1"));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
			
			Token.Kind actual1 = actual.getKind();
			Token.Kind expected1 = Token.Kind.NUMBER;
			assertThat(actual1, is(expected1));
			
			int actual2 = actual.getIntValue();
			int expected2 = 1;
			assertThat(actual2, is(expected2));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト06 () {
		BufferedReader br = new BufferedReader(new StringReader("1.1"));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
			
			Token.Kind actual1 = actual.getKind();
			Token.Kind expected1 = Token.Kind.REALNUMBER;
			assertThat(actual1, is(expected1));
			
			double actual2 = actual.getDoubleValue();
			double expected2 = 1.1;
			assertThat(actual2, is(expected2));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト07 () {
		BufferedReader br = new BufferedReader(new StringReader("a"));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
			
			Token.Kind actual1 = actual.getKind();
			Token.Kind expected1 = Token.Kind.SYMBOL;
			assertThat(actual1, is(expected1));
			
			String actual2 = actual.getSymbolValue();
			String expected2 = "a";
			assertThat(actual2, is(expected2));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト08 () {
		BufferedReader br = new BufferedReader(new StringReader("Z"));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
			
			Token.Kind actual1 = actual.getKind();
			Token.Kind expected1 = Token.Kind.SYMBOL;
			assertThat(actual1, is(expected1));
			
			String actual2 = actual.getSymbolValue();
			String expected2 = "Z";
			assertThat(actual2, is(expected2));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト09 () {
		BufferedReader br = new BufferedReader(new StringReader("+"));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
			
			Token.Kind actual1 = actual.getKind();
			Token.Kind expected1 = Token.Kind.SYMBOL;
			assertThat(actual1, is(expected1));
			
			String actual2 = actual.getSymbolValue();
			String expected2 = "+";
			assertThat(actual2, is(expected2));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト10 () {
		BufferedReader br = new BufferedReader(new StringReader("="));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
			
			Token.Kind actual1 = actual.getKind();
			Token.Kind expected1 = Token.Kind.SYMBOL;
			assertThat(actual1, is(expected1));
			
			String actual2 = actual.getSymbolValue();
			String expected2 = "=";
			assertThat(actual2, is(expected2));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト11 () {
		BufferedReader br = new BufferedReader(new StringReader("!"));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
			
			Token.Kind actual1 = actual.getKind();
			Token.Kind expected1 = Token.Kind.SYMBOL;
			assertThat(actual1, is(expected1));
			
			String actual2 = actual.getSymbolValue();
			String expected2 = "!";
			assertThat(actual2, is(expected2));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト12 () {
		BufferedReader br = new BufferedReader(new StringReader("?"));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
			
			Token.Kind actual1 = actual.getKind();
			Token.Kind expected1 = Token.Kind.SYMBOL;
			assertThat(actual1, is(expected1));
			
			String actual2 = actual.getSymbolValue();
			String expected2 = "?";
			assertThat(actual2, is(expected2));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト13 () {
		BufferedReader br = new BufferedReader(new StringReader("_"));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
			
			Token.Kind actual1 = actual.getKind();
			Token.Kind expected1 = Token.Kind.SYMBOL;
			assertThat(actual1, is(expected1));
			
			String actual2 = actual.getSymbolValue();
			String expected2 = "_";
			assertThat(actual2, is(expected2));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト14 () {
		BufferedReader br = new BufferedReader(new StringReader("("));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
			
			Token.Kind actual1 = actual.getKind();
			Token.Kind expected1 = Token.Kind.LEFTPAR;
			assertThat(actual1, is(expected1));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト15 () {
		BufferedReader br = new BufferedReader(new StringReader(")"));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
			
			Token.Kind actual1 = actual.getKind();
			Token.Kind expected1 = Token.Kind.RIGHTPAR;
			assertThat(actual1, is(expected1));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト16 () {
		BufferedReader br = new BufferedReader(new StringReader("."));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
			
			Token.Kind actual1 = actual.getKind();
			Token.Kind expected1 = Token.Kind.DOT;
			assertThat(actual1, is(expected1));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト17 () {
		BufferedReader br = new BufferedReader(new StringReader("'"));
		Lexer lexer = new Lexer(br);
		try {
			Token.Kind actual = lexer.getNextToken().getKind();
			Token.Kind expected = Token.Kind.QUOTE;
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト18 () {
		BufferedReader br = new BufferedReader(new StringReader("#t"));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
			
			Token.Kind actual1 = actual.getKind();
			Token.Kind expected1 = Token.Kind.BOOLEAN;
			assertThat(actual1, is(expected1));
			
			boolean actual2 = actual.getBooleanValue();
			boolean expected2 = true;
			assertThat(actual2, is(expected2));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト19 () {
		BufferedReader br = new BufferedReader(new StringReader("#f"));
		Lexer lexer = new Lexer(br);
		try {
			Token actual = lexer.getNextToken();
			
			Token.Kind actual1 = actual.getKind();
			Token.Kind expected1 = Token.Kind.BOOLEAN;
			assertThat(actual1, is(expected1));
			
			boolean actual2 = actual.getBooleanValue();
			boolean expected2 = false;
			assertThat(actual2, is(expected2));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト20 () {
		BufferedReader br = new BufferedReader(new StringReader("#p"));
		Lexer lexer = new Lexer(br);
		try {
			Token.Kind actual = lexer.getNextToken().getKind();
		} catch (SyntaxErrorException e) {
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	
	@Test
	public void テスト21 () {
		BufferedReader br = new BufferedReader(new StringReader(";test\n1"));
		Lexer lexer = new Lexer(br);
		try {
			Token.Kind actual = lexer.getNextToken().getKind();
			Token.Kind expected = Token.Kind.NUMBER;
			assertThat(actual, is(expected));
		} catch(Exception e) {
			fail("Exception thrown");
		}
	}
	

}
