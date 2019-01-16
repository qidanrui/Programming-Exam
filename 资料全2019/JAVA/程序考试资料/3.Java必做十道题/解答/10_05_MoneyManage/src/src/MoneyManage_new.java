package src;

/*
 * MoneyManage.java:
 * 这个文件包含了如下的类：
 * 主类:MoneyManage;
 * 类：Money;
 * 内部类：addHandler,modifyHandler,delHandler,nextHandler,preHandler,saveHandler,openHandler,resultHandler
 * 
 */

/**
 * 说明：实现了一个简单的家庭理财信息系统
 */

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Money: 定义了类Money的几个属性
 * 
 * @author ZhangYuchao
 * @version 1.0
 */
class Money
{
	public String name = null;
	public String source = null;
	public String date = null;
	public String amount = null;
}

/**
 * MoneyManage: 主类，实现了界面，并通过添加内部类包含了事件监听器
 * 
 * @author ZhangYuchao
 * @version 1.0
 */
@SuppressWarnings("serial")
public class MoneyManage_new extends JFrame
{
	private ArrayList<Money> list = new ArrayList<Money>();
	private int position = -1;// 位置为-1表示尚不存在元素

	private JButton addB = new JButton("增加");
	private JButton modifyB = new JButton("修改");
	private JButton delB = new JButton("删除");
	private JButton preB = new JButton("上一条");
	private JButton nextB = new JButton("下一条");
	private JButton saveB = new JButton("保存文件");
	private JButton openB = new JButton("打开文件");

	private JLabel nameL = new JLabel("姓名");
	private JTextField nameT = new JTextField("", 4);
	private JLabel sourceL = new JLabel("来源");
	private JTextField sourceT = new JTextField("", 4);
	private JLabel dateL = new JLabel("日期-yyyymmdd");
	private JTextField dateT = new JTextField("", 4);
	private JLabel amountL = new JLabel("金额—收入用正数表示，支出用负数表示");
	private JTextField amountT = new JTextField("", 4);

	private JLabel staticL = new JLabel("以下时间段的收支统计为");
	private JLabel fromL = new JLabel("从(yyyymmdd)");
	private JTextField fromT = new JTextField("", 4);
	private JLabel toL = new JLabel("到(yyyymmdd)");
	private JTextField toT = new JTextField("", 4);
	private JButton resultB = new JButton("结果是：");
	private JTextField resultT = new JTextField("", 3);

	/**
	 * MoneyManage(): 构造方法：初始化界面，添加事件监听器
	 */
	public MoneyManage_new()
	{
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		c.add(addB);
		c.add(modifyB);
		c.add(delB);
		c.add(preB);
		c.add(nextB);
		c.add(saveB);
		c.add(openB);

		c.add(nameL);
		c.add(nameT);
		c.add(sourceL);
		c.add(sourceT);
		c.add(dateL);
		c.add(dateT);
		c.add(amountL);
		c.add(amountT);

		c.add(staticL);
		c.add(fromL);
		c.add(fromT);
		c.add(toL);
		c.add(toT);
		c.add(resultB);
		c.add(resultT);

		addB.addActionListener(new addHandler());
		modifyB.addActionListener(new modifyHandler());
		delB.addActionListener(new delHandler());
		preB.addActionListener(new preHandler());
		nextB.addActionListener(new nextHandler());
		saveB.addActionListener(new saveHandler());
		openB.addActionListener(new openHandler());
		resultB.addActionListener(new resultHandler());

	}

	/**
	 * refresh: 成员方法：通过更新文本框里显示的信息来更新界面
	 * 
	 * @param i
	 *            list里数组的小标值
	 */
	public void refresh(int i)
	{
		nameT.setText(list.get(i).name);
		sourceT.setText(list.get(i).source);
		dateT.setText(list.get(i).date);
		amountT.setText(list.get(i).amount);
	}

	/**
	 * setEmptyInfo: 成员方法：将文本框里的信息设置为空白信息
	 */
	public void setEmptyInfo()
	{
		nameT.setText("");
		sourceT.setText("");
		dateT.setText("");
		amountT.setText("");
	}

