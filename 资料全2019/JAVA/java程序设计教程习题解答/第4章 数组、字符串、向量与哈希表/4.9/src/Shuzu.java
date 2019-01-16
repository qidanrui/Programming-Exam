
public class Shuzu {

	/**
	 * @param args
	 */
	
	int [] n;
	int c=0;
	
	public Shuzu(){
		n=new int [9];
		for(int i=0;i<9;i++){
			n[i]=i+1;
		}
	}
	
	public boolean check(){
		if(n[0]+n[1]+n[2]!=15){return false;}
		else if (n[3]+n[4]+n[5]!=15){return false;}
		else if (n[6]+n[7]+n[8]!=15){return false;}
		
		else if (n[0]+n[3]+n[6]!=15){return false;}
		else if (n[1]+n[4]+n[7]!=15){return false;}
		else if (n[2]+n[5]+n[8]!=15){return false;}
		
		else if (n[0]+n[4]+n[8]!=15){return false;}
		else if (n[2]+n[4]+n[6]!=15){return false;}
		else return true;
	}
	
	public void swap(int n1,int n2){
		int temp;
		temp=n[n1];
		n[n1]=n[n2];
		n[n2]=temp;
	}
	
	public void perm(int num){
		if(num==1){
			if(check()==true){
				
				for(int i1=0;i1<9;i1++){
					System.out.print(" "+n[i1]);
				}
				System.out.println(" " + ++c);
			}
		}
		else {
			
			for(int i=0;i<num;i++){
				
				swap(i,num-1);
				perm(num-1);
				swap(i,num-1);
			}
			
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Shuzu s = new Shuzu();
		s.perm(9);
			
		
		
		
	}

}
