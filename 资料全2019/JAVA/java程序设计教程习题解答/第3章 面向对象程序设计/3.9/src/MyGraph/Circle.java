package MyGraph;

import java.awt.geom.Point2D;

public class Circle {
	Point2D.Double c;
	double r;
	public Circle(Point2D.Double c1,double r1){
		c= c1;
		r= r1;
	}
	public double zhouchang(){
		return 2*Math.PI*r;
	}
	public double mianji(){
		return Math.PI*r*r;
	}
	
}
