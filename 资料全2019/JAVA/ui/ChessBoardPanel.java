package thu.hcguo.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChessBoardPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static int N = 5;
	private static int COUNT = 4;
	public static JButton[][] btns = new JButton[N][N];
	public static int[][] flag = new int[N][N];

	private static boolean redMan = true;
	private static boolean blueMan = false;

	public ChessBoardPanel() {
		setLayout(new GridLayout(N, N));
		int index = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				btns[i][j] = new JButton();
				btns[i][j].setActionCommand(String.valueOf(index));
				btns[i][j].addActionListener(this);
				add(btns[i][j]);
				index++;
			}
		}
	}

	public static void init() {
		flag = new int[N][N];
		redMan = true;
		blueMan = false;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				btns[i][j].setBackground(null);
			}
		}
	}

	public static boolean save(String filename) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filename));
			oos.writeObject(btns);
			oos.writeObject(flag);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	public static boolean read(String filename) {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(filename));
			JButton readBtns[][] = (JButton[][]) ois.readObject();
			int readFlag[][] = (int[][]) ois.readObject();
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					btns[i][j].setBackground(readBtns[i][j].getBackground());
					flag[i][j] = readFlag[i][j];
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (btn.getActionCommand()
						.equals(btns[i][j].getActionCommand())) {
					if (flag[i][j] != 0) {
						JOptionPane.showMessageDialog(this, "此处已经有棋子，请选择其他位置！",
								"错误信息", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (redMan) {
						btns[i][j].setBackground(Color.red);
						redMan = false;
						blueMan = true;
						flag[i][j] = 1;
						if (isWin(i, j)) {
							JOptionPane.showMessageDialog(this, "红方胜利！",
									"胜利信息", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					} else if (blueMan) {
						btns[i][j].setBackground(Color.blue);
						redMan = true;
						blueMan = false;
						flag[i][j] = 2;
						if (isWin(i, j)) {
							JOptionPane.showMessageDialog(this, "蓝方胜利！",
									"胜利信息", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					}
					return;
				}
			}
		}
	}

	private boolean isWin(int i, int j) {
		return judgeRow(i, j) || judgeCol(i, j)
				|| judgeDiagonalFromLeftTopToRightDown(i, j)
				|| judgeDiagonalFromLeftDownToRightTop(i, j);
	}

	private boolean judgeDiagonalFromLeftTopToRightDown(int i, int j) {
		int value = flag[i][j];
		for (int row = 0; row <= N - COUNT; row++) {
			int c = 0;
			for (; c < COUNT; c++) {
				if (flag[row + c][row + c] != value)
					break;
			}
			if (c == COUNT)
				return true;
		}
		return false;
	}

	private boolean judgeDiagonalFromLeftDownToRightTop(int i, int j) {
		int value = flag[i][j];
		for (int row = 0; row <= N - COUNT; row++) {
			int c = 0;
			for (; c < COUNT; c++) {
				if (flag[row + c][N - 1 - row - c] != value)
					break;
			}
			if (c == COUNT)
				return true;
		}
		return false;
	}

	private boolean judgeCol(int i, int j) {
		int value = flag[i][j];

		for (int row = 0; row <= N - COUNT; row++) {
			int c = 0;
			for (; c < COUNT; c++) {
				if (flag[row + c][j] != value)
					break;
			}
			if (c == COUNT)
				return true;
		}
		return false;
	}

	private boolean judgeRow(int i, int j) {
		int value = flag[i][j];

		for (int col = 0; col <= N - COUNT; col++) {
			int c = 0;
			for (; c < COUNT; c++) {
				if (flag[i][col + c] != value)
					break;
			}
			if (c == COUNT)
				return true;
		}
		return false;
	}
}
