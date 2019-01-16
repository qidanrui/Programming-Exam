import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyGUI extends JFrame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MyGUI m = new MyGUI();
	}

	public MyGUI() {
		this.setSize(500, 500);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		this.setLayout(new GridLayout(3,3));
		
		for(int i =0;i<3;++i){
			for(int j=0;j<3;++j){
				bs[i][j] = new JButton();
				this.add(bs[i][j]);
				bs[i][j].addMouseListener(new MyButtonListener());
			}
		}
	}

	JButton[][] bs = new JButton[3][3];
	
	class MyButtonListener implements MouseListener{

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
			
			JButton b = (JButton)e.getSource();
			
			b.setBackground(Color.BLACK);
			
			for(int i =0;i<3;++i){
				for(int j=0;j<3;++j){
					bs[i][j] = new JButton();

					if(bs[i][j].equals(b)){
						// i j
					}
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	

}
