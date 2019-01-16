import java.awt.Color;
import java.awt.Graphics2D;


public class Rectangle extends MyShape{
    private int x;
    private int y;
    private int width;
    private int height;
    /**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
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
	/**
	 * @return the ifFilled
	 */
	public boolean isIfFilled() {
		return ifFilled;
	}
	/**
	 * @param ifFilled the ifFilled to set
	 */
	public void setIfFilled(boolean ifFilled) {
		this.ifFilled = ifFilled;
	}
	private Color color;
    private boolean ifFilled;
    public Rectangle(){};
    public Rectangle(int x,int y,int width,int height,Color color, boolean ifFilled)
    {
    	this.x=x;
    	this.y=y;
    	this.width=width;
    	this.height=height;
    	this.color=color;
    	this.ifFilled=ifFilled;
    }
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(color);
		if(ifFilled)
			g.fillRect(x, y, width, height);
		else 
		    g.drawRect(x, y, width, height);
		
	}
}
