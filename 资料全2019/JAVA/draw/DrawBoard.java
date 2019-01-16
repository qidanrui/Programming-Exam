package thu.hcguo.draw;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class DrawBoard extends JFrame {
	private static final long serialVersionUID = 1L;

	public DrawBoard() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension dimension = toolkit.getScreenSize();
		setSize(dimension.width, dimension.height);
		setLocation(0, 0);
		setResizable(false);
		setTitle("Draw Board");

		OptionPanel optionPanel = new OptionPanel();
		add(optionPanel, BorderLayout.NORTH);

		DrawBoardPanel drawBoardPanel = new DrawBoardPanel();
		add(drawBoardPanel, BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		DrawBoard drawBoard = new DrawBoard();
		drawBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		drawBoard.setVisible(true);
	}
}
