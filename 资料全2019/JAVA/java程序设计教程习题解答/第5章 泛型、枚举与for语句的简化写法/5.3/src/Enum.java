enum Week {星期一,星期二,星期三,星期四,星期五,星期六,星期日}
public class Enum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Week [] w = Week.values();
		for(int i=0;i<w.length;i++){
			System.out.print(" "+w[i].name());
		}
			
	}

}
