import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Three extends JFrame {
	int[][] m = new int[3][3];
	public static final int width = 600;

	public static void main(String[] args) {
		int n = 3;

		Three t = new Three();
	}

	JLabel l = new JLabel("");
	JButton[][] bb = new JButton[3][3];

	Three() {
		JPanel p = new JPanel();

		p.setLayout(new GridLayout(3, 3));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 3; ++j) {
				bb[i][j] = new JButton();
				bb[i][j].addMouseListener(new MouseL());
				p.add(bb[i][j]);
			}
		}

		this.setLayout(new GridLayout(2, 1));
		this.add(p);
		this.add(l);

		this.pack();
		this.setVisible(true);
		this.setSize(new Dimension(width, width));
	}

	class MouseL implements java.awt.event.MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			JButton b = (JButton) e.getSource();

			if (!b.getText().equals("")) {
				return;
			}

			int code = e.getButton();
			if (code == e.BUTTON1) {
				b.setText("o");
			} else if (code == e.BUTTON2) {
				b.setText("x");
			} else {
				b.setText("x");
			}

			isWin();

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		//ÅÐ¶ÏÍæ¼ÒÊÇ·ñÊ¤Àû
		boolean isWin() {
			
			//¼ì²éÃ¿ÐÐ
			for (int i = 0; i < 3; i++) {

				String a = bb[i][0].getText();
				String b = bb[i][1].getText();
				String c = bb[i][2].getText();

				if (!a.equals("") && a.equals(b) && a.equals(c)) {
					if (a.equals("o")) {// left
						l.setText("×ó¼üÊ¤Àû");
						return true;
					} else {
						l.setText("ÓÒ¼üÊ¤Àû");
						return true;
					}
				}
			}

			//¼ì²éÃ¿ÁÐ
			for (int i = 0; i < 3; i++) {

				String a = bb[0][i].getText();
				String b = bb[1][i].getText();
				String c = bb[2][i].getText();

				if (!a.equals("") && a.equals(b) && a.equals(c)) {
					if (a.equals("o")) {// left
						l.setText("×ó¼üÊ¤Àû");
						return true;
					} else {
						l.setText("ÓÒ¼üÊ¤Àû");
						return true;
					}
				}
			}

			boolean flag = false;
			
			//Ð±Ïò¼ì²é1
			String a = bb[0][0].getText();
			String b = bb[1][1].getText();
			String c = bb[2][2].getText();
			
			if (!a.equals("") && a.equals(b) && a.equals(c)) {
				if (a.equals("o")) {// left
					l.setText("×ó¼üÊ¤Àû");
					return true;
				} else {
					l.setText("ÓÒ¼üÊ¤Àû");
					return true;
				}
			}
			
			//Ð±Ïò¼ì²é2
			a = bb[0][2].getText();
			b = bb[1][1].getText();
			c = bb[2][0].getText();
			
			if (!a.equals("") && a.equals(b) && a.equals(c)) {
				if (a.equals("o")) {// left
					l.setText("×ó¼üÊ¤Àû");
					return true;
				} else {
					l.setText("ÓÒ¼üÊ¤Àû");
					return true;
				}
			}
			return true;
		}
	}
}
