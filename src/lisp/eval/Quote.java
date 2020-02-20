package lisp.eval;

import lisp.exception.SyntaxErrorException;



/**
 * 特殊形式 : quote
 * @author bp15046
 */



public class Quote extends SpecialForm {
	@Override
	SExpression apply(SExpression sexp, Environment env) throws SyntaxErrorException {
		if (sexp instanceof ConsCell) {
			ConsCell tmp = (ConsCell)sexp;

			if ( !(tmp.getCdr() instanceof EmptyList) ) {
       			throw new SyntaxErrorException();
			}

			return tmp.getCar();
      	}
      	else {
       		throw new SyntaxErrorException();
      	}
   	}



   	@Override 
   	public String toString() {
      	return "#<syntax quote>";
   	}

}
