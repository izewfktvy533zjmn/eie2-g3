package lisp.eval;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;



/**
 * 
 * キャンバス
 * @author bp15046
 *
 */



public class Fund extends JFrame implements SExpression {
	private int width;
	private int height;
	private int sx = 0;
	private int sy = 0;
	private int ex = 0;;
	private int ey = 0;
	private SExpression keiro = EmptyList.getInstance();


	public Fund(int width, int height) {
		super("Turtle Graphics");

		this.width = width;
		this.height = height;
		setSize(this.width, this.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}



	public void setKeiro(SExpression keiro) {
		this.keiro = keiro;
	}



	@Override
	public void update(Graphics g) {
		paint(g);
	}



	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.width, this.height);
		
		SExpression tmpKeiro = this.keiro;
		
		SExpression start_point;
		SExpression end_point;
		SExpression sx;
		SExpression sy;
		SExpression ex;
		SExpression ey;
		double tmp_double;
      
      if (tmpKeiro instanceof EmptyList) {
      }

      else {

		   while(true) {
			   if (tmpKeiro instanceof EmptyList) {
				   g.setColor(Color.RED);
				   g.drawOval(this.ex+this.width/2-10, this.ey+this.height/2-10, (int)(2.0*10), (int)(2.0*10));

			   	break;
			   }


   			start_point = ((ConsCell)tmpKeiro).getCar();
	   		start_point = ((ConsCell)start_point).getCar();

		   	end_point = ((ConsCell)tmpKeiro).getCar();
			   end_point = ((ConsCell)end_point).getCdr();
	   		end_point = ((ConsCell)end_point).getCar();



		   	//System.out.println(start_point);
			   //System.out.println(end_point);
			

			
   			sx = ((ConsCell)start_point).getCar();
	   		sy = ((ConsCell)((ConsCell)start_point).getCdr()).getCar();
			
		   	ex = ((ConsCell)end_point).getCar();
			   ey = ((ConsCell)((ConsCell)end_point).getCdr()).getCar();

			
			
   			//System.out.println(sx);
	   		//System.out.println(sy);
   			//System.out.println(ex);
   			//System.out.println(ey);
			
			
			
	   		if (sx instanceof Int) {
		   		this.sx = ((Int)sx).getValue();
			   }
		   	else if (sx instanceof Dbl) {
			   	tmp_double = ((Dbl)sx).getValue();		
   				this.sx = (int)tmp_double;
	   		}
	   		else {
	   		}
			
		   	if (sy instanceof Int) {
			   	this.sy = ((Int)sy).getValue();
		   	}
			   else if (sy instanceof Dbl) {
				   tmp_double = ((Dbl)sy).getValue();		
		   		this.sy = (int)tmp_double;
   			}
   			else {
   			}
			
	   		if (ex instanceof Int) {
	   			this.ex = ((Int)ex).getValue();
	   		}
		   	else if (ex instanceof Dbl) {
			   	tmp_double = ((Dbl)ex).getValue();		
	   			this.ex = (int)tmp_double;
   			}
	   		else {
		   	}
			
			   if (ey instanceof Int) {
				   this.ey = ((Int)ey).getValue();
		   	}
			   else if (ey instanceof Dbl) {
				   tmp_double = ((Dbl)ey).getValue();		
   				this.ey = (int)tmp_double;
   			}
	   		else {
	   		}

			
			
		   	g.setColor(Color.BLACK);
			   g.drawLine(this.sx+this.width/2, this.sy+this.height/2, this.ex+this.width/2, this.ey+this.height/2);
			


		   	tmpKeiro = ((ConsCell)tmpKeiro).getCdr();

	   	}
	
		   //System.out.println();
      }


	}


	
	@Override
	public String toString() {
		return "@" + this.hashCode();
	}

}

