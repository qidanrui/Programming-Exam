package thu.hcguo.simplemath;

/**
 * 找两个数的最大公因子
 */
public class CommonDivisor {
	public static void divisor(int num1, int num2) {
		int min = Math.min(num1, num2);
		for(int i = min; i >= 1; i--) {
			if(num1 % i == 0 && num2 % i == 0) {
				System.out.println(i);
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		divisor(12, 18);
	}
}
