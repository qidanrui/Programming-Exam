package src;

import java.math.*;
import java.util.*;

public class NPower
{

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		System.out.print("请输入正整数n：");
		Integer n = input.nextInt();

		BigInteger result = new BigInteger(n.toString());

		for (int i = 0; i < n; i++)
		{
			result = result.multiply(new BigInteger(n.toString()));// 整数乘法
		}
		
		 BigInteger temp =  result.mod(new BigInteger("1000")); 
         /*Returns a BigInteger whose value is (this mod m).*/ 
		
		System.out.println(result);//N的N次方
		System.out.println(temp);//N的N次方的末三位

	}

}
