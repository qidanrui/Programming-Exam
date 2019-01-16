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
public class Circle implements IShape{

    private int start_x, start_y;
    private int end_x, end_y;

    public void draw(Graphics g) {
        g.setColor(color);
        int d = (int)(Math.min(Math.abs(start_x - end_x), Math.abs(start_y-end_y)));

        int x = -1;
        if(end_x < start_x){
            x = Math.max((start_x - d), end_x);
        }else{
            x = start_x;
        }

        int y = -1;
        if(end_y < start_y){
            y = Math.max((start_y - d), end_y);
        }else{
            y = start_y;
        }
        if(this.isFill){
            g.fillOval(x, y, d, d);
        }else{
            g.drawOval(x, y, d, d);
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
