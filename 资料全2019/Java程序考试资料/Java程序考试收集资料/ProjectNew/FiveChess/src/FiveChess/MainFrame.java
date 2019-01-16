package FiveChess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainFrame  extends JFrame{
      /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton resetBtn;
      private JButton openBtn;
      private JButton saveBtn;
      private boolean black=true;
      private ChessButton[][] chesses=new ChessButton[8][8];
      public MainFrame()
      {
    	  ActionHandle handle=new ActionHandle();
    	  resetBtn=new JButton("Reset");
    	  resetBtn.addActionListener(handle);
    	  openBtn=new JButton("Read");
    	  openBtn.addActionListener(handle);
    	  saveBtn=new JButton("Save");
    	  JPanel topPanel=new JPanel();
    	  topPanel.add(resetBtn);
    	  topPanel.add(openBtn);
    	  topPanel.add(saveBtn);
    	  JPanel centerPanel=new JPanel(new GridLayout(8,8,0,0));
    	  saveBtn.addActionListener(handle);
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
					}
    				  
    			  });
         	    
    		  }
    	  }
    	  this.add(topPanel,BorderLayout.NORTH);
    	  this.add(centerPanel,BorderLayout.CENTER);
    	  this.setSize(new Dimension(400,480));
    	  this.setVisible(true);
    	  this.setTitle("五指棋");
      }
      
      public void execute(ChessButton btn)
      {
    	  int midX=btn.getRow();
    	  int midY=btn.getCol();
    	  if(black&&btn.getStatus()==ChessStatus.WHITE)
    	  {
    		  int leftNum=0;
    		  int rightNum=0;
    		  int n=1;
              btn.setStatus(ChessStatus.BLACK);
              black=false;
              btn.repaint();
              while(midX-n>=0&&midY-n>=0)  //西北方向
              {
            	  ChessButton button=chesses[midX-n][midY-n];
            	  if(button.getStatus()==ChessStatus.BLACK)
            	  { 
            		  leftNum++;
            		  n++;
            	  }
            	  else
                     break;
              }
              n=1;
              while(midX+n<=7&&midY+n<=7) //东南方向
              {
            	  ChessButton button=chesses[midX+n][midY+n];
            	  if(button.getStatus()==ChessStatus.BLACK)
            	  { 
            		  rightNum++;
            		  n++;
            	  }
            	  else
                     break;
              }
              if((leftNum+rightNum+1)>=5)
              {
            	  JOptionPane.showMessageDialog(null, "黑方获胜");
              }
              
              leftNum=0;
    		  rightNum=0;
    		  n=1;
              while(midX+n<=7&&midY-n>=0) //东北方向
              {
            	  ChessButton button=chesses[midX+n][midY-n];
            	  if(button.getStatus()==ChessStatus.BLACK)
            	  { 
            		  leftNum++;
            		  n++;
            	  }
            	  else
                     break;
              }
              n=1;
              while(midX-n>=0&&midY+n<=7)//西南方向
              {
            	  ChessButton button=chesses[midX-n][midY+n];
            	  if(button.getStatus()==ChessStatus.BLACK)
            	  { 
            		  rightNum++;
            		  n++;
            	  }
            	  else
                     break;
              }
              if((leftNum+rightNum+1)>=5)
              {
            	  JOptionPane.showMessageDialog(null, "黑方获胜");
              }
    		  
              leftNum=0;
    		  rightNum=0;
    		  n=1;
              while(midY-n>=0) //正北方向
              {
            	  ChessButton button=chesses[midX][midY-n];
            	  if(button.getStatus()==ChessStatus.BLACK)
            	  { 
            		  leftNum++;
            		  n++;
            	  }
            	  else
                     break;
              }
              n=1;
              while(midY+n<=7)//正南方向
              {
            	  ChessButton button=chesses[midX][midY+n];
            	  if(button.getStatus()==ChessStatus.BLACK)
            	  { 
            		  rightNum++;
            		  n++;
            	  }
            	  else
                     break;
              }
              if(leftNum+rightNum+1>=5)
              {
            	  JOptionPane.showMessageDialog(null, "黑方获胜");
              }
              
              leftNum=0;
    		  rightNum=0;
    		  n=1;
              while(midX+n<=7) //正东方向
              {
            	  ChessButton button=chesses[midX+n][midY];
            	  if(button.getStatus()==ChessStatus.BLACK)
            	  { 
            		  leftNum++;
            		  n++;
            	  }
            	  else
                     break;
              }
              n=1;
              while(midX-n>=0)//正西方向
              {
            	  ChessButton button=chesses[midX-n][midY];
            	  if(button.getStatus()==ChessStatus.BLACK)
            	  { 
            		  rightNum++;
            		  n++;
            	  }
            	  else
                     break;
              }
              if(leftNum+rightNum+1>=5)
              {
            	  JOptionPane.showMessageDialog(null, "黑方获胜");
              }
    	  }
    	  else if(!black&&btn.getStatus()==ChessStatus.WHITE)
    	  {
              btn.setStatus(ChessStatus.GRAY);
              btn.repaint();
              black=true;
    		  int leftNum=0;
    		  int rightNum=0;
    		  int n=1;
              while(midX-n>=0&&midY-n>=0)  //西北方向
              {
            	  ChessButton button=chesses[midX-n][midY-n];
            	  if(button.getStatus()==ChessStatus.GRAY)
            	  { 
            		  leftNum++;
            		  n++;
            	  }
            	  else
                     break;
              }
              n=1;
              while(midX+n<=7&&midY+n<=7) //东南方向
              {
            	  ChessButton button=chesses[midX+n][midY+n];
            	  if(button.getStatus()==ChessStatus.GRAY)
            	  { 
            		  rightNum++;
            		  n++;
            	  }
            	  else
                     break;
              }
              if(leftNum+rightNum+1>=5)
              {
            	  JOptionPane.showMessageDialog(null, "白方获胜");
              }
              
              leftNum=0;
    		  rightNum=0;
    		  n=1;
              while(midX+n<=7&&midY-n>=0) //东北方向
              {
            	  ChessButton button=chesses[midX+n][midY-n];
            	  if(button.getStatus()==ChessStatus.GRAY)
            	  { 
            		  leftNum++;
            		  n++;
            	  }
            	  else
                     break;
              }
              n=1;
              while(midX-n>=0&&midY+n<=7)//西南方向
              {
            	  ChessButton button=chesses[midX-n][midY+n];
            	  if(button.getStatus()==ChessStatus.GRAY)
            	  { 
            		  rightNum++;
            		  n++;
            	  }
            	  else
                     break;
              }
              if(leftNum+rightNum+1>=5)
              {
            	  JOptionPane.showMessageDialog(null, "白方获胜");
              }
    		  
              leftNum=0;
    		  rightNum=0;
    		  n=1;
              while(midY-n>=0) //正北方向
              {
            	  ChessButton button=chesses[midX][midY-n];
            	  if(button.getStatus()==ChessStatus.GRAY)
            	  { 
            		  leftNum++;
            		  n++;
            	  }
            	  else
                     break;
              }
              n=1;
              while(midY+n<=7)//正南方向
              {
            	  ChessButton button=chesses[midX][midY+n];
            	  if(button.getStatus()==ChessStatus.GRAY)
            	  { 
            		  rightNum++;
            		  n++;
            	  }
            	  else
                     break;
              }
              if(leftNum+rightNum+1>=5)
              {
            	  JOptionPane.showMessageDialog(null, "白方获胜");
              }
              
              leftNum=0;
    		  rightNum=0;
    		  n=1;
              while(midX+n<=7) //正东方向
              {
            	  ChessButton button=chesses[midX+n][midY];
            	  if(button.getStatus()==ChessStatus.GRAY)
            	  { 
            		  leftNum++;
            		  n++;
            	  }
            	  else
                     break;
              }
              n=1;
              while(midX-n>=0)//正西方向
              {
            	  ChessButton button=chesses[midX-n][midY];
            	  if(button.getStatus()==ChessStatus.GRAY)
            	  { 
            		  rightNum++;
            		  n++;
            	  }
            	  else
                     break;
              }

              if(leftNum+rightNum+1>=5)
              {
            	  JOptionPane.showMessageDialog(null, "白方获胜");
              }
    	  }
    	  else
    	  {
    		  JOptionPane.showMessageDialog(null, "该位置已有棋子");
    	  }
      }
      private class ActionHandle implements ActionListener
      {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==resetBtn)
			{
				black=true;
				for(int i=0;i<8;i++)
				{
					for(int j=0;j<8;j++)
					{
						chesses[i][j].setStatus(ChessStatus.WHITE);
						chesses[i][j].repaint();
					}
				}
			}
			else if(e.getSource()==openBtn)
			{
				JFileChooser fileChooser=new JFileChooser();
				FileOperate operate=new FileOperate();
				int k=fileChooser.showOpenDialog(null);
				if(k==JFileChooser.APPROVE_OPTION)
				{
					File f=fileChooser.getSelectedFile();
					Result r=(Result)operate.readObject(f.getAbsolutePath());
					black=r.isBlack();
					for(int i=0;i<8;i++)
					{
						for(int j=0;j<8;j++)
						{
							chesses[i][j].setStatus(r.getChesses()[i][j].getStatus());
							chesses[i][j].repaint();
						}
					}
						
				}
			}
			else if(e.getSource()==saveBtn)
			{
				JFileChooser fileChooser=new JFileChooser();
				FileOperate operate=new FileOperate();
				int k=fileChooser.showSaveDialog(null);
				if(k==JFileChooser.APPROVE_OPTION)
				{
					File f=fileChooser.getSelectedFile();
					Result r=new Result();
					r.setBlack(black);
					r.setChesses(chesses);
					if(operate.writeObj(r, f.getAbsolutePath()))
					{
						JOptionPane.showMessageDialog(null, "存档成功");
					}
					else 
						JOptionPane.showMessageDialog(null, "存档失败");
				}
			}
		}
    	  
      }
      public static void main(String[] args)
      {
    	  new MainFrame();
      }
}
