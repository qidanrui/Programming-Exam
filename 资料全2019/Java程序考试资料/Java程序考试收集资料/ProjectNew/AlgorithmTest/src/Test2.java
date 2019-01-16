import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 编写一个类，实现简单的栈操作。数据的操作按照先进后出的顺序。成员函数为pop,push,size,full.empty.print等等
 * @author sinllychen
 *
 */
public class Test2 {
     public static void main(String[] args)
     {
    	 InputStreamReader reader=new InputStreamReader(System.in);
    	 BufferedReader input=new BufferedReader(reader);
    	 Stack<Integer> mystack=new Stack<Integer>();
    	 while(true)
    	 {
    		 try
    		 {
    			 System.out.println("请输入进栈数据(以输入字符串结束):");
    			 int n=Integer.parseInt(input.readLine());
    			 mystack.push(n);		 
    		 }
    		 catch(Exception p)
    		 {
    			 break;
    		 }
    	 }
    	 System.out.println("出栈数据:");
    	 while(!mystack.isEmpty())
    	 {
    		 System.out.print(mystack.pop()+"  ");
    	 }
     }
}
