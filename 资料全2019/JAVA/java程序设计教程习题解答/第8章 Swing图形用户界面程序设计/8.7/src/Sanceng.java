import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Sanceng extends JFrame{

	/**
	 * @param args
	 */
	public Sanceng(){
		JButton jb1 = new JButton("1");
		jb1.setBounds(0, 0, 100, 50);
		JButton jb2 = new JButton("2");
		jb2.setBounds(100, 0, 100, 50);
		JButton jb3 = new JButton("3");
		jb3.setBounds(200, 0, 100, 50);
		JButton jb4 = new JButton("4");
		jb4.setBounds(100, 50, 100, 50);
		JButton jb5 = new JButton("5");
		jb5.setBounds(50, 100, 100, 50);
		JButton jb6 = new JButton("6");
		jb6.setBounds(150, 100, 100, 50);
		
		Container c = this.getContentPane();
		c.setLayout(null);
		c.add(jb1);
		c.add(jb2);
		c.add(jb3);
		c.add(jb4);
		c.add(jb5);
		c.add(jb6);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sanceng s = new Sanceng();
		s.setVisible(true);
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s.setSize(305,180);
	}

}
