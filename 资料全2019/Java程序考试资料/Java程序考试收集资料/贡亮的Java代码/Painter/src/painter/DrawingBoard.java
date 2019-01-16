/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.ArrayList;
import shapes.IShape;

/**
 *
 * @author student
 */
public class DrawingBoard extends JPanel implements Serializable{

    public ArrayList<IShape> shape_list;
    @Override
    protected void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
                if(shape_list!=null){
                    for(int i=0;i<shape_list.size();i++){
			IShape shape = shape_list.get(i);
			if(shape!=null){

				shape.draw(g2d);
			}
                    }
                }
		
	}
}
