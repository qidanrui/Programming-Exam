package MyGraph;

import java.awt.geom.Point2D;



public class Three {
	
		Point2D.Double p1 ;
		Point2D.Double p2 ;
		Point2D.Double p3 ;
	public Three(Point2D.Double p11,Point2D.Double.Double p22,Point2D.Double.Double p33){
		p1=p11;
		p2=p22;
		p3=p33;
		
	}
	public double zhouchang(){
		double result;
		result=p1.distance(p2)+p2.distance(p3)+p3.distance(p1);
		
		
		return result;
	}
	public double mianji(){
		double a = p1.distance(p2);
		double b = p2.distance(p3);
		double c = p3.distance(p1);
		double p = (a+b+c)/2;
		double result = Math.sqrt(p*(p-a)*(p-b)*(p-c));
		return result;
		
		
		
		
	}
	
	
}
