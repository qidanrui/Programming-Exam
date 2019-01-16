package game;

import javax.swing.JPanel; //import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class PlayBoard extends JPanel
{
	public final int SizeOfGrid = 50; // 方格大小
	public final int NumberOfGrid = 3; // 行列方格的数量
	public byte stateOfGrid[][]; // 方格的状态， 0 空， 1 圈 ，2 叉
	public byte process; // 游戏进程， 1 圈走， 2 叉走

	public boolean BoradAlreadPaint;

	public PlayBoard()
	{
		mb_initData(); // 初始化棋盘，前缀mb表示成员函数的意思

		addMouseListener(new MouseAdapter() // 添加鼠标监听器
		{
			public void mousePressed(MouseEvent e) // 按下鼠标
			{
				int x = e.getX(); // 获得x坐标
				int y = e.getY(); // 获得y坐标

				if (x >= 0 && x <= SizeOfGrid * NumberOfGrid && y >= 0 && y <= SizeOfGrid * NumberOfGrid)
				{
					int c = x / SizeOfGrid; // 获得列号
					int r = y / SizeOfGrid; // 获得行号

					if (mb_isEmptyGrid(r, c)) // 检验是否允许放置
					{
						if (process == 1)// 如果该圈走
						{
							stateOfGrid[r][c] = 1; // 画圈

							if (mb_isGameOver(r, c))// 判断游戏是否结束
							{
								int temp = JOptionPane.showConfirmDialog(null, "圈胜！是否继续？\n是：继续；\n否：退出。");
								if (temp == 0)
								{
									mb_initData();
								}
								else
								{
									System.exit(0);
								}
							}
							else
							{
								process = 2; // 下一步画叉
							}
						}

						else if (process == 2)// 如果该叉走
						{
							stateOfGrid[r][c] = 2; // 画叉

							if (mb_isGameOver(r, c))
							{
								int temp = JOptionPane.showConfirmDialog(null, "叉胜！是否继续？\n是：继续；\n否：退出。");
								if (temp == 0)
								{
									mb_initData();
								}
								else
								{
									System.exit(0);
								}
							}
							else
							{
								process = 1; // 下一步画圈
							}
						}

						if (mb_isFull())// 棋盘下满了还未分出胜负则和棋
						{
							int temp = JOptionPane.showConfirmDialog(null, "和棋！是否继续？\n是：继续；\n否：退出。");
							if (temp == 0)
							{
								mb_initData();
							}
							else
							{
								System.exit(0);
							}
						}

						repaint(); // 会再次调用paintComponent(Graphics g)方法

					} // if 结束
				} // if 结束
			} // 方法结束
		} // 实现内部类
		); // 方法调用结束
	} // 构造方法结束

	/** 初始化相关变量 */
	public void mb_initData()
	{
		process = 1; // 令圈为先手
		stateOfGrid = new byte[NumberOfGrid][NumberOfGrid]; // 分配内存

		// 初始化棋盘方格的状态
		for (int i = 0; i < NumberOfGrid; i++)
			for (int j = 0; j < NumberOfGrid; j++)
			{
				stateOfGrid[i][j] = 0; // 初始化为0，即所有格子为空
			}
	}

	/** 画图 */
	protected void paintComponent(Graphics g)
	{
		setBounds(0, 0, SizeOfGrid * NumberOfGrid + 2, SizeOfGrid * NumberOfGrid + 2); // 设置画板区域

		// 绘制棋盘网格线
		for (int i = 0; i <= NumberOfGrid; i++)
		{
			g.drawLine(i * SizeOfGrid, 0, i * SizeOfGrid, NumberOfGrid * SizeOfGrid);// 画3道竖线
			g.drawLine(0, i * SizeOfGrid, NumberOfGrid * SizeOfGrid, i * SizeOfGrid);// 画3道横线
		}

		BoradAlreadPaint = true;

		for (int i = 0; i < NumberOfGrid; i++)
			for (int j = 0; j < NumberOfGrid; j++)
			{
				if (stateOfGrid[i][j] == 1) // 画圈
				{
					g.setColor(Color.blue);
					g.drawOval(j * SizeOfGrid + 10, i * SizeOfGrid + 10, SizeOfGrid - 20, SizeOfGrid - 20);
				}
				else if (stateOfGrid[i][j] == 2) // 画叉
				{
					g.setColor(Color.red);
					g.drawLine(j * SizeOfGrid + 10, i * SizeOfGrid + 10, j * SizeOfGrid + SizeOfGrid - 10, i
							* SizeOfGrid + SizeOfGrid - 10);
					g.drawLine(j * SizeOfGrid + SizeOfGrid - 10, i * SizeOfGrid + 10, j * SizeOfGrid + 10, i
							* SizeOfGrid + SizeOfGrid - 10);
				}
			}
	}

	/** 判断某一个格子是否为空 */
	public boolean mb_isEmptyGrid(int r, int c)
	{
		if (stateOfGrid[r][c] != 0) // 已被占 不能再放置
			return false;

		else
			return true; // 
	}

	/** 判断游戏是否结束 */
	public boolean mb_isGameOver(int r, int c)
	{
		int i, j;
		int state = stateOfGrid[r][c]; // 获得当前的状态

		// 行r不变，判断当前所在列
		for (i = 0; i < NumberOfGrid; i++)
		{
			if (stateOfGrid[r][i] != state)
			{
				break;
			}
			if (i == NumberOfGrid - 1)// 说明一行出现连续三个圈或叉了
				return true;
		}

		// 列c不变，判断当前所在行
		for (i = 0; i < NumberOfGrid; i++)
		{
			if (stateOfGrid[i][c] != state)
			{
				break;
			}
			if (i == NumberOfGrid - 1)// 说明一列出现连续三个圈或叉了
				return true;
		}

		// 左右对角线
		if (r == c)// 主对角线
		{
			for (i = 0, j = 0; i < NumberOfGrid && j < NumberOfGrid; i++, j++)
			{
				if (stateOfGrid[i][j] != state)
				{
					break;
				}
				if (j == NumberOfGrid - 1)
					return true;
			}
		}

		if (r + c == NumberOfGrid - 1)// 次对角线
		{
			for (i = 0, j = NumberOfGrid - 1; i < NumberOfGrid && j >= 0; i++, j--)
			{
				if (stateOfGrid[i][j] != process)
				{
					break;
				}
				if (j == 0)
					return true;
			}
		}

		return false;
	}

	/** 判断棋盘是否已满，即是否和棋 */
	public boolean mb_isFull()
	{
		for (int i = 0; i < NumberOfGrid; i++)
			for (int j = 0; j < NumberOfGrid; j++)
			{
				if (stateOfGrid[i][j] == 0)
				{
					return false;
				}
			}
		return true;
	}
}
