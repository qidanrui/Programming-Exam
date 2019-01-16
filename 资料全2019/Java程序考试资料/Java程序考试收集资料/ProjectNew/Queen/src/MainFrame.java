import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class MainFrame extends JFrame {
       private JButton resetBtn;
       private JButton readBtn;
       private JButton saveBtn;
       private JButton beginBtn;
       private int num=0;
       private ChessButton[][] chesses=new ChessButton[8][8];
       private long startTime;
       public MainFrame()
       {
    	   ButtonHandle handle=new ButtonHandle();
    	   resetBtn=new JButton("Reset");
    	   resetBtn.addActionListener(handle);
    	   readBtn=new JButton("Read");
    	   readBtn.addActionListener(handle);
    	   saveBtn=new JButton("Save");
    	   saveBtn.addActionListener(handle);
    	   beginBtn=new JButton("Start");
    	   beginBtn.addActionListener(handle);
    	   JPanel topPanel=new JPanel();
    	   topPanel.add(resetBtn);
    	   topPanel.add(readBtn);
    	   topPanel.add(saveBtn);
    	   topPanel.add(beginBtn);
    	   JPanel centerPanel=new JPanel(new GridLayout(8,8,0,0));
    	   for(int i=0;i<8;i++)
    	   {
    		   for(int j=0;j<8;j++)
    		   {
    			   chesses[i][j]=new ChessButton(i,j);
    			   centerPanel.add(chesses[i][j]);
    			   chesses[i][j].addActionListener(new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						ChessButton btn=(ChessButton) e.getSource();
						execute(btn);
					}});
    				
    		   }
    		   
    	   }
    	   this.add(topPanel,BorderLayout.NORTH);
    	   this.add(centerPanel,BorderLayout.CENTER);
    	   this.setSize(new Dimension(400,480));
    	   this.setTitle("八皇后");
    	   this.setResizable(false);
    	   this.setVisible(true);
       }
       private class ButtonHandle implements  ActionListener
       {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==beginBtn)
				startTime=System.currentTimeMillis();
			if(e.getSource()==resetBtn)
			{
				startTime=0;
				num=0;
				for(int i=0;i<8;i++)
				{
					for(int j=0;j<8;j++)
					{
						chesses[i][j].setStatus(ChessStatus.WHITE);
						chesses[i][j].repaint();
					}
				}
			}
		}
    	   
       }
       public void execute(ChessButton btn)
       {
    	   int midX=btn.getRow();
    	   int midY=btn.getCol();
    	   if(btn.getStatus()==ChessStatus.RED)
    			JOptionPane.showMessageDialog(null, "该位置不合法");
    	   else  if(btn.getStatus()==ChessStatus.WHITE)
    	   {
    		    int n=1;
    		    while(midX-n>=0&&midY-n>=0)  //西北方向
    		    {
    		    	ChessButton chess=chesses[midX-n][midY-n];
                    chess.setStatus(ChessStatus.RED);
    		    	n++;
    		    }
    		    
    		    	n=1;
    		    	 while(midX+n<=7&&midY+n<=7)  //东南方向
    	    		    {
    	    		    	ChessButton chess=chesses[midX+n][midY+n];
    	    		    	 chess.setStatus(ChessStatus.RED);
    	    		    	n++;
    	    		    }

    		    	n=1;
   		       	 while(midX+n<=7&&midY-n>=0) //西南方向
   	    		    {
   	    		    	ChessButton chess=chesses[midX+n][midY-n];	   
   	    		     chess.setStatus(ChessStatus.RED);
   	    		    	n++;
   	    		    }
   		    	 
    		    	n=1;
   		    	 while(midX-n>=0&&midY+n<=7) //东北方向
   	    		    {
   	    		    	ChessButton chess=chesses[midX-n][midY+n];
   	    		       chess.setStatus(ChessStatus.RED);
   	    		    	n++;
   	    		    }
    		    
    		    	n=1;
   		    	 while(midX-n>=0) //正北方向
   	    		    {
   	    		    	ChessButton chess=chesses[midX-n][midY];
   	    		     chess.setStatus(ChessStatus.RED);
   	    		    	n++;
   	    		    }
   		    	 
    		    	n=1;
   		    	 while(midX+n<=7) //正北方向
   	    		    {
   	    		    	ChessButton chess=chesses[midX+n][midY];
   	    		     chess.setStatus(ChessStatus.RED);
   	    		    	n++;
   	    		    }

    		    	n=1;
   		    	 while(midY+n<=7) //正东方向
   	    		    {
   	    		    	ChessButton chess=chesses[midX][midY+n];
   	    		     chess.setStatus(ChessStatus.RED);
   	    		    	n++;
   	    		    }
   		    	 
    		    	n=1;
   		    	 while(midY-n>=0) //正西方向
   	    		    {
   	    		    	ChessButton chess=chesses[midX][midY-n];
   	    		       chess.setStatus(ChessStatus.RED);
   	    		    	n++;
   	    		    }
   		    	 
    		    	btn.setStatus(ChessStatus.BLACK);
    		    	
    		    	for(int i=0;i<8;i++)
    		    	{
    		    		for(int j=0;j<8;j++)
    		    			chesses[i][j].repaint();
    		    	}
    		    	num++;
    		    	if(num==8)
    		    	{
    		    		long endTime=System.currentTimeMillis();
    		    		JOptionPane.showMessageDialog(null, "恭喜您，成功完成八皇后!"+"\n"+"您所用的时间为:"+(endTime-startTime)/1000+"s");
    		    	}
    	   }
    	   else
    		   JOptionPane.showMessageDialog(null, "该位置不合法");
       }
       public static void main(String[] args)
       {
    	   new MainFrame();
       }
}
