package src;

public class Combination
{
	/**
	 * 使用非递归求解n的阶乘
	 * 
	 * @param n 被计算的阶乘
	 * @return 返回n的阶乘计算出来的结果
	 */
	public static int doFactorial(int n)
	{
		int result = 1;// 结果
		if (n < 0)
		{// 传入的n不合法
			return -1;// 返回-1，说明参数不合法
		}
		if (n == 0)
		{// 0!=1
			return 1;
		}

		for (int i = 1; i <= n; i++)
		{// 从1~n相乘
			result *= i;
		}
		return result;// 返回结果
	}

	/**
	 * 组合生成算法C(n,r)
	 * 
	 * @param n
	 * @param r
	 */
	public static void combination_n_r(int n, int r)
	{
		int c_n_r = doFactorial(n) / (doFactorial(r) * doFactorial(n - r));// 计算组合的个数
																			// C(n,r)=n!/(r!*(n-r)!)
		System.out.println("共有" + c_n_r + "种组合：");

		if (c_n_r != 0)// 当输入合法，组合存在时
		{

			int[] s = new int[r + 1];
			for (int i = 1; i <= r; i++)
			{
				s[i] = i;
			}

			for (int i = 1; i <= r; i++)
			{
				System.out.print(s[i]);// 打印第一个组合

			}
			System.out.println();// 换行

			for (int i = 2; i <= c_n_r; i++)
			{
				int m = r;// ////////////
				int max_val = n;// ////////
				while (s[m] == max_val)
				{
					// 从右向左找到第一个非最大值的元素
					m--;
					max_val--;
				}

				if (m == 0)
				{
					System.out.println("!!!!!!!!!!!!!!!");
					return;

				}

				// 将从右向左第一个非最大值的元素加1
				s[m] = s[m] + 1;

				// s[m]之后的元素依次递增
				for (int j = m + 1; j <= r; j++)
					s[j] = s[j - 1] + 1;

				for (int k = 1; k <= r; k++)
					System.out.print(s[k]);// 打印第个组合
				System.out.println();// 换行
			}
		}

	}

	public static void main(String[] args)
	{
		combination_n_r(4, 2);

	}
}
