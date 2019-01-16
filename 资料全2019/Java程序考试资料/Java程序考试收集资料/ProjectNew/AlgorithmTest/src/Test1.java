import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * 变成计算i的i次方的和，n为用户输入的任意整数(考虑结果可能超出长整数long的表示范围
 * @author sinllychen
 *
 */
public class Test1 {
	public static void main(String[] args)
	{
		InputStreamReader reader =new InputStreamReader(System.in);
		BufferedReader input=new BufferedReader(reader);
		while(true)
		{
			try{
				System.out.print("请输入一个数字(请以输入任意一个字符串结束)");
		    	int n=Integer.parseInt(input.readLine());
/*		    	System.out.println(new BigInteger("234567890").add(new BigInteger("1234567890")));*/
		    	BigInteger sum=new BigInteger("0");
		    	for(int i=1;i<=n;i++)
		    	{
		    		BigInteger a=new BigInteger(String.valueOf(i));
		    		sum=sum.add(a.pow(i));
		    	}
		    	System.out.println("sum="+sum.toString());
		    	
			}
			catch(Exception e)
			{
				break;
			}
		}
	    	
	}

}
