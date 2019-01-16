package src;

/**
 * 清华大学软件学院程序设计语言考试练习题 题目:请编写程序求解篮球错排问题。已知n个篮子一字排开（n为用户输入的
 * 任意正整数），从左到右分别标着号：1，2，... ...，n；每个球也有 编号，分别也是1，2，... ...，n。现要将这n个球全部放入这n个篮子
 * 中，满足：每个篮子放置1个球，球的号不能与其所在的篮子的号相同 ，且在相邻篮子内的球的球号不能相邻。例如，如果在相邻两个篮子内
 * 的球的球号分别为9和10，则是不允许的。请输出所有符合要求的放球 方式（对于每种符合要求的放球方式，都应输出在每个篮子中的球号）。
 * 
 * @author THSS MSE08 HUANGBIN
 * 
 */
public class Demo
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		new BascketBallSort(5);// 参数为用户输入的n
	}

}
