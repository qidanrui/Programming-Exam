package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Ipower
{
	private static int n;

	private static BigInteger calculate(int n)
	{
		BigInteger result = BigInteger.valueOf(0);
		for (int i = 1; i <= n; i++)
		{
			BigInteger temp = BigInteger.valueOf(i).pow(i);
			result = result.add(temp);
		}
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("请输入n！");
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s = br.readLine();
			n = Integer.parseInt(s);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("计算结果是" + calculate(n));
	}

}
