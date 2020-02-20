package lisp.eval;

import lisp.exception.NumberRequiredException;


/**
 *組み込み手続き : Multi
 * @author bp15068
 */
public class Multi extends Subroutine {

	
	@Override
	   SExpression apply(SExpression sexp) throws NumberRequiredException {
		//System.out.println(sexp);
		  //applyメソッドの引数がConscellの場合
		  if (sexp instanceof ConsCell) {
			 ConsCell tmp = (ConsCell)sexp;
			 
			 //car部分が整数値の場合
			 if(tmp.getCar() instanceof Int) {
				 //returnはSExpressionかも
				 Int tmp_kari = (Int)tmp.getCar();
				 SExpression cont = (SExpression)tmp.getCdr();
				 try {
					 SExpression nikon = this.apply(cont);

					 // nikonがIntオブジェクトの場合
					 if(nikon instanceof Int) {
						 int nikon_int = ((Int)nikon).getValue();
						 nikon_int *= tmp_kari.getValue();
						 return (SExpression)Int.valueOf(nikon_int);
					 }
					 // nikonがDblオブジェクトの場合
					 else if(nikon instanceof Dbl) {
						 double nikon_double = ((Dbl)nikon).getValue();
						 nikon_double *= (double)(tmp_kari.getValue());
						 return (SExpression)Dbl.valueOf(nikon_double);
					 }
					 else {
						 throw new NumberRequiredException();
					 }				
				} catch(NumberRequiredException e) {
					throw e;
				}
			 }
				 
			 //car部分が浮動小数点数の時
			 if(tmp.getCar() instanceof Dbl) {
				 //returnはSExpressionかも
				 Dbl tmp_kari = (Dbl)tmp.getCar();
				 SExpression cont = (SExpression)tmp.getCdr();
				 try {
					 SExpression nikon = this.apply(cont);

					 // nikonがIntオブジェクトの場合
					 if(nikon instanceof Int) {
						 double nikon_double = (double)((Int)nikon).getValue();
						 nikon_double *= tmp_kari.getValue();
						 return (SExpression)Dbl.valueOf(nikon_double);
					 }
					 // nikonがDblオブジェクトの場合
					 else if(nikon instanceof Dbl) {
						 double nikon_double = ((Dbl)nikon).getValue();
						 nikon_double *= (double)tmp_kari.getValue();
						 return (SExpression)Dbl.valueOf(nikon_double);
					 }
					 else {
						 throw new NumberRequiredException();
					 }				
				} catch(NumberRequiredException e) {
					throw e;
				}
			 }
			 
			 //car部分がそれ以外の場合
			 else {
				 throw new NumberRequiredException();
			 }
		  }
		  //applyメソッドの引数がEmptyListの場合
		  else if(sexp instanceof EmptyList) {
			 return (SExpression)Int.valueOf(1); 
		  }
		  
		  //applyメソッドの引数がそれ以外の場合
		  else {
			  throw new NumberRequiredException();
		  }
	}
	
	   @Override 
	   public String toString() {
	      return "#<subr *>";
	   }
}
