package src;

import java.util.*;

public class Word_Count_2
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入一些数字，用空格隔开，回车结束：");
		String input = scanner.nextLine();

		String[] number = input.split(" ");

		int max = 0;
		int min = 0;

		for (int i = 0; i < number.length; i++)
		{
			if (Integer.parseInt(number[i]) > max)
				max = Integer.parseInt(number[i]);
			if (Integer.parseInt(number[i]) < min)
				min = Integer.parseInt(number[i]);

		}

		System.out.println("最大值等于：" + max);
		System.out.println("最小值等于：" + min);
	}

}