	/**
	 * addHandler: 内部类——实现了点击“填加”按钮时引发的操作，填加记录到list里
	 * 
	 * @author ZhangYuchao
	 * @version 1.0
	 */
	class addHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (nameT.getText().equals("") || sourceT.getText().equals("") || dateT.getText().equals("")
					|| amountT.getText().equals(""))
				JOptionPane.showMessageDialog(null, "任意一项都不能为空，请重新输入");
			else
			{
				Money money = new Money();

				money.name = nameT.getText();
				money.source = sourceT.getText();
				money.date = dateT.getText();
				money.amount = amountT.getText();
				list.add(money);

				position = list.size() - 1;// position位置始终指向队尾，下一次在队尾添加
				refresh(position);
				JOptionPane.showMessageDialog(null, "已添加");
			}

		}
	}

	/**
	 * modifyHandler: 内部类——实现了点击“修改”按钮时引发的操作，修改当前记录
	 * 
	 * @author ZhangYuchao
	 * @version 1.0
	 */
	class modifyHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (list.size() == 0)
			{
				JOptionPane.showMessageDialog(null, "记录为空");
				setEmptyInfo();
			}
			else
			{
				list.get(position).name = nameT.getText();// 从本文框中读取信息保存到list中
				list.get(position).source = sourceT.getText();
				list.get(position).date = dateT.getText();
				list.get(position).amount = amountT.getText();

				refresh(position);
				JOptionPane.showMessageDialog(null, "已修改");
			}

		}

	}

	/**
	 * delHandler: 内部类——实现了点击“删除”按钮时引发的操作，删除当前记录
	 * 
	 * @author student
	 * @version 1.0
	 */
	class delHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (list.size() == 0)
			{
				JOptionPane.showMessageDialog(null, "记录为空");
				setEmptyInfo();
			}
			else
			{
				if (list.size() == 1)
				{
					list.remove(position);
					setEmptyInfo();
				}
				else
				{
					if (position == list.size() - 1)
					{
						list.remove(position);
						position = list.size() - 1;
					}
					else
						list.remove(position);

					refresh(position);// 删掉一条记录后，刷新出这个位置的新元素
					JOptionPane.showMessageDialog(null, "已删除");
				}
			}

		}

	}

	/**
	 * preHandler: 内部类——实现了点击“上一条”按钮时引发的操作，当前记录变成上一条记录
	 * 
	 * @author student
	 * @version 1.0
	 */
	class preHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (position == -1)
				JOptionPane.showMessageDialog(null, "还没有记录");

			else if (position == 0)
				JOptionPane.showMessageDialog(null, "已经是第一条记录");
			else
			{
				position--;
				refresh(position);// 刷新出新位置的元素显示在界面上
			}

		}
	}

	/**
	 * addHandler: 内部类——实现了点击“下一条”按钮时引发的操作，当前记录变成下一条记录
	 * 
	 * @author student
	 * @version 1.0
	 */
	class nextHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (position == -1)
				JOptionPane.showMessageDialog(null, "还没有记录");

			else if (position == list.size() - 1)
				JOptionPane.showMessageDialog(null, "已经是最后一条记录");
			else
			{
				position++;
				refresh(position);
			}
		}
	}

	/**
	 * addHandler: 内部类——实现了点击“保存”按钮时引发的操作，把list里的记录保存到文件“收支记录存档文件.txt”里
	 * 
	 * @author student
	 * @version 1.0
	 */
	class saveHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				FileWriter fw = new FileWriter("收支记录存档文件.txt");
				for (int i = 0; i < list.size(); i++)
				{
					fw.write(list.get(i).name + "," + list.get(i).source + "," + list.get(i).date + ","
							+ list.get(i).amount + "," + "\r\n");
				}
				fw.close();
				JOptionPane.showMessageDialog(null, "收支记录保存成功！");
			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}

	/**
	 * addHandler: 内部类——实现了点击“打开”按钮时引发的操作，将文件“收支记录存档文件.txt”里的记录读出来
	 * 
	 * @author ZhangYuchao
	 * @version 1.0
	 */
	class openHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			try
			{
				File f = new File("收支记录存档文件.txt");
				if (!f.exists())
				{
					JOptionPane.showMessageDialog(null, "文件不存在");
					setEmptyInfo();
				}
				else
				{
					BufferedReader br = new BufferedReader(new FileReader(f));
					String line = br.readLine();// 读取一行字符，遇到

					if (line == null)
					{
						JOptionPane.showMessageDialog(null, "记录为空");
						setEmptyInfo();
					}
					else
					{
						list.clear();
						while (line != null)
						{
							String temp[] = line.split(",");

							Money money = new Money();

							money.name = temp[0];
							money.source = temp[1];
							money.date = temp[2];
							money.amount = temp[3];

							list.add(money);

							line = br.readLine();// 继续再往下读一行，因为写入的时候每条记录完了都写如了个"\r\n"
						}
						position = 0;// 从第0条记录的内容开始显示
						refresh(position);
					}

				}

			}
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}

	/**
	 * addHandler: 内部类——实现了点击“结果是”按钮时引发的操作，将计算出的该段时间的收支的和显示在文本框里
	 * 
	 * @author ZhangYuchao
	 * @version 1.0
	 */
	class resultHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Float result = 0.0f;
			for (int i = 0; i < list.size(); i++)
			{
				if (Float.parseFloat(list.get(i).date) >= Integer.parseInt(fromT.getText())
						&& Float.parseFloat(list.get(i).date) <= Integer.parseInt(toT.getText()))

					result = result + Float.parseFloat(list.get(i).amount);
			}
			resultT.setText(String.valueOf(result));
		}
	}

	/**
	 * main: 主方法
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		MoneyManage_new app = new MoneyManage_new();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(570, 180);
		app.setVisible(true);

	}

}
