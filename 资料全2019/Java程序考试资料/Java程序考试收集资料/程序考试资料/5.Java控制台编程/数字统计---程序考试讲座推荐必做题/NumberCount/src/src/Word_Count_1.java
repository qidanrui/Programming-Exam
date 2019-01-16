package src;

import java.util.*;

public class Word_Count_1
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

		int sum = 0;
		for (int i = 0; i < number.length; i++)
		{
			sum = sum + Integer.parseInt(number[i]);
		}
		double average = sum / number.length;
		System.out.println("平均值等于：" + average);

		double total = 0;
		for (int j = 0; j < number.length; j++)
		{
			total = total + (Integer.parseInt(number[j]) - average)
					* (Integer.parseInt(number[j]) - average);
		}
		double deviation = total / number.length;
		System.out.println("方差等于："+ deviation);
	}

}
