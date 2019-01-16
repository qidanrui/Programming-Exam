package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * 
 * @author Lusar
 *
 */
public class GobangFrame extends JFrame {
	private static final long serialVersionUID = -8138738169454123146L;
	private Canvas		canvas;			//棋盘画布
	private ChessMatrix chessMatrix;	//棋盘矩阵
	private	JPanel	    navigationBar;	//导航栏
	private JButton  	saveBtn,		//导航栏按钮——保存棋盘
						loadBtn,		//导航栏按钮——加载棋盘
						restartBtn;		//导航栏按钮——开始按钮
	private JLabel 		status;			//导航栏按钮——状态标志
	
	public GobangFrame(){
		initLayout();
		initSetAction();
	}
	
	private void initLayout(){
		this.setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);//设置不可进行调整布局大小
		canvas 		  = new Canvas();
		saveBtn 	  = new JButton();
		restartBtn 	  = new JButton();
		loadBtn 	  = new JButton();
		status		  = new JLabel("白方持子");
		navigationBar = new JPanel();
		chessMatrix   = new ChessMatrix(canvas,status);
		
		canvas.setBackground(Color.lightGray);				//设置棋盘背景颜色
		navigationBar.setLayout(new FlowLayout());
		restartBtn.setText("重新开始");
		saveBtn.setText("保存棋盘");
		loadBtn.setText("载入棋盘");
		navigationBar.add(status);
		navigationBar.add(restartBtn);
		navigationBar.add(saveBtn);
		navigationBar.add(loadBtn);
		this.setLayout(new BorderLayout());
		this.add(navigationBar,BorderLayout.NORTH);
		this.add(canvas,BorderLayout.CENTER);
	}
	//按钮事件处理: 从文件加载棋盘,保存棋盘到文件,重新开始
	private void initSetAction(){
		saveBtn.addActionListener(new ActionListener(){
			@Override  
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();				//选择要保存的文件路径
				fileChooser.setDialogTitle("save chess");
				int code = fileChooser.showSaveDialog(GobangFrame.this);
				if(code == JFileChooser.APPROVE_OPTION)
					chessMatrix.writeToFile(fileChooser.getSelectedFile());
			}
		});
		loadBtn.addActionListener(new ActionListener(){
			@Override 
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();				//选择要读取的文件路径
				fileChooser.setDialogTitle("load chess");
				int code = fileChooser.showOpenDialog(GobangFrame.this);
				if(code == JFileChooser.APPROVE_OPTION)
					chessMatrix.loadChessFrom(fileChooser.getSelectedFile());
			}
		});
		restartBtn.addActionListener(new ActionListener(){
			@Override 
			public void actionPerformed(ActionEvent e) {
				chessMatrix.restart();
			}
		});
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		chessMatrix.repaintChess();		//重绘棋盘，棋子
	}

	class ChessMatrix{
		private int 		matrix[][];			//棋盘二维表,奇数代表——白方放置放置的棋子，偶数——黑方顺序放置的棋子
		private Canvas		canvas;				//棋盘画布
		private JLabel 		status;				//棋手状态
		private int 		count 	 = 0;		//棋子计数器
		private final int  	row		 = 100,		//棋盘大小
							column	 = 100;		//棋盘大小
		
		public ChessMatrix(Canvas panel, JLabel label){
			canvas = panel;
			status = label;
			matrix = new int[row][column];
			restart();
			canvas.addMouseListener(new MouseAdapter(){
				@Override public void mouseClicked(MouseEvent e) {//鼠标点击时候，进行棋盘坐标转换
					int x = (e.getX() - ChessMatrix.this.canvas.getX()) / ChessMatrix.this.canvas.dis,
						y = (e.getY() - ChessMatrix.this.canvas.getY()) / ChessMatrix.this.canvas.dis;
					if(!isPointExist(x, y))
						addPoint(x, y);						
				}
			});
		}
		//重新画棋盘
		public void restart(){
			count = 0;
			for(int i = 0;i<row;i++)
				for(int j=0;j<column;j++)
					matrix[i][j] = 0;			//清除棋盘二维表计数
			status.setText("白方持棋");
			canvas.clearCanvas();
			canvas.drawChessBorad();
		}
		//判断棋盘某点是否已放棋子
		public boolean isPointExist(int x,int y){
			return matrix[x][y] > 0 ? true : false;
		}
		//放置棋子
		public void addPoint(int x,int y){
			matrix[x][y] = ++count;
			ifAPointHerePaint(x,y);				//绘制棋子
			if(isWin(x, y)){
				String whoWin = matrix[x][y]%2 == 1 ? "白子":"黑子";
				canvas.showMessage(whoWin+"获胜~~~");
			}
			String whoNextPut = matrix[x][y]%2 == 1 ? "黑方持子" : "白方持子";
			status.setText(whoNextPut);			//设置对方持子
		}
		//重绘棋盘
		public void repaintChess(){
			canvas.drawChessBorad();
			for(int i=0;i<row;i++)
				for(int j=0;j<column;j++)
					ifAPointHerePaint(i, j);
		}
		//在某点画棋子
		public void ifAPointHerePaint(int x, int y){
			if(matrix[x][y] == 0) return ;
			Color c  = matrix[x][y]%2 == 0 ? Color.black : Color.white;
			canvas.drawPointCricle(canvas.getX() + x * canvas.dis ,canvas.getY() + y * canvas.dis,c); 
		}
		//判断某位置放置棋子后，是否已经胜利了。
		public boolean isWin(int x, int y){
			int v 		    =   matrix[x][y]%2;
			int[][] counts  =   {{0,0,0},{0,0,0},{0,0,0}};
			for(int i = -1; i<=1; i++){					//x 三个方向 左右，上下，不变
				for(int j = -1; j<=1; j++){				//y 三个方向 左右，上下，不变
					if( i == 0 && j==0) continue;		//x,y 都保持不变时候，跳过, 这样有效组合就是8个
					for(int x1 =x, y1 = y; isLegal(x1,y1)
											&& matrix[x1][y1] > 0 
											&& matrix[x1][y1]%2 == v; x1 += i, y1 += j){
						counts[1+i][1+j] ++;
						if(counts[1+i][1+j] + counts[1-i][1-j] > 5)	//动手画下图，就应该知道了. >5 由于(x,y)多加了一次
							return true;
					}
				}
			}
			return false;
		}
		//保存棋盘到文件中
		public void writeToFile(File file){
			try {
				ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(file));
				writer.writeInt(count);
				writer.writeObject(matrix);
				writer.close();
			} catch (IOException e) {
				canvas.showMessage("存储棋盘失败~");
			}
		}
		//从文件中加载棋盘
		public void loadChessFrom(File file){
			try {
				ObjectInputStream reader = new ObjectInputStream(new FileInputStream(file));
				count   = reader.readInt();
				matrix  = (int[][]) reader.readObject();
				reader.close();
				repaintChess();
			} catch (Exception e) {
				canvas.showMessage("加载棋盘失败~");
			}
		}
		
		private boolean isLegal(int x,int y){
			return x >= 0 && x < row && y >=0 && y < column;
		}
	}
	
	class Canvas extends JPanel{
		private static final long serialVersionUID = -7271177548967533087L;
		private int 	dis   = 30;

		public void drawChessBorad(){
			for(int x = getX(); x < getX() + getWidth(); x += dis )
				draw(new Line2D.Double(x, getY(), x, getY() + getHeight()), Color.black);
			for(int y = getY(); y <  getY() + getHeight(); y += dis )
				draw(new Line2D.Double(getX(), y, getX() + getWidth(), y), Color.black);
		}
		
		public void clearCanvas(){
			draw(new Rectangle2D.Double(getX(),getY(),getWidth(),getHeight()),Color.lightGray);
		}
		
		public void drawPointCricle(int x, int y, Color c){
			draw(new Ellipse2D.Double(x+2,y+2,dis-2,dis-2),c);
		}
	
		public void showMessage(String message){
			JOptionPane.showMessageDialog(this, message);
		}
	
		private void draw(Shape s, Color c){
			if(getGraphics() == null)	return; 
			Graphics2D graphic = (Graphics2D) getGraphics();
			graphic.setColor(c);
			graphic.draw(s);
			graphic.fill(s);
		}
	}
	
	public static void main(String args[]){
		new GobangFrame().setVisible(true);
	}
}