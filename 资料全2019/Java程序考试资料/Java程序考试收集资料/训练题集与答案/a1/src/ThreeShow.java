import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

import sun.net.www.content.image.jpeg;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class ThreeShow extends JFrame {

	JRadioButton radio1 = new JRadioButton("连续保存");
	JRadioButton radio2 = new JRadioButton("原有删除后保存");

	JButton one = new JButton("1");
	JButton two = new JButton("2");
	JButton three = new JButton("3");
	JButton four = new JButton("4");
	JButton five = new JButton("5");
	JButton six = new JButton("6");
	JButton seven = new JButton("7");
	JButton eight = new JButton("8");
	JButton nine = new JButton("9");
	JButton zero = new JButton("0");

	JButton add = new JButton("+");
	JButton sub = new JButton("-");
	JButton div = new JButton("/");
	JButton mul = new JButton("*");

	JButton cal = new JButton("=");

	JTextField op1 = new JTextField();
	JLabel op2 = new JLabel("=");

	JTextField num1 = new JTextField();
	JTextField num2 = new JTextField();
	JTextField result = new JTextField();

	boolean flag = false; // 判断第一个操作数是否输入完成，true为完成
	boolean doneFlag = false;
	boolean newNum1 = true;

	public ThreeShow() {

		one.addActionListener(new ButtonListener());
		two.addActionListener(new ButtonListener());
		three.addActionListener(new ButtonListener());
		four.addActionListener(new ButtonListener());
		five.addActionListener(new ButtonListener());
		six.addActionListener(new ButtonListener());
		seven.addActionListener(new ButtonListener());
		eight.addActionListener(new ButtonListener());
		nine.addActionListener(new ButtonListener());
		zero.addActionListener(new ButtonListener());

		add.addActionListener(new ButtonListener());
		sub.addActionListener(new ButtonListener());
		mul.addActionListener(new ButtonListener());
		div.addActionListener(new ButtonListener());
		cal.addActionListener(new ButtonListener());

		JPanel up = new JPanel();
		up.setLayout(new GridLayout(1, 5));
		up.add(num1);
		up.add(op1);
		up.add(num2);
		up.add(op2);
		up.add(result);

		JPanel down = new JPanel();
		down.setLayout(new GridLayout(4, 4));
		down.add(add);
		
		down.add(one);
		down.add(two);
		down.add(three);

		down.add(sub);
		down.add(four);
		down.add(five);
		down.add(six);

		down.add(mul);

		down.add(seven);
		down.add(eight);
		down.add(nine);

		down.add(div);
		down.add(zero);
		down.add(cal);
		// save

		JPanel save = new JPanel();
		ButtonGroup group = new ButtonGroup();
		group.add(radio1);
		group.add(radio2);

		save.add(radio1);
		save.add(radio2);
		radio1.setSelected(true);
		

		this.setLayout(new GridLayout(3, 1));
		this.add(up);
		this.add(down);
		this.add(save);

		this.pack();
		this.setSize(new Dimension(500,500));
		this.setVisible(true);

	}

	public void writeFile() {
		File f = new File("output.txt");
		String outStr = "";

		BufferedWriter bw;

		try {
			if (radio1.isSelected()) { // 连续保存
				bw = new BufferedWriter(new FileWriter(f, true));
			} else {
				bw = new BufferedWriter(new FileWriter(f));
			}
			outStr += num1.getText() + op1.getText() + num2.getText() + "="
					+ result.getText();
			outStr += "\r\n";
			bw.write(outStr);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("文件输出错误，空间不足？");
			System.exit(1);
		}
	}

	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			String s = b.getText();

			if (s.equals("+") || s.equals("-") || s.equals("*")
					|| s.equals("/")) {
				if (!flag) { // 计算符没有输入完成
					op1.setText(s);
					flag = true;
				}
			} else if (s.equals("=")) {// ///

				int n1 = Integer.parseInt(num1.getText());
				int n2 = Integer.parseInt(num2.getText());

				String op = op1.getText();

				double rs = 0;

				if (op.equals("+")) {
					rs = n1 + n2;
				} else if (op.equals("-")) {
					rs = n1 - n2;
				} else if (op.equals("*")) {
					rs = n1 * n2;
				} else if (op.equals("/")) {

					if (n2 == 0) {
						result.setText("除数为0 ");
						return;
					}
					rs = n1 / n2;
				}

				result.setText(Double.toString(rs));

				writeFile();
				newNum1 = true;
			}

			else {
				int num = Integer.parseInt(s);

				if (newNum1) {
					num1.setText(s);
					newNum1 = false;
					num2.setText("");
					result.setText("");
					op1.setText("");
					flag = false;
				} else {

					if (!flag) {
						num1.setText(num1.getText() + s);
					} else {
						num2.setText(num2.getText() + s);
					}
				}
			}

		}

	}

}
