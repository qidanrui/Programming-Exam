package src;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class SortGame extends JFrame implements ActionListener {
	
	private JPanel mainPanel = new JPanel();
	private JButton button[] = new JButton[16];
	private int butNum[] = new int[16];
	private Vector<String> optionalNum = new Vector<String>();
	
	public SortGame(){		
		super("16方格排序游戏");	
		mainPanel.setLayout(new GridLayout(4,4,3,3));
		getContentPane().add(mainPanel);
		for(int i=0; i<16; i++) {
			button[i] = new JButton();
			button[i].addActionListener(this);
			mainPanel.add(button[i]);	
			
			butNum[i] = 0;
		}
		initButtonNum();
	}//SortGame
	
	private void initButtonNum() {
		for(int i=0; i<16; i++) {
			optionalNum.add(String.valueOf(i));
		}
		int index = -1;
		String str = null;
		for(int i=0; i<16; i++){
			index = (int)(Math.random()*optionalNum.size());
			str = optionalNum.get(index);
			if(str.equals("0")){
			    button[i].setText("");
			    butNum[i] = 0;
			}
			else {
			    button[i].setText(str);
			    butNum[i] = Integer.parseInt(str) ;
			}
			optionalNum.remove(index);
		}	
	}//initButtonNum

	public void actionPerformed(ActionEvent e) {
		int num =-1, location = -1;
		int aim = -1;
		int i=0;
		
		if(e.getActionCommand().length() == 0)
			return;
		
		num = Integer.parseInt(e.getActionCommand());
		
		while(i<16){
			if(num == butNum[i++]){
				location = i-1;
		        break;
			}
		}
		int candidates[] = {location-1, location+1, location-4, location+4};
		for(int j=0; j<4; j++){
			if(check(candidates[j]))
				aim = candidates[j];
		}
		if(aim >= 0 && aim < 16){
			String tempStr = button[location].getText();
			int tempNum = butNum[location];
			button[location].setText("");
			butNum[location] = 0;
			button[aim].setText(tempStr);
			butNum[aim] = tempNum;
		}
		if(evaluate()){
			int choice = JOptionPane.showConfirmDialog(this, "成功啦！祝贺你！再来一次吗？",null,JOptionPane.YES_NO_OPTION);
		    if(choice == JOptionPane.YES_OPTION)
		    	initButtonNum();
		    else
		    	System.exit(0);
		}	
	}

	private boolean check(int index) {
		if(index >= 0 && index < 16 && butNum[index] == 0)
			return true;
		else
		    return false;
	}
	
	private boolean evaluate() {
		int i,j;
		for(i=0; i<16; i++){
			if(butNum[i] != i)
				break;
		}
		if(i == 16)  return true;
		
		for(i=0; i<15; i++){
			if(butNum[i] != i+1)
				break;
		}
		if(i == 15)  return true;
		
		for(i=0,j=15; j>=0; i++,j--){
			if(butNum[j] != i)
				break;
		}
		if(i == 16)  return true;
		
		for(i=0,j=15; j>0; i++,j--){
			if(butNum[j] != i+1)
				break;
		}
		if(i == 15)  return true;
		
		return false;
	}

	public static void main(String[] args) {
		SortGame game = new SortGame();
		game.setDefaultCloseOperation(EXIT_ON_CLOSE);
		game.setSize(300,300);
		game.setVisible(true);
	}
}
