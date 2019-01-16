
public class Jiecheng {

	/**
	 * @param args
	 */
	public static int digui(int n){
		if(n==1){
			return 1;
		}
		else{
			return n*digui(n-1);
		}
	}
	public static int feidigui(int n){
		int result=1;
		while(n!=1){
			result*=n;
			n--;
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(digui(10));
		System.out.println(feidigui(10));
	}

}
