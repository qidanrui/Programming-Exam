import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Map extends JFrame{

	JButton[][] jbuttons;
	JButton startButton;
	JButton exitButton;
	boolean [][] flags;
	
	public void init(){
		this.getContentPane().setVisible(true);
		this.setBounds(100, 100, 500, 500);
		this.setLayout(null);
		jbuttons = new JButton[4][4];
		startButton = new JButton("Start");
		exitButton = new JButton("Exit");
		this.getContentPane().add(startButton);
		this.getContentPane().add(exitButton);
		startButton.setBounds(100, 350, 100, 50);
		exitButton.setBounds(300, 350, 100, 50);
		flags = new boolean[4][4];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				flags[i][j]=false;
				jbuttons[i][j]= new JButton();
				this.getContentPane().add(jbuttons[i][j]);
				jbuttons[i][j].setBounds(110+50*j,110+50*i,50,50);
				jbuttons[i][j].addActionListener(new clickActionPerformed());
			}
		}
		
		startButton.addActionListener(new startActionPerformed());
		exitButton.addActionListener(new exitActionPerformed());
		
		this.setVisible(true);
	}
	
	class clickActionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int row=-1;
			int col=-1;
			for(int j=0;j<4;j++){
				for(int i=0;i<4;i++){
					if(((JButton)(e.getSource())).equals(jbuttons[i][j])){
						row = i;
						col = j;
					}
				}
			}

			if((row-1)>=0&&flags[row-1][col]==false){
				jbuttons[row-1][col].setText(jbuttons[row][col].getText());
				jbuttons[row][col].setText("");
				flags[row][col]=false;
				flags[row-1][col]=true;
			}
			else if((col-1)>=0&&flags[row][col-1]==false){
				String a = jbuttons[row][col].getText();
				jbuttons[row][col-1].setText(jbuttons[row][col].getText().toString());
				jbuttons[row][col].setText("");
				flags[row][col]=false;
				flags[row][col-1]=true;
			}
			else if((col+1)<=3&&flags[row][col+1]==false){
				jbuttons[row][col+1].setText(jbuttons[row][col].getText().toString());
				jbuttons[row][col].setText("");
				flags[row][col+1]=true;
				flags[row][col]=false;
			}
			else if((row+1)<=3&&flags[row+1][col]==false){
				jbuttons[row+1][col].setText(jbuttons[row][col].getText().toString());
				jbuttons[row][col].setText("");
				flags[row+1][col]=true;
				flags[row][col]=false;
			}
		}
	}
	
	class exitActionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	
	class startActionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e){
			for(int i=0;i<15;i++){
				int x = (int) ((Math.random()*4));
				int y = (int) ((Math.random()*4));
				while(flags[x][y]==true){
					x = (int) ((Math.random()*4));
					y = (int) ((Math.random()*4));
				}
				flags[x][y]=true;
				jbuttons[x][y].setText(Integer.toString(i+1));
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map map = new Map();
		map.init();
		map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
