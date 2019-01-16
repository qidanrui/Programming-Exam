import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.JScrollPane;

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
    private JLabel numlbl;
    private JTextArea digitNumTxt;
    private JButton generateSeqBtn;
    private JTextArea digitTxt;
    private JButton computeBtn;
    private JButton compute1Btn;
    private JButton compute2Btn;
    private JLabel timelbl;
    private JTextArea timeTxt;
    private JLabel time1lbl;
    private JTextArea time1Txt;
    private JLabel time2lbl;
    private JTextArea time2Txt;
    private JTextArea finalSeqTxt;
    private JPanel firstPanel;
    private JPanel secondPanel;
    private JPanel thirdPanel;
    private JPanel lastPanel;
    private JScrollPane  scrollTop;
    private JScrollPane  scrollBottom;
    private JTextArea lengthtxt;
    private JLabel lengthlbl;
    private static int[] data;
    
	   public DrawPanelFrame()
	   {
		   numlbl=new JLabel("随机序列长度:");
		   digitNumTxt=new JTextArea();
		   digitNumTxt.setColumns(8);
		   generateSeqBtn=new JButton("生成序列");
		   firstPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		   firstPanel.add(numlbl);
		   firstPanel.add(digitNumTxt);
		   firstPanel.add(generateSeqBtn);
		   
		   digitTxt=new JTextArea(11,23);
		   digitTxt.setLineWrap(true);
		   scrollTop=new JScrollPane(digitTxt);
		   secondPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		   secondPanel.add(scrollTop);
		   
		   computeBtn=new JButton("执行(nlgn)算法");
		   timelbl=new JLabel("运行时间:");
		   timeTxt=new JTextArea();
		   timeTxt.setColumns(8);
		   compute1Btn=new JButton("执行(n2)算法");
		   time1lbl=new JLabel("运行时间:");
		   time1Txt=new JTextArea();
		   time1Txt.setColumns(8);
		   compute2Btn=new JButton("执行(LCS)算法");
		   time2lbl=new JLabel("运行时间:");
		   time2Txt=new JTextArea();
		   time2Txt.setColumns(8);
		   JPanel tempPanel1=new JPanel(new FlowLayout(FlowLayout.CENTER));
		   thirdPanel=new  JPanel(new GridLayout(3,1,2,2));
		   tempPanel1.add(computeBtn);
		   tempPanel1.add(timelbl);
		   tempPanel1.add(timeTxt);
		   JPanel tempPanel2=new JPanel(new FlowLayout(FlowLayout.CENTER));
		   tempPanel2.add(compute1Btn);
		   tempPanel2.add(time1lbl);
		   tempPanel2.add(time1Txt);
		   JPanel tempPanel3=new JPanel(new FlowLayout(FlowLayout.CENTER));
		   tempPanel3.add(compute2Btn);
		   tempPanel3.add(time2lbl);
		   tempPanel3.add(time2Txt);   
		   thirdPanel.add(tempPanel1);
		   thirdPanel.add(tempPanel2);
		   thirdPanel.add(tempPanel3);

		   
		   finalSeqTxt=new JTextArea(11,23);
		   finalSeqTxt.setLineWrap(true);
		   scrollBottom=new JScrollPane(finalSeqTxt);
		   lastPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		   lastPanel.add(scrollBottom);
		   
		   JPanel bottom=new JPanel(new FlowLayout(FlowLayout.CENTER));
		   lengthlbl=new JLabel("最长递增子序列的个数为:");
		   lengthtxt=new JTextArea();
		   lengthtxt.setColumns(10);
		   bottom.add(lengthlbl);
		   bottom.add(lengthtxt);
		   this.setLayout(new FlowLayout());
           this.add(firstPanel);
           this.add(secondPanel);
           this.add(thirdPanel);
           this.add(lastPanel);
           this.add(bottom);
           this.setTitle("计算最长单调递增子序列");
           generateSeqBtn.addActionListener(
				   new ActionListener()
				   { 
					    public void actionPerformed(ActionEvent e) {
						int n=Integer.valueOf(digitNumTxt.getText());
						digitTxt.setText("");
						finalSeqTxt.setText("");
						Random r=new Random();
						data=new int[n];
						for(int i=0;i<n;i++)
						{
							data[i]=r.nextInt(501);
							if(i==n-1)
								digitTxt.setText(digitTxt.getText()+data[i]);
							else 
							  digitTxt.setText(digitTxt.getText()+data[i]+",");
						}
					}
				   }
				   );
           computeBtn.addActionListener(
				   new ActionListener()
				   { 
					    public void actionPerformed(ActionEvent e) {
					    finalSeqTxt.setText("");
					    String[] temp=digitTxt.getText().split(",");
					    data=new int[temp.length];
					    for(int i=0;i<temp.length;i++)
					    	data[i]=Integer.valueOf(temp[i]);
						long start=System.nanoTime();
						int maxLength=LongIncrSeq.getLongIncrSeqByNlgn(data);
						LongIncrSeq.outputByNlgn(finalSeqTxt, maxLength,data);
						long end=System.nanoTime();
						timeTxt.setText(timeFormat(end-start));
						lengthtxt.setText(String.valueOf(maxLength));
					}
				   }
				   );
           compute1Btn.addActionListener(
				   new ActionListener()
				   { 
					    public void actionPerformed(ActionEvent e) {
					    finalSeqTxt.setText("");
					    String[] temp=digitTxt.getText().split(",");
					    data=new int[temp.length];
					    for(int i=0;i<temp.length;i++)
					    	data[i]=Integer.valueOf(temp[i]);
					    long start=System.nanoTime();
						int maxLength=LongIncrSeq.getLongIncrSeqByN2(data);
						LongIncrSeq.outputByN2(finalSeqTxt, maxLength, data, data.length-1);
						long end=System.nanoTime();
						time1Txt.setText(timeFormat(end-start));
						lengthtxt.setText(String.valueOf(maxLength));
					}
				   }
				   );
           compute2Btn.addActionListener(
				   new ActionListener()
				   { 
					    public void actionPerformed(ActionEvent e) {
					    finalSeqTxt.setText("");
					    String[] temp=digitTxt.getText().split(",");
					    data=new int[temp.length];
					    for(int i=0;i<temp.length;i++)
					    	data[i]=Integer.valueOf(temp[i]);
						long start=System.nanoTime();
						int maxLength=LongIncrSeq.getLongIncrSeqByLCS(finalSeqTxt,data);
						long end=System.nanoTime();
						time2Txt.setText(timeFormat(end-start));
						lengthtxt.setText(String.valueOf(maxLength));
					}
				   }
				   );
	   }
	   /**
		 * 时间格式转换
		 * @param time
		 * @return
		 */
		public static String timeFormat(long time)
		{
			DecimalFormat df=new DecimalFormat(".####");
	    	if(String.valueOf((double)(time)/1000000).substring(0,1).equals("0"))
	    		return "0"+df.format((double)(time)/1000000)+"ms";
	    	else 
	    		return df.format((double)(time)/1000000)+"ms";
		}
		   public static void main(String[] args)
		   {
		       DrawPanelFrame f=new DrawPanelFrame();
			   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			   f.setSize(330,620);
			   f.setVisible(true);
			   f.setResizable(false);
		   }
}
