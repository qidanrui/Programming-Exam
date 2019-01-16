/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package painter2.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

/**
 *
 * @author student
 */
public interface IShape extends Serializable{
    public void draw(Graphics g);

    public void setCurrentPara(int x, int y);

    public void setInitPara(int x, int y);

    public void setFill(boolean fill, Color black);

}
