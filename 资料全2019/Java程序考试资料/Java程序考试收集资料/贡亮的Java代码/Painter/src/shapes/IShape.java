/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shapes;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;

/**
 *
 * @author student
 */
public interface IShape extends Serializable{
    public static final int MOUSE_DRAGGED =  1;
    public static final int MOUSE_LEFT_RELEASED =2;
    public static final int MOUSE_RIGHT_PRESSED = 3;

    public void draw(Graphics2D g);

    public void processMouseEvent(MouseEvent evt, int type);
}
