package lisp.eval;

import lisp.exception.CanvasRequiredException;
import lisp.exception.ListRequiredException;
import lisp.exception.NumberRequiredException;



/**
 *
 * 組込み手続き drawline
 * @author bp15046
 *
 */



public class Drawline extends Subroutine {
	@Override 
	SExpression apply(SExpression sexp) throws CanvasRequiredException, ListRequiredException, NumberRequiredException {
		if ( !(sexp instanceof ConsCell) ) {
			throw new ListRequiredException();
		}
		

		SExpression tmp = ((ConsCell)sexp).getCar();


		if ( !(tmp instanceof Fund) ) {
			throw new CanvasRequiredException();
		}

		
		Fund canvas = (Fund)tmp;
		

		//SExpression keiro_list = ((ConsCell)(((ConsCell)sexp).getCdr())).getCar();
		SExpression keiro_list = ((ConsCell)sexp).getCdr();


		if ( !(keiro_list instanceof ConsCell) ) {
			throw new ListRequiredException();
		}

	
		keiro_list = ((ConsCell)keiro_list).getCar();

		
		if ( !(keiro_list instanceof ConsCell) ) {		
			throw new ListRequiredException();
		}


		SExpression tmpKeiro = keiro_list;
		SExpression start_point;
		SExpression end_point;
		SExpression sx;
		SExpression sy;
		SExpression ex;
		SExpression ey;
		double tmp_double;
		
		
		while(true) {
			if (((ConsCell)tmpKeiro).getCdr() instanceof EmptyList) {
				start_point = ((ConsCell)tmpKeiro).getCar();
				start_point = ((ConsCell)start_point).getCar();

				end_point = ((ConsCell)tmpKeiro).getCar();
				end_point = ((ConsCell)end_point).getCdr();
				end_point = ((ConsCell)end_point).getCar();



				//System.out.println(start_point);
				//System.out.println(end_point);
			
				

				if ( !(start_point instanceof ConsCell) ) {
					throw new ListRequiredException();
				}

				if ( !(((ConsCell)start_point).getCdr() instanceof ConsCell) ) {
					throw new ListRequiredException();
				}
				
				if ( !(end_point instanceof ConsCell) ) {
					throw new ListRequiredException();
				}
				
				if ( !(((ConsCell)end_point).getCdr() instanceof ConsCell) ) {
					throw new ListRequiredException();
				}



				sx = ((ConsCell)start_point).getCar();
				sy = ((ConsCell)((ConsCell)start_point).getCdr()).getCar();
			
				ex = ((ConsCell)end_point).getCar();
				ey = ((ConsCell)((ConsCell)end_point).getCdr()).getCar();
			
			
			
				//System.out.println(sx);
				//System.out.println(sy);
				//System.out.println(ex);
				//System.out.println(ey);


		
				if (sx instanceof Int) {
				}
				else if (sx instanceof Dbl) {
				}
				else {
					throw new NumberRequiredException();
				}
			
				if (sy instanceof Int) {
				}
				else if (sy instanceof Dbl) {
				}
				else {
					throw new NumberRequiredException();
				}
				
				if (ex instanceof Int) {
				}
				else if (ex instanceof Dbl) {
				}
				else {
					throw new NumberRequiredException();
				}
			
				if (ey instanceof Int) {
				}
				else if (ey instanceof Dbl) {
				}
				else {
					throw new NumberRequiredException();
				}
			
				break;
			}
			else if ( !(((ConsCell)tmpKeiro).getCdr() instanceof ConsCell) ) {
				throw new ListRequiredException();
			}
			else {
				tmpKeiro = ((ConsCell)tmpKeiro).getCdr();
			}
		}
			
					
		canvas.setKeiro(keiro_list);

		canvas.repaint();

		
		try {	
			Thread.sleep(250);
		} catch(Exception e) {}


		return Undef.getInstance();
	}



	public String toString() {
		return "#<subr drawline>";
	}

}
