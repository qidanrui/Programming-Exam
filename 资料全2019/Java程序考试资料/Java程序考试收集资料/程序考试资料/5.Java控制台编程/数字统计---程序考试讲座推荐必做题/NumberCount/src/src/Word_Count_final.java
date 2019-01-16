package src;

import java.util.*;

public class Word_Count_final
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		/*输入数字*/
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入一些数字，用空格隔开，回车结束：");
		String input = scanner.nextLine();

		/*进行分词*/
		String[] number = input.split(" ");

		/*计算最大最小值和平均值*/
		int max = Integer.parseInt(number[0]);
		int min = Integer.parseInt(number[0]);
		int sum = 0;
		for (int i = 0; i < number.length; i++)
		{
			sum = sum + Integer.parseInt(number[i]);
			if (Integer.parseInt(number[i]) > max)
				max = Integer.parseInt(number[i]);
			if (Integer.parseInt(number[i]) < min)
				min = Integer.parseInt(number[i]);
		}
		double average = sum / number.length;		
		System.out.println("最大值等于：" + max);
		System.out.println("最小值等于：" + min);
		System.out.println("平均值等于：" + average);

		
		/*计算方差*/
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
