package src;

import java.math.*;

public class BigIntegerDemo
{

	public static void testBigInteger()
	{
		BigInteger bi = new BigInteger("888");
		BigInteger result = bi.multiply(new BigInteger("2"));//整数乘法
		System.out.println(result);

		result = bi.divide(new BigInteger("2"));//整数除法
		System.out.println(result);

		result = bi.add(new BigInteger("232"));//整数加法
		System.out.println(result);

		result = bi.subtract(new BigInteger("23122"));//整数减法
		System.out.println(result);

		result = bi.shiftRight(10);//移位
		System.out.println(result);

	}

	public static void testBigDecimal()
	{
		BigDecimal bi = new BigDecimal("888.888");
		BigDecimal result = bi.multiply(new BigDecimal("2.3"));//小数乘法
		System.out.println(result);

		result = bi.divide(new BigDecimal("90.4"), 2);//小数除法
		System.out.println(result);

		result = bi.add(new BigDecimal("232.34"));//小数加法
		System.out.println(result);

		result = bi.subtract(new BigDecimal("343.3434"));//小数减法
		System.out.println(result);

	}

	public static void main(String[] args)
	{
		System.out.println("大整数整数运算：");
		testBigInteger();
		System.out.println("");
		System.out.println("大整数小数运算：");
		testBigDecimal();
	}

}