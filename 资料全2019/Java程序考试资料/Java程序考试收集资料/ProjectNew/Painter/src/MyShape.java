import java.awt.Graphics2D;
import java.io.Serializable;
public abstract class  MyShape implements Serializable{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract void draw(Graphics2D g);
}
