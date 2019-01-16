package src;

import java.io.*;

public class ScoreCount_1
{

	public static void main(String[] args) throws Exception
	{
		/*利用文件输入流将文件Score.txt中的数据读取到字节数组buf中*/
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Yuchao.Yuchao-PC\\Desktop\\编程考试\\1.Java控制台编程\\成绩统计\\Score.txt");
		byte[] buf = new byte[100];
		int len = fis.read(buf);

		/*利用字节数组buf来构造字符串数组score*/
		String input = new String(buf, 0, len);//这个构造函数很关键！
		
		/*对字符串数组score按照分隔符进行分词*/
		String[] score = input.split("(\\s)+");

		/*计算最高分最低分和去掉最高分和最低分的平均成绩*/
		int max = Integer.parseInt(score[0]);
		int min = Integer.parseInt(score[0]);
		int sum = 0;
		for (int i = 0; i < score.length; i++)
		{
			if (Integer.parseInt(score[i]) > max)
				max = Integer.parseInt(score[i]);
			if (Integer.parseInt(score[i]) < min)
				min = Integer.parseInt(score[i]);
			sum = sum + Integer.parseInt(score[i]);
		}
		double average = (double)(sum - max - min)/(score.length-2);//平均成绩必须强制转换成double型，否则丢失精度		

		/*打印出结果*/
		System.out.println("最高分："+max);
		System.out.println("最低分："+min);
		
		System.out.println("去掉最高分和最低分的平均成绩："+average);
		
		/*关闭文件输入流*/
		fis.close();
	}
}
