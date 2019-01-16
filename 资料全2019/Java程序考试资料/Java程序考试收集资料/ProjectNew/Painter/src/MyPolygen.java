import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class MyPolygen extends MyShape {
   private Polygon polygen;
   private Color myColor;
   private boolean iffilled;
   public MyPolygen(Polygon polygen,Color color,boolean iffilled)
   {
	   this.polygen=polygen;
	   this.myColor=color;
	   this.iffilled=iffilled;
   }
@Override
 public void draw(Graphics2D g) {
	// TODO Auto-generated method stub
	g.setColor(myColor);
	if(iffilled)
		 g.fillPolygon(polygen);
	else 
		 g.drawPolygon(polygen);
}

}
