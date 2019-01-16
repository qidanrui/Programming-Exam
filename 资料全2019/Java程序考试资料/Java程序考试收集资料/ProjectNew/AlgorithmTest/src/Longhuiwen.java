import java.util.Scanner;

/**
 * 最长回文子序列（奇数）
 * @author sinllychen
 *
 */
public class Longhuiwen {
	 private static String longest="";
	 private static boolean checkHuiWen(String s)
	 {
		boolean flag=true;
		for(int i=0;i<s.length()/2;i++){
		    if(s.charAt(i)!=s.charAt(s.length()-i-1))
		    {
		    	flag=false;
		    	break;
		    }
		}
		return flag;
	 }
	 
	 private static void computeHuiWen(String s)
	 {
		 for(int i=0;i<s.length();i++)
		 {
			 for(int j=i;j<s.length();j++)
			 {
				 String temp=s.substring(i,j+1);
				 if(checkHuiWen(temp))
				 {
					 if(temp.length()>longest.length()&&temp.length()%2!=0)
						 longest=temp;
				 }
			 }
		 }
	 }
     public static void main(String[] args)
     {
    	 Scanner input=new Scanner(System.in);
    	 System.out.println("请输入字符串:");
    	 String str=input.next();
    	 computeHuiWen(str);
    	 if(longest.equals(""))
    		 System.out.println("该字符串中不存在奇数长度的回文");
    	 else
    		 System.out.println("最长奇数回文为:"+longest);
     }
}
