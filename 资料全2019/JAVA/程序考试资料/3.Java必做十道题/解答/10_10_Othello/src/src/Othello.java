package src;

/*
 * Othello.java:
 * 这个文件包含了以下几个类：
 * 主类：Othello;内部类：newHandler,savaHandler,openHandler,exitHandler,Handler
 * 
 */

/**
 * 黑白棋游戏：
 * 可供两人对弈
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Othello: 主类：实现了界面、并对每个按钮添加了内部类以实现事件监听功能（每个棋盘格子用button表示）
 * 
 * @author zhaoyuan
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Othello extends JFrame
{

	private boolean isBlackPlay = true;// 是否是黑棋先手
	boolean gameOver = false;
	private int blackChess = 0, whiteChess = 0;
	private int data[][] = new int[4][4];

	private JPanel p1 = new JPanel();
	private JButton newB = new JButton("重新开始");
	private JButton saveB = new JButton("保存");
	private JButton openB = new JButton("打开");
	private JButton exitB = new JButton("退出");

	private JPanel p2 = new JPanel();
	private JButton button[][] = new JButton[4][4];

	/**
	 * Othello(): 构造方法，实现了界面，为按狃添加了事件监听器
	 */
	public Othello()
	{
		Container c = getContentPane();

		c.add(p1, BorderLayout.NORTH);
		p1.add(newB);
		p1.add(saveB);
		p1.add(openB);
		p1.add(exitB);

		c.add(p2, BorderLayout.CENTER);
		p2.setLayout(new GridLayout(4, 4));

		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				button[i][j] = new JButton("");
			}
		}
		for (int i = 0; i < 4; i++)
		{
			for (int j = 0; j < 4; j++)
			{
				p2.add(button[i][j]);
				button[i][j].setBackground(Color.GREEN);
				button[i][j].addActionListener(new Handler(i, j));
			}
		}
		button[1][1].setBackground(Color.WHITE);
		button[1][2].setBackground(Color.BLACK);
		button[2][1].setBackground(Color.BLACK);
		button[2][2].setBackground(Color.WHITE);

		newB.addActionListener(new newHandler());
		saveB.addActionListener(new saveHandler());
		openB.addActionListener(new openHandler());
		exitB.addActionListener(new exitHandler());

	}

	/**
	 * newHandler： 内部类——当点“重新开始”按钮时，重新初试化界面及一些参数
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 * 
	 */
	private class newHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			for (int i = 0; i < 4; i++)
			{
				for (int j = 0; j < 4; j++)
				{
					button[i][j].setBackground(Color.GREEN);
				}
			}
			button[1][1].setBackground(Color.WHITE);
			button[1][2].setBackground(Color.BLACK);
			button[2][1].setBackground(Color.BLACK);
			button[2][2].setBackground(Color.WHITE);

			isBlackPlay = true;
			blackChess = 0;
			whiteChess = 0;
		}
	}

	/**
	 * saveHandler： 内部类——当点“保存”按钮时，保存数据到指定文件中，用二元数组表示棋盘，用数字表示棋子
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 * 
	 */
	private class saveHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			for (int i = 0; i < 4; i++)
			{
				for (int j = 0; j < 4; j++)
				{
					if (button[i][j].getBackground() == Color.BLACK)// 黑棋用1表示
						data[i][j] = 1;
					else if (button[i][j].getBackground() == Color.WHITE)// 白旗用-1表示
						data[i][j] = -1;
					else
						data[i][j] = 0;// 棋盘空位置用0表示
				}
			}
			try
			{
				FileWriter fw = new FileWriter("10.txt");
				for (int i = 0; i < 4; i++)
				{
					fw.write(data[i][0] + "," + data[i][1] + "," + data[i][2] + "," + data[i][3] + "\r\n");
				}
				fw.write(isBlackPlay + "\r\n");
				fw.write("0表示空白，1表示黑棋，-1表示白棋。true表示该黑棋走了");

				fw.close();
				JOptionPane.showMessageDialog(null, "保存成功");
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}

	/**
	 * openHandler： 内部类——当点“打开”按钮时，打开文件读出数据，棋盘恢复到保存时的状态
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 * 
	 */
	private class openHandler implements ActionListener
	{
		String ss;

		public void actionPerformed(ActionEvent e)
		{
			try
			{
				BufferedReader br = new BufferedReader(new FileReader("10.txt"));

				for (int i = 0; i < 4; i++)
				{
					String line = br.readLine();
					String temp[] = line.split(",");

					data[i][0] = Integer.parseInt(temp[0]);
					data[i][1] = Integer.parseInt(temp[1]);
					data[i][2] = Integer.parseInt(temp[2]);
					data[i][3] = Integer.parseInt(temp[3]);
				}
				String s = br.readLine();
				isBlackPlay = Boolean.parseBoolean(s);
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}

			for (int i = 0; i < 4; i++)
			{
				for (int j = 0; j < 4; j++)
				{
					if (data[i][j] == 1)
						button[i][j].setBackground(Color.BLACK);
					else if (data[i][j] == -1)
						button[i][j].setBackground(Color.WHITE);
					else
						button[i][j].setBackground(Color.GREEN);
				}
			}

			if (isBlackPlay == true)
				ss = "黑";
			else
				ss = "白";

			JOptionPane.showMessageDialog(null, "打开成功,下面该" + ss + "方下");

		}
	}

	/**
	 * exitHandler： 内部类——当点“退出”按钮时，退出游戏并关闭
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 * 
	 */
	private class exitHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);// //
		}
	}

	/**
	 * Handler： 内部类——当点每个棋盘格子按钮时，判断所点位置是否合法，若合法则更新棋盘
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 * 
	 */
	private class Handler implements ActionListener
	{
		private int row = -1, col = -1;

		/**
		 * Handler： 构造方法：将按钮的坐标值传进来
		 * 
		 * @param x
		 *            按钮的横坐标
		 * @param y
		 *            按钮的纵坐标
		 */
		public Handler(int x, int y)
		{
			row = x;
			col = y;
		}

		/**
		 * isValidPosition： 判断该位置是否合法
		 * 
		 * @param x
		 *            按钮的横坐标
		 * @param y
		 *            按钮的纵坐标
		 * @return
		 */
		private boolean isValidPosition(int x, int y)
		{
			if (button[x][y].getBackground() != Color.GREEN)
				return false;

			if (isBlackPlay == true) // 若是黑方下
			{
				// 水平方向上判断
				for (int i = 0; i < 4; i++)
				{
					if (button[i][y].getBackground() == Color.BLACK)
					{
						if ((i - x) >= 2)// 下棋的位置在左
						{
							int count = 0;
							for (int k = x; k < i; k++)// 实际上k应从x+1开始，因为该黑棋走，所以button[x][y]肯定不是白棋
							{
								if (button[k][y].getBackground() == Color.WHITE)
									count++;
							}
							if (count == (i - x - 1))// 如果count不等于这个值，说明没有白子被夹，位置仍不合法
								return true;
						}

						if ((x - i) >= 2)// 下棋的位置在右
						{
							int count = 0;
							for (int k = x; k > i; k--)// 实际上k应从x-1开始，因为该黑棋走，所以button[x][y]肯定不是白棋
							{
								if (button[k][y].getBackground() == Color.WHITE)
									count++;
							}
							if (count == (x - i - 1))
								return true;
						}
					}
				}

				// 竖直方向判断
				for (int j = 0; j < 4; j++)
				{

					if (button[x][j].getBackground() == Color.BLACK)
					{
						if ((j - y) >= 2)// 下棋的位置在上
						{
							int count = 0;
							for (int k = y + 1; k < j; k++)
							{
								if (button[x][k].getBackground() == Color.WHITE)
									count++;
							}
							if (count == (j - y - 1))
								return true;
						}

						if ((y - j) >= 2)// 下棋的位置在下
						{
							int count = 0;
							for (int k = y - 1; k > j; k--)
							{
								if (button[x][k].getBackground() == Color.WHITE)
									count++;
							}
							if (count == (y - j - 1))
								return true;
						}
					}
				}

				// 斜线方向上判断
				for (int i = 0; i < 4; i++)
				{
					for (int j = 0; j < 4; j++)
					{
						if (button[i][j].getBackground() == Color.BLACK)
						{
							if ((x - i) == (y - j) && (x - i) >= 2)// 下棋的位置在右上
							{
								int yy = y;
								int count = 0;
								for (int k = x; k > i; k--)
								{
									if (button[k][yy].getBackground() == Color.WHITE)
									{
										count++;
									}
									yy--;
								}
								if (count == (x - i - 1))
									return true;
							}

							if ((x - i) == (j - y) && (x - i) >= 2)// 下棋的位置在右下
							{
								int yy = y;
								int count = 0;
								for (int k = x; k > i; k--)
								{
									if (button[k][yy].getBackground() == Color.WHITE)
									{
										count++;
									}
									yy++;

								}
								if (count == (x - i - 1))
									return true;
							}

							if ((i - x) == (y - j) && (i - x) >= 2)// 下棋的位置在左下
							{
								int yy = y;
								int count = 0;
								for (int k = x; k < i; k++)
								{
									if (button[k][yy].getBackground() == Color.WHITE)
									{
										count++;
									}
									yy--;

								}
								if (count == (i - x - 1))
									return true;
							}

							if ((i - x) == (j - y) && (i - x) >= 2)// 下棋的位置在左上
							{
								int yy = y;
								int count = 0;
								for (int k = x; k < i; k++)
								{
									if (button[k][yy].getBackground() == Color.WHITE)
									{
										count++;
									}
									yy++;

								}
								if (count == (i - x - 1))
									return true;
							}

						}
					}
				}

				return false;

			}

			else
			// 若是白方下
			{
				for (int i = 0; i < 4; i++)
				{
					if (button[i][y].getBackground() == Color.WHITE)
					{
						if ((i - x) >= 2)
						{
							int count = 0;
							for (int k = x; k < i; k++)
							{
								if (button[k][y].getBackground() == Color.BLACK)
									count++;
							}
							if (count == (i - x - 1))
								return true;
						}
						if ((x - i) >= 2)
						{
							int count = 0;
							for (int k = x; k > i; k--)
							{
								if (button[k][y].getBackground() == Color.BLACK)
									count++;
							}
							if (count == (x - i - 1))
								return true;
						}
					}
				}

				for (int j = 0; j < 4; j++)
				{
					if (button[x][j].getBackground() == Color.WHITE)
					{
						if ((j - y) >= 2)
						{
							int count = 0;
							for (int k = y; k < j; k++)
							{
								if (button[x][k].getBackground() == Color.BLACK)
									count++;
							}
							if (count == (j - y - 1))
								return true;
						}

						if ((y - j) >= 2)
						{
							int count = 0;
							for (int k = y; k > j; k--)
							{
								if (button[x][k].getBackground() == Color.BLACK)
									count++;
							}
							if (count == (y - j - 1))
								return true;
						}

					}
				}

				for (int i = 0; i < 4; i++)
				{
					for (int j = 0; j < 4; j++)
					{
						if (button[i][j].getBackground() == Color.WHITE)
						{
							if ((x - i) == (y - j) && (x - i) >= 2)
							{
								int yy = y;
								int count = 0;
								for (int k = x; k > i; k--)
								{
									if (button[k][yy].getBackground() == Color.BLACK)
									{
										count++;
									}
									yy--;

								}
								if (count == (x - i - 1))
									return true;
							}

							if ((x - i) == (j - y) && (x - i) >= 2)
							{
								int yy = y;
								int count = 0;
								for (int k = x; k > i; k--)
								{
									if (button[k][yy].getBackground() == Color.BLACK)
									{
										count++;
									}
									yy++;

								}
								if (count == (x - i - 1))
									return true;
							}

							if ((i - x) == (y - j) && (i - x) >= 2)
							{
								int yy = y;
								int count = 0;
								for (int k = x; k < i; k++)
								{
									if (button[k][yy].getBackground() == Color.BLACK)
									{
										count++;
									}
									yy--;
								}
								if (count == (i - x - 1))
									return true;
							}

							if ((i - x) == (j - y) && (i - x) >= 2)
							{
								int yy = y;
								int count = 0;
								for (int k = x; k < i; k++)
								{
									if (button[k][yy].getBackground() == Color.BLACK)
									{
										count++;
									}
									yy++;
								}
								if (count == (i - x - 1))
									return true;
							}

						}
					}
				}

				return false;

			}

		}

		/**
		 * refresh： 更新棋盘
		 * 
		 * @param x
		 *            按钮的横坐标
		 * @param y
		 *            按钮的纵坐标
		 */
		private void refresh(int x, int y)
		{
			if (isBlackPlay == true)// 若是黑方下
			{
				for (int i = 0; i < 4; i++)
				{
					if (button[i][y].getBackground() == Color.BLACK)
					{
						if ((i - x) >= 2)
						{
							int count = 0;
							for (int k = x; k < i; k++)
							{
								if (button[k][y].getBackground() == Color.WHITE)
									count++;
							}

							if (count == (i - x - 1))
							{
								for (int k = x; k < i; k++)
									button[k][y].setBackground(Color.BLACK);
							}

						}

						if ((x - i) >= 2)
						{
							int count = 0;// /////////////////////////
							for (int k = x; k > i; k--)
							{
								if (button[k][y].getBackground() == Color.WHITE)
									count++;
							}

							if (count == (x - i - 1))
							{
								for (int k = x; k > i; k--)
									button[k][y].setBackground(Color.BLACK);
							}
						}
					}
				}

				for (int j = 0; j < 4; j++)
				{
					if (button[x][j].getBackground() == Color.BLACK)
					{
						if ((j - y) >= 2)
						{
							int count = 0;
							for (int k = y; k < j; k++)
							{
								if (button[x][k].getBackground() == Color.WHITE)
									count++;
							}

							if (count == (j - y - 1))
							{
								for (int k = y; k < j; k++)
									button[x][k].setBackground(Color.BLACK);
							}
						}

						if ((y - j) >= 2)
						{
							int count = 0;
							for (int k = y; k > j; k--)
							{
								if (button[x][k].getBackground() == Color.WHITE)
									count++;
							}
							if (count == (y - j - 1))
							{
								for (int k = y; k > j; k--)
									button[x][k].setBackground(Color.BLACK);
							}
						}
					}
				}

				for (int i = 0; i < 4; i++)
				{
					for (int j = 0; j < 4; j++)
					{
						if (button[i][j].getBackground() == Color.BLACK)
						{
							if ((x - i) == (y - j) && (x - i) >= 2)
							{
								int yy = y;
								int count = 0;
								for (int k = x; k > i; k--)
								{
									if (button[k][yy].getBackground() == Color.WHITE)
									{
										count++;
									}
									yy--;

								}
								if (count == (x - i - 1))
								{
									yy = y;
									for (int k = x; k > i; k--)
									{
										button[k][yy].setBackground(Color.BLACK);
										yy--;
									}
								}
							}

							if ((x - i) == (j - y) && (x - i) >= 2)
							{
								int yy = y;
								int count = 0;
								for (int k = x; k > i; k--)
								{
									if (button[k][yy].getBackground() == Color.WHITE)
									{
										count++;
									}
									yy++;

								}
								if (count == (x - i - 1))
								{
									yy = y;
									for (int k = x; k > i; k--)
									{
										button[k][yy].setBackground(Color.BLACK);
										yy++;
									}
								}
							}

							if ((i - x) == (y - j) && (i - x) >= 2)
							{
								int yy = y;
								int count = 0;
								for (int k = x; k < i; k++)
								{
									if (button[k][yy].getBackground() == Color.WHITE)
									{
										count++;
									}
									yy--;

								}
								if (count == (i - x - 1))
								{
									yy = y;
									for (int k = x; k < i; k++)
									{
										button[k][yy].setBackground(Color.BLACK);
										yy--;
									}
								}
							}

							if ((i - x) == (j - y) && (i - x) >= 2)
							{
								int yy = y;
								int count = 0;
								for (int k = x; k < i; k++)
								{
									if (button[k][yy].getBackground() == Color.WHITE)
									{
										count++;
									}
									yy++;

								}
								if (count == (i - x - 1))
								{
									yy = y;
									for (int k = x; k < i; k++)
									{
										button[k][yy].setBackground(Color.BLACK);
										yy++;
									}
								}
							}

						}
					}
				}

			}
			else
			// 若是白方下
			{
				for (int i = 0; i < 4; i++)
				{
					if (button[i][y].getBackground() == Color.WHITE)
					{
						if ((i - x) >= 2)
						{
							int count = 0;
							for (int k = x; k < i; k++)
							{
								if (button[k][y].getBackground() == Color.BLACK)
									count++;
							}

							if (count == (i - x - 1))
							{
								for (int k = x; k < i; k++)
									button[k][y].setBackground(Color.WHITE);
							}

						}
						if ((x - i) >= 2)
						{
							int count = 0;
							for (int k = x; k > i; k--)
							{
								if (button[k][y].getBackground() == Color.BLACK)
									count++;
							}

							if (count == (x - i - 1))
							{
								for (int k = x; k > i; k--)
									button[k][y].setBackground(Color.WHITE);
							}
						}
					}
				}

				for (int j = 0; j < 4; j++)
				{
					if (button[x][j].getBackground() == Color.WHITE)
					{
						if ((j - y) >= 2)
						{
							int count = 0;
							for (int k = y; k < j; k++)
							{
								if (button[x][k].getBackground() == Color.BLACK)
									count++;
							}

							if (count == (j - y - 1))
							{
								for (int k = y; k < j; k++)
									button[x][k].setBackground(Color.WHITE);
							}
						}
						if ((y - j) >= 2)
						{
							int count = 0;
							for (int k = y; k > j; k--)
							{
								if (button[x][k].getBackground() == Color.BLACK)
									count++;
							}
							if (count == (y - j - 1))
							{
								for (int k = y; k > j; k--)
									button[x][k].setBackground(Color.WHITE);
							}
						}
					}
				}

				for (int i = 0; i < 4; i++)
				{
					for (int j = 0; j < 4; j++)
					{
						if (button[i][j].getBackground() == Color.WHITE)
						{
							if ((x - i) == (y - j) && (x - i) >= 2)
							{
								int yy = y;
								int count = 0;
								for (int k = x; k > i; k--)
								{
									if (button[k][yy].getBackground() == Color.BLACK)
									{
										count++;
									}
									yy--;
								}
								if (count == (x - i - 1))
								{
									yy = y;
									for (int k = x; k > i; k--)
									{
										button[k][yy].setBackground(Color.WHITE);
										yy--;
									}
								}
							}

							if ((x - i) == (j - y) && (x - i) >= 2)
							{
								int yy = y;
								int count = 0;
								for (int k = x; k > i; k--)
								{
									if (button[k][yy].getBackground() == Color.BLACK)
									{
										count++;
									}
									yy++;

								}
								if (count == (x - i - 1))
								{
									yy = y;
									for (int k = x; k > i; k--)
									{
										button[k][yy].setBackground(Color.WHITE);
										yy++;
									}
								}
							}
							if ((i - x) == (y - j) && (i - x) >= 2)
							{
								int yy = y;
								int count = 0;
								for (int k = x; k < i; k++)
								{
									if (button[k][yy].getBackground() == Color.BLACK)
									{
										count++;
									}
									yy--;

								}
								if (count == (i - x - 1))
								{
									yy = y;
									for (int k = x; k < i; k++)
									{
										button[k][yy].setBackground(Color.WHITE);
										yy--;
									}
								}
							}
							if ((i - x) == (j - y) && (i - x) >= 2)
							{
								int yy = y;
								int count = 0;
								for (int k = x; k < i; k++)
								{
									if (button[k][yy].getBackground() == Color.BLACK)
									{
										count++;
									}
									yy++;

								}
								if (count == (i - x - 1))
								{
									yy = y;
									for (int k = x; k < i; k++)
									{
										button[k][yy].setBackground(Color.WHITE);
										yy++;

									}
								}
							}

						}
					}
				}

			}

		}

		/**
		 * hasValidPosition： 判断是否还有位置可下
		 * 
		 * @return：true表示还有位置可下，false表示无位置可下
		 */
		private boolean hasValidPosition()
		{
			for (int i = 0; i < 4; i++)
			{
				for (int j = 0; j < 4; j++)
				{
					if (isValidPosition(i, j))
						return true;
				}
			}
			return false;
		}

		/**
		 * changePlayer： 切换到另一方
		 */
		public void changePlayer()
		{
			if (isBlackPlay == true)
				isBlackPlay = false;
			else
				isBlackPlay = true;
		}

		/**
		 * whoWin： 根据棋盘上所剩的棋子数量来判断哪一方获胜
		 */
		private void whoWin()
		{
			for (int i = 0; i < 4; i++)
			{
				for (int j = 0; j < 4; j++)
				{
					if (button[i][j].getBackground() == Color.BLACK)
						blackChess++;
					if (button[i][j].getBackground() == Color.WHITE)
						whiteChess++;
				}
			}

			if (blackChess > whiteChess)
				JOptionPane.showMessageDialog(null, "恭喜黑方获胜");
			else if (blackChess < whiteChess)
				JOptionPane.showMessageDialog(null, "恭喜白方获胜");
			else
				JOptionPane.showMessageDialog(null, "平局了");

		}

		/**
		 * actionPerformed： 如果游戏已经结束则返回；否则首先判断位置是否合法，若否则提示并返回，若是则更新棋盘，
		 * 并切换到对方；这时判断是否还有位置可下，若是则返回；若否，则切换到己方；此时再
		 * 判断是否有位置可下，若无则游戏结束判断胜负，若否则提示切换到了己方并返回
		 */
		public void actionPerformed(ActionEvent e)
		{
			if (gameOver == true)
			{
				JOptionPane.showMessageDialog(null, "棋局已结束，请重新开始");
				return;
			}
			else
			{
				if (!isValidPosition(row, col))
				{
					JOptionPane.showMessageDialog(null, "非法位置，请重放");
					return;
				}
				else
				{
					refresh(row, col);
					changePlayer();
					if (!hasValidPosition())
					{
						changePlayer();
						{
							if (!hasValidPosition())
							{
								gameOver = true;
								whoWin();
							}
							else
							{
								JOptionPane.showMessageDialog(null, "对方无处可下，己方继续");
								return;
							}
						}
					}
					else
						return;

				}

			}

		}
	}

	/**
	 * main：
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		Othello app = new Othello();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(350, 400);
		app.setVisible(true);

	}

}
