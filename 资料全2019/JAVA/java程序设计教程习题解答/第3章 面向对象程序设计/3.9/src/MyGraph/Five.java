package MyGraph;

import java.awt.geom.Point2D;

public class Five {

	Point2D.Double center;
	Point2D.Double p1;
	double len;
	public Five(Point2D.Double c1,Point2D.Double p11){
		center=c1;
		p1=p11;
		len = c1.distance(p11);
	}
	public double zhouchang(){
		return 10*Math.cos(Math.PI/5)*len;
	}
	public double mianji(){
		return 5.0/2*len*len*Math.sin(2*Math.PI/5);
	}
	
}
