package lisp.eval;

import lisp.exception.InvalidApplicationException;
import lisp.exception.ListRequiredException;
import lisp.exception.LispException;



/**
 *
 * 高階関数 map
 * @author bp15046
 *
 */



public class Map extends Subroutine {
	@Override
	public SExpression apply(SExpression sexp) throws InvalidApplicationException, ListRequiredException, LispException {
		if ( !(sexp instanceof ConsCell) ){
			throw new ListRequiredException();
		}

		// 手続きとリストに分ける
		SExpression proc = ((ConsCell)sexp).getCar();
		SExpression list = ((ConsCell)sexp).getCdr();

		if ( !(list instanceof ConsCell) ) {
			throw new ListRequiredException();
		}

		// 手続きが組込みて続きの場合
		if (proc instanceof Subroutine) {
			//System.out.println("Map | proc : " + proc);
			//System.out.println("Map | list : " + list);	


			// リストの第一要素を元に結果を作っていく
			SExpression ret = ((ConsCell)list).getCar();
			list = ((ConsCell)list).getCdr();


			//System.out.println("Map | ret : " + ret);
			//System.out.println("Map | list : " + list);
			

			SExpression tmp_list;
			SExpression tmp_list_element;
			SExpression tmp_ret = ret;
			SExpression tmp_ret_element;
			SExpression result_proc;
			SExpression result_proc_list;

			
			while(true) {
				// リストが空リストの場合
				if (list instanceof EmptyList) {
					//System.out.println("Map | list     : " + list);
					break;
				}
				// リストがConsCellではない場合
				else if ( !(list instanceof ConsCell) ) {
					throw new ListRequiredException();
				}
				else {
					ConsCell tmp = ConsCell.getInstance(EmptyList.getInstance(), EmptyList.getInstance());
					ConsCell TMP = tmp;
					
					
					tmp_list = ((ConsCell)list).getCar();
					list = ((ConsCell)list).getCdr();

					
					//System.out.println("Map | tmp_list : " + tmp_list);
					//System.out.println("Map | list     : " + list);


					// list_elementがConsCellではない場合例外を投げる
					if ( !(tmp_list instanceof ConsCell) ) {
						throw new ListRequiredException();
					}
					
					while(true) {
						if (tmp_list instanceof EmptyList || tmp_ret instanceof EmptyList) {
							break;
						}
						else if ( !(tmp_list instanceof ConsCell) || !(tmp_ret instanceof ConsCell) ) {
							throw new ListRequiredException();
						}

						
						tmp_ret_element = ((ConsCell)tmp_ret).getCar();
						tmp_ret = ((ConsCell)tmp_ret).getCdr();

						tmp_list_element = ((ConsCell)tmp_list).getCar();
						tmp_list = ((ConsCell)tmp_list).getCdr();


						//System.out.println(tmp_ret_element);
						//System.out.println(tmp_list_element);
						//System.out.println();

						
						
						try {
							//System.out.println(((Subroutine)proc).apply(ConsCell.getInstance(tmp_ret_element, ConsCell.getInstance(tmp_list_element, EmptyList.getInstance()))));
							
							result_proc = ((Subroutine)proc).apply(ConsCell.getInstance(tmp_ret_element, ConsCell.getInstance(tmp_list_element, EmptyList.getInstance())));
							
							result_proc_list = ConsCell.getInstance(result_proc, EmptyList.getInstance());
							
							TMP.setCdr(result_proc_list);
							
							TMP = (ConsCell)result_proc_list;

						} catch (LispException e) {
							throw e;
						}

					}
					tmp_ret = tmp.getCdr();

				}
				
			}		

			return tmp_ret;

		}
		// 手続きがクロージャーの場合
		else if (proc instanceof Closure) {
			//System.out.println("Map | proc : " + proc);
			//System.out.println("Map | list : " + list);	


			// リストの第一要素を元に結果を作っていく
			SExpression ret = ((ConsCell)list).getCar();
			list = ((ConsCell)list).getCdr();


			//System.out.println("Map | ret : " + ret);
			//System.out.println("Map | list : " + list);
			

			SExpression tmp_list;
			SExpression tmp_list_element;
			SExpression tmp_ret = ret;
			SExpression tmp_ret_element;
			SExpression result_proc;
			SExpression result_proc_list;

			
			while(true) {
				// リストが空リストの場合
				if (list instanceof EmptyList) {
					//System.out.println("Map | list     : " + list);
					break;
				}
				// リストがConsCellではない場合
				else if ( !(list instanceof ConsCell) ) {
					//System.out.println("Map | list     : " + list);
					throw new ListRequiredException();
				}
				else {
					ConsCell tmp = ConsCell.getInstance(EmptyList.getInstance(), EmptyList.getInstance());
					ConsCell TMP = tmp;
					
					
					tmp_list = ((ConsCell)list).getCar();
					list = ((ConsCell)list).getCdr();

					
					//System.out.println("Map | tmp_list : " + tmp_list);
					//System.out.println("Map | list     : " + list);


					// list_elementがConsCellではない場合例外を投げる
					if ( !(tmp_list instanceof ConsCell) ) {
						throw new ListRequiredException();
					}
					
					while(true) {
						if (tmp_list instanceof EmptyList || tmp_ret instanceof EmptyList) {
							break;
						}
						
						tmp_ret_element = ((ConsCell)tmp_ret).getCar();
						tmp_ret = ((ConsCell)tmp_ret).getCdr();

						tmp_list_element = ((ConsCell)tmp_list).getCar();
						tmp_list = ((ConsCell)tmp_list).getCdr();


						//System.out.println(tmp_ret_element);
						//System.out.println(tmp_list_element);
						//System.out.println();

						
						
						try {
							//System.out.println(((Subroutine)proc).apply(ConsCell.getInstance(tmp_ret_element, ConsCell.getInstance(tmp_list_element, EmptyList.getInstance()))));
							
							result_proc = ((Closure)proc).apply(ConsCell.getInstance(tmp_ret_element, ConsCell.getInstance(tmp_list_element, EmptyList.getInstance())));
							
							result_proc_list = ConsCell.getInstance(result_proc, EmptyList.getInstance());
							
							TMP.setCdr(result_proc_list);
							
							TMP = (ConsCell)result_proc_list;

						} catch (LispException e) {
							throw e;
						}

					}
					tmp_ret = tmp.getCdr();

				}
				
			}		

			return tmp_ret;

		}
		else {
			throw new InvalidApplicationException();
		}
	
	}
	
	
	
	@Override
	public String toString() {
		return "#<subr map>";
	}

}
