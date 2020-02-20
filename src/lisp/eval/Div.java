package lisp.eval;

import lisp.exception.DivisionException;
import lisp.exception.NumberRequiredException;



/**
 *
 *組み込み手続き : Div
 *@author bp15068
 *@author bp15046
 *
 */



public class Div extends Subroutine{
	@Override
	SExpression apply(SExpression sexp) throws NumberRequiredException, DivisionException {
		
		//applyメソッドの引数がConscellの場合
		if (sexp instanceof ConsCell) {
			ConsCell tmp = (ConsCell)sexp;
			SExpression cont = (SExpression)tmp.getCdr();
	
			try{
				Multi multier = new Multi();
					
				//car部分が整数値の場合
				if(tmp.getCar() instanceof Int) {
						
					if(tmp.getCdr() instanceof EmptyList) {
						  int sexps = ((Int)tmp.getCar()).getValue();
						  int one = (Int.valueOf(1)).getValue();
						  
						  if (sexps == 0.0) {
						    	throw new DivisionException();
						  }
						  
						  return Dbl.valueOf((double)one/(double)sexps);
					 }
						 
					 else {
						 
						 SExpression get = multier.apply(cont);
						 Int tmp_kari = (Int)tmp.getCar();
					
						 if(get instanceof Int) {
							 Int cdr = (Int)get;
							 int anscdr = cdr.getValue();
							 //	Int tmp_kari = (Int)tmp.getCar();
							 int anscar = tmp_kari.getValue();
							 
							 if (anscdr == 0.0) {
						    	throw new DivisionException();
						     }
						  	 
							 return (SExpression)Dbl.valueOf((double)anscar/(double)anscdr);
						 }
						 else if(get instanceof Dbl) {
							 Dbl cdr =(Dbl)get;
							 double anscdr =cdr.getValue();
							 //Dbl tmp_kari = (Dbl)tmp.getCar();
							 double anscar = (double)tmp_kari.getValue();
							 //double anscar =tmp_kari.getValue();
						  	 
							 if (anscdr == 0.0) {
						    	throw new DivisionException();
						     }

							 return (SExpression)Dbl.valueOf(anscar/anscdr);
						 }
						 else {
							 System.out.println("苦るわけない");
							 throw new NumberRequiredException();
						 }
					 }	
				}
					
				//car部分が浮動小数点数値の場合
				else if(tmp.getCar() instanceof Dbl) {
						
					if(tmp.getCdr() instanceof EmptyList) {
						  double sexps = ((Dbl)tmp.getCar()).getValue();
						  double one = (Dbl.valueOf(1.0)).getValue();
						  
						  if (sexps == 0.0) {
						    throw new DivisionException();
						  }

						  return (SExpression)Dbl.valueOf(one/sexps);
					 }
						 
					 else {
						
						 SExpression get = multier.apply(cont);
						 Dbl tmp_kari = (Dbl)tmp.getCar();
						
						 if(get instanceof Int) {
							 Int cdr = (Int)get;
							 double anscdr = (double)cdr.getValue();
							 //Dbl tmp_kari = (Dbl)tmp.getCar();
							 double anscar =tmp_kari.getValue();
							 
							 if (anscdr == 0.0) {
							 	throw new DivisionException();
							 }
						
							 return (SExpression)Dbl.valueOf(anscar/anscdr);
						 }
						 else if(get instanceof Dbl) {
							 Dbl cdr =(Dbl)get;
							 double anscdr =cdr.getValue();
							 //Dbl tmp_kari = (Dbl)tmp.getCar();
							 double anscar =tmp_kari.getValue();
							
							 if (anscdr == 0.0) {
							 	throw new DivisionException();
							 }

							 return (SExpression)Dbl.valueOf(anscar/anscdr);
						 }
						 else {
							 System.out.println("苦るわけない");
							 throw new NumberRequiredException();
						 }
						
					 }
					/*	Dbl cdr =(Dbl)multier.apply(cont);
						Dbl tmp_kari = (Dbl)tmp.getCar();
						double anscar =tmp_kari.getValue();
						double anscdr =cdr.getValue();
					 	
						return (SExpression)Dbl.valueOf(anscar/anscdr);*/
				}
				else {
					throw new NumberRequiredException();
				}
			} catch(NumberRequiredException e) {
				throw e;	
			}
		}
			
		//applyメソッドの引数が空リストの場合
		else if(sexp instanceof EmptyList) {
			return (SExpression)Int.valueOf(1);
		}
			
		//applyメソッドの引数がそれ以外の場合
		else {
			throw new  NumberRequiredException();
		}
	}

		
	@Override 
	   public String toString() {
	      return "#<subr />";
	   }

}
