
public class Cal {

	/**
	 * @param args
	 */
	static int result=0;
	public static int mb_fibonacci(int n){
		if(n<1){
			return 0;
		}
		else if(n==1||n==2){
			return 1;
		}
		return (mb_fibonacci(n-1)+mb_fibonacci(n-2));
	}
	public static int mb_calculate(int n){
		result++;
		if(n<1){
			return 0;
		}
		else if(n==1||n==2){
			return 1;
		}
		return (mb_calculate(n-1)+mb_calculate(n-2));		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mb_calculate(20);
		System.out.print("运算"+result+"次"+"值为"+mb_fibonacci(20));
	}

}
