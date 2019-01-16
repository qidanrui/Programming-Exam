/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author student
 */
public class Tool_Line implements IShape {

    private Color color;
    private int start_x, start_y;
    private int cur_x, cur_y;

    public Tool_Line(int x, int y, Color color) {
        this.start_x = x;
        this.start_y = y;
        this.cur_x = start_x;
        this.cur_y = start_y;
        this.color = color;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.setStroke(new BasicStroke(1.0f));
        g.drawLine(start_x, start_y, cur_x, cur_y);
    }

    public void processMouseEvent(MouseEvent evt, int type) {
        if(type==IShape.MOUSE_DRAGGED){
            this.cur_x = evt.getX();
            this.cur_y = evt.getY();
        }
    }


}
