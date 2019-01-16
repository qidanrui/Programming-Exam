package src;
//输入一个字符串，统计其中有多少个单词？单词之间用空格分开

import java.util.Scanner;

public class WordCount_1
{
	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.print("请输入:");
		String sentence = input.nextLine();

		// 将输入的句子转化为单词数组
		String[] words = sentence.split(" ");// 这里单词用空格隔开，如果用标点隔开则不能判断了

		// 单词数组的长度就是单词数
		System.out.println("共有 " + words.length + " 个单词.");
	}
}