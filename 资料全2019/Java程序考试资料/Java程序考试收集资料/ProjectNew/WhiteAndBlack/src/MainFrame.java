import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;


public class MainFrame extends JFrame {
       private JButton openBtn;
       private JButton saveBtn;
       private JButton reopenBtn;
       private boolean black=true;
       private JPanel centerPanel;
       private boolean judge=false;  //是否进行游戏结束以及判断是否有路可走
       private ChessButton[][]  chesses=new ChessButton[8][8];
       public MainFrame()
       {
    	     ActionHandle buttonHandle=new ActionHandle();
             openBtn=new JButton("Read");
             openBtn.addActionListener(buttonHandle);
             saveBtn=new JButton("Save");
             saveBtn.addActionListener(buttonHandle);
             reopenBtn=new JButton("ReOpen");
             reopenBtn.addActionListener(buttonHandle);
             JPanel topPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
             topPanel.add(openBtn);
             topPanel.add(saveBtn);
             topPanel.add(reopenBtn);
             
             centerPanel=new JPanel(new GridLayout(8,8,0,0));
            for(int i=0;i<8;i++) //棋子初始化
            {
            	for(int j=0;j<8;j++)
            	{
            		chesses[i][j]=new ChessButton(i,j);
            		if((i==3&&j==3)||(i==4&&j==4))
            			chesses[i][j].setStatus(ChessStatus.BLACK);
            		else if((i==3&&j==4)||(i==4&&j==3))
            			chesses[i][j].setStatus(ChessStatus.GRAY);
            		chesses[i][j].addActionListener(new ActionListener()
            		{

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							ChessButton btn=(ChessButton)e.getSource();
							execute(btn);
						}
            			
            		});
            		centerPanel.add(chesses[i][j]);
            	}
            }
            this.setTitle("黑白棋");
            this.add(topPanel,BorderLayout.NORTH);
            this.add(centerPanel,BorderLayout.CENTER);
            this.setSize(new Dimension(400,480));
            this.setResizable(false);
            this.setVisible(true);
       }
       private class ActionHandle implements ActionListener
       {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==saveBtn)  //存档
			{
				FileOperate operate=new FileOperate();
				Result result=new Result(chesses,black);
				JFileChooser fileChooser=new JFileChooser();
				int k=fileChooser.showSaveDialog(null);
				if(k==JFileChooser.APPROVE_OPTION)
				{
					File f=fileChooser.getSelectedFile();
				    if(operate.writeObject(result, f.getAbsolutePath()))
				    	JOptionPane.showMessageDialog(null, "存档成功!");
				    else 
				    	JOptionPane.showMessageDialog(null, "存档失败!");
				}
			}
			else if(e.getSource()==openBtn)  //读档
			{
				FileOperate oprate=new FileOperate();
				Result result=null;
				JFileChooser fileChooser=new JFileChooser();
				int k=fileChooser.showOpenDialog(null);
				if(k==JFileChooser.APPROVE_OPTION)
				{
					File f=fileChooser.getSelectedFile();
					result=(Result) oprate.readObject(f.getAbsolutePath());
					if(result!=null)
					{
						black=result.isBlack();
						for(int i=0;i<8;i++)
						{
							for(int j=0;j<8;j++)
							{
                                chesses[i][j].setStatus(result.getButtons()[i][j].getStatus());
								chesses[i][j].repaint();
								
							}
						}
					}
				}
			}
			else if(e.getSource()==reopenBtn)   //重置
			{
				reOpen();
			}
		}
    	   
       }
       /**
        * 判断是否游戏结束，同时判断 正方和反方都是否有路可以走，若都没有路，则游戏结束，棋子多的为胜
        * @return  true表示游戏结束，false表示游戏未结束
        */
       public boolean gameOver()
       {
    	   boolean flag=true;
    	   boolean temp=black; //保存当前的Player
    	   judge=true;
    	   black=true;
    	   for(int i=0;i<8;i++)  //判断正方是否有路可走
    	   {
    		   for(int j=0;j<8;j++)
    		   {
    			   if(operate(chesses[i][j]))
    			   {
    				   flag=false;
    				   black=temp;
    				   return flag;
    			   }
    		   }
    	   }
    	   
    	   black=false;
    	   for(int i=0;i<8;i++) //判断反方是否有路可走
    	   {
    		   for(int j=0;j<8;j++)
    		   {
    			   if(operate(chesses[i][j]))
    			   {
    				   flag=false;
    				   black=temp;
    				   return flag;
    			   }
    		   }
    	   }
    	   judge=false;
    	   black=temp;
    	   return flag;
       }
       
       /**
        * 获得获胜者信息
        * @return  String  获胜者信息
        */
       public String getWinner()
       {
    	    int blackNum=0;
    	    int grayNum=0;
    	    for(int i=0;i<8;i++)
    	    {
    	    	for(int j=0;j<8;j++)
    	    	{
    	    		if(chesses[i][j].getStatus()==ChessStatus.BLACK)
    	    			blackNum++;
    	    		else if(chesses[i][j].getStatus()==ChessStatus.GRAY)
    	    			grayNum++;
    	    	}
    	    }
    	    if(blackNum>grayNum)
    	    	return new String("游戏结束，正方胜");
    	    else if(blackNum==grayNum)
    	    	return new String("游戏结束，平局");
    	    else 
    	    	return new String("游戏结束，反方胜");
    	   }
        /**
         * 判断是否有路
         * @return   true表示有路，false表示没有路
         */
         public boolean hasPath()
         {
      	      judge=true;
        	  boolean flag=false;
        	   for(int i=0;i<8;i++)
       	     {
       		   for(int j=0;j<8;j++)
       		   {
       			   if(operate(chesses[i][j]))
       			   {
       				   judge=false;
       				   flag=true;
       				   return flag;
       			   }
       		   }
       	   }
        	   judge=false;
        	   return flag;
         }
         
       /**
        * 放置一颗棋子执行过程
        * @param btn
        */
       public void execute(ChessButton btn)
       {
    	  if(!operate(btn))  //该位置不能走
    		  JOptionPane.showMessageDialog(null, "该位置不能走");
    	  else
    	  {
    		  for(int i=0;i<8;i++)  //之所以绘制两次是因为结束时需要先绘制完才能够判断游戏是否结束
       		   for(int j=0;j<8;j++)
       			   chesses[i][j].repaint();
    		  
    		  if(gameOver())  //游戏结束
       		   JOptionPane.showMessageDialog(null, getWinner());
       	   else if(!hasPath())  //某一方没有路可走，则换成另一方走
       	   {
       		   if(black)
       		   {
       			   JOptionPane.showMessageDialog(null, "正方无路可走，换成反方走");
       			   black=false;
       		   }
       		   else
       			   {
       			   black=true;
       		  	 JOptionPane.showMessageDialog(null, "反方无路可走，换成正方走");
       			   }
       	   }
    	   for(int i=0;i<8;i++)
    		   for(int j=0;j<8;j++)
    			   chesses[i][j].repaint();
    	  
    	  }
       }
       /**
        * 判断棋子是否放置正确，若正确则改变相应的状态
        * @param btn
        * @return  true 表示当前放置位置合法，false表示当前放置位置不合法
        */
       public boolean operate(ChessButton btn)
       {
    	   int midX=btn.getRow();
    	   int midY=btn.getCol();
    	   boolean flag=false;//有棋子可走
    	   List<ChessButton> changeChess=new ArrayList<ChessButton>();
    	   if(btn.getStatus()==ChessStatus.WHITE&&black)   //当前放置位置为空，且当前为正方下
    	   {
        	   List<ChessButton> btns=new ArrayList<ChessButton>();
        	   boolean change=false;//每个方向颜色是否需要改变
        	   int n=1;
    	       while(midX-n>=0&&midY-n>=0)//西北方向（斜上方)
    	       {
    	    	   ChessButton chess=chesses[midX-n][midY-n];
    		       if(chess.getStatus()==ChessStatus.GRAY)  //保存路径上的灰色棋子
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK&&n!=1)
    		       {
    		    	       change=true;
    		    	       flag=true;
    		    	       break;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)  
    	    	   changeChess.addAll(btns);
    	       
    	       btns=new ArrayList<ChessButton>();
        	   change=false;//每个方向颜色是否需要改变
        	   n=1;
    	       while(midX+n<=7&&midY+n<=7)//东南方向（斜下方)
    	       {
    	    	   ChessButton chess=chesses[midX+n][midY+n];
    		       if(chess.getStatus()==ChessStatus.GRAY)
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK&&n!=1)
    		       {
		    	       change=true;
		    	       flag=true;
		    	       break;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)
    	    	   changeChess.addAll(btns);
    	       
    	       btns=new ArrayList<ChessButton>();
        	   change=false;//每个方向颜色是否需要改变
        	   n=1;
    	       while(midX+n<=7&&midY-n>=0)//东北方向（斜上方)
    	       {
    	    	   ChessButton chess=chesses[midX+n][midY-n];
    		       if(chess.getStatus()==ChessStatus.GRAY)
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK&&n!=1)
    		       {
		    	       change=true;
		    	       flag=true;
		    	       break;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)
    	    	   changeChess.addAll(btns);
    	       
    	       btns=new ArrayList<ChessButton>();
        	   change=false;//每个方向颜色是否需要改变
        	   n=1;
    	       while(midX-n>=0&&midY+n<=7)//西南方向（斜下方)
    	       {
    	    	   ChessButton chess=chesses[midX-n][midY+n];
    		       if(chess.getStatus()==ChessStatus.GRAY)
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK&&n!=1)
    		       {
		    	       change=true;
		    	       flag=true;
		    	       break;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)
    	    	   changeChess.addAll(btns);
    	       
    	       btns=new ArrayList<ChessButton>();
        	   change=false;//每个方向颜色是否需要改变
        	   n=1;
    	       while(midY-n>=0)//北方向（正上方)
    	       {
    	    	   ChessButton chess=chesses[midX][midY-n];
    		       if(chess.getStatus()==ChessStatus.GRAY)
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK&&n!=1)
    		       {
		    	       change=true;
		    	       flag=true;
		    	       break;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)
    	    	   changeChess.addAll(btns);
    	       
    	       btns=new ArrayList<ChessButton>();
        	   change=false;//每个方向颜色是否需要改变
        	   n=1;
    	       while(midY+n<=7)//南方向（正西方)
    	       {
    	    	   ChessButton chess=chesses[midX][midY+n];
    		       if(chess.getStatus()==ChessStatus.GRAY)
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK&&n!=1)
    		       {
		    	       change=true;
		    	       flag=true;
		    	       break;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)
    	    	   changeChess.addAll(btns);
    	       
    	       btns=new ArrayList<ChessButton>();
        	   change=false;//每个方向颜色是否需要改变
        	   n=1;
    	       while(midX-n>=0)//西方向（正西方)
    	       {
    	    	   ChessButton chess=chesses[midX-n][midY];
    		       if(chess.getStatus()==ChessStatus.GRAY)
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK&&n!=1)
    		       {
		    	       change=true;
		    	       flag=true;
		    	       break;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)
    	    	   changeChess.addAll(btns);
    	       
    	       btns=new ArrayList<ChessButton>();
        	   change=false;//每个方向颜色是否需要改变
        	   n=1;
    	       while(midX+n<=7)//东方向（正西方)
    	       {
    	    	   ChessButton chess=chesses[midX+n][midY];
    		       if(chess.getStatus()==ChessStatus.GRAY)
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK&&n!=1)
    		       {
		    	       change=true;
		    	       flag=true;
		    	       break;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)
    	    	   changeChess.addAll(btns);
    	       
    	       if(flag)
    	       {
    	    	   if(!judge)
    	    	   { for(ChessButton b:changeChess)
    	    	   {
    	    		   b.setStatus(ChessStatus.BLACK);
    	    	   }
                   btn.setStatus(ChessStatus.BLACK);
        	       black=false;}
    	       }

    	   }
    	   else if(btn.getStatus()==ChessStatus.WHITE&&!black)//当前放置位置为空，且当前为反方下
    	   {
    		   List<ChessButton> btns=new ArrayList<ChessButton>();
        	   boolean change=false;//每个方向颜色是否需要改变
        	   int n=1;
    	       while(midX-n>=0&&midY-n>=0)//西北方向（斜上方)
    	       {
    	    	   ChessButton chess=chesses[midX-n][midY-n];
    		       if(chess.getStatus()==ChessStatus.GRAY&&n!=1)
    		       {
		    	       change=true;
		    	       flag=true;
		    	       break;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK)
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)
    	    	   changeChess.addAll(btns);
    	       
    	       btns=new ArrayList<ChessButton>();
        	   change=false;//每个方向颜色是否需要改变
        	   n=1;
    	       while(midX+n<=7&&midY+n<=7)//东南方向（斜下方)
    	       {
    	    	   ChessButton chess=chesses[midX+n][midY+n];
    		       if(chess.getStatus()==ChessStatus.GRAY&&n!=1)
    		       {
		    	       change=true;
		    	       flag=true;
		    	       break;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK)
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)
    	    	   changeChess.addAll(btns);
    	       
    	       btns=new ArrayList<ChessButton>();
        	   change=false;//每个方向颜色是否需要改变
        	   n=1;
    	       while(midX+n<=7&&midY-n>=0)//东北方向（斜上方)
    	       {
    	    	   ChessButton chess=chesses[midX+n][midY-n];
    	    	   if(chess.getStatus()==ChessStatus.GRAY&&n!=1)
    		       {
		    	       change=true;
		    	       flag=true;
		    	       break;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK)
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)
    	    	   changeChess.addAll(btns);
    	       
    	       btns=new ArrayList<ChessButton>();
        	   change=false;//每个方向颜色是否需要改变
        	   n=1;
    	       while(midX-n>=0&&midY+n<=7)//西南方向（斜下方)
    	       {
    	    	   ChessButton chess=chesses[midX-n][midY+n];
    	    	   if(chess.getStatus()==ChessStatus.GRAY&&n!=1)
    		       {
		    	       change=true;
		    	       flag=true;
		    	       break;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK)
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)
    	    	   changeChess.addAll(btns);
    	       
    	       btns=new ArrayList<ChessButton>();
        	   change=false;//每个方向颜色是否需要改变
        	   n=1;
    	       while(midY-n>=0)//北方向（正上方)
    	       {
    	    	   ChessButton chess=chesses[midX][midY-n];
    	    	   if(chess.getStatus()==ChessStatus.GRAY&&n!=1)
    		       {
		    	       change=true;
		    	       flag=true;
		    	       break;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK)
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)
    	    	   changeChess.addAll(btns);
    	       
    	       btns=new ArrayList<ChessButton>();
        	   change=false;//每个方向颜色是否需要改变
        	   n=1;
    	       while(midY+n<=7)//南方向（正西方)
    	       {
    	    	   ChessButton chess=chesses[midX][midY+n];
    	    	   if(chess.getStatus()==ChessStatus.GRAY&&n!=1)
    		       {
		    	       change=true;
		    	       flag=true;
		    	       break;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK)
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)
    	    	   changeChess.addAll(btns);
    	       
    	       btns=new ArrayList<ChessButton>();
        	   change=false;//每个方向颜色是否需要改变
        	   n=1;
    	       while(midX-n>=0)//西方向（正西方)
    	       {
    	    	   ChessButton chess=chesses[midX-n][midY];
    	    	   if(chess.getStatus()==ChessStatus.GRAY&&n!=1)
    		       {
		    	       change=true;
		    	       flag=true;
		    	       break;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK)
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)
    	    	   changeChess.addAll(btns);
    	       
    	       btns=new ArrayList<ChessButton>();
        	   change=false;//每个方向颜色是否需要改变
        	   n=1;
    	       while(midX+n<=7)//东方向（正西方)
    	       {
    	    	   ChessButton chess=chesses[midX+n][midY];
    	    	   if(chess.getStatus()==ChessStatus.GRAY&&n!=1)
    		       {
		    	       change=true;
		    	       flag=true;
		    	       break;
    		       }
    		       else if(chess.getStatus()==ChessStatus.BLACK)
    		       {
    		    	   btns.add(chess);
    		    	   n++;
    		       }
    		       else
    		    	   break;
    	       }
    	       if(change)
    	    	   changeChess.addAll(btns);
    	       
    	       if(flag)
    	       {
    	    	   if(!judge)
    	    	   { for(ChessButton b:changeChess)
    	    	   {
    	    		   b.setStatus(ChessStatus.GRAY);
    	    	   }
                   btn.setStatus(ChessStatus.GRAY);
        	       black=true;}
    	       }
    	   }
    	   return flag;
       }
       /**
        * 重新开局
        */
       public void reOpen()
       {
    	   for(int i=0;i<8;i++)
           {
           	for(int j=0;j<8;j++)
           	{
           		if((i==3&&j==3)||(i==4&&j==4))
           			chesses[i][j].setStatus(ChessStatus.BLACK);
           		else if((i==3&&j==4)||(i==4&&j==3))
           			chesses[i][j].setStatus(ChessStatus.GRAY);
           		else
           			chesses[i][j].setStatus(ChessStatus.WHITE);
           		chesses[i][j].repaint();
           	}
           }
    	   black=true;
       }
       
       public static void main(String[] args)
       {
    	   UIManager.put("Button.select", Color.white);
    	   new MainFrame();
       }
}
