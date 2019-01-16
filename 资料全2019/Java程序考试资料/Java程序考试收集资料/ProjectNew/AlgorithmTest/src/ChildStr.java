import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 求一个字符串的子集，先求出二进制。根据二进制获得子集
 * @author sinllychen
 *
 */
public class ChildStr {
	  private static  List<String> result=new ArrayList<String>();
	  private static  List<String> binaryStr=new ArrayList<String>();
	  public static void main(String[] args)
	  {
		  System.out.println("请输入字符串:");
		  Scanner input=new Scanner(System.in);
		  String str=input.next();
		  char[] s=str.toCharArray();
		  int m=(int) Math.pow(2, str.length())-1;
		  for(int i=1;i<=m;i++)
		  {
			  StringBuffer buf=new StringBuffer(Integer.toBinaryString(i));
			  int length=buf.length();
			  int sub=str.length()-length;
			  while(sub>0)
			  {
				  buf.insert(0, "0");
				  sub--;
			  }
			  System.out.println(buf.toString());
			  binaryStr.add(buf.toString());
		  }
		  for(int i=0;i<binaryStr.size();i++)
		  {
			  char[] bir=binaryStr.get(i).toCharArray();
			  String temp="";
			  for(int j=0;j<str.length();j++)
			  {
				  if(bir[j]=='1')
					  temp=temp.concat(String.valueOf(s[j]));
			  }
			  result.add(temp);
		  }
		  System.out.println("共有"+result.size()+"非空子集");
		  for(int i=0;i<result.size();i++)
		  {
			  System.out.println(result.get(i));
		  }
	  }
	
}
