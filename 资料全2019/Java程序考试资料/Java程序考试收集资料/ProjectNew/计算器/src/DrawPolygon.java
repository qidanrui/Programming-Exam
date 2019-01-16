
import javax.swing.*;
import java.awt.*;
import java.awt.color.*;

public class DrawPolygon {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawFrame frame = new DrawFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

class DrawFrame extends JFrame {
	public DrawFrame() {
		this.setTitle("Draw a Polygon");
		this.setSize(400, 400);
		DrawPanel panel = new DrawPanel();
		this.add(panel);
	}
}

class DrawPanel extends JPanel {

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Polygon po = new Polygon();
		int[] X = { 10, 180, 90, 110, 130, 50 }; // 顶点X坐标 ，改成用户接收用户输入的坐标
		int[] Y = { 15, 55, 95, 115, 135, 180 }; // 顶点Y坐标
		for (int i = 0; i < X.length; i++)    //如果不封闭，那么我想应该就是线的连接了。
			po.addPoint(X[i], Y[i]);
		//边的颜色 按要求加判断是否需要画边的颜色
		g.setColor(Color.BLUE);
		g.drawPolygon(po);
		//填充多边形的颜色 按要求加判断是否需要填充的颜色
		g.setColor(Color.RED);
		g.fillPolygon(po);

		g.dispose();
	}
}
