import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.LineBorder;


public class ChessButton extends JButton{
	  /**
	 * 
	 */
	private static final long serialVersionUID = -1717815164739400141L;
	private int row;
	  private int col;
	  private ChessStatus status;
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
	public ChessButton(int row,int col)
	  {
		  status=ChessStatus.WHITE;
		  this.setRow(row);
		  this.setCol(col);
		  this.setBorder(new LineBorder(Color.black));
		  this.setBackground(Color.white);
	  }
	  public void paintComponent(Graphics g)
	  {
		  super.paintComponent(g);
		  Graphics2D g2d=(Graphics2D)g;
		  int width=this.getWidth();
		  int height=this.getHeight();
		  switch(status)
		  {
		  case BLACK:
			  g2d.setColor(Color.black);
			  g2d.fillOval(0, 0, width, height);
			  break;
		  case WHITE:
			  g2d.setColor(Color.white);
			  g2d.fillOval(0, 0, width, height);
			  break;
		  case RED:
			  g2d.setColor(Color.red);
			  g2d.fillOval(0, 0, width, height);
			  break;
		  }
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
