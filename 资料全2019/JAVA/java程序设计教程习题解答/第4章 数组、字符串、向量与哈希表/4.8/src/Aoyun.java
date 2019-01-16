
public class Aoyun {

	/**
	 * @param args
	 */
	int[] n = new int [10];
	
	public Aoyun(){
		for(int i=0;i<10;i++){
			n[i]=i;
		}
		
	}
	
	public boolean judge(){
		if(n[4]+10*n[3]+100*n[2]+1000*n[1]+10000*n[0]+20085==n[5]+10*n[6]+100*n[7]+1000*n[8]+10000*n[9]){
			return true;
		}
		else{
			return false;
		}
	}
	public void swap(int i,int j){
		int t =n[i];
		n[i] = n[j];
		n[j] = t;
	}
	
	
	public void perm(int num){
		if(num==1){
			if(judge()==true){
					for(int j=0;j<10;j++){
						System.out.print(" "+n[j]);
					}
					System.out.println();
				}
		}
		else{
			for(int i=0;i<num;i++){
				
				swap(i,num-1);
				perm(num-1);
				swap(i,num-1);
			}
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Aoyun a = new Aoyun();
		a.perm(10);
		
	}

}
