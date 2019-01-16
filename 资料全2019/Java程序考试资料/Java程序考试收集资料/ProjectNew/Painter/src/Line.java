import java.awt.Color;
import java.awt.Graphics2D;


public class Line  extends  MyShape {
    private int sx;
    private int sy;
    private int ex;
    private int ey;
    private Color color;
    public Line(){};
    public Line(int sx,int sy,int ex,int ey,Color color, boolean isFilled)
    {
    	this.sx=sx;
    	this.sy=sy;
    	this.ex=ex;
    	this.ey=ey;
    	this.color=color;
    }
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(color);
		g.drawLine(sx, sy, ex, ey);
	}
	/**
	 * @return the sx
	 */
	public int getSx() {
		return sx;
	}
	/**
	 * @param sx the sx to set
	 */
	public void setSx(int sx) {
		this.sx = sx;
	}
	/**
	 * @return the sy
	 */
	public int getSy() {
		return sy;
	}
	/**
	 * @param sy the sy to set
	 */
	public void setSy(int sy) {
		this.sy = sy;
	}
	/**
	 * @return the ex
	 */
	public int getEx() {
		return ex;
	}
	/**
	 * @param ex the ex to set
	 */
	public void setEx(int ex) {
		this.ex = ex;
	}
	/**
	 * @return the ey
	 */
	public int getEy() {
		return ey;
	}
	/**
	 * @param ey the ey to set
	 */
	public void setEy(int ey) {
		this.ey = ey;
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
