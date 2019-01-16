import java.math.BigInteger;

public class LongNum {
	public static void main(String [] args){
		
		BigInteger result=new BigInteger("0");
		
		for(int i=1;i<21;i++){
			result=result.add(BigInteger.valueOf((long) Math.pow(i, i)));
		}
		System.out.print(result);
		
	
		
	}
}
