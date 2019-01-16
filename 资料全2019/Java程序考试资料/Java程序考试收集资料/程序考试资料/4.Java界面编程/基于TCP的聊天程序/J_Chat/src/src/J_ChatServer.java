package src;

// ////////////////////////////////////////////////////////
// 
// J_ChatServer.java
// 
// 
// 简介:基于TCP的聊天例程——服务器端程序部分
//
// ////////////////////////////////////////////////////////

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class J_ChatServer extends JFrame
{
	private ObjectInputStream m_input; // 输入流
	private ObjectOutputStream m_output; // 输出流
	private JTextField m_enter; // 输入区域
	private JTextArea m_display; // 显示区域
	private int m_clientNumber = 0; // 连接的客户数

	/** 构造方法，用来 在图形界面中添加组件 */
	public J_ChatServer()
	{
		super("聊天程序服务器端");
		Container c = getContentPane();// 必须先获取JFrame内容面板，然后才能添加组件，这是和awt包不同的地方
		m_enter = new JTextField();
		m_enter.setEnabled(false);// 在未连接前输入框为禁用状态

		m_enter.addActionListener(new ActionListener()// 这里注册的监听器是响应文本框有输入文本后按回车后的事件
				{
					public void actionPerformed(ActionEvent event)
					{
						try
						// 向客户端发送数据
						{
							String s = event.getActionCommand();// 获取文本区域的输入内容
							m_output.writeObject(s);// 向ObjectOutputStream写入特定的对象
							m_output.flush();// 刷新输出流，强制缓冲区中的输出字节被写出
							mb_displayAppend("服务器端: " + s);// 在本端显示刚刚发出去的内容
							m_enter.setText(""); // 清除输入区域的原有内容
						}
						catch (Exception e)
						{
							System.err.println("发生异常:" + e);
							e.printStackTrace();
						} // try-catch结构结束
					} // 方法actionPerformed结束
				} // 实现接口ActionListener的内部类结束
				); // addActionListener方法调用结束

		c.add(m_enter, BorderLayout.NORTH);
		m_display = new JTextArea();
		c.add(new JScrollPane(m_display), BorderLayout.CENTER);// 添加一个滚动面板，内容是m_display（JTextArea）
	}

	/** 服务器端的显示函数 */
	public void mb_displayAppend(String s)
	{
		m_display.append(s + "\n");// 向JTextArea文本区域追加写内容
		m_display.setCaretPosition(m_display.getText().length());// 随着文字的增多滚动条能够自动地往下滚动
		m_enter.requestFocusInWindow(); // 总是转移输入焦点到输入区域
	}

	/** 来自客户端的消息为以下这些字符将结束客户端聊天进程 */
	public boolean mb_isEndSession(String m)
	{
		if (m.equalsIgnoreCase("q"))
			return (true);
		if (m.equalsIgnoreCase("quit"))
			return (true);
		if (m.equalsIgnoreCase("exit"))
			return (true);
		if (m.equalsIgnoreCase("end"))
			return (true);
		if (m.equalsIgnoreCase("结束"))
			return (true);
		return (false);
	}

	/** 服务器端的运行函数 */
	public void mb_run()
	{
		try
		{
			ServerSocket server = new ServerSocket(5000);// 指定端口号为5000
			String m; // 来自客户端的消息

			while (true)// 不断检查客户端是否有连接请求
			{
				m_clientNumber++;
				mb_displayAppend("等待连接[" + m_clientNumber + "]");
				Socket s = server.accept();
				mb_displayAppend("接收到客户端连接[" + m_clientNumber + "]");

				m_output = new ObjectOutputStream(s.getOutputStream());
				m_input = new ObjectInputStream(s.getInputStream());
				m_output.writeObject("连接成功");// 向ObjectOutputStream写入特定对象，注意客户端那边会自己加上“服务器端：”字样的前缀的
				m_output.flush();// 刷新输出流，强制缓冲区中的输出字节被写出
				m_enter.setEnabled(true);

				do// 接收来自客户端的消息
				{
					m = (String) m_input.readObject();// 从ObjectInputStream读取对象，这里强制转换成String类型
					mb_displayAppend("客户端: " + m);// 显示在服务器端
				}
				while (!mb_isEndSession(m));// 当客户端发来的消息不是结束标识时，就一直读取并显示消息；否则客户端退出程序

				m_output.writeObject("q"); // 通知客户端退出程序
				m_output.flush();
				m_enter.setEnabled(false);
				m_output.close();// 关闭输出流
				m_input.close();// 关闭输入流
				s.close();// 关闭连接套接字

				mb_displayAppend("连接[" + m_clientNumber + "]结束");
			} // while循环结束
		}
		catch (Exception e)
		{
			System.err.println("发生异常:" + e);
			e.printStackTrace();
			mb_displayAppend("连接[" + m_clientNumber + "]发生异常");
		} // try-catch结构结束
	} // 方法mb_run结束

	public static void main(String args[])
	{
		J_ChatServer app = new J_ChatServer();

		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(350, 150);
		app.setVisible(true);

		app.mb_run();
	} // 方法main结束
} // 类J_ChatServer结束
