/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

/**
 *
 * @author student
 */
public class Tool_Oval implements IShape{
    private int start_x, start_y;
    private int x_diameter, y_diameter;
    private int cur_x, cur_y;
    private Color color;
    private boolean isfill;

    public Tool_Oval(int start_x, int start_y, Color color, boolean isfill){
        this.start_x = start_x;
        this.start_y = start_y;
        cur_x = start_x;
        cur_y = start_y;
        x_diameter = 0;
        y_diameter = 0;
        this.color =color;
        this.isfill = isfill;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        int x = Math.min(start_x, cur_x);
        int y = Math.min(start_y, cur_y);
        if(isfill){
            g.fillOval(x, y, x_diameter, y_diameter);
        }else{
            g.drawOval(x, y, x_diameter, y_diameter);
        }
    }

    public void processMouseEvent(MouseEvent evt, int type) {
        if(type==IShape.MOUSE_DRAGGED){
            cur_x = evt.getX();
            cur_y = evt.getY();

            x_diameter = Math.abs(start_x - cur_x);
            y_diameter = Math.abs(start_y - cur_y);
        }
    }

}
