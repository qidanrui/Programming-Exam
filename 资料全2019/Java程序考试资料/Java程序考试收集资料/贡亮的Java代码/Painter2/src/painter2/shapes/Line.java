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
public class Line implements IShape{

    private int start_x, start_y;
    private int end_x, end_y;

    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(start_x, start_y, end_x, end_y);
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
