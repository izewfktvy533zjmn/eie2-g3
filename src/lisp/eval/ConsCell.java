package lisp.eval;



/**
 * Cons cell (ドット対)
 * @author tetsuya
 * @author bp15046
 */



public class ConsCell implements SExpression {
	private SExpression car;
	private SExpression cdr;

	public SExpression getCar() {
		return this.car;
	}
	public SExpression getCdr() {
		return this.cdr;
	}
	public void setCar(SExpression car) {
		this.car = car;
	}
	public void setCdr(SExpression cdr) {
		this.cdr = cdr;
	}
	
	public static ConsCell getInstance(SExpression car, SExpression cdr) {
		return new ConsCell(car, cdr);
	}
	
	private ConsCell(SExpression car, SExpression cdr) {
		this.car = car;
		this.cdr = cdr;
	}
	
	@Override
	public String toString() {
		if (this.cdr instanceof EmptyList) {
			return "(" + this.car + ")";
		}
		else if ( !(this.cdr instanceof ConsCell) ){
			return "(" + this.car + " . " + this.cdr + ")";
		}
		else {
			SExpression tmpCar = this.car;
			SExpression tmpCdr = this.cdr;
			String str = "" + this.car;
			
			while (true) {
				if (tmpCdr instanceof EmptyList) {
					return "(" + str + ")";
				}
				else if ( !(tmpCdr instanceof ConsCell) ) {
					return "(" + str + " . " + tmpCdr + ")";
				}
				else {
					tmpCar = ((ConsCell)tmpCdr).getCar();
					tmpCdr = ((ConsCell)tmpCdr).getCdr();
					str += " " + tmpCar;

				}
			}
		}
	
		/* デバッグ用 */
		//return "(" + this.car + " . " + this.cdr + ")";
		
	
	}
}
