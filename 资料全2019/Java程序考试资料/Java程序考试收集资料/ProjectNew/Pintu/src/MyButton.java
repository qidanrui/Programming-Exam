import javax.swing.JButton;


public class MyButton extends JButton{
	private int row;
	private int col;
	private String value;
    public MyButton(int row,int col)
    {
    	this.setRow(row);
    	this.setCol(col);
    	setValue("");
    }
	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * @return the col
	 */
	public int getCol() {
		return col;
	}
	/**
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
    
}
