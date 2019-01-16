import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Sushu {

	/**
	 * @param args
	 */
	static boolean isSushu(int k){
		switch (k) {
		case 1 :  return false;
					
		case 2 :  return true;
		
		case 3 : return true;
		
		default : 	for(int i=2;i<k/2+1;i++){	
						if(k%i==0){
							return false;
						}
						
					}
					return true;
		
		}
		
	}
	
	public static void main(String[] args) {
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n =Integer.parseInt(br.readLine()) ;
			//System.out.println(""+n);
			ArrayList<Integer> al = new ArrayList<Integer>();
			
			
			for(int i = 2;i<n;i++){
				if(isSushu(i)){
					al.add(i);
				}
			}	
				
			
			
			
			al.trimToSize();
			
			int []result = new int [10];
			for (int i=0;i<al.size();i++){
				int a = al.get(i);
				while(a/10!=0){
					result[a%10]++;
					a=a/10;
				}
				result[a%10]++;
			}
			
			for(int i=0;i<10;i++){
				System.out.println(""+i+":"+result[i]);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
