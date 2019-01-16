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
public class BascketBallSort
{
	// 篮子
	private String[] basckets;
	// 篮球
	private String[] balls;

	public BascketBallSort(int n)
	{
		// 初始化篮子
		basckets = new String[n];
		for (int i = 0; i < n; i++)
			basckets[i] = String.valueOf(i + 1);

		// 初始化篮球
		balls = new String[n];
		for (int i = 0; i < n; i++)
			balls[i] = String.valueOf(i + 1);

		// 对n个篮球进行全排列
		perm(balls, 0, balls.length - 1);
	}

	// 对n个篮球进行全排列，采用分治策略进行递归求解
	public void perm(String[] buf, int start, int end)
	{

		if (start == end)// 只有一个球进行全排列时，则不需要再递归
		{
			// 检查是否符合要求，如果符合要求，则输出
			if (check(buf))
			{
				for (int i = 0; i < buf.length; i++)
				{
					System.out.print(basckets[i] + "(" + buf[i] + ") ");
				}
				System.out.println();
			}
		}

		else
		{
			for (int i = start; i <= end; i++)
			{
				// 交换i与start元素的值
				String temp = buf[i];
				buf[i] = buf[start];
				buf[start] = temp;

				perm(buf, start + 1, end);// 递归求解球的全排列

				// 还原i与start元素的值
				temp = buf[i];
				buf[i] = buf[start];
				buf[start] = temp;
			}
		}
	}

	// 对每一个全排列结果进行检查，保证篮子号与球号不一样，并且球号不相邻
	public boolean check(String tempBalls[])
	{
		// 球号与篮子号不能相同
		for (int i = 0; i < tempBalls.length; i++)
		{
			if (tempBalls[i].equals(basckets[i]))
			{
				return false;
			}
		}

		// 相邻篮子内的球号不能相邻
		for (int i = 0; i < tempBalls.length - 1; i++)
		{
			if (Math.abs(Integer.parseInt(tempBalls[i]) - Integer.parseInt(tempBalls[i + 1])) == 1)
				return false;
		}
		return true;
	}
}
