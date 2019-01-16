package thu.hcguo.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ChessBoard extends JFrame{
	private static final long serialVersionUID = 1L;
	public ChessBoard() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		int width = dimension.width / 2;
		int height = dimension.height / 2;
		setSize(width, height);
		setLocation(100,100);
		setTitle("Chess Borad");
		
		JPanel chessBoardPanel = new ChessBoardPanel();
		JPanel controlPanel = new ControlPanel();
		add(chessBoardPanel,BorderLayout.CENTER);
		add(controlPanel, BorderLayout.SOUTH);
	}
	public static void main(String[] args) {
		JFrame chessBoard = new ChessBoard();
		chessBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessBoard.setVisible(true);
	}
}
