package thu.hcguo.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawBoardPanel extends JPanel implements MouseListener,
		MouseMotionListener {
	private static final long serialVersionUID = 1L;

	public static int type = 1;
	public static boolean isFill = false;
	public static Color color = Color.BLACK;

	private int x1, x2, y1, y2;

	private ArrayList<ShapeInfo> shapeList = new ArrayList<ShapeInfo>();
	private Shape shape;

	public DrawBoardPanel() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		for (ShapeInfo shapeInfo : shapeList) {
			g2d.setColor(shapeInfo.getColor());
			if (shapeInfo.isFill())
				g2d.fill(shapeInfo.getShape());
			else
				g2d.draw(shapeInfo.getShape());
		}

		if (shape != null) {
			g2d.setColor(color);
			if (isFill)
				g2d.fill(shape);
			else {
				g2d.draw(shape);
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		x1 = e.getX();
		y1 = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		ShapeInfo shapeInfo = new ShapeInfo();
		shapeInfo.setShape(shape);
		shapeInfo.setFill(isFill);
		shapeInfo.setColor(color);
		shapeList.add(shapeInfo);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		x2 = e.getX();
		y2 = e.getY();

		switch (type) {
		case Type.LINE:
			shape = new Line2D.Double(x1, y1, x2, y2);
			break;
		case Type.ELLIPSE:
			shape = new Ellipse2D.Double(x1, y1, Math.abs(x1 - x2), Math.abs(y1 - y2));
			break;
		case Type.CIRCLE:
			int r = (int) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
			shape = new Ellipse2D.Double(x1, y1, r, r);
			break;
		case Type.RECTANGLE:
			shape = new Rectangle2D.Double(x1,y1,Math.abs(x1-x2),Math.abs(y1-y2));
			break;
		case Type.SQUARE:
			shape = new Rectangle2D.Double(x1,y1,Math.abs(x1-x2),Math.abs(x1-x2));
			break;
		default:
			break;
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
