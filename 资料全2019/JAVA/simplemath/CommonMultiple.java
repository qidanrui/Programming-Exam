package thu.hcguo.simplemath;

/**
 * 找两个数的最小公倍数
 */
public class CommonMultiple {
	public static void multiple(int num1, int num2) {
		int max = Math.max(num1, num2);
		for(int i = max; i < Integer.MAX_VALUE; i++) {
			if(i % num1 == 0 && i % num2 == 0) {
				System.out.println(i);
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		multiple(11, 13);
	}
}
