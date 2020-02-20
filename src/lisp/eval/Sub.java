package lisp.eval;
import lisp.exception.NumberRequiredException;

public class Sub extends Subroutine{


	/**
	 *組み込み手続き : Sub
	 * @author bp15024
	 * @author bp15068(変更)
	 */
	
		@Override
		SExpression apply(SExpression sexp) throws NumberRequiredException {
			//System.out.println(sexp);
			  //applyメソッドの引数がConscellの場合
			  if (sexp instanceof ConsCell) {
				 ConsCell tmp = (ConsCell)sexp;
				 SExpression cont = (SExpression)tmp.getCdr();
				 try{
					 Add adder = new Add();
					 
					 //car部分が整数値の場合
					 if(tmp.getCar() instanceof Int) {
						 
						 if(tmp.getCdr() instanceof EmptyList) {
							  int sexps = ((Int)tmp.getCar()).getValue();
			 				  return (SExpression)Int.valueOf(-sexps);
						 }
						 
						 else {
							 
							SExpression get = adder.apply(cont);
							Int tmp_kari = (Int)tmp.getCar();
						
							if(get instanceof Int) {
								Int cdr = (Int)get;
								int anscdr = cdr.getValue();
							//	Int tmp_kari = (Int)tmp.getCar();
								int anscar =tmp_kari.getValue();
								return (SExpression)Int.valueOf(anscar-anscdr);
							}
							if(get instanceof Dbl) {
								Dbl cdr =(Dbl)get;
								double anscdr =cdr.getValue();
//								Dbl tmp_kari = (Dbl)tmp.getCar();
								double anscar = (double)tmp_kari.getValue();
//								double anscar =tmp_kari.getValue();
								return (SExpression)Dbl.valueOf(anscar-anscdr);
							}
							else {
								System.out.println("苦るわけない");
								throw new NumberRequiredException();
							}
						 }
						 
						 /* 変更前
						//test System.out.println(cont);
						 SExpression g = adder.apply(cont);
						//test System.out.println("sexpression : " + g);
						 if(g instanceof Int) {
					    //test	 System.out.println("OK");
							 Int cdr = (Int)g;
							 
					    //test System.out.println("cdr : " + cdr);
							 
							 int anscdr = cdr.getValue();
							 Int tmp_kari = (Int)tmp.getCar();
							 int anscar =tmp_kari.getValue();
							 
							 return (SExpression)Int.valueOf(anscar-anscdr);
						 }
						 else if(adder.apply(cont) instanceof Dbl) {
							 Dbl cdr =(Dbl)adder.apply(cont);
							 Dbl tmp_kari = (Dbl)tmp.getCar();
							 double anscar =tmp_kari.getValue();
							 double anscdr =cdr.getValue();
							 
							 return (SExpression)Dbl.valueOf(anscar-anscdr);
						 }
						 else {
							 System.out.println("苦るわけない");
							 throw new OperationIsNotDefinedException();
							 
						 }*/

					 }
					 //car部分が浮動小数点数値の時
					 else if(tmp.getCar() instanceof Dbl) {
						 
						 if(tmp.getCdr() instanceof EmptyList) {
							  double sexps = ((Dbl)tmp.getCar()).getValue();
			 				  return (SExpression)Dbl.valueOf(-sexps);
						 }
						 
						 else {
							 
							SExpression get = adder.apply(cont);
							Dbl tmp_kari = (Dbl)tmp.getCar();
							
							if(get instanceof Int) {
								Int cdr = (Int)get;
								double anscdr = (double)cdr.getValue();
								//Dbl tmp_kari = (Dbl)tmp.getCar();
								double anscar =tmp_kari.getValue();
							
								return (SExpression)Dbl.valueOf(anscar-anscdr);
							}
							else if(get instanceof Dbl) {
								Dbl cdr =(Dbl)get;
								double anscdr =cdr.getValue();
								//Dbl tmp_kari = (Dbl)tmp.getCar();
								double anscar =tmp_kari.getValue();
								
								return (SExpression)Dbl.valueOf(anscar-anscdr);
								
								/*	Dbl cdr =(Dbl)multier.apply(cont);
								Dbl tmp_kari = (Dbl)tmp.getCar();
								double anscar =tmp_kari.getValue();
								double anscdr =cdr.getValue();
							 	
								return (SExpression)Dbl.valueOf(anscar-anscdr);*/
							}
							else {
								System.out.println("苦るわけない");
								throw new NumberRequiredException();
							}
							
						 }
					 }
					 else {
						 throw new NumberRequiredException();
					 }
				 }catch(NumberRequiredException e) {
						throw e;
				}
			  }
			  
			//applyメソッドの引数が空リストの場合
			  else if(sexp instanceof EmptyList) {
				  return (SExpression)Int.valueOf(0);
			  }

			//applyメソッドの引数がそれ以外の場合
			  else {
				  throw new  NumberRequiredException();
			  }
		
		}
		
		@Override 
		   public String toString() {
		      return "#<subr ->";
		   }
}

//returnはSExpressionかも
//Int tmp_kari = (Int)tmp.getCar();

//	 while(tmp.getCdr() instanceof ConsCell) { 
// }
					 
					/* public SExpression saiki(ConsCell list ) {
						 Int list_car = (Int)list.getCar();
						 sum
					 }
							return this.car;
						}
					// try {
					//	 SExpression nikon = add.apply(cont);
					//	 while(cont instanceof ConsCell) {
							 
						// }
				 
				 */
