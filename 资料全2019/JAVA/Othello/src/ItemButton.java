import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.border.LineBorder;


public class ItemButton extends JButton{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5769040946620232521L;
	private int xPos; //从左向右,按钮在棋盘上的列数,0为最左
	private int yPos; //从上向下,按钮在棋盘上的行数,0为最上
	private ItemStatus itemStatus; //棋盘上该位置的情况
	
	public ItemButton(int x, int y) {
		super();
		this.xPos = x;
		this.yPos = y;
		this.setPreferredSize(new Dimension(50, 50));
		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(Color.BLACK));
		this.setMargin(new Insets(0, 0, 0, 0));
		itemStatus = ItemStatus.EMPTY;
	}
	
	/**
	 * 重画按钮，增加其未占用、黑色子、白色子的样式
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g2);
		Color color = Color.WHITE;
		g2.setColor(color);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		switch (itemStatus) {
		case BLACK:
			color = Color.BLACK;
			g2.setColor(color);
			g2.fillArc(1, 1, this.getWidth() - 3, this.getHeight() - 3, 0, 360);
			break;
		case WHITE:
			color = Color.GRAY;
			g2.setColor(color);
			g2.fillArc(1, 1, this.getWidth() - 3, this.getHeight() - 3, 0, 360);
			break;
		case EMPTY:default:
			break;
		}
		
	}

	public int getXPos() {
		return xPos;
	}

	public void setXPos(int xPos) {
		this.xPos = xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setYPos(int yPos) {
		this.yPos = yPos;
	}

	public ItemStatus getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(ItemStatus itemStatus) {
		this.itemStatus = itemStatus;
	}
}
