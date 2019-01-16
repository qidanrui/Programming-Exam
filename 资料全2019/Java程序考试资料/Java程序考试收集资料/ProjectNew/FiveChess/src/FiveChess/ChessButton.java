package FiveChess;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

/**
 * 棋盘中的每个棋子
 * @author sinllychen
 *
 */
public class ChessButton extends JButton{
	   private ChessStatus status;     //棋盘状态
	   private int row;  //横坐标
	   private int col;  //纵坐标
	   private int width;  //方格宽度
	   private int height; //方格长度
       public ChessButton(int row,int col)
       {
    	   super();
    	   status=ChessStatus.WHITE;
    	   this.row=row;
    	   this.col=col;
    	   this.setBackground(Color.white);
    	   this.setBorder(new LineBorder(Color.BLACK));
       }
       public void paintComponent(Graphics g)
       {
    	   super.paintComponent(g);
    	   Graphics2D g2d=(Graphics2D)g;
    	   width=this.getWidth();
    	   height=this.getHeight();
    	   switch(status)
    	   {
    	   case BLACK:
    		     g2d.setColor(Color.black);
    		     g2d.fill(new Ellipse2D.Double(0,0,(double)width,(double)height));
    		     break;
    	   case WHITE:
    		     g2d.setColor(Color.white);
    		     g2d.fill(new Ellipse2D.Double(0,0,width,height));
    		     break;
    	   case GRAY:
    		     g2d.setColor(Color.gray);
    		     g2d.fill(new Ellipse2D.Double(0,0,width,height));
    	   }
       }
	/**
	 * @return the status
	 */
	public ChessStatus getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(ChessStatus status) {
		this.status = status;
	}
	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}
	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}
}
