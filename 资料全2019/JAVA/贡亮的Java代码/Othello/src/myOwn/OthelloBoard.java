/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myOwn;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author student
 */
public class OthelloBoard extends JPanel {
    private int [][]pieces = null;

    private int cur_side = 1;

    private Color border_color = Color.black;
    private Stroke border_stroke = new BasicStroke(1.0f);
    private Point cur_point = null;
    private int num_pieces = 0;

    public OthelloBoard(){
        super();
        this.setSize(640, 640);
        this.setBackground(Color.white);
        this.init_board();
    }

    protected void init_board(){
        this.pieces = new int[8][8];
        this.num_pieces = 4;
        pieces[3][3] = 1;
        pieces[3][4] = 2;
        pieces[4][3] = 2;
        pieces[4][4] = 1;
        this.cur_side = 1;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        drawBorders(g2d);
        drawPieces(g2d);
    }

    protected void drawBorders(Graphics2D g){
        g.setColor(border_color);
        g.setStroke(border_stroke);
        for(int i=0;i<8;i++){
            int start_x = i*80;
            int start_y = 0;
            int end_x = start_x;
            int end_y = 8*80;
            g.drawLine(start_x, start_y, end_x, end_y);
        }

        for(int j=0;j<8;j++){
            int start_y = j*80;
            int start_x = 0;
            int end_y = start_y;
            int end_x = 8*80;
            g.drawLine(start_x, start_y, end_x, end_y);
        }
    }

    protected void drawPieces(Graphics2D g){
        Color piece_color = Color.black;
        g.setColor(piece_color);
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(pieces[i][j]==1){
                    g.drawOval(i*80, j*80, 80, 80);
                }else if(pieces[i][j]==2){
                    g.fillOval(i*80, j*80, 80, 80);
                }
            }
        }
    }

    public void processMousePressed(MouseEvent evt) {
        this.calculateCurrentPosition(evt);
        if(this.cur_point==null){
            return ;
        }
        if(pieces[cur_point.x][cur_point.y]==0&&canput()){
            if(this.cur_side==0){
                pieces[cur_point.x][cur_point.y]=1;
            }else{
                pieces[cur_point.x][cur_point.y]=2;
            }
            this.num_pieces++;
            //System.out.println(num_pieces);
            
            
            this.cur_side = Math.abs(cur_side-1);
            cheek();
        }
        this.repaint();
        
        if(this.num_pieces==64){
                game_over();
                return;
            }
    }

    protected void calculateCurrentPosition(MouseEvent evt){
        int x = evt.getX()/80;
        int y = evt.getY()/80;
        if(x<8&&y<8){
            this.cur_point = new Point(x, y);
        }
    }

    protected void cheek(){
         this.cur_side = Math.abs(cur_side-1);
        int x = cur_point.x;
        int y = cur_point.y;

        //向右查找
        for(int i=x+1;i<8;i++){
            if(pieces[i][y]==0){
                break;
            }
            if(pieces[i][y]==this.cur_side+1){
                for(int k=x;k<i;k++){
                    pieces[k][y] = this.cur_side+1;
                }
                break;
            }
        }

        //向左

         for(int i=x-1;i>=0;i--){
             if(pieces[i][y]==0){
                break;
            }
            if(pieces[i][y]==this.cur_side+1){
                for(int k=x;k>i;k--){
                    pieces[k][y] = this.cur_side+1;
                }
                break;
            }
        }

        //向上

        for(int i=y+1;i<8;i++){
            if(pieces[x][i]==0){
                break;
            }
            if(pieces[x][i]==this.cur_side+1){
                for(int k=y;k<i;k++){
                    pieces[x][k] = this.cur_side+1;
                }
                break;
            }
        }

        //向下

         for(int i=y-1;i>=0;i--){
             if(pieces[x][i]==0){
                break;
            }
            if(pieces[x][i]==this.cur_side+1){
                for(int k=y;k>i;k--){
                    pieces[x][k] = this.cur_side+1;
                }
                break;
            }
        }
        //向右上
        for(int j=y+1,i=x-1;j<8&&i>0;j++,i--){
            if(pieces[i][j]==0){
                break;
            }
            if(pieces[i][j]==this.cur_side+1){
                for(int k_i=x,k_j=y;k_i>i&&k_j<j;k_i--,k_j++){
                    pieces[k_i][k_j] = this.cur_side+1;
                    //System.out.println(k_i +"," + k_j);
                }
                break;
            }
        }

        //向右下

        for(int j=y+1,i=x+1;j<8&&i<8;j++,i++){
            if(pieces[i][j]==0){
                break;
            }
            if(pieces[i][j]==this.cur_side+1){
                for(int k_i=x,k_j=y;k_i<i&&k_j<j;k_i++,k_j++){
                    pieces[k_i][k_j] = this.cur_side+1;
                    //System.out.println(k_i +"," + k_j);
                }
                break;
            }
        }

        //向左上

        for(int j=y-1,i=x-1;j>0&&i>0;j--,i--){
            if(pieces[i][j]==0){
                break;
            }
            if(pieces[i][j]==this.cur_side+1){
                for(int k_i=x,k_j=y;k_i>i&&k_j>j;k_i--,k_j--){
                    pieces[k_i][k_j] = this.cur_side+1;
                    //System.out.println(k_i +"," + k_j);
                }
                break;
            }
        }

        //向左下
        for(int j=y-1,i=x+1;j>0&&i<8;j--,i++){
            if(pieces[i][j]==0){
                break;
            }
            if(pieces[i][j]==this.cur_side+1){
                for(int k_i=x,k_j=y;k_i<i&&k_j>j;k_i++,k_j--){
                    pieces[k_i][k_j] = this.cur_side+1;
                    //System.out.println(k_i +"," + k_j);
                }
                break;
            }
        }

         this.cur_side = Math.abs(cur_side-1);
    }

    private void game_over() {
        int white_count = 0, black_count = 0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(pieces[i][j]==1){
                    white_count++;
                }else{
                    black_count++;
                }
            }
        }
        String result = "Black: " + black_count + "\r\nWhite: "+ white_count + "\r\n";
        if(black_count>white_count){
            result += "black win";
        }else if(black_count<white_count){
            result += "white win";
        }else{
            result += "even";
        }
        //System.out.println(result);
        JOptionPane.showMessageDialog(this, result);

        this.init_board();

        repaint();
    }

    private boolean canput() {
        int x = cur_point.x;
        int y = cur_point.y;

        int low_x = Math.max(0, x-1);
        int max_x = Math.min(7, x+1);
        int low_y = Math.max(0, y-1);
        int max_y = Math.min(7, y+1);

        if(pieces[low_x][low_y]+pieces[max_x][low_y]+pieces[low_x][max_y]+pieces[max_x][max_y]>0){
            return true;
        }else{
            return false;
        }
    }


}

