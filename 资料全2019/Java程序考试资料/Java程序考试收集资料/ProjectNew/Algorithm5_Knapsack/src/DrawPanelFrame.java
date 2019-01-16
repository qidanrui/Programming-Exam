import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JLabel bagCapacity;
    private JLabel goodsNumlbl;
    private JTextArea goodsNumTxt;
    private JTextArea bagCapacityTxt;
    private JTextArea goodsInfo;
    private JButton generateBtn;
    private JTextArea goodsFinalInfo;
    private JLabel inputlbl;
    private JLabel outputlbl;
    private JScrollPane scrollLeft;
    private JScrollPane scrollRight;
    private int total;
    private int c;//背包容量
    private int[] w;
    private int[] v;
 
    
	   public DrawPanelFrame()
	   {
		   bagCapacity=new JLabel("背包容量:");
		   bagCapacityTxt=new JTextArea();
		   bagCapacityTxt.setColumns(5);
		   goodsNumlbl=new JLabel("物品数量");
		   goodsNumTxt=new JTextArea();
		   goodsNumTxt.setColumns(5);
		   JPanel firstPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		   firstPanel.add(bagCapacity);
		   firstPanel.add(bagCapacityTxt);
		   firstPanel.add(goodsNumlbl);
		   firstPanel.add(goodsNumTxt);
		   
		   goodsInfo=new JTextArea(15,5);
		   goodsInfo.setLineWrap(true);
		   scrollLeft=new JScrollPane(goodsInfo);
		   inputlbl=new JLabel("物品输入信息");
		   StringBuffer info=new StringBuffer();
		   info.append("物品信息输入形式如下:\n");
		   info.append("重量,价值\n");
		   info.append("30,20\n");
		   info.append("12,34\n");
		   info.append("12,34\n");
		   info.append("3,4\n");
		   goodsInfo.setText(info.toString());
		  JPanel leftPanel=new JPanel(new BorderLayout());
		  leftPanel.add(inputlbl,BorderLayout.NORTH);
		  leftPanel.add(scrollLeft,BorderLayout.CENTER);
		   
		 generateBtn=new JButton("执行");

		   
		  goodsFinalInfo=new JTextArea(15,5);
		   goodsFinalInfo.setLineWrap(true);
		   scrollRight=new JScrollPane(goodsFinalInfo);
		   outputlbl=new JLabel("背包物品信息");
		  JPanel rightPanel=new JPanel(new BorderLayout());
		  rightPanel.add(outputlbl,BorderLayout.NORTH);
		  rightPanel.add(scrollRight,BorderLayout.CENTER);
		   
		  JPanel centerPanel=new JPanel(new FlowLayout(FlowLayout.CENTER));
		  centerPanel.add(leftPanel);
         centerPanel.add(generateBtn);
		  centerPanel.add(rightPanel);
		   
		   this.setLayout(new BorderLayout());
           this.add(firstPanel,BorderLayout.NORTH);
           this.add(centerPanel,BorderLayout.CENTER);
           this.setTitle("0-1背包问题");
           generateBtn.addActionListener(
				   new ActionListener()
				   { 
					    public void actionPerformed(ActionEvent e) {
					    	total=Integer.valueOf(goodsNumTxt.getText());
					    	String[] value=goodsInfo.getText().split("\n");
					    	if(value.length!=total)
					    	   JOptionPane.showMessageDialog(null, "物品数量与实际输入量不符合", "错误信息", JOptionPane.ERROR_MESSAGE);
					    	else
					    	{
					    	value=goodsInfo.getText().split("\n");
						    c=Integer.valueOf(bagCapacityTxt.getText());
						    total=Integer.valueOf(goodsNumTxt.getText());
					    	w=new int[total+1];
					    	v=new int[total+1];
					    	for(int i=1;i<=total;i++)
					    	{
					    		String[] temp=value[i-1].split(",");
					    		w[i]=Integer.valueOf(temp[0]);
					    		v[i]=Integer.valueOf(temp[1]);
					    	}
					    	Knapsack.knapsack(total, c, v, w, goodsFinalInfo);
					    	}
					}
				   }
				   );
	   }
		   public static void main(String[] args)
		   {
		       DrawPanelFrame f=new DrawPanelFrame();
			   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			   f.setSize(250,340);
			   f.setVisible(true);
			   f.setResizable(false);
		   }
		/**
		 * @return the total
		 */
		public int getTotal() {
			return total;
		}
		/**
		 * @param total the total to set
		 */
		public void setTotal(int total) {
			this.total = total;
		}
		/**
		 * @return the w
		 */
		public int[] getW() {
			return w;
		}
		/**
		 * @param w the w to set
		 */
		public void setW(int[] w) {
			this.w = w;
		}
		/**
		 * @return the v
		 */
		public int[] getV() {
			return v;
		}
		/**
		 * @param v the v to set
		 */
		public void setV(int[] v) {
			this.v = v;
		}
		/**
		 * @return the c
		 */
		public int getC() {
			return c;
		}
		/**
		 * @param c the c to set
		 */
		public void setC(int c) {
			this.c = c;
		}
}
