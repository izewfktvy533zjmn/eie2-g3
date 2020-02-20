package lisp.eval;



import lisp.exception.ExitException;
import lisp.exception.WrongNumberOfArgumentsException;



/**
 * 組込み手続き : exit
 * @author bp15046
 */
public class Exit extends Subroutine {
	@Override
	SExpression apply(SExpression sexp) throws ExitException, WrongNumberOfArgumentsException {
		if (sexp instanceof EmptyList ){
			throw new ExitException();
		}
		else {
			throw new WrongNumberOfArgumentsException();
		}
	}
	
	@Override 
	public String toString() {
		return "#<subr exit>";
	}
}
