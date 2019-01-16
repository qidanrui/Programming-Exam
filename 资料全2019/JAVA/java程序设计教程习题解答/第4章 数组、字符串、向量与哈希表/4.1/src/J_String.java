
public class J_String {

	/**
	 * @param args
	 */
	public static void mb_operate(String x,String y){
		x.concat(y);
		y=x;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = "A";
		String b = "B";
		mb_operate(a,b);
		System.out.println(a+"."+b);
	}

}
