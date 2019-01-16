
public class J_Test {
	
	public static void mb_createException(){
		throw new ArrayIndexOutOfBoundsException();
	}
	
	public static void mb_method(){
		try {
			mb_createException();
			System.out.print("a");
		}
		catch(ArithmeticException e){
			System.err.print("b");
		}
		finally {
			System.out.print("c");
		}
	}
	
	public static void main(String args []){
		try {
			mb_method();
		}
		catch(Exception e){
			System.err.print('m');
		}
		System.out.print('n');
	}
}
