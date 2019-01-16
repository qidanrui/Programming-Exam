package thu.hcguo.draw;

import java.awt.Color;
import java.awt.Shape;

public class ShapeInfo {
	private Shape shape;
	private boolean isFill;
	private Color color;

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isFill() {
		return isFill;
	}

	public void setFill(boolean isFill) {
		this.isFill = isFill;
	}

}
