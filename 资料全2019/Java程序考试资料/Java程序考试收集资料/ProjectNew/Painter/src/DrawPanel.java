import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class DrawPanel extends JPanel{
     private JLabel statusLbl;
     private Color curColor;
     private int shapeType;
     private boolean ifFilled;
     private boolean ifDraw;
     private Stack<MyShape> redoShapes;
     /**
	 * @return the redoShapes
	 */
	public Stack<MyShape> getRedoShapes() {
		return redoShapes;
	}
	/**
	 * @param redoShapes the redoShapes to set
	 */
	public void setRedoShapes(Stack<MyShape> redoShapes) {
		this.redoShapes = redoShapes;
	}
	private Stack<MyShape> undoShapes;
     private MyShape midShape;
     private MyShape curShape;
     private Polygon polygon;
     private boolean ifmidShape=false;
     private boolean ifmousePress=false;
     private Point pre;
     private Point last;
     private Point mid;
     public DrawPanel(){};
     public DrawPanel(JLabel label)
     { 
    	 this.statusLbl=label;
    	 MouseHandle mouseHandle=new MouseHandle();
    	 this.setBackground(Color.WHITE);
    	 this.addMouseListener(mouseHandle);
    	 this.addMouseMotionListener(mouseHandle);
    	 curColor=Color.black;
    	 ifFilled=false;
    	 ifDraw=false;
    	 shapeType=1;
    	 redoShapes=new Stack<MyShape>();
    	 undoShapes=new Stack<MyShape>();
    	 polygon=new Polygon();
    	 pre=new Point();
    	 last=new Point();
     }
     public void paintComponent(Graphics g)
     {
    	 super.paintComponent(g);
    	 Graphics2D g2d=(Graphics2D)g;
    	 for(int i=0;i<redoShapes.size();i++)
    		  redoShapes.get(i).draw(g2d);
    	 if(ifmousePress&&ifDraw)
    		 g2d.drawPolyline(polygon.xpoints, polygon.ypoints, polygon.npoints);
    	 if(ifmidShape&&!ifDraw)
    		 midShape.draw(g2d);
     }
     public void setProp(Color curColor,boolean ifFilled,boolean ifDraw,int shapeType)
     {
    	 this.curColor=curColor;
    	 this.ifFilled=ifFilled;
    	 this.ifDraw=ifDraw;
    	 this.shapeType=shapeType;
     }
     public void undo()
     {
    	 if(redoShapes.size()>0)
    	 {
    		 MyShape shape=redoShapes.pop();
    		 undoShapes.push(shape);
        	 repaint();
    	 }
     }
     public void redo()
     {
    	 if(undoShapes.size()>0)
    	 {
    		 MyShape shape=undoShapes.pop();
    		 redoShapes.push(shape);
    		 repaint();
    	 }
     }
     public void clearAll()
     {
    	 undoShapes=new Stack<MyShape>();
    	 redoShapes=new Stack<MyShape>();
    	 polygon=new Polygon();
    	 pre=new Point();
    	 last=new Point();
    	 ifFilled=false;
    	 ifDraw=false;
         ifmidShape=false;
         ifmousePress=false;
         repaint();
     }
     public void setProperties(Color color,int shapeType,boolean ifFilled,boolean ifDraw)
     {
    	 this.curColor=color;
    	 this.shapeType=shapeType;
    	 this.ifFilled=ifFilled;
    	 this.ifDraw=ifDraw;
     }
     private class MouseHandle extends MouseAdapter 
     {

		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			ifmidShape=true;
			mid=new Point();
			mid.x=e.getX();
			mid.y=e.getY();
			statusLbl.setText(String.format("(%d,%d)",e.getX(),e.getY()));
			Point p=new Point();
			p.x=mid.x<pre.x? mid.x:pre.x;
			p.y=mid.y<pre.y? mid.y:pre.y;
			int width=Math.abs(mid.x-pre.x);
			int height=Math.abs(mid.y-pre.y);
			polygon.addPoint(e.getX(), e.getY());
			switch(shapeType)
			{
			case 1:
				   midShape=new Line(pre.x,pre.y,mid.x,mid.y,curColor,ifFilled);
				   break;
			case 2:
				   midShape=new Rectangle(p.x,p.y,width,height,curColor,ifFilled);
				   break;
			case 3:
				   midShape=new Oval(p.x,p.y,width,height,curColor,ifFilled);
				   break;
			case  4:
				  midShape=new Oval(p.x,p.y,width,width,curColor,ifFilled);
				  break;
			case 5:
				  break;
			}
			repaint();
		}

		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			statusLbl.setText(String.format("(%d,%d)",e.getX(),e.getY()));
		}
		  /**
	     * {@inheritDoc}
	     */
	    public void mousePressed(MouseEvent e) {
	    	pre=new Point();
	    	last=new Point();
	    	pre.x=e.getX();
	    	pre.y=e.getY();
	    	last.x=e.getX();
	    	last.y=e.getY();
	    	polygon=new Polygon();
	    	ifmousePress=true;
	    	polygon.addPoint(e.getX(), e.getY());
	    }

	    /**
	     * {@inheritDoc}
	     */
	    public void mouseReleased(MouseEvent e) {
	    	ifmousePress=false;
	    	ifmidShape=false;
	    	last=new Point();
	    	last.x=e.getX();
	    	last.y=e.getY();
	    	polygon.addPoint(e.getX(), e.getY());
	    	if(!ifDraw)
	    	{
	    		switch(shapeType)
	    		{
	    		case 1:
	    		case 2:
	    		case 3:
	    		case 4:
	    		case 5:
    			    curShape=midShape;
    			    redoShapes.push(curShape);
    			    break;
	    		}
	    	}
	    	else 
	    		{
	    		  PologenLine polyLine=new PologenLine(polygon,curColor);
	    		  redoShapes.push(polyLine);
	    		}
			repaint();
	       }
     }
}
