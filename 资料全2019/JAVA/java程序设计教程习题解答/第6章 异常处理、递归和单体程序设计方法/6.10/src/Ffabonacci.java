
public class Ffabonacci {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 6;
		int a=1;
		int b=1;
		int c = 0;
		
		for(int i=3;i<=n;i++){
			c=a+b;
			a=b;
			b=c;
		}
		System.out.print(c);
	}

}
