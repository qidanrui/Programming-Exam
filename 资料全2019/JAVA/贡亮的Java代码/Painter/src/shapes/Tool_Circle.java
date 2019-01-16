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
public class Tool_Circle implements IShape {

    private Color color;
    private int start_x, start_y, cur_x, cur_y;
    private int diameter = 0;
    private boolean isfill;

    public Tool_Circle(int x, int y, Color color, boolean isfill){
        start_x = x;
        start_y = y;
        cur_x = start_x;
        cur_y = start_y;
        this.color = color;
        this.isfill = isfill;
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        int x,y;
        if(start_x<cur_x){
            x= start_x;
        }else{
            x= start_x - diameter;
        }
        if(start_y <cur_y){
            y = start_y;
        }else{
            y = start_y - diameter;
        }
        if(isfill){
            g.fillOval(x, y, diameter, diameter);
        }else{
            g.drawOval(x, y, diameter, diameter);
        }
    }

    public void processMouseEvent(MouseEvent evt, int type) {
        if(type==IShape.MOUSE_DRAGGED){
            cur_x = evt.getX();
            cur_y = evt.getY();

            diameter = Math.max(Math.abs(cur_x - start_x), Math.abs(cur_y - start_y));
        }
    }

}
