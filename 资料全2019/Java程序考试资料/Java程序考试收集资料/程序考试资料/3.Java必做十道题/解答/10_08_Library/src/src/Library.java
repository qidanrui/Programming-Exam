package src;

/*
 * Library.java:
 * 这个文件包含了以下几个类：
 * 主类：Library;类：Book;内部类：addHandler,modifyHandler,delHandler,lookHandler,
 * serchTypeHandler,serchAuthorHandler,serchPressHandler,
 * serchReaderHandler,sortPriceHandler,sortDateHandler,priceComp,dateComp
 * 
 */

/**
 * 说明：一个简单的图书管理信息系统
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Book: 定义了类Book的几个属性
 * 
 * @author zhaoyuan
 * @version1.0
 */
class Book
{
	public String id = null;
	public String name = null;
	public String type = null;
	public String author = null;
	public String price = null;
	public String pressDate = null;
	public String press = null;
	public String buyDate = null;
	public String reader = null;

}

/**
 * Library: 主类，实现了界面，并添加了实现事件监听器的内部类
 * 
 * @author 赵媛
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Library extends JFrame
{

	private ArrayList<Book> list = new ArrayList<Book>();
	private ArrayList<Book> serchList = new ArrayList<Book>();

	private JPanel p1 = new JPanel();
	private JButton addB = new JButton("录入");
	private JButton modifyB = new JButton("修改");
	private JButton delB = new JButton("删除");
	private JButton lookB = new JButton("查看");
	private JButton serchTypeB = new JButton("搜索-按类型");
	private JButton serchAuthorB = new JButton("搜索-按作者");
	private JButton serchPressB = new JButton("搜索-按出版社");
	private JButton serchReaderB = new JButton("搜索-按借书人");
	private JButton sortPriceB = new JButton("排序-按价格");
	private JButton sortDateB = new JButton("排序-按出版日期");

	private JPanel p2 = new JPanel();
	private JLabel idL = new JLabel("编号");
	private JTextField idT = new JTextField("", 3);
	private JLabel nameL = new JLabel("书名");
	private JTextField nameT = new JTextField("", 3);
	private JLabel typeL = new JLabel("类型");
	private JTextField typeT = new JTextField("", 3);
	private JLabel authorL = new JLabel("作者");
	private JTextField authorT = new JTextField("", 3);
	private JLabel priceL = new JLabel("价格");
	private JTextField priceT = new JTextField("", 3);
	private JLabel pressDateL = new JLabel("出版日期");
	private JTextField pressDateT = new JTextField("", 3);
	private JLabel pressL = new JLabel("出版社");
	private JTextField pressT = new JTextField("", 3);
	private JLabel buyDateL = new JLabel("购买日期");
	private JTextField buyDateT = new JTextField("", 3);
	private JLabel readerL = new JLabel("借阅人");
	private JTextField readerT = new JTextField("", 3);

	private JTextArea info = new JTextArea();

	/**
	 * Library(): 构造方法，实现了界面，并对每个按钮添加了事件监听器
	 * 
	 * @author zhaoyuan
	 */
	public Library()
	{
		Container c = getContentPane();

		p1.add(addB);// panel可以直接添加
		p1.add(modifyB);
		p1.add(delB);
		p1.add(lookB);
		p1.add(serchTypeB);
		p1.add(serchAuthorB);
		p1.add(serchPressB);
		p1.add(serchReaderB);
		p1.add(sortPriceB);
		p1.add(sortDateB);

		p2.add(idL);
		p2.add(idT);
		p2.add(nameL);
		p2.add(nameT);
		p2.add(typeL);
		p2.add(typeT);
		p2.add(authorL);
		p2.add(authorT);
		p2.add(priceL);
		p2.add(priceT);
		p2.add(pressDateL);
		p2.add(pressDateT);
		p2.add(pressL);
		p2.add(pressT);
		p2.add(buyDateL);
		p2.add(buyDateT);
		p2.add(readerL);
		p2.add(readerT);

		c.add(p1, BorderLayout.NORTH);// 而顶层容器Frame则需要先获取内容面板再添加
		c.add(p2, BorderLayout.SOUTH);
		c.add(new JScrollPane(info), BorderLayout.CENTER);// 把JTextArea封装到JScrollPane中再添加到Frame中

		addB.addActionListener(new addHandler());
		modifyB.addActionListener(new modifyHandler());
		delB.addActionListener(new delHandler());
		lookB.addActionListener(new lookHandler());
		serchTypeB.addActionListener(new serchTypeHandler());
		serchAuthorB.addActionListener(new serchAuthorHandler());
		serchPressB.addActionListener(new serchPressHandler());
		serchReaderB.addActionListener(new serchReaderHandler());
		sortPriceB.addActionListener(new sortPriceHandler());
		sortDateB.addActionListener(new sortDateHandler());

	}

	/**
	 * refresh: 更新textaAea info的信息
	 * 
	 * @param list
	 *            ：表示要更新的list，如搜索到的信息的list
	 * @author zhaoyuan
	 */
	private void refresh(ArrayList<Book> list)
	{
		StringBuffer sb = new StringBuffer("");
		sb.append("编号" + "\t" + "书名" + "\t" + "类型" + "\t" + "作者" + "\t" + "价格" + "\t" + "出版日期" + "\t" + "出版社" + "\t"
				+ "购买日期" + "\t" + "借阅人" + "\n");
		for (int i = 0; i < list.size(); i++)
			sb.append(list.get(i).id + "\t" + list.get(i).name + "\t" + list.get(i).type + "\t" + list.get(i).author
					+ "\t" + list.get(i).price + "\t" + list.get(i).pressDate + "\t" + list.get(i).press + "\t"
					+ list.get(i).buyDate + "\t" + list.get(i).reader + "\n");

		info.setText(sb.toString());

	}

	/**
	 * addHandler: 实现了点“录入”时的功能——增加一条信息
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 */
	private class addHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Book book = new Book();
			book.id = idT.getText();
			book.name = nameT.getText();
			book.type = typeT.getText();
			book.author = authorT.getText();
			book.price = priceT.getText();
			book.pressDate = pressDateT.getText();
			book.press = pressT.getText();
			book.buyDate = buyDateT.getText();
			book.reader = readerT.getText();

			list.add(book);
			refresh(list);
		}
	}

	/**
	 * modifyHandler: 实现了点“修改”时的功能——修改某个指定的id的信息
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 */
	private class modifyHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int index = -1;
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i).id.equals(idT.getText()))
				{
					index = i;
					break;
				}
			}
			if (index == -1)
				JOptionPane.showMessageDialog(null, "此id不存在！");

			else
			{
				list.get(index).id = idT.getText();
				list.get(index).name = nameT.getText();
				list.get(index).type = typeT.getText();
				list.get(index).author = authorT.getText();
				list.get(index).price = priceT.getText();
				list.get(index).pressDate = pressDateT.getText();
				list.get(index).press = pressT.getText();
				list.get(index).buyDate = buyDateT.getText();
				list.get(index).reader = readerT.getText();

				refresh(list);
			}

		}
	}

	/**
	 * delHandler: 实现了点“删除”时的功能——输出指定id的信息
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 */
	private class delHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			int index = -1;
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i).id.equals(idT.getText()))
				{
					index = i;
					break;
				}
			}
			if (index != -1)
			{
				list.remove(index);

				refresh(list);
			}
			else
				JOptionPane.showMessageDialog(null, "此id不存在！");
		}
	}

	/**
	 * lookHandler: 实现了点“查看”时的功能——查看所有信息
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 */
	private class lookHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			refresh(list);
		}
	}

	/**
	 * serchTypeHandler: 实现了点“搜索-按类型”时的功能——搜索并显示指定类型的数据
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 */
	private class serchTypeHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			serchList.clear();
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i).type.equals(typeT.getText()))
				{
					serchList.add(list.get(i));
				}
			}
			refresh(serchList);
		}
	}

	/**
	 * serchAuthorHandler: 实现了点“搜索-按作者”时的功能——搜索并显示指定作者的数据
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 */
	private class serchAuthorHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			serchList.clear();
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i).author.equals(authorT.getText()))
				{
					serchList.add(list.get(i));
				}
			}
			refresh(serchList);
		}
	}

	/**
	 * serchPressHandler: 实现了点“搜索-按出版社”时的功能——搜索并显示指定出版社的数据
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 */
	private class serchPressHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			serchList.clear();
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i).press.equals(pressT.getText()))
				{
					serchList.add(list.get(i));
				}
			}
			refresh(serchList);
		}
	}

	/**
	 * serchReaderHandler: 实现了点“搜索-按借阅人”时的功能——搜索并显示指定借阅人的数据
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 */
	private class serchReaderHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			serchList.clear();
			for (int i = 0; i < list.size(); i++)
			{
				if (list.get(i).reader.equals(readerT.getText()))
				{
					serchList.add(list.get(i));
				}
			}
			refresh(serchList);
		}
	}

	/**
	 * sortPriceHandler: 实现了点“排序-按价格”时的功能——按价格从低到高排序并显示出来
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 */
	private class sortPriceHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Comparator<Book> comparator = new priceComp();// //////////////////
			Collections.sort(list, comparator);
			refresh(list);
		}

	}

	/**
	 * pricComp: 按价格比较的比较器，实现了接口Comparator<Book>
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 */
	private class priceComp implements Comparator<Book>
	{
		public int compare(Book book1, Book book2)// //////////////////////
		{
			return book1.price.compareTo(book2.price);
		}
	}

	/**
	 * sortDateHandler: 实现了点“排序-按购买日期”时的功能——按购买日期从低到高排序并显示出来
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 */
	private class sortDateHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			Comparator<Book> comparator = new dateComp();
			Collections.sort(list, comparator);
			refresh(list);
		}
	}

	/**
	 * dateComp: 按购买日期比较的比较器，实现了接口Comparator<Book>
	 * 
	 * @author zhaoyuan
	 * @version 1.0
	 */
	class dateComp implements Comparator<Book>
	{
		public int compare(Book book1, Book book2)
		{
			return book1.buyDate.compareTo(book2.buyDate);
		}
	}

	/**
	 * main： 主方法
	 * 
	 * @param args
	 * @author zhaoyuan
	 */
	public static void main(String[] args)
	{
		Library app = new Library();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(800, 500);
		app.setVisible(true);

	}

}
