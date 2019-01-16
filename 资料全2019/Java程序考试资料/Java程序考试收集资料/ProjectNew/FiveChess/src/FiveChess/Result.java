package FiveChess;

import java.io.Serializable;

public class Result  implements Serializable{
       private ChessButton[][] chesses;
       private boolean black;
       public Result(){};
	/**
	 * @return the chesses
	 */
	public ChessButton[][] getChesses() {
		return chesses;
	}
	/**
	 * @param chesses the chesses to set
	 */
	public void setChesses(ChessButton[][] chesses) {
		this.chesses = chesses;
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
