package lisp.eval;

import lisp.exception.IntRequiredException;
import lisp.exception.ListRequiredException;
import lisp.exception.WrongNumberOfArgumentsException;



/**
 *
 * 組込み手続き canvas
 * @author bp15046
 *
 */



public class Canvas extends Subroutine {
	@Override
	SExpression apply(SExpression sexp) throws IntRequiredException, ListRequiredException, WrongNumberOfArgumentsException {
		if ( !(sexp instanceof ConsCell) ) {
			throw new ListRequiredException();
		}

		SExpression width = ((ConsCell)sexp).getCar();
		SExpression tmp_height = ((ConsCell)sexp).getCdr();

		if ( !(tmp_height instanceof ConsCell) ) {
			throw new ListRequiredException();
		}

		if ( !(((ConsCell)tmp_height).getCdr() instanceof EmptyList) ) {
			throw new WrongNumberOfArgumentsException();
		}

		SExpression height = ((ConsCell)tmp_height).getCar();

		if ( !(width instanceof Int && height instanceof Int) ) {
			throw new IntRequiredException();
		}

		int widthValue = ((Int)width).getValue();
		int heightValue = ((Int)height).getValue();

		Fund canvas = new Fund(widthValue, heightValue);

		return canvas;
	}



	@Override
	public String toString() {
		return "#<subr canvas>";
	}



}
