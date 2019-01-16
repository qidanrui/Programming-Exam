import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
public class DrawPanelFrame extends JFrame{
	   private JButton undo;
	   private JButton clear;
	   private String colorname[]={"black","white","blue","cyan","dark_gray","gray","green","light_gray","magenta","orange","pink","red"};
	   private Color colors[]={Color.BLACK,Color.WHITE,Color.BLUE,Color.CYAN,Color.DARK_GRAY,Color.GRAY,Color.GREEN,Color.LIGHT_GRAY,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.YELLOW};
	   private String linethick[]={"1","2","3","4","5"};
	   private float linethickvalue[]={1.0f,2.0f,3.0f,4.0f,5.0f};
	   private String lineshape[]={"Full_Line","Dashed_Line"};
	   private JComboBox colorbox;
	   private String s[]={"Line","Rectangle","Oval","Curve"};
	   private JComboBox shapebox;
	   private JComboBox linethickbox;
	   private JComboBox lineshapebox;
	   private JCheckBox iffill;
	   private JCheckBox ifmousedraw;
	   private JPanel component;
	   private JLabel statuslabel;
	   private JLabel colorlabel;
	   private JLabel shapelabel;
	   private JLabel linethicklabel;
	   private JLabel lineshapelabel;
	   private DrawPanel shape;
	   int shapenum=1;
	   Color c=Color.BLACK;
	   boolean iffilled=false;
	   boolean ifdashed=false;
	   boolean mousedraw=false;
	   float thick=1f;
	   public DrawPanelFrame()
	   {
		   undo=new JButton("UnDo");
		   clear=new JButton("Clear");
		   colorbox=new JComboBox(colorname);
		   shapebox=new JComboBox(s);
		   linethickbox=new JComboBox(linethick);
		   lineshapebox=new JComboBox(lineshape);
		   iffill=new JCheckBox("Filled");
		   ifmousedraw=new JCheckBox("MouseDraw");
		   component=new JPanel(new FlowLayout(FlowLayout.CENTER));
		   statuslabel=new JLabel();
		   colorlabel=new JLabel("Color:");
		   colorlabel.setForeground(Color.RED);
		   shapelabel=new JLabel("Shape：");
		   shapelabel.setForeground(Color.RED);
		   linethicklabel=new JLabel("LineThickness：");
		   linethicklabel.setForeground(Color.RED);
		   lineshapelabel=new JLabel("LineShape：");
		   lineshapelabel.setForeground(Color.RED);
		   shape=new DrawPanel(statuslabel);
		   component.add(undo);
		   component.add(clear);
		   component.add(colorlabel);
		   component.add(colorbox);
		   component.add(shapelabel);
		   component.add(shapebox);
		   component.add(linethicklabel);
		   component.add(linethickbox);
		   component.add(lineshapelabel);
		   component.add(lineshapebox);
		   component.add(iffill);
		   component.add(ifmousedraw);
		   add(component,BorderLayout.NORTH);
		   add(shape,BorderLayout.CENTER);
		   add(statuslabel,BorderLayout.SOUTH);
		   shapebox.addItemListener(
				   new ItemListener()
				   {
					   public void itemStateChanged(ItemEvent e)
					   {
						   if(e.getStateChange()==ItemEvent.SELECTED)
						   {
							   shapenum=shapebox.getSelectedIndex()+1;
							   shape.Set(shapenum, c, iffilled,ifdashed,thick,mousedraw);
						   }
					   }
				   }
				   );
		   colorbox.addItemListener(
				   new ItemListener()
				   {
					   public void itemStateChanged(ItemEvent e)
					   {
						   if(e.getStateChange()==ItemEvent.SELECTED)
						   {
							   c=colors[colorbox.getSelectedIndex()];
							   shape.Set(shapenum, c, iffilled,ifdashed,thick,mousedraw);
						   }
					   }
				   }
				   );
		   iffill.addItemListener(
				   new ItemListener()
				   {
					   public void itemStateChanged(ItemEvent e)
					   {
						   if(e.getSource()==iffill)
						   {
							   iffilled=iffill.isSelected()? true:false;
							   shape.Set(shapenum, c, iffilled,ifdashed,thick,mousedraw);
						   }
					   }
				   }
				   );
		   ifmousedraw.addItemListener(
				   new ItemListener()
				   {

					public void itemStateChanged(ItemEvent e) {
						// TODO Auto-generated method stub
						if(e.getSource()==ifmousedraw)
						{
							mousedraw=ifmousedraw.isSelected()? true:false;
							shape.Set(shapenum, c, iffilled, ifdashed, thick, mousedraw);
						}
					}
					   
				   }
				   );
		   linethickbox.addItemListener(
				   new ItemListener()
				   {

					  public void itemStateChanged(ItemEvent e) {
						// TODO Auto-generated method stub
						if(e.getStateChange()==ItemEvent.SELECTED)
						{
							thick=linethickvalue[linethickbox.getSelectedIndex()];
						    shape.Set(shapenum, c, iffilled, ifdashed, thick,mousedraw);
						}
					   }
					   
				   }
				  );
		   lineshapebox.addItemListener(
				   new ItemListener()
				   {

					 public void itemStateChanged(ItemEvent e) {
						// TODO Auto-generated method stub
						if(e.getStateChange()==ItemEvent.SELECTED)
						{
							if(lineshapebox.getSelectedItem()=="Full_Line")
								ifdashed=false;
							else 
								ifdashed=true;
							shape.Set(shapenum, c, iffilled, ifdashed, thick,mousedraw);
						}
					 }
					   
				   }
				   );
		   undo.addActionListener(
				   new ActionListener()
				   {
					  
					  public void actionPerformed(ActionEvent e) {
						shape.clearLastShape();
						
					}
				   }
				   );
		   clear.addActionListener(
				   new ActionListener()
				   {
					   public void actionPerformed(ActionEvent e)
					   {
						   shape.clearDrawing();
					   }
				   }
				   );
		   
		   
	   }
}
