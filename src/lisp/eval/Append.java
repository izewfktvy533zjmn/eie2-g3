package lisp.eval;

import lisp.exception.ListRequiredException;



/**
 * 組込み手続き append
 * @author bp15024
 * @author bp15046
 *
 */



public class Append extends Subroutine{
	
	@Override
	SExpression apply(SExpression sexp) throws ListRequiredException {

		
		 if (sexp instanceof ConsCell) {
			 ConsCell tmp = (ConsCell)sexp;
			 SExpression car = tmp.getCar();
			 SExpression cdr = tmp.getCdr();

			 //System.out.println("Append | car : " + car);
			 //System.out.println("Append | cdr : " + cdr);
			 
			 
			 try {
			 			 
			 	if(cdr instanceof EmptyList) {
					return car;
				}
			 	else {

				 	SExpression tmpCar = car;
				 	
					// carが空リストだった場合
					if (tmpCar instanceof EmptyList) {
						car = ((ConsCell)cdr).getCar();
						tmpCar = ((ConsCell)cdr).getCdr();

						if ( !(tmpCar instanceof ConsCell) ) {
							return car;
						}
					}

					// carの中の最後のcdrにあるEmpytListの中を上書きしたものを返す
					while (true) {
						 if ( !(tmpCar instanceof ConsCell) ) {
		  					throw new ListRequiredException();
					 	}else {
					 		if (((ConsCell)tmpCar).getCdr() instanceof EmptyList) {
								break;
					 		}
					 		else {
								tmpCar = ((ConsCell)tmpCar).getCdr();
					 		}
					 	}
				 	}
				 
				 
				 	((ConsCell)tmpCar).setCdr(apply(cdr));
				 	return car;
			 	}
			 }catch(ListRequiredException e) {
					throw e;
			 }
				 
		
		 }
		 else {
		  throw new ListRequiredException();
		 }
	}



	@Override
	public String toString() {
		return "#<subr append>";
	}

}

