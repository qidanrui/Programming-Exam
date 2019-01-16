import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Test extends JFrame {

	/**
	 * @param args
	 */
	int[] num;
	JButton[] j;

	Test() {
		JMenuBar jmb = new JMenuBar();
		JMenu jm = new JMenu("File");
		JMenuItem open = new JMenuItem("打开");
		open.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String tt =JOptionPane.showInputDialog("请输入打开路径","data.txt");
				try {
					FileReader fr = new FileReader(tt);
					String input = null;
					for(int i=fr.read();i!=-1;i=fr.read()){
						input+=(char)i;
					}
					input=input.substring(4);
					JOptionPane.showMessageDialog(null, input);
					String []inputs = input.split(",");
					if(inputs.length!=9)
						JOptionPane.showMessageDialog(null, "文件格式不符");
					label:
					for(int i=0;i<9;i++)
						for(int j=i+1;j<9;j++)
							if(num[i]==num[j]){
								JOptionPane.showMessageDialog(null, "文件内容不符,请修改后保存");
								break label;
								}
					
					for(int i=0;i<9;i++){
						num[i]=Integer.parseInt(inputs[i]);
					}
					
					shownum();
					fr.close();
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		JMenuItem save = new JMenuItem("保存");
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String ss =JOptionPane.showInputDialog("请输入保存路径","data.txt");
				try {
					FileWriter fw = new FileWriter(ss);
					for(int i=0;i<9;i++){
						fw.append(num[i]+",");
					}
					fw.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		jmb.add(jm);
		jm.add(open);
		jm.add(save);

		num = new int[15];
		j = new JButton[15];
		this.setJMenuBar(jmb);
		Container c = this.getContentPane();

		c.setLayout(new GridLayout(4, 4));
		for (int i = 0; i < 15; i++) {
			j[i] = new JButton();
			num[i] = 0;
		}
		for (int i = 0; i < 3; i++) {
			c.add(j[i]);
			j[i].addActionListener(new dealer(i));
		}
		j[9].setEnabled(false);
		c.add(j[9]);

		for (int i = 3; i < 6; i++) {
			c.add(j[i]);
			j[i].addActionListener(new dealer(i));
		}
		j[10].setEnabled(false);
		c.add(j[10]);

		for (int i = 6; i < 9; i++) {
			c.add(j[i]);
			j[i].addActionListener(new dealer(i));
		}
		j[11].setEnabled(false);
		c.add(j[11]);

		for (int i = 12; i < 15; i++) {
			j[i].setEnabled(false);
			c.add(j[i]);
		}

		

	}
	
	class dealer implements ActionListener{

		int t=0;
		public dealer(int i){
			t=i;
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int temp;
			temp=Integer.parseInt(JOptionPane.showInputDialog("请输入1-9的数字"));
			while(check(temp)){
				temp=Integer.parseInt(JOptionPane.showInputDialog("请重新输入1-9的数字"));
			}
			num[t]=temp;
			
			shownum();
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test t = new Test();
		t.setVisible(true);
		t.setSize(500, 500);
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public boolean check(int k) {
		// TODO Auto-generated method stub
		if(k<1||k>9)return true;
		for(int i=0;i<9;i++){
			if(num[i]==k){
				return true;
			}
		}
		return false;
	}

	public void shownum() {
		// TODO Auto-generated method stub
		num[9]=num[0]+num[1]+num[2];
		num[10]=num[3]+num[4]+num[5];
		num[11]=num[6]+num[7]+num[8];
		num[12]=num[0]+num[3]+num[6];
		num[13]=num[1]+num[4]+num[7];
		num[14]=num[2]+num[5]+num[8];
		for(int i=0;i<15;i++){
			j[i].setText(""+num[i]);
		}
		if(num[9]==15)
			if(num[10]==15)
				if(num[11]==15)
					if(num[12]==15)
						if(num[13]==15)
							if(num[14]==15)
								JOptionPane.showMessageDialog(null, "恭喜,成功了");
			
		
		
		
		
		
	}

}
