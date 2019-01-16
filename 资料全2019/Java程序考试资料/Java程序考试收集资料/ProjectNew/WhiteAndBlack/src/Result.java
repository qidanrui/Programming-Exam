import java.io.Serializable;

/**
 * 存档结果
 * @author sinllychen
 *
 */
public class Result implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private ChessButton[][] buttons;  //棋盘
    private boolean black;//玩家状态
    public Result(){};
    public Result(ChessButton[][] buttons,boolean black)
    {
    	this.setButtons(buttons);
    	this.black=black;
    }
	/**
	 * @return the buttons
	 */
	public ChessButton[][] getButtons() {
		return buttons;
	}
	/**
	 * @param buttons the buttons to set
	 */
	public void setButtons(ChessButton[][] buttons) {
		this.buttons = buttons;
	}
	/**
	 * @return the black
	 */
	public boolean isBlack() {
		return black;
	}
	/**
	 * @param black the black to set
	 */
	public void setBlack(boolean black) {
		this.black = black;
	}
}
