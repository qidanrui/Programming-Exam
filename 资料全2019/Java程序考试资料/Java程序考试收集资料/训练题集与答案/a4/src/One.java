import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class One {

	public static void main(String[] args) {
		while(true){
			System.out.println(fibonacci(getN()));
		}
		
	}

	public static BigInteger fibonacci(int n) {

		BigInteger prev = new BigInteger("1");
		BigInteger sum = new BigInteger("1");
		
		
		for(int i =3;i<=n;++i){
			BigInteger temp = BigInteger.ZERO.add(sum);
			sum = prev.add(sum);
			prev = temp;
		}
		
		return sum;
		
	}

	//获得用户输入的数
	public static int getN() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = 0;
		try {
			String s = br.readLine();
			n = Integer.parseInt(s);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return n;
	}
}
