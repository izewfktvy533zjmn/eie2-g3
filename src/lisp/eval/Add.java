package lisp.eval;

import lisp.exception.NumberRequiredException;


/**
 *組み込み手続き : Add
 * @author bp15068
 */
public class Add extends Subroutine {
	
//private int sum=0;
//private Token token=null;
	
	@Override
	   SExpression apply(SExpression sexp) throws NumberRequiredException {
		//SExpression apply(SExpression sexp) throws PairRequiredException,OperationIsNotDefinedException {
	//	int value = lisp.reader.token.getIntValue();
		//int sum_int=0;
	//	double sum_dbl=0.0;
		//int check=0;
		//Int tmp_int_sum = Int.valueOf(0);
		//Dbl tmp_dbl_sum = Dbl.valueOf(0.0);
		
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

					 // nikonはIntオブジェクトの場合
					 if(nikon instanceof Int) {
						 int nikon_int = ((Int)nikon).getValue();
						 nikon_int += tmp_kari.getValue();
						 return (SExpression)Int.valueOf(nikon_int);
					 }
					 // nikonはDblオブジェクトの場合
					 else if(nikon instanceof Dbl) {
						 double nikon_double = ((Dbl)nikon).getValue();
						 nikon_double += (double)(tmp_kari.getValue());
						 return (SExpression)Dbl.valueOf(nikon_double);
					 }
					 else {
						 throw new NumberRequiredException();
					 }				
				} catch(NumberRequiredException e) {
					throw e;
				}
			 }
				 
			 //car部分が浮動小数点数の場合
			 if(tmp.getCar() instanceof Dbl) {
				 //returnはSExpressionかも
				 Dbl tmp_kari = (Dbl)tmp.getCar();
				 SExpression cont = (SExpression)tmp.getCdr();
				 try {
					 SExpression nikon = this.apply(cont);

					 // nikonがIntオブジェクトの場合
					 if(nikon instanceof Int) {
						 double nikon_double = (double)((Int)nikon).getValue();
						 nikon_double += tmp_kari.getValue();
						 return (SExpression)Dbl.valueOf(nikon_double);
					 }
					 // nikonがDblオブジェクトの場合
					 else if(nikon instanceof Dbl) {
						 double nikon_double = ((Dbl)nikon).getValue();
						 nikon_double += (double)tmp_kari.getValue();
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
			 return (SExpression)Int.valueOf(0); 
		  }

		  //applyメソッドの引数がそれ以外の場合
		  else {
			  throw new NumberRequiredException ();
		  }
	}
	   @Override 
	   public String toString() {
	      return "#<subr +>";
	   }
}
			 
			 
			 
	/*		 
			 //cdr部分が空リストの時
			 if(tmp.getCdr() instanceof EmptyList) {
				 //car部分が整数値の時
				 if(tmp.getCar() instanceof Int) {
					 //returnはSExpressionかも
					 Int tmp_kari = (Int)tmp.getCar();
					 return tmp_kari;
				 }
				 //car部分が浮動小数点の時
				 else if(tmp.getCar() instanceof Dbl) {
					//returnはSExpressionかも
					 Dbl tmp_kari = (Dbl)tmp.getCar();
					 return tmp_kari;
				 }
				 //car部分がそれ以外の時
				 else {
					 throw new OperationIsNotDefinedException();
				 }
			 }
			 
			 //cdr部分が空リストではない時
			 else {
					SExpression cont = (SExpression)tmp.getCdr();
					this.apply(cont);
					
			 }
		  }
		  
		  //a
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
				if (tmp.getCar() instanceof Int) {
					Int tmp_kari = (Int)tmp.getCar();
					int tmp_kari_int = tmp_kari.getValue();
	//			    sum_int += tmp_kari_int; 
//					int tmp_sum_int=tmp_int_sum.getValue();
//					Int result_sum = Int.valueOf(sum);
//				    System.out.println("途中="+sum_int+"");
				    if(!(tmp.getCdr() instanceof EmptyList)) {
						SExpression cont = (SExpression)tmp.getCdr();
						
						Int sum_int=(Int)this.apply(cont);
						
						System.out.println("途中="+sum_int+"");
					}
	        	}
		/*		else if (tmp.getCar() instanceof Dbl) {
					Dbl tmp_kari2 = (Dbl)tmp.getCar();
			    	double tmp_kari2_dbl = tmp_kari2.getValue();
			    	sum_dbl += tmp_kari2_dbl;
			    	check=1;
			    }
				
				else if(tmp.getCdr() instanceof EmptyList) {
					System.out.println("break\n");
		
				}
				else {
			         throw new OperationIsNotDefinedException();
				}
				//tmp = (ConsCell)tmp.getCdr();
	         }
		  else {
			  throw new PairRequiredException();
		  }
			//if(check==0) {
				Int result_int = Int.valueOf(sum_int);
				SExpression result = (SExpression)result_int;
				return result;
			//}
			//else if(check==1) {
			/*	Int result_int = Int.valueOf(sum_int);
				Dbl result_to_dbl = (Dbl)result_int;
				double result_int_dbl = result_to_dbl.getValue();*/
			/*	Dbl result_dbl = Dbl.valueOf(sum_dbl+sum_int);
				SExpression result = (SExpression)result_dbl;
				return result;*/
			//}
			/*	else {
			  throw new PairRequiredException();
		  }
	   }


	   @Override 
	   public String toString() {
	      return "#<sub +>";
	   }
}
*/
