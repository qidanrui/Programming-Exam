import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * 界面及事件
 * @author sinllychen
 *
 */
public class DrawPanelFrame extends JFrame{
	   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JLabel dataSourcelbl;
    private JTextArea dataSourcePath;
    private JButton selectSourceBtn;
    private JLabel changeLbl1;
    private JTextArea  mostChangedList;
    private JLabel changeLbl2;
    private JTextArea  mostLineChangedList;
    private JButton importBtn;
 
    
	   public DrawPanelFrame()
	   {
		   dataSourcelbl=new JLabel("Log文件");
		   dataSourcePath=new JTextArea();
		   dataSourcePath.setColumns(25);
		   selectSourceBtn=new JButton("选择");
		   JPanel topPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		   topPanel.add(dataSourcelbl);
		   topPanel.add(dataSourcePath);
		   topPanel.add(selectSourceBtn);
		   
		   
		   changeLbl1=new JLabel("修改次数最多的文件");
		   mostChangedList=new JTextArea(10,40);
		   JScrollPane scrollPane1=new JScrollPane(mostChangedList);
		   JPanel centerPanel=new JPanel(new BorderLayout());
		   centerPanel.add(changeLbl1,BorderLayout.NORTH);
		   centerPanel.add(scrollPane1,BorderLayout.CENTER);
		   
		   changeLbl2=new JLabel("修改代码行最多的文件");
		   mostLineChangedList=new JTextArea(10,40);
		   JScrollPane scrollPane2=new JScrollPane(mostLineChangedList);
		   JPanel bottomPanel=new JPanel(new BorderLayout());
		   bottomPanel.add(changeLbl2,BorderLayout.NORTH);
		   bottomPanel.add(scrollPane2,BorderLayout.CENTER);
		   
		
		   
		   importBtn=new JButton("分析数据");
		   
		   this.setLayout(new FlowLayout());
           this.add(topPanel);
           this.add(centerPanel);
           this.add(bottomPanel);
           this.add(importBtn);
           this.setTitle("日志分析");
           importBtn.addActionListener(
				   new ActionListener()
				   { 
					    public void actionPerformed(ActionEvent e) {
			                   LogFileReader reader=new LogFileReader(dataSourcePath.getText());
			                   reader.getFinalData();
			                   StringBuffer buf=new StringBuffer();
			                   StringBuffer buf2=new StringBuffer();
			                   for(int i=0;i<LogFileReader.mostCountList.size();i++)
			                   {
			                	   buf.append(LogFileReader.mostCountList.get(i).getKey()+"   "+LogFileReader.mostCountList.get(i).getValue());
			                	   buf.append("\n");
			                   }
			                   mostChangedList.setText(buf.toString());
			                   for(int i=0;i<LogFileReader.mostLineList.size();i++)
			                   {
			                	   buf2.append(LogFileReader.mostLineList.get(i).getKey()+"   "+LogFileReader.mostLineList.get(i).getValue());
			                	   buf2.append("\n");
			                   }
			                   mostLineChangedList.setText(buf2.toString());
			                   
					}
				   }
				   );
           selectSourceBtn.addActionListener(
				   new ActionListener()
				   { 
					    public void actionPerformed(ActionEvent e) {
					    	JFileChooser chooser = new JFileChooser();
					    	 int returnVal = chooser.showOpenDialog(DrawPanelFrame.this);
					    	    if(returnVal == JFileChooser.APPROVE_OPTION) {
					    	    	dataSourcePath.setText(chooser.getSelectedFile().getAbsolutePath());
					    	    }
					}
				   }
				   );
	   }
		   public static void main(String[] args)
		   {
		       DrawPanelFrame f=new DrawPanelFrame();
			   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			   f.setSize(500,550);
			   f.setVisible(true);
			   f.setResizable(false);
		   }
	
}
