import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
public class Line extends MyShape{
	private int x;
	private int y;
	private int x2;
	private int y2;
    private float linestick;
    private boolean dashed=false;
	Color mycolor;
	public Line(int a,int b,int c,int d,Color color,float stick)
	{
		x=a;y=b;x2=c;y2=d;mycolor=color;linestick=stick;
	}
	public void setDashed(boolean a)
	{
		dashed=a;
	}
	public void Draw(Graphics2D g)
	{
		if(dashed)
		{
		  float dash1[] = {10.0f}; 
		  g.setStroke(new BasicStroke(linestick, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND,1.0f,dash1, 0.0f));
		}
		else 
		  g.setStroke(new BasicStroke(linestick));
		g.setColor(mycolor);
		g.drawLine(x, y, x2,y2);
	}

}
