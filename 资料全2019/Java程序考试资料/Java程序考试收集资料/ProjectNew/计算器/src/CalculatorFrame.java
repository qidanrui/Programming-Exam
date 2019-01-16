import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class CalculatorFrame extends JFrame{
	private String num[]={"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
	private JButton[] numb=new JButton[16];
	private JTextField text;
	private JPanel t;
	private JPanel content;
    private double total=0;
    private double  pre=0;//保留按下操作符时的数据
    private double  now=0;//保留当前的数据
    private boolean ispressed=false;//判断是否按下操作符,若按下则下一次的输入需要将前一次清空
    private char c;//保留操作符
	public CalculatorFrame()
	{
		super("计算器");
	    text=new JTextField(20);
	    text.setEditable(false);
	    text.setFont(new Font(Font.SERIF,Font.PLAIN,20));
	    text.setBackground(Color.WHITE);
	    text.setText(""+0);
	    t=new JPanel();
	    content=new JPanel();
	    t.add(text);
	    GridLayout g=new GridLayout(4,4);
	    content.setLayout(g);
	    CalculatorHandler handler=new CalculatorHandler();
	    for(int i=0;i<num.length;i++)
	    {
	    	numb[i]=new JButton(num[i]);
	    	numb[i].setFont(new Font("Serif",Font.PLAIN,20));
	    	numb[i].addActionListener( handler);
	    	content.add(numb[i]);
	    }
	    add(t,BorderLayout.NORTH);
	    add(content);
		
	}
	private class CalculatorHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			for(int i=0;i<num.length;i++)
			{
				if(e.getSource()==numb[i])
				{ 
				    if(numb[i].getText().equals("."))
					{
						text.setText(text.getText()+".");
					}
					else if(numb[i].getText().equals("+"))
						{
						  c='+';
						  pre=Double.valueOf(text.getText());//将文本中的string转化为相应的double类型
						  ispressed=true;
						}
					else if(numb[i].getText().equals("-"))
						{
						   c='-';
						   pre=Double.valueOf(text.getText());
						   ispressed=true;
						}
					else if(numb[i].getText().equals("*"))
						{
						   c='*';
						   pre=Double.valueOf(text.getText());
						   ispressed=true;
						}
					else if(numb[i].getText().equals("/"))
						{
						   c='/';
						   pre=Double.valueOf(text.getText());
						   ispressed=true;
						}
					else if(numb[i].getText().equals("="))
						{  
						   operate(c);
						   now=Double.valueOf(text.getText());
						   ispressed=true;
						}
					else
					{
						if(text.getText().equals("0"))
						    text.setText(numb[i].getText());
						else if(!ispressed)
							text.setText(text.getText()+numb[i].getText());
						else 
							{
							  text.setText(""+numb[i].getText());
							  ispressed=false;
							}
					}
					break;
						
				}
			}
		}
	}
	public void operate(char m)
	
	{
		now=Double.valueOf(text.getText());
		switch(m)
		{
		case '+':
			total=pre+now;
			break;
		case '-':
			total=pre-now;
			break;
		case '*':
			total=pre*now;
			break;
		case '/':
			total=pre/now;
			break;
		}
		text.setText(""+total);
		total=0;
	}
}
