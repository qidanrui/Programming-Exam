import java.util.Scanner;

/**
 * 将一个字符串奇数和偶数位进行对调
 * @author sinllychen
 * @version 1.0
 *
 */
public class oddAndeven {
     public static void main(String[] args)
     {
    	 Scanner input=new Scanner(System.in);
    	 System.out.println("请输入字符串:");
    	 String temp=input.next();
    	 char[] temp1=temp.toCharArray();
    	 for(int i=0;i<temp.length();)
    	 {
    		 if(i+1<temp.length())
    		 {
    			 char t=temp1[i];
    			 temp1[i]=temp1[i+1];
    			 temp1[i+1]=t;
    		 }
    		 i=i+2;
    	 }
    	 System.out.println(String.valueOf(temp1));
     }
}
