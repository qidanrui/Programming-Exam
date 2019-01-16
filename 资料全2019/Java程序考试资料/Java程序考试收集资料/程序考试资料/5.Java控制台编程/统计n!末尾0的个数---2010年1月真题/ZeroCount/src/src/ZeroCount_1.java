package src;

import java.util.*;

public class ZeroCount_1
{

	public static int countzero(int st, int dt)
	{
		int count = 0;
		int i;
		for (i = st; i <= dt; i++)
		{
			int temp = i;
			while (temp % 5 == 0)
			{
				++count;
				temp /= 5;
			}
		}
		return count;
	}

	public static void main(String[] args)
	{
		System.out.print("请输入正整数N=");
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();

		System.out.println(countzero(1, n));

	}

}
