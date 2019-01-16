
public class Sushuhe {

	/**
	 * @param args
	 */
	public static boolean sushu(int x){
		int num =x;
		for (int i=2;i<num/2+1;i++){
			if(num%i==0){
				
				return false;
			}
			
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int result = 0;
		for(int i=2;i<101;i++){
			if(sushu(i)==true){
				System.out.println(i);
				result += i;
			}
		}
		System.out.println(result);
	}

}
