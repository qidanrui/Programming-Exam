import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 求几个数的最大公约数
 * @author sinllychen
 *
 */
public class MaxGongyueshu {
        public static void main(String[] args)
        {
        	Scanner input=new Scanner(System.in);
        	System.out.println("请输入数,根据空格符分隔开以quit结束:");
        	List<Integer> num=new ArrayList<Integer>();
        	while(true)
        	{
        		 try
        		 {
        	     num.add(input.nextInt());	
        		 }
        		 catch(Exception e)
        		 {
        			 break;
        		 }
        	}
            int min=num.get(0);
        	for(int i=0;i<num.size();i++)
        	{
        		if(num.get(i)<min)
        			min=num.get(i);
        	}
            int n=0;
        	for(int i=1;i<=min;i++)
        	{
        		boolean flag=true;
        		for(int a:num)
        		{
        			if(a%i!=0)
        				flag=false;
        		}
        		if(flag)
        			n=i;
        	}
           System.out.println("最大公约数为:"+n);
        }
}
