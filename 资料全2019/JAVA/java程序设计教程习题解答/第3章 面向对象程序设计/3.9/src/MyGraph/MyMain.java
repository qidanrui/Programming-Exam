package MyGraph;

import java.awt.geom.Point2D;

public class MyMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Point2D.Double p1 = new Point2D.Double(1.0,1.0);
		Point2D.Double p2 = new Point2D.Double(1.0,2.0);
		Point2D.Double p3 = new Point2D.Double(3.0,1.0);
		
		Three t = new Three(p1,p2,p3);
		System.out.println("三角形周长是：" +t.zhouchang());
		System.out.println("三角形面积是：" +t.mianji());
		
		
		Point2D.Double p = new Point2D.Double(5.0,5.0);
		double length = 3;
		Four f  = new Four(p,length);
		System.out.println("正方形周长是：" +f.zhouchang());
		System.out.println("正方形面积是：" +f.mianji());
		
		
		Point2D.Double c = new Point2D.Double(5.0,5.0);
		double r = 3;
		
		Circle s = new Circle(c,r);
		System.out.println("圆周长是：" +s.zhouchang());
		System.out.println("圆面积是：" +s.mianji());
		
		
		Point2D.Double w1 = new Point2D.Double(3.0,3.0);
		Point2D.Double w2 = new Point2D.Double(3.0,2.0);
		Five w = new Five(w1,w2);
		System.out.println("五边形周长是：" +w.zhouchang());
		System.out.println("五边形面积是：" +w.mianji());
	}

}
