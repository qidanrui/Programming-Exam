package MyGraph;

import java.awt.geom.Point2D;

public class Four {
	
	Point2D.Double p;
	double edge;
	
	public Four(Point2D.Double p1,Double edge1 ){
		p=p1;
		edge = edge1;
	}
	public double zhouchang(){
		return 4*edge;
	}
	
	public double mianji(){
		return edge*edge;
	} 
	
	
}
