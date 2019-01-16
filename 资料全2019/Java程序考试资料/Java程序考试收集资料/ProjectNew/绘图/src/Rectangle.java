import java.awt.*;
public class Rectangle extends MyShape {
	private int x;
	private int y;
	private int width;
	private int height;
	private float linestick;
	private boolean filled=false;
	private boolean dashed=false;
	Color mycolor;
    public Rectangle(int a,int b,int c,int d,Color color,float stick)
    {
    	x=a;y=b;width=c;height=d;mycolor=color;linestick=stick;
    }
    public void setFilled(boolean a)
    {
    	filled=a;
    }
    public void setDashed(boolean b)
    {
    	dashed=b;
    }
    public void Draw(Graphics2D g)
    {
    	if(dashed)
    	{
    		float dash1[]={10f};
		    g.setStroke(new BasicStroke(linestick, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND,1.0f,dash1, 0.0f));
    	}
    	else
    		g.setStroke(new BasicStroke(linestick));
    	g.setColor(mycolor);
    	if(filled)
    		g.fillRect(x, y, width, height);
    	else 
    	    g.drawRect(x, y, width, height);
    }
}
