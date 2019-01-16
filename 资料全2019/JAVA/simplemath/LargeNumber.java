package thu.hcguo.simplemath;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 大数据运算处理
 */
public class LargeNumber {
	/**
	 * 大数加，减，乘，除
	 * @param big1
	 * @param big2
	 */
	public static void calculate(BigDecimal big1, BigDecimal big2) {
		System.out.println(big1.add(big2));
		System.out.println(big1.subtract(big2));
		System.out.println(big1.multiply(big2));
		System.out.println(big1.divide(new BigDecimal("2.5")));
	}
	
	/**
	 * 利用大数运算求阶乘
	 * @param num
	 */
	public static void factorial(int num) {
		BigInteger res = new BigInteger("1");
		for(int i = 1; i <= num; i++) {
			res = res.multiply(new BigInteger(""+i));
		}
		System.out.println(res);
	}
	
	public static void main(String[] args) {
		BigDecimal big1 = new BigDecimal("54646548787841321549879856135132154");
		BigDecimal big2 = new BigDecimal("1234123412341235645736756");
		calculate(big1, big2);
		factorial(35);
	}
}
