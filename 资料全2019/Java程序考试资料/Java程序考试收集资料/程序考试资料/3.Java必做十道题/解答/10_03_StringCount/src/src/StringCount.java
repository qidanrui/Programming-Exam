package src;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class StringCount
{

	/**
	 * 主函数
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		String ss = null;
		System.out.println("请输入字符串,以“.”结束！");
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String s = br.readLine();
			ss = s.substring(0, s.indexOf("."));// 注意substring的区间是左闭右开，所以ss中不包括"."
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		String[] temp = ss.split("#");
		int n = temp.length;
		System.out.println("字符串个数为" + n);

		int max = -1;
		int min = 100000000;
		String maxS = null, minS = null;

		for (int i = 0; i < n; i++)
		{
			if (temp[i].length() > max)
			{
				max = temp[i].length();
				maxS = temp[i];
			}
			if (temp[i].length() < min)
			{
				min = temp[i].length();
				minS = temp[i];
			}
		}

		System.out.println("最长字符串为" + maxS + "其个数为" + max);
		System.out.println("最短字符串为" + minS + "其个数为" + min);

		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < n; i++)
		{
			list.add(temp[i]);
		}

		Collections.sort(list);

		try
		{
			FileWriter fw = new FileWriter("3.txt");
			for (int i = 0; i < n; i++)
			{
				fw.write(list.get(i) + "\n");// 将排好序的字符串序列写入文件
			}
			fw.write("最长字符串为" + maxS + "其个数为" + max);
			fw.write("最短字符串为" + minS + "其个数为" + min);
			fw.write("字符串个数为" + n);
			fw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
