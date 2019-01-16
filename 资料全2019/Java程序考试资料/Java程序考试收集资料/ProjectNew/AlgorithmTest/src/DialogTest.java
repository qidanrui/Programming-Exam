import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class DialogTest extends JFrame{
	private JLabel label;
	private MyDialog dialog;
    public DialogTest()
    {
    	label=new JLabel("nihao");
    	JButton button=new JButton("click");
    	button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog=new MyDialog();
				dialog.show();
			}
    		
    	});
    	this.setLayout(new FlowLayout(FlowLayout.CENTER));
    	this.add(button);
    	this.add(label);
    	this.setVisible(true);
    	this.setSize(new Dimension(500,500));
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    private class MyDialog extends JDialog
    {
    	private JTextField text;
    	private JButton btn;
    	public MyDialog()
    	{
    		setText(new JTextField("hello"));
    		JButton btn=new JButton("click me");
    		btn.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					label.setText(text.getText());
					dispose();
				}
    			
    		});
    		this.setLayout(new FlowLayout(FlowLayout.CENTER));
    		this.add(text);
    		this.add(btn);
    		this.setVisible(true);
    		this.setSize(new Dimension(200,200));
    	}
		/**
		 * @return the text
		 */
		public JTextField getText() {
			return text;
		}
		/**
		 * @param text the text to set
		 */
		public void setText(JTextField text) {
			this.text = text;
		}
		/**
		 * @return the btn
		 */
		public JButton getBtn() {
			return btn;
		}
		/**
		 * @param btn the btn to set
		 */
		public void setBtn(JButton btn) {
			this.btn = btn;
		}
    }
	public static void main(String[] args)
	{
		new DialogTest();
	}
}
