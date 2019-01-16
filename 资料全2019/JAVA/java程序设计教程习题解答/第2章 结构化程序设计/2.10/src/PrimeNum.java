
public class PrimeNum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] num = new int [51] ;
		for(int i=0;i<51;i++){
			num[i]=i+50;
		}
		for(int i=2;i<51;i++){
			for(int j=0;j<51;j++){
				if(num[j]%i==0){
					num[j]=0;
				}
			}
		}
		System.out.print("Primes:");
		for(int i=0;i<51;i++){
			if(num[i]!=0){
				System.out.print(Integer.toString(num[i])+" ");
			}
		}
		
				
	}

}
