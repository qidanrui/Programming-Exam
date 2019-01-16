import java.awt.BorderLayout; 
import java.awt.Color; 
import java.awt.Container; 
import java.awt.Dimension; 
import java.awt.FlowLayout; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JPanel; 
import javax.swing.JScrollPane; 
import javax.swing.JTable; 
import javax.swing.table.AbstractTableModel; 

/** 
* 表格示例，主要是演示 使用表格模型提供数据 
* @author JingKang 
*/ 
public class TestJTable extends JFrame{ 

/** 
* 
*/ 
private static final long serialVersionUID = 1L; 
private MyTable mt ; 
private JTable table ; 
private JScrollPane sc; 
private Container cn ; 
private JLabel lblStatus ; 
private JPanel pStatus ; 

public TestJTable(){ 
super("简单表格"); 
init() ; 
} 

private void init(){ 
cn = getContentPane(); 
mt = new MyTable(); 
table=new JTable(mt) ; 
//设置此表视口的首选大小。 
table.setPreferredScrollableViewportSize(new Dimension(400,80)); 
//单元格之间间距的高度和宽度 
table.setIntercellSpacing(new Dimension(1,1)); 
table.setGridColor(Color.RED); 
table.setForeground(Color.BLUE); 

table.setAutoCreateRowSorter(true); 

sc=new JScrollPane(table) ; 
cn.setLayout(new BorderLayout()); 
cn.add(sc,BorderLayout.CENTER); 

lblStatus=new JLabel("选择单元格为："); 
pStatus=new JPanel(new FlowLayout()); 
pStatus.add(lblStatus,FlowLayout.LEFT); 

cn.add(pStatus,BorderLayout.SOUTH); 

this.setVisible(true); 
this.pack(); 
this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
} 

public static void main(String[] args) { 
new TestJTable(); 
} 
} 

class MyTable extends AbstractTableModel{ 
/** 
* 
*/ 
private static final long serialVersionUID = 1L; 

private String[] columns  ={"姓名","性别","住址"}; 

private Object[][] datas = { 
{"小龙","男","长沙市"}, 
{"小花","女","北京市"} 
}; 

@Override 
public int getColumnCount() { 
return columns.length; 
} 

@Override 
public int getRowCount() { 
return datas.length; 
} 

/** 
* 功能：将获得单元格的内容显示在面板上 
*/ 
@Override 
public Object getValueAt(int rowIndex, int columnIndex) { 
return datas[rowIndex][columnIndex]; 
} 

/** 
* 获得单元格的列名 
*/ 
@Override 
public String getColumnName(int col){ 
return columns[col]; 
} 

/** 
* 功能：设定单元格为可编辑 
*/ 
@Override 
public boolean isCellEditable(int rowIndex,int colIndex){ 
return true ; 
} 

/** 
* 功能：将用户修改后的值赋给指定的单元格 
*/ 
@Override 
public void setValueAt(Object aValue, int rowIndex, int columnIndex){ 
datas[rowIndex][columnIndex]=aValue ; 
} 
} 