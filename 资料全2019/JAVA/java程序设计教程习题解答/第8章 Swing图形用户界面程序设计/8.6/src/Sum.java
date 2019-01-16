import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


public class Sum extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public Sum(){
		Container c =this.getContentPane();
		
		c.setLayout(new FlowLayout());
		final JTextField jf1= new JTextField("1",8);
		//jf1.setEditable(true);
		jf1.setSize(80, 50);
		JTextField jf2= new JTextField("+",3);
		jf2.setEditable(false);
		final JTextField jf3= new JTextField("2",8);
		//jf3.setEditable(true);	
		jf3.setSize(80, 50);
		JButton jb= new JButton("=");
		//jb.setEnabled(false);
		final JTextField jf4= new JTextField("3",8);
		jf4.setEditable(true);
		jf4.setSize(80, 50);
		jb.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int a= Integer.parseInt(jf1.getText());
				int b= Integer.parseInt(jf3.getText());
				String c=String.valueOf(a+b);
				jf4.setText(c);
			}
			
		});
		
		c.add(jf1);
		c.add(jf2);
		c.add(jf3);
		c.add(jb);
		c.add(jf4);
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sum s = new Sum();
		s.setVisible(true);
		s.setSize(400, 100);
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
