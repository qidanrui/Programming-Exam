package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TestGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3777676486796788439L;

	public TestGUI(){
		this.setSize(500,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JButton[][] jbs = new JButton[3][3];
		this.setLayout(new GridLayout(3,3));
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++){
				jbs[i][j] = new JButton();
				jbs[i][j].addMouseListener(new GameButtonListener());
				this.add(jbs[i][j]);
			}
	}
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D graphic = (Graphics2D) g;
		Ellipse2D ee2d = new Ellipse2D.Double(10,10,10,10);
		graphic.draw(ee2d);
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String args[]){
		TestGUI gui = new TestGUI();
		gui.show();
	}
	
	class GameButtonListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JButton btn  	= new JButton();
			JButton button = (JButton) e.getSource();
			button.setBackground(btn.getBackground());
		}

		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX(), y = e.getY();
			JButton button = (JButton) e.getSource();
			button.setBackground(Color.BLUE);
			System.out.println(x+" "+y);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
		
	}
}
