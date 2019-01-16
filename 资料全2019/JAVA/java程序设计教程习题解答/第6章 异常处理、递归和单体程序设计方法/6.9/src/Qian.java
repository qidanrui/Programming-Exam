
public class Qian {

	/**
	 * @param args
	 */
	public static void cal(int n){
		for(int i=0;i<=n;i++)
			for(int j=0;j<=n;j++)
				for(int k=0;k<=n;k++){
					if(i*5+j*2+k*1==n)
						System.out.println("5:"+i+" 2:"+j+" 1:"+k);
				}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		cal(5);
	}

}
