package src;

import java.awt.*;
import java.awt.event.*;

public class CalculatorGUI
{
	private Frame f;
	private Panel p1, p2;
	private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9;
	private Button bPoint, bAdd, bSubstract, bMultiply, bDivide, bEqual,bSqrt,bClear;
	private TextField tf;

	private String s, op;
	private boolean ifop;
	private Calculator cal = new Calculator();

	public CalculatorGUI()
	{
		f = new Frame("Calculator");
		p1 = new Panel();
		p2 = new Panel();

		b0 = new Button("0");
		b1 = new Button("1");
		b2 = new Button("2");
		b3 = new Button("3");
		b4 = new Button("4");
		b5 = new Button("5");
		b6 = new Button("6");
		b7 = new Button("7");
		b8 = new Button("8");
		b9 = new Button("9");

		bPoint = new Button(".");
		bAdd = new Button("+");
		bSubstract = new Button("-");
		bMultiply = new Button("*");
		bDivide = new Button("/");
		bEqual = new Button("=");
		bSqrt = new Button("¡Ì");
		bClear = new Button("C");

		tf = new TextField(25);
		tf.setEditable(false);

	}

	private void launchFrame()
	{
		f.setSize(220, 200);
		f.setLocation(600, 200);
		f.setResizable(false);
		f.addWindowListener(new MyWindowListener());

		p1.setLayout(new FlowLayout(FlowLayout.CENTER));
		p1.add(tf);
		f.add(p1, BorderLayout.NORTH);
		p2.setLayout(new GridLayout(6, 3));

		b0.addActionListener(new SetTextLabel_ActionListener());
		b1.addActionListener(new SetTextLabel_ActionListener());
		b2.addActionListener(new SetTextLabel_ActionListener());
		b3.addActionListener(new SetTextLabel_ActionListener());
		b4.addActionListener(new SetTextLabel_ActionListener());
		b5.addActionListener(new SetTextLabel_ActionListener());
		b6.addActionListener(new SetTextLabel_ActionListener());
		b7.addActionListener(new SetTextLabel_ActionListener());
		b8.addActionListener(new SetTextLabel_ActionListener());
		b9.addActionListener(new SetTextLabel_ActionListener());
		bPoint.addActionListener(new SetTextLabel_ActionListener());

		bAdd.addActionListener(new SetOperator_ActionListenner());
		bSubstract.addActionListener(new SetOperator_ActionListenner());
		bMultiply.addActionListener(new SetOperator_ActionListenner());
		bDivide.addActionListener(new SetOperator_ActionListenner());
		bEqual.addActionListener(new SetOperator_ActionListenner());
		bSqrt.addActionListener(new SetOperator_ActionListenner());
		bClear.addActionListener(new SetOperator_ActionListenner());

		p2.add(bAdd);
		p2.add(bSubstract);
		p2.add(bEqual);
		
		p2.add(bMultiply);
		p2.add(bDivide);
		p2.add(bSqrt);
				
		p2.add(bPoint);
		p2.add(b0);
		p2.add(bClear);
		
		p2.add(b7);
		p2.add(b8);
		p2.add(b9);

		p2.add(b4);
		p2.add(b5);
		p2.add(b6);		

		p2.add(b1);
		p2.add(b2);
		p2.add(b3);		

		f.add(p2, BorderLayout.SOUTH);
		f.setVisible(true);

	}

	class MyWindowListener extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	}

	class SetTextLabel_ActionListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			Button tempB = (Button) e.getSource();
			s = tempB.getLabel();
			setTextFieldText();
		}
	}

	class SetOperator_ActionListenner implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			Button tempB = (Button) e.getSource();
			op = tempB.getLabel();

			if (op.equals("+"))
			{
				tf.setText(cal.opAdd(tf.getText()));
				ifop = true;
			}
			else if (op.equals("-"))
			{
				tf.setText(cal.opSubstract(tf.getText()));
				ifop = true;
			}
			else if (op.equals("*"))
			{
				tf.setText(cal.opMultiply(tf.getText()));
				ifop = true;
			}
			else if (op.equals("/"))
			{
				tf.setText(cal.opDivide(tf.getText()));
				ifop = true;
			}
			else if (op.equals("="))
			{
				tf.setText(cal.opEqual(tf.getText()));
				ifop = true;
			}
			else if(op.equals("¡Ì"))
			{
				tf.setText(cal.opSqrt(tf.getText()));
				ifop = false;
			}
			else if(op.equals("C"))
			{
				cal.opClear();
				tf.setText("0");			
			}

		}

	}

	private void setTextFieldText_Temp()
	{
		if (tf.getText().length() < 15 && tf.getText().indexOf(".") == -1
				|| s.equalsIgnoreCase("."))
		{
			tf.setText(tf.getText() + s);
		}
		else
			tf.setText((tf.getText() + s).substring(0, 15));
	}

	private void setTextFieldText()
	{
		if (ifop)
		{
			ifop = false;
			tf.setText("");
			setTextFieldText_Temp();
		}
		else
			setTextFieldText_Temp();
	}

	public static void main(String[] args)
	{
		CalculatorGUI calculator = new CalculatorGUI();
		calculator.launchFrame();

	}

}
