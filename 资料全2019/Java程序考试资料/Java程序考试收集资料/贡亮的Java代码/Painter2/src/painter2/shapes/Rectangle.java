/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package painter2.shapes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author student
 */
public class Rectangle implements IShape{

    private int start_x, start_y;
    private int end_x, end_y;

    public void draw(Graphics g) {
        g.setColor(color);
        if(isFill){
            g.fillRect(Math.min(start_x, end_x), Math.min(start_y, end_y), Math.abs(start_x - end_x), Math.abs(start_y-end_y));
        }else{
            g.drawRect(Math.min(start_x, end_x), Math.min(start_y, end_y), Math.abs(start_x - end_x), Math.abs(start_y-end_y));
        }
       
    }

    public void setCurrentPara(int x, int y) {
        end_x = x;
        end_y = y;
    }

    public void setInitPara(int x, int y) {
        start_x = x;
        start_y = y;
        end_x = x;
        end_y = y;
    }

   private Color color = Color.black;
    private boolean isFill = false;
    public void setFill(boolean fill, Color c) {
        this.color = c;
        this.isFill = fill;
    }
}
