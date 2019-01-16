import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Three extends JFrame {

	public static void main(String[] args) {
		Three t = new Three();
	}

	JButton[][] bs = new JButton[4][4];
	JLabel[][] ls = new JLabel[4][4];
	JLabel l = new JLabel();

	Three() {
		JPanel p = new JPanel();

		this.setLayout(new GridLayout(2, 1));
		p.setLayout(new GridLayout(4, 4,5,5));
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j) {
				bs[i][j] = new JButton();
				bs[i][j].addMouseListener(new MouseL());
				p.add(bs[i][j]);
			}
		}

		generateNums();

		this.add(p);
		this.add(l);
		this.pack();
		this.setVisible(true);
		this.setSize(new Dimension(500, 500));
	}

	public static void generateNums() {
		mark = new int[4][4];
		a = new int[4][4];
		Random r = new Random();
		for (int i = 0; i < 4; ++i) {
			for (int j = 0; j < 4; ++j) {
				a[i][j] = r.nextInt(100);
			}
		}
	}

	public static int stepsCount = 0;
	public static int[][] mark;
	public static int[][] a;

	class MouseL implements java.awt.event.MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			JButton b = (JButton) e.getSource();

			if (stepsCount == 0) {
				for (int ii = 0; ii < 4; ++ii) {
					for (int jj = 0; jj < 4; ++jj) {
						bs[ii][jj].setText("");
					}
				}
			}

			for (int i = 0; i < 4; ++i) {
				for (int j = 0; j < 4; ++j) {
					if (bs[i][j].equals(b)) {

						if (stepsCount < 2) {// 第一个未完成
							stepsCount++;
							// b.setText("" + a[i][j]);

							if (mark[i][j] == 0) {// 未被占用
								mark[i][j] = 1;

							} else {
								return;
							}
						} else if (stepsCount < 4) {
							// b.setText("" + a[i][j]);

							if (mark[i][j] == 0) {// 未被占用
								mark[i][j] = 2;

								stepsCount++;

								if (stepsCount == 4) {
									int sa = 0;
									int sb = 0;
									for (int ii = 0; ii < 4; ++ii) {
										for (int jj = 0; jj < 4; ++jj) {
											if (mark[ii][jj] == 1) {

												sa += a[ii][jj];
												bs[ii][jj].setText(""
														+ a[ii][jj]);
											}
											if (mark[ii][jj] == 2) {
												sb += a[ii][jj];
												bs[ii][jj].setText(""
														+ a[ii][jj]);
											}

										}
									}
									if (sa > sb) {
										l.setText("a has won the match." + sa);
									} else if (sb > sa) {
										l.setText("b has won the match." + sb);
									} else {
										l.setText("分数相同");
									}

									File f = new File("result.txt");

									BufferedWriter bw;
									try {
										bw = new BufferedWriter(new FileWriter(
												f, true));
										bw.write(l.getText() + "\r\n");
										bw.flush();
										bw.close();
									} catch (IOException ee) {
										ee.printStackTrace();
										System.out.println("文件输出错误，空间不足？");
										System.exit(1);
									}

									generateNums();
									stepsCount = 0;
								}

							} else {
								return;
							}
						}

						return;
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
