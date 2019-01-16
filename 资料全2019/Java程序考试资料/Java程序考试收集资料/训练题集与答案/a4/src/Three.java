import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Three extends JFrame {

	public static void main(String[] args) {
		Three t = new Three();
	}

	JButton[][] b = new JButton[10][10];

	Three() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(10, 10, 2, 2));
		for (int i = 0; i < 10; ++i) {
			for (int j = 0; j < 10; ++j) {
				b[i][j] = new JButton();
				b[i][j].addMouseListener(new Click());
				p.add(b[i][j]);
			}
		}
		this.add(p);
		this.pack();
		this.setVisible(true);
		this.setSize(new Dimension(1000, 700));
		run();
	}

	Timer t;
	Timer fresher;
	int ri, rj;

	void run() {
		restart();
		int delay = 1000; // ms
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// ...Perform a task...
				if (JOptionPane.showConfirmDialog(null, "lose,time out") > -1) {
					t.restart();
					restart();
				}
			}
		};
		t = new Timer(delay, taskPerformer);
		t.start();
	}

	void restart() {
		b[ri][rj].setText("");
		Random r = new Random();
		ri = r.nextInt(10);
		rj = r.nextInt(10);

		b[ri][rj].setText("R");
	}

	class Click implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

			t.stop();
			JButton jb = (JButton) (e.getSource());

			for (int i = 0; i < 10; ++i) {
				for (int j = 0; j < 10; ++j) {
					if (b[i][j].equals(jb)) {
						if (i == ri && j == rj) {
							// won
							if (JOptionPane.showConfirmDialog(null, "win") > -1) {
								restart();
								t.restart();
							}

							return;
						} else {
							if (JOptionPane.showConfirmDialog(null,
									"lose, wrong button") > -1) {
								restart();
								t.restart();
							}
							return;
						}
					}
				}
			}
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

	}
}
