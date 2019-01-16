import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;


public class MainFrame extends JFrame {
      private static List<Book> books;
      private static List<Book> currentBooks;
      public JButton insertBtn;
      private JButton deleteBtn;
      private JTextField typeTxt;
      private JTextField authorTxt;
      private JTextField publisherTxt;
      private JTextField receiverTxt;
      private JButton searchBtn;
      private JTable table;
      private TableModel model;
      private JButton saveBtn;
      private boolean insert=false;
      private JButton cancelBtn;
      public MainFrame()
      {
    	   books=new ArrayList<Book>();
    	   currentBooks=new ArrayList<Book>();
    	   for(int i=0;i<10;i++)
    	   {
    		   Book book=new Book();
    		book.setId((Integer) i);
       	  	book.setName(String.valueOf(i));
       		book.setType(String.valueOf(i));
       		book.setAuthor(String.valueOf(i));
       		book.setPrice(i);
       		book.setPubDate("1991-"+i+"-"+"16");
       		book.setPublisher(String.valueOf(i));
       		book.setBuyDate(String.valueOf(i));
       		book.setReceiver(String.valueOf(i));
       		books.add(book);
    	   }
    	  ActionHandle handle=new ActionHandle();
    	   insertBtn=new JButton("录入");
    	   insertBtn.addActionListener(handle);
    	   deleteBtn=new JButton("删除");
    	   deleteBtn.addActionListener(handle);
    	   JLabel typelbl=new JLabel("类型");
    	   typeTxt=new JTextField(10);
    	   JLabel authorlbl=new JLabel("作者");
    	   authorTxt=new JTextField(10);
    	   JLabel publisherLbl=new JLabel("出版社");
    	   publisherTxt=new JTextField(10);
    	   JLabel receiverLbl=new JLabel("借阅人");
    	   receiverTxt=new JTextField(10);
    	   searchBtn=new JButton("查询");
    	   searchBtn.addActionListener(handle);
    	   JPanel topPanel=new JPanel();
    	   topPanel.add(insertBtn);
    	   topPanel.add(deleteBtn);
    	   topPanel.add(typelbl);
    	   topPanel.add(typeTxt);
    	   topPanel.add(authorlbl);
    	   topPanel.add(authorTxt);
    	   topPanel.add(publisherLbl);
    	   topPanel.add(publisherTxt);
    	   topPanel.add(receiverLbl);
    	   topPanel.add(receiverTxt);
    	   topPanel.add(searchBtn);
    	   model=new TableModel();
    	   model.setObject(books);
    	   table=new JTable(model);
    	   //table.setPreferredScrollableViewportSize(new Dimension(300,400));
    	   table.setAutoCreateRowSorter(true);
    	   JScrollPane scrollPanel=new JScrollPane(table);
    	   JPanel bottomPanel=new JPanel();
    	   saveBtn=new JButton("保存");
    	    saveBtn.addActionListener(handle);
    	    cancelBtn=new JButton("取消");
    	    cancelBtn.addActionListener(handle);
    	    bottomPanel.add(saveBtn);
    	    bottomPanel.add(cancelBtn);
    	   this.add(topPanel,BorderLayout.NORTH);
    	   this.add(scrollPanel,BorderLayout.CENTER);
    	   this.add(bottomPanel,BorderLayout.SOUTH);
    	   this.setVisible(true);
    	   this.setSize(new Dimension(1000,600));
    	   
      }
      private class ActionHandle implements  ActionListener
      {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==insertBtn)
			{
				Book b=new Book();
				books.add(b);
				model.setObject(books);
				table.setModel(model);
				table.updateUI();
				insert=true;
			}
			else if(e.getSource()==saveBtn)
			{
				int k=JOptionPane.showConfirmDialog(null, "确定保存数据","确认信息",JOptionPane.YES_NO_OPTION);
				if(k==JOptionPane.YES_OPTION)
				{
					books=model.getObject();
					JOptionPane.showMessageDialog(null, "保存成功!");
					if(insert)
						insert=false;
				}
				else
				{
					if(insert)
					{
						books.remove(books.size()-1);
						model.setObject(books);
						table.setModel(model);
						table.updateUI();
						insert=false;
					}
					else
					{
						model.setObject(books);
						table.setModel(model);
						table.updateUI();
					}
					  
				}
			}
			else if(e.getSource()==deleteBtn)
			{
				int i=table.getSelectedRow();
				books=BookOperate.deleteById(books,books.get(i).getId());
				model.setObject(BookOperate.search(books, typeTxt.getText(), authorTxt.getText(), publisherTxt.getText(), receiverTxt.getText()));
				table.setModel(model);
				table.updateUI();
			}
			else if(e.getSource()==searchBtn)
			{
				List<Book> list=BookOperate.search(books, typeTxt.getText(), authorTxt.getText(), publisherTxt.getText(), receiverTxt.getText());
				model.setObject(list);
				table.setModel(model);
				table.updateUI();
			}
		}
    	  
      }
      public void delete(int index)
      {
    	   books.remove(index);
    	   model.setObject(books);
    	   table.setModel(model);
    	   table.updateUI();
      }
      private class TableModel extends AbstractTableModel
      {
        private String[] columns={"编号","书名","类型","作者","价格","出版日期","出版社","购入日期","借阅人"};
        private Object[][] object;
        public void setObject(List<Book> books)
        {
        	object=new Object[books.size()][9];
        	for(int i=0;i<books.size();i++)
        	{
        		object[i][0]=books.get(i).getId();
        		object[i][1]=books.get(i).getName();
        		object[i][2]=books.get(i).getType();
        		object[i][3]=books.get(i).getAuthor();
        		object[i][4]=books.get(i).getPrice();
        		object[i][5]=books.get(i).getPubDate();
        		object[i][6]=books.get(i).getPublisher();
        		object[i][7]=books.get(i).getBuyDate();
        		object[i][8]=books.get(i).getReceiver();
        	}
        }
        public List<Book> getObject()
        {
        	List<Book> bks=new ArrayList<Book>();
        	for(int i=0;i<object.length;i++)
        	{
        		Book book=new Book();
        		book.setId((Integer) object[i][0]);
        		book.setName((String) object[i][1]);
        		book.setType((String) object[i][2]);
        		book.setAuthor((String) object[i][3]);
        		book.setPrice(Double.valueOf(String.valueOf(object[i][4])));
        		book.setPubDate((String) object[i][5]);
        		book.setPublisher((String) object[i][6]);
        		book.setBuyDate((String) object[i][7]);
        		book.setReceiver((String) object[i][8]);
        		bks.add(book);
        	}
        	return bks;
        	
        }
        public String getColumnName(int column) {
        	return columns[column];
            }
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return object.length;
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columns.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			return object[rowIndex][columnIndex];
		}
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	    	return true;
	        }
	    
	    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	    	object[rowIndex][columnIndex]=aValue;
	    }
      }
      public static void main(String[] args)
      {
    	  new MainFrame();
      }
}
