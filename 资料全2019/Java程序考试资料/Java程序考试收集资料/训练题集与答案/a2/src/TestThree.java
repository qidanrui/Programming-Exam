import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class TestThree extends JFrame{
	 private JButton[][] btns=new JButton[3][3];
     public TestThree()
     {
    	this.setLayout(new GridLayout(3,3,0,0));
    	for(int i=0;i<3;i++)
    	{
    		for(int j=0;j<3;j++)
    		{
    			btns[i][j]=new JButton();
    		    this.add(btns[i][j]);
    		    btns[i][j].addMouseListener(new MouseListener(){

					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						JButton btn=(JButton) e.getSource();
						if(e.getButton()==e.BUTTON1)
							btn.setText("o");
						else
							btn.setText("*");
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
    		    	
    		    });
    		}
    	}
    	this.setVisible(true);
         this.setSize(new Dimension(200,200));
         this.setDefaultCloseOperation(EXIT_ON_CLOSE);
     }
     public static void main(String[] args)
     {
    	 new TestThree();
     }
}
