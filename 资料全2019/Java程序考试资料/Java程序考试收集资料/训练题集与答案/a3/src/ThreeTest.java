import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class ThreeTest extends JFrame{
      private MyButton[][] btns=new MyButton[4][4];
      private int[][] num=new int[4][4];
      private int suma=0;
      private int sumb=0;
      private int count=0;
      private JButton startBtn;
      public ThreeTest()
      {
    	 JPanel topPanel=new JPanel();
    	 startBtn=new JButton("Start");
    	 startBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Random rand=new Random();
				for(int i=0;i<4;i++)
				{
					for(int j=0;j<4;j++)
					{
						num[i][j]=rand.nextInt(100)+1;
					}
				}
			}
    		 
    	 });
    	 topPanel.add(startBtn);
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
						count++;
						MyButton btn=(MyButton) e.getSource();
						btn.setFlag(true);
						if(count>=4)
						{
							for(int i=0;i<4;i++)
							{
								for(int j=0;j<4;j++)
								{
									if(btns[i][j].isFlag())
									{
										btns[i][j].setText(String.valueOf(num[i][j]));
										btns[i][j].setFlag(false);
									}
								}
							}
							if(suma>sumb)
								JOptionPane.showMessageDialog(null, "A:"+suma+","+"B:"+sumb+","+"A玩家获胜");
							else if(suma<sumb)
								JOptionPane.showMessageDialog(null, "A:"+suma+","+"B:"+sumb+","+"B玩家获胜");
							else
								JOptionPane.showMessageDialog(null, "A:"+suma+","+"B:"+sumb+","+"AB玩家平局");
							count=0;
							suma=0;
							sumb=0;
						}
						else if(count==1)
						{
							for(int i=0;i<4;i++)
							{
								for(int j=0;j<4;j++)
								{
										btns[i][j].setText("");
								}
							}
							suma=suma+num[btn.getRow()][btn.getCol()];
						}
						else if(count<=2)
							suma=suma+num[btn.getRow()][btn.getCol()];
						else
							sumb=sumb+num[btn.getRow()][btn.getCol()];
					}
    				 
    			 });
    		 }
    	 }
    	 this.setVisible(true);
    	 this.add(topPanel,BorderLayout.NORTH);
    	 this.add(centerPanel,BorderLayout.CENTER);
    	 this.setSize(new Dimension(300,400));
    	 this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      }
      public static void main(String[] args)
      {
    	  new ThreeTest();
      }
}
