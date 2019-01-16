import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;


public class PologenLine extends MyShape{
    private Polygon p;
    private Color color;
    public PologenLine(){};
    public PologenLine(Polygon p,Color color)
    {
    	this.p=p;
    	this.setColor(color);
    }
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(color);
		g.drawPolyline(p.xpoints, p.ypoints, p.npoints);
	}
	/**
	 * @return the p
	 */
	public Polygon getP() {
		return p;
	}
	/**
	 * @param p the p to set
	 */
	public void setP(Polygon p) {
		this.p = p;
	}
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

}
