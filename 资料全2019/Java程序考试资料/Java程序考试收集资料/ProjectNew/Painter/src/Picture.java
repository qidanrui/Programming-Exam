import java.io.Serializable;
import java.util.Stack;


public class Picture implements Serializable {
      private Stack<MyShape> shapes;
      public Picture()
      {
    	  
      }

	/**
	 * @return the shapes
	 */
	public Stack<MyShape> getShapes() {
		return shapes;
	}

	/**
	 * @param shapes the shapes to set
	 */
	public void setShapes(Stack<MyShape> shapes) {
		this.shapes = shapes;
	}
}
