/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.MouseEvent;

/**
 *
 * @author student
 */
public class Tool_Polygon implements IShape{
    private Polygon core  = new Polygon();
    private Color color;
    private boolean isfill;

    public Tool_Polygon(int start_x, int start_y,Color color, boolean isfill){
        core.addPoint(start_x, start_y);
        this.cur_x = start_x;
        this.cur_y = start_y;
        this.color = color;
        this.isfill = isfill;
    }

    private int cur_x,cur_y;

    private boolean is_finished = false;

    public void draw(Graphics2D g) {
        g.setColor(color);
        if(is_finished){
           if(isfill){
               g.fillPolygon(core);
           }else{
                g.drawPolygon(core);
           }
        }else{
            for(int i=0;i<core.npoints-1;i++){
                g.drawLine(core.xpoints[i], core.ypoints[i], core.xpoints[i+1], core.ypoints[i+1]);
            }
            g.drawLine(core.xpoints[core.npoints-1], core.ypoints[core.npoints-1], cur_x, cur_y);
        }
    }

    public void processMouseEvent(MouseEvent evt, int type) {
        cur_x = evt.getX();
        cur_y = evt.getY();
        if(type==IShape.MOUSE_DRAGGED){

        }else if(type==IShape.MOUSE_RIGHT_PRESSED){
            core.addPoint(cur_x, cur_y);
        }else if(type==IShape.MOUSE_LEFT_RELEASED){
            core.addPoint(cur_x, cur_y);
            this.is_finished = true;
        }
    }


}
