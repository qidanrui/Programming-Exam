
public class Han {

	/**
	 * @param args
	 */
	
	public static int calculate(int n){
		if(n<=1){
			return 1;
		}
		else {
			return calculate(n-1)*2+1;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(calculate(10));
	}

}
