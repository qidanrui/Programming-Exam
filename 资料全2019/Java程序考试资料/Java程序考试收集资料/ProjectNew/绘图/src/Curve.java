import java.awt.geom.QuadCurve2D;
import java.awt.*;
public class Curve extends MyShape{
	private double st_x;
	private double st_y;
	private double end_x;
	private double end_y;
	private double mid_x;
	private double mid_y;
	private float thick;
	private boolean dashed=false;
	private Color mycolor;
	public Curve(double x1,double y1,double x2,double y2,double x3,double y3,Color color,float t)
	{
		st_x=x1;st_y=y1;end_x=x3;end_y=y3;mid_x=x2;mid_y=y2;mycolor=color;thick=t;
	}
	public void setDashed(boolean a)
	{
		dashed=a;
	}
	@Override
	public void Draw(Graphics2D g) {
		// TODO Auto-generated method stub
		if(dashed)
		{
			float dash1[]={10f};
		    g.setStroke(new BasicStroke(thick, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND,1.0f,dash1, 0.0f));
		}
		else 
			g.setStroke(new BasicStroke(thick));
		g.setColor(mycolor);
		g.draw(new QuadCurve2D.Double(st_x,st_y,mid_x,mid_y,end_x,end_y));
	}
	
	

}
