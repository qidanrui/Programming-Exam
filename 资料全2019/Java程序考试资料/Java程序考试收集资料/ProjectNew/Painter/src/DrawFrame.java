import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;


public class DrawFrame extends JFrame {
    private JPanel topPanel;
    private JButton openBtn;
    private JButton saveBtn;
    private JButton clearBtn;
    private JButton redoBtn;
    private JButton undoBtn;
    private JComboBox shapeBox;
    private JComboBox colorBox;
    private JCheckBox  filledChk;
    private JCheckBox  drawChk;
    private DrawPanel drawPanel;
    private String[] shapeStr={"Line","Rectangle","Oval","Ellipse","Polygen"};
    private String[] colorStr={"Black","Red","Yellow","Green"};
    private Color[]  colors={Color.black,Color.red,Color.yellow,Color.green};
    private Color curColor=Color.black;
    private boolean isFilled=false;
    private JLabel statusLbl;
    private int shapeType=1;
    private boolean ifdraw=false;
    private FileOperate fileOperate;
    public DrawFrame()
    {
    	topPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
    	ButtonHandle btnHandle=new ButtonHandle();
    	ItemHandle itemHandle=new ItemHandle();
    	openBtn=new JButton("Open");
    	openBtn.addActionListener(btnHandle);
    	saveBtn=new JButton("Save");
    	saveBtn.addActionListener(btnHandle);
    	clearBtn=new JButton("Clear");
    	clearBtn.addActionListener(btnHandle);
    	redoBtn=new JButton("Redo");
    	redoBtn.addActionListener(btnHandle);
    	undoBtn=new JButton("Undo");
    	undoBtn.addActionListener(btnHandle);
    	JLabel shapeLbl=new JLabel("Shape");
    	shapeBox=new JComboBox(shapeStr);
    	shapeBox.addItemListener(itemHandle);
    	JLabel colorLbl=new JLabel("Color");
    	colorBox=new JComboBox(colorStr);
    	colorBox.addItemListener(itemHandle);
    	filledChk=new JCheckBox("filled");
    	filledChk.addItemListener(itemHandle);
    	drawChk=new JCheckBox("draw");
    	drawChk.addItemListener(itemHandle);
    	topPanel.add(openBtn);
    	topPanel.add(saveBtn);
    	topPanel.add(clearBtn);
    	topPanel.add(redoBtn);
    	topPanel.add(undoBtn);
    	topPanel.add(shapeLbl);
    	topPanel.add(shapeBox);
    	topPanel.add(colorLbl);
    	topPanel.add(colorBox);
    	topPanel.add(filledChk);
    	topPanel.add(drawChk);
    	statusLbl=new JLabel();
    	drawPanel=new DrawPanel(statusLbl);
    	fileOperate=new FileOperate();
    	this.add(topPanel,BorderLayout.NORTH);
    	this.add(drawPanel,BorderLayout.CENTER);
    	this.add(statusLbl,BorderLayout.SOUTH);
    	this.setSize(new Dimension(800,600));
    	this.setVisible(true);
    }
    private class ButtonHandle  implements  ActionListener
    {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==clearBtn)
				drawPanel.clearAll();
			else if(e.getSource()==redoBtn)
				drawPanel.redo();
			else if(e.getSource()==undoBtn)
				drawPanel.undo();
			else if(e.getSource()==openBtn)
			{
				JFileChooser fileChooser=new JFileChooser();
				fileChooser.setDialogTitle("Open File");
				int flag=fileChooser.showOpenDialog(drawPanel);
				if(flag==JFileChooser.APPROVE_OPTION)
				{
					File f=fileChooser.getSelectedFile();
					Stack<MyShape> shape=(Stack<MyShape>) fileOperate.readObject(f.getAbsolutePath());
					if(shape==null)
						  JOptionPane.showMessageDialog(drawPanel, "Sorry,read error");
					else
					{	  
						drawPanel.setRedoShapes(shape);
					    drawPanel.repaint();
					}
				}
			 }
			else if(e.getSource()==saveBtn)
			{
				JFileChooser fileChooser=new JFileChooser();
				fileChooser.setDialogTitle("Save File");
				int flag=fileChooser.showSaveDialog(drawPanel);
				if(flag==JFileChooser.APPROVE_OPTION)
				{
					File f=fileChooser.getSelectedFile();
					if(f.exists())
					{
						int k=JOptionPane.showConfirmDialog(null, "File already exist, do you want to cover it?","warning",JOptionPane.YES_NO_OPTION);
						if(k==JOptionPane.YES_OPTION)
						{
							if(fileOperate.writeObject(drawPanel.getRedoShapes(), f.getAbsolutePath()))
		                    	JOptionPane.showMessageDialog(drawPanel, "Save success");
		                    else 
		                    	JOptionPane.showMessageDialog(drawPanel, "Save failure");
						}
					}
				}
			}
		}
    	
    }
    private class ItemHandle implements ItemListener
    {

		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==shapeBox)
			{
				shapeType=shapeBox.getSelectedIndex()+1;
				drawPanel.setProp(curColor, isFilled, ifdraw, shapeBox.getSelectedIndex()+1);
			}
			else if(e.getSource()==colorBox)
			{
				curColor=colors[colorBox.getSelectedIndex()];
				drawPanel.setProp(colors[colorBox.getSelectedIndex()], isFilled, ifdraw, shapeType);
			}
			else if(e.getSource()==filledChk)
			{
				isFilled=filledChk.isSelected()? true:false;
				drawPanel.setProp(curColor, isFilled, ifdraw, shapeType);
			}
			else if(e.getSource()==drawChk)
			{
				ifdraw=drawChk.isSelected()? true:false;
				drawPanel.setProp(curColor, isFilled, ifdraw, shapeType);
			}
		}
    	
    }
    public static void main(String[] args)
    {
    	new DrawFrame();
    }
}
