import java.awt.*;
public class PolegonLine extends MyShape {
  private Polygon mypolygon;
  private Color mycolor;
  private float thick;
  public PolegonLine(Polygon polygon,Color color,float t)
  {
	  mypolygon=polygon;mycolor=color;thick=t;
  }
@Override
public void Draw(Graphics2D g) {
	// TODO Auto-generated method stub
	g.setStroke(new BasicStroke(thick));
	g.setColor(mycolor);
	g.drawPolyline(mypolygon.xpoints, mypolygon.ypoints, mypolygon.npoints);
}
  
}
