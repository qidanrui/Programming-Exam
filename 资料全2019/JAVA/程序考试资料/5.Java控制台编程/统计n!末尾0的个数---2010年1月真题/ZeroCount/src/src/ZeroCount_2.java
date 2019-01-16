package src;

import java.util.*;

public class ZeroCount_2
{
	/*
	 * 令f(x)表示x!末尾所含0的个数，则有： 当0 < n < 5时，f(n!) = 0; 当n >= 5时，f(n!) = k + f(k!),
	 * 其中 k = n / 5（取整）。
	 */
	public static int countzero(int n)
	{
		if (n < 5)
			return 0;
		else
		{
			int k = n / 5;
			return k + countzero(k);
		}
	}

	public static void main(String[] args)
	{
		System.out.print("请输入正整数n=");
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();

		System.out.println(countzero(n));
	}

}
