enum Month {January,February,March,April,May,June,July,
	Autumn,September,October,November,December} 

public class Year {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] d = {31,29,31,30,31,30,31,31,30,31,30,31};
		Month [] m = Month.values();
		for(int i=0;i<m.length;i++){
			System.out.println(" "+m[i].name()+": "+d[i]);
		}
	}

}
