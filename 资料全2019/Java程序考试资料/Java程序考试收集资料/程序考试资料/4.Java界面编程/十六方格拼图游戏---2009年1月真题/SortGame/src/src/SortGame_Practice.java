package src;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class SortGame_Practice
{
	/** 定义成员变量 */
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JButton[] button;
	private int[] butNum;
	private Vector<String> optionalNum;

	/** 构造函数---用来对成员变量进行初始化 */
	public SortGame_Practice()
	{
		mainFrame = new JFrame("16方格排序游戏");
		mainPanel = new JPanel();
		button = new JButton[16];
		butNum = new int[16];
		for (int i = 0; i < 16; i++)
		{
			button[i] = new JButton();
		}
		optionalNum = new Vector<String>();
		initButtonNum();// 随即排列按钮编号函数

	}

	/** 游戏窗体的启动函数---用来设置窗口大小，添加按钮，注册监听器，最后显示窗口等 */
	public void launchFrame()
	{

		mainFrame.setSize(300, 300);// 设置窗口大小
		mainFrame.setDefaultCloseOperation(3);// 设置默认关闭操作

		mainPanel.setLayout(new GridLayout(4, 4, 3, 3));// 设置面板布局，这里的Layout用到了awt包中的内容
		mainFrame.getContentPane().add(mainPanel);// 必须先获取JFrame内容面板，然后才能添加组件，这是和awt包不同的地方

		for (int i = 0; i < 16; i++)
		{
			button[i].addActionListener(new SetOperator_ActionListener());// 给每个按钮注册事件监听器
			mainPanel.add(button[i]);
		}

		mainFrame.setVisible(true);// 显示窗口
	}

	/** 随即排列按钮编号---关键是Vector的get和remove的联用！即可保证每个按钮上的数字都不同 */
	private void initButtonNum()
	{
		for (int i = 0; i < 16; i++)
		{
			optionalNum.add(String.valueOf(i));// 往向量中顺序添加元素0~15
		}
		int index = -1;
		String str = null;
		for (int i = 0; i < 16; i++)
		{
			index = (int) (Math.random() * optionalNum.size());// 随即产生0~optionalNum.size之间的数字赋给index
			str = optionalNum.get(index);// 然后从向量中按随即数去取元素，然后显示在button上，并存储在butNum[]中
			if (str.equals("0"))
			{
				button[i].setText("");
				butNum[i] = 0;
			}
			else
			{
				button[i].setText(str);
				butNum[i] = Integer.parseInt(str);
			}
			optionalNum.remove(index);// 删除index对应位置的元素
		}
	}

	/** 检查函数---看该按钮是否为0号按钮 */
	private boolean check(int index)
	{
		if (index >= 0 && index < 16 && butNum[index] == 0)
			return true;
		else
			return false;
	}

	/** 判断是否玩成功了 */
	private boolean evaluate()
	{
		int i, j;
		for (i = 0; i < 16; i++)
		{
			if (butNum[i] != i)
				break;
		}
		if (i == 16)
			return true;// 正序成功，空格在左上角

		for (i = 0; i < 15; i++)
		{
			if (butNum[i] != i + 1)
				break;
		}
		if (i == 15)
			return true;// 正序成功，空格在右下角

		for (i = 0, j = 15; j >= 0; i++, j--)
		{
			if (butNum[j] != i)
				break;
		}
		if (i == 16)
			return true;// 反序成功，空格在左上角

		for (i = 0, j = 15; j > 0; i++, j--)
		{
			if (butNum[j] != i + 1)
				break;
		}
		if (i == 15)
			return true;// 反序成功，空格在右下角

		return false;
	}

	/** SetOperator_ActionListener类---实现按钮点击事件的处理 */
	class SetOperator_ActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int num = -1, location = -1;
			int aim = -1;
			int i = 0;

			if (e.getActionCommand().length() == 0)// 如果点击的是0号按钮（因为0号按钮的String长度为0），则什么也不做
				return;

			num = Integer.parseInt(e.getActionCommand());// 获取点击的按钮的数字编号

			while (i < 16)
			{
				if (num == butNum[i++])
				{
					location = i - 1;// 这个循环做的其实就是确定location的位置
					//System.out.println(location);
					break;
				}
			}

			int candidates[] = { location - 1, location + 1, location - 4,
					location + 4 }; // candidates[]数组存放上下左右四个位置
			for (int j = 0; j < 4; j++)
			{
				if (check(candidates[j]))// 检查上下左右四个位置哪个是0按钮
					aim = candidates[j];// aim就是编号为0的空白按钮
			}
			if (aim >= 0 && aim < 16)// 为了避免出现意外情况，这里最好判断一下aim的范围
			{
				String tempStr = button[location].getText();
				int tempNum = butNum[location];
				button[location].setText("");// 被点的按钮变为空白
				butNum[location] = 0;// 被点的按钮编号置为0
				button[aim].setText(tempStr);// 目标按钮变为被点按钮的文本
				butNum[aim] = tempNum;// 目标按钮的编号变为被点按钮的编号
			}
			if (evaluate())// 判断是否玩成功了
			{
				int choice = JOptionPane.showConfirmDialog(mainFrame,
						"成功啦！祝贺你！再来一次吗？", null, JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION)
					initButtonNum();
				else
					System.exit(0);
			}
		}
	}

	/** 主函数 */
	public static void main(String[] args)
	{
		SortGame_Practice game = new SortGame_Practice();
		game.launchFrame();

	}

}
