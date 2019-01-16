import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainFrame extends JFrame {
    private MyButton[][] btns=new MyButton[4][4];
    private JButton startBtn;
    private JButton closeBtn;

    public MainFrame()
    {
    	ButtonHandle handle=new ButtonHandle();
    	startBtn=new JButton("Start");
    	startBtn.addActionListener(handle);
    	closeBtn=new JButton("Close");
    	closeBtn.addActionListener(handle);
    	JPanel topPanel=new JPanel();
    	topPanel.add(startBtn);
    	topPanel.add(closeBtn);
    	JPanel centerPanel=new JPanel(new GridLayout(4,4,0,0));
    	
        for(int i=0;i<4;i++)
        {
        	for(int j=0;j<4;j++)
        	{
        		btns[i][j]=new MyButton(i,j);
        		centerPanel.add(btns[i][j]);
        		btns[i][j].addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						MyButton btn=(MyButton) e.getSource();
						int x=btn.getRow();
						int y=btn.getCol();
						String value=btn.getValue();
						if(!value.equals(""))
						{
							
							 if(x-1>=0&&btns[x-1][y].getValue().equals(""))
							{
								btns[x-1][y].setValue(value);
								btns[x-1][y].setText(value);
				                btn.setValue("");
				                btn.setText("");
							}
							else if(x+1<4&&btns[x+1][y].getValue().equals(""))
							{
								btns[x+1][y].setValue(value);
								btns[x+1][y].setText(value);
				                btn.setValue("");
				                btn.setText("");
							}
							else if(y+1<4&&btns[x][y+1].getValue().equals(""))
							{
								btns[x][y+1].setValue(value);
								btns[x][y+1].setText(value);
				                btn.setValue("");
				                btn.setText("");
							}
							else if(y-1>=0&&btns[x][y-1].getValue().equals(""))
							{
								btns[x][y-1].setValue(value);
								btns[x][y-1].setText(value);
				                btn.setValue("");
				                btn.setText("");
							}
						}
					}
        			
        		});
        	}
        }
        this.add(topPanel,BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);
        this.setSize(new Dimension(400,500));
        this.setVisible(true);
    }
    public void generate()
    {
    	Random rand=new Random();
    	for(int i=1;i<=15;i++)
    	{
    		int row=rand.nextInt(4);
    		int col=rand.nextInt(4);
    		while(!btns[row][col].getText().equals(""))
    		{
    			row=rand.nextInt(4);
        		col=rand.nextInt(4);
    		}
    		btns[row][col].setValue(String.valueOf(i));
    		btns[row][col].setText(String.valueOf(i));
    	}
    }
    private class ButtonHandle implements ActionListener
    {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==startBtn)
			{
				generate();
			}
		}
    	
    }
    public static void main(String[] args)
    {
    	new MainFrame();
    }
}
