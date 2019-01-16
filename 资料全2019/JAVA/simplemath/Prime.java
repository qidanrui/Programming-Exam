package thu.hcguo.simplemath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 质数,除了1和本身外,不能被其他任何自然数整数的自然数。又叫做素数，最小的素数是2.
 */
public class Prime {
	public static boolean isPrime(int num) {
		for(int i = 2; i < num; i++) {
			if(num % i == 0) 
				return false;
		}
		return true;
	}
	
	/**
	 * 从控制台输入一个整数，找出小于该整数的所有质数
	 */
	public static void main(String[] args){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = 0;
		try {
			num = Integer.parseInt(br.readLine());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(int i = 1; i < num; i++) {
			if(isPrime(i))
				System.out.println(i);
		}
	}
}
