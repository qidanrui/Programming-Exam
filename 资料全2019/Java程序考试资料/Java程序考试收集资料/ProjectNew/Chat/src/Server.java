import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Server extends JFrame{
     private JTextField input;
     private JTextArea  output;
     private ObjectOutputStream out;
     private ObjectInputStream in;
     public Server()
     {
    	 JLabel inputlbl=new JLabel("內容");
    	 input=new JTextField(30);
    	 input.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String s=e.getActionCommand();
					System.out.println(s);
 					out.writeObject(s);
					out.flush();
					outputStr("服务端发送:"+e.getActionCommand());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					outputStr("服务端发送出现异常");
				}
			}
    		 
    	 });
    	 output=new JTextArea();
    	 
    	 JPanel top=new JPanel();
    	 top.add(inputlbl);
    	 top.add(input);
    	 this.add(top,BorderLayout.NORTH);
    	 this.add(output,BorderLayout.CENTER);
    	 this.setVisible(true);
    	 this.setSize(new Dimension(400,400));
     }
     public void outputStr(String str)
     {
    	 output.append(str+"\n");
    	 output.setCaretPosition(output.getText().length());
     }
     public void init() throws IOException, ClassNotFoundException
     {
    	 ServerSocket s=new ServerSocket(5000);
    	 outputStr("等待客户端连接");
          while(true)
          {	
        	  Socket st=s.accept();
    		 outputStr("已连接到客户端");
    		 
    		 out=new ObjectOutputStream(st.getOutputStream());
    		 in=new ObjectInputStream(st.getInputStream());
    		 out.writeObject(new String("连接成功"));
    		 out.flush();
    		 String str="";
    		 do
    		 {
    			 str=(String) in.readObject();
    			 outputStr(str);
    		 }while(!str.equals("quit"));
    	 }
     }
    	 

     public static void main(String[] args)
     {
    	 Server s=new Server();
    	 try {
			s.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
}
