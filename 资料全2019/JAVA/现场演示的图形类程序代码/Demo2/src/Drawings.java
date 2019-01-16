import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;

import javax.swing.JFrame;

public class Drawings extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Drawings d = new Drawings();
	}

	public Drawings() {
		this.setSize(500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.addMouseListener(new MyMouseListener());
		this.addMouseMotionListener(new MyMotionAdapter());
	}
	
	int startX, startY, endX, endY;
	
	public void paint(Graphics g){
		
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		g.drawLine(startX, startY, endX, endY);
		
		Ellipse2D e2d = new Ellipse2D.Double(startX,startY,10,10);
		
		g2d.setColor(Color.BLUE);
		g2d.draw(e2d);
		g2d.fill(e2d);
		
		Ellipse2D e2d2 = new Ellipse2D.Double(endX,endY,10,10);
		
		g2d.setColor(Color.RED);
		g2d.draw(e2d2);
		g2d.fill(e2d2);
		
		
		g2d.drawString("a string", Math.abs(endX+startX)/2, Math.abs(endY+startX)/2);
		
	}
	
	class MyMotionAdapter extends MouseMotionAdapter{

		@Override
		public void mouseDragged(MouseEvent e) {
			super.mouseDragged(e);
			
			endX = e.getX();
			endY = e.getY();
			
			repaint();
		}
		
	}

	class MyMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
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

		@Override
		public void mousePressed(MouseEvent e) {
			startX = e.getX();
			startY = e.getY();
		}

		@Override
		public void mouseReleased(MouseEvent e) {

			endX = e.getX();
			endY = e.getY();
			
			repaint();
		}

	}
}
