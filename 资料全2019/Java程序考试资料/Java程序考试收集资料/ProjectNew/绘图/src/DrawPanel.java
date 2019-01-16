import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.*;
import java.awt.Graphics2D;
public class DrawPanel extends JPanel  {
   private MyShape  myshape[];//保存所有图形
   private int shapeCount;//图形个数
   private int shapeType;//图形类型
   private MyShape currentShape;//当前图形形状
   private MyShape midShape;
   private boolean ifmidShape=false;
   private Color currentColor;//图形颜色
   private boolean filledShape;//空心实心图形判断
   private boolean linedashed;//实线虚线判断
   private boolean mousedraw;//是否用鼠标画线
   private float linestick;
   private JLabel statusLabel;
   private Point pre=new Point();
   private Point last=new Point();//直线
   private Polygon mypolygon;//用鼠标画线，设置多边形
   private boolean mousepressed=false;
   //画曲线的参数
  /* private boolean iscurve=false;
   private boolean isinline=false;//判断此时的鼠标是否在直线上
   private int pressedcount=0; //鼠标点击的次数
   private Point[] curvepoint=new Point[3];//存储曲线的三个参数
   private Point secondpoint=new Point(0,0);//鼠标松开后第二次点击的时候的点的坐标*/
   public DrawPanel(JLabel label)
   {
	   statusLabel=label;
	   myshape=new MyShape[100];
	   shapeCount=0;
	   shapeType=1;//line
	   currentShape=null;
	   midShape=null;
	   currentColor=Color.BLACK;
	   this.setBackground(Color.WHITE);
	   this.addMouseListener(new MouseHandler()); 
	   this.addMouseMotionListener(new MouseHandler());   
   }
   public void Set(int shapetype,Color current,boolean iffill,boolean dashed,float stick,boolean mousedraw)
   {
	   shapeType=shapetype;
	   currentColor=current;
	   filledShape=iffill;
	   linedashed=dashed;
	   linestick=stick;
	   this.mousedraw=mousedraw;
   }
   public void clearLastShape()
   {
	   shapeCount--;
	   if(shapeCount<0)
		   shapeCount=0;
	   repaint();
   }
   public void clearDrawing()
   {
	   shapeCount=0;
	   repaint();
   }
   public void  paintComponent(Graphics g)
   {
	   
	   super.paintComponent(g);
	   Graphics2D g2d=(Graphics2D)g;
	    for(int i=0;i<shapeCount;i++)
		      myshape[i].Draw(g2d);	 
	    if(mousepressed&&mousedraw)
	    	g2d.drawPolyline(mypolygon.xpoints, mypolygon.ypoints, mypolygon.npoints);
	    if(ifmidShape&&!mousedraw)
	    	midShape.Draw(g2d);
   }
   private class MouseHandler extends MouseAdapter 
   {
	   public void mousePressed(MouseEvent event)
	   {
		   pre.x=event.getX();
		   pre.y=event.getY();
		   last.x=event.getX();
		   last.y=event.getY();
		   /*if(pressedcount==0)
		   {
		      curvepoint[0].x=event.getX();
		      curvepoint[0].y=event.getY();
		   }
		   else
		   {
			   secondpoint.x=event.getX();
			   secondpoint.y=event.getY();
		   }*/
		   mypolygon=new Polygon();
		   mousepressed=true;
		   mypolygon.addPoint(event.getX(), event.getY());
	   }
	   public void mouseReleased(MouseEvent event)
	   {
		   mousepressed=false;
		   ifmidShape=false;
		   /*pressedcount++;
		   if(pressedcount==1)
		   {
			   curvepoint[1].x=event.getX();
			   curvepoint[1].y=event.getY();
		   }
		   else 
		   {
			   curvepoint[2].x=event.getX();
			   curvepoint[2].y=event.getY();
		   }*/
		   if(!mousedraw)
		   {
		   switch(shapeType)
		   {
		   case 1:
			   currentShape=midShape;
			   myshape[shapeCount]=currentShape;
			   shapeCount++;
			   //pressedcount=0;
			   break;
		   case 2:
			   currentShape=midShape;
			   myshape[shapeCount]=currentShape;
			   shapeCount++;
			   //pressedcount=0;
			   break;
		   case 3:
			   currentShape=midShape;
			   myshape[shapeCount]=currentShape;
			   shapeCount++;
			  // pressedcount=0;
			   break;
		    }
		  /* if(shapeType==4)
		   {
			   if(pressedcount>1)
			   {   currentShape=midShape;
			   
			       myshape[shapeCount]=currentShape;
			       shapeCount++;
			   }
			    
		   }*/
		   }
		   else
		   {
		     PolegonLine line=new PolegonLine(mypolygon,currentColor,linestick);
			 currentShape=line;
			 //pressedcount=0;
			 myshape[shapeCount]=currentShape;
			 shapeCount++;
		   }
		   currentShape=null;
		     repaint();
		}

	public void mouseDragged(MouseEvent e) 
	{
		ifmidShape=true;
		statusLabel.setText(String.format("(%d,%d)",e.getX(),e.getY()));
		last.x=e.getX();
		last.y=e.getY();
		Point p=new Point();
        p.x=pre.x<last.x? pre.x:last.x;
		p.y=pre.y<last.y? pre.y:last.y;
		int width=Math.abs(pre.x-last.x);
		int height=Math.abs(pre.y-last.y);
		mypolygon.addPoint(e.getX(), e.getY());
		switch(shapeType)
		{
		case 1:
			Line line=new Line(pre.x,pre.y,last.x,last.y,currentColor,linestick);
		    line.setDashed(linedashed);
		    midShape=line;
		    break;
		case 2:
			  Rectangle rec=new Rectangle(p.x,p.y,width,height,currentColor,linestick);
			   rec.setFilled(filledShape);
			   rec.setDashed(linedashed);
			   midShape=rec;
			   break;
		case 3:
			   Oval oval=new Oval(p.x,p.y,width,height,currentColor,linestick);
			   oval.setFilled(filledShape);
			   oval.setDashed(linedashed);
			   midShape=oval;
			   break;
		}
		/*if(shapeType==4)
		{
			Curve curve;
			if(pressedcount==0)	
			{ 
				curve=new Curve(pre.x,pre.y,(pre.x+last.x)/2,(pre.y+last.y)/2,last.x,last.y,currentColor,linestick);
				curve.setDashed(linedashed);
				midShape=curve;
				
			}
			else 
			{
				if(isonline())
				{
					curve=new Curve(curvepoint[0].x,curvepoint[0].y,curvepoint[2].x,curvepoint[2].y,curvepoint[1].x,curvepoint[1].y,currentColor,linestick);
					curve.setDashed(linedashed);
					midShape=curve;
				}
			}
				
		}*/
		repaint();
	}

	public void mouseMoved(MouseEvent e)
	{
       statusLabel.setText(String.format("(%d,%d)",e.getX(),e.getY()));
	}
   }
   /*public boolean isonline()
   {
	   if(curvepoint[0].x==curvepoint[1].x)
	   {
		   if(secondpoint.x==curvepoint[0].x)
			   isinline=true;
	   }
	   else if(curvepoint[0].y==curvepoint[0].y)
	   {
		   if(secondpoint.y==curvepoint[0].y)
			   isinline=true;
	   }
	   else 
	   {
		   if((secondpoint.y-curvepoint[0].y)/(secondpoint.x-curvepoint[0].x)==(curvepoint[1].y-secondpoint.y)/(curvepoint[1].x-secondpoint.x))
		   {
			   isinline=true;
		   }
	   }
	   isinline=false;
	   return isinline;
   }*/
   
}

