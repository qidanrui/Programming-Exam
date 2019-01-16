import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class MainFrame extends JFrame{
	private JButton[][] btns=new JButton[10][10];
	public MainFrame()
	{
		this.setLayout(new GridLayout(10,10,0,0));
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
			{
				btns[i][j]=new JButton();
				this.add(btns[i][j]);
				btns[i][j].addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						JButton b=(JButton) e.getSource();
						t.stop();
						if(b.getText().equals("R"))
						{
							JOptionPane.showMessageDialog(null,"win");
							start();
							t.restart();
						}
						else
						{
							JOptionPane.showMessageDialog(null,"lose");
							start();
							t.restart();
						}
					}
					
				});
			}
		}
		this.setVisible(true);
		this.setSize(new Dimension(600,600));
		run();
		
	}
	public void start()
	{
		Random rand=new Random();
		int i=rand.nextInt(10);
		int j=rand.nextInt(10);
		for(int m=0;m<10;m++)
		{
			for(int n=0;n<10;n++)
				btns[m][n].setText("");
		}
		btns[i][j].setText("R");
	}
	 Timer t;
	public void run()
	{
		start();
		int delay=1000;
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// ...Perform a task...
				if (JOptionPane.showConfirmDialog(null, "lose,time out","Information",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
                       t.restart();
                       start();
				}
			}
		};
		t = new Timer(delay, taskPerformer);
		t.start();
	}
	public static void main(String[] args)
	{
		new MainFrame();
	}
}
