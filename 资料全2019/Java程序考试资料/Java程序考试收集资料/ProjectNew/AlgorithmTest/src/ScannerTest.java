import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;


public class ScannerTest {
    public static void main(String[] args) throws IOException
    {
    	Scanner scanner=new Scanner(System.in);   //Scanner读取普通控制台输入字符串
    	System.out.println("请输入你的姓名:");
    	String name=scanner.next();
    	System.out.println("请输入你的学号和年龄:");
    	int num=scanner.nextInt();
    	int age=scanner.nextInt();
    	System.out.println("姓名:"+name+","+"学号:"+num+","+"年龄:"+age);
    	
    	System.out.println("请输入你的电话号码和qq用逗号隔开:");//Scanner读取行，在每个next之后需要nextLine将指针切换到下一行
    	scanner.nextLine();
    	String temp=scanner.nextLine();
    	String[] t3=temp.split(",");
    	int tel=Integer.valueOf(t3[0]);
    	int qq=Integer.valueOf(t3[1]);
    	System.out.println("联系方式和号码分别为:"+tel+","+qq);
    	
    	System.out.println("2请输入你的电话号码和qq用逗号隔开:");//BufferedReader输入，主要是逐行读取
        BufferedReader input=new BufferedReader(new InputStreamReader(System.in));
        String temp2;
		try {
			temp2 = input.readLine();
			  String t2[]=temp2.split(",");
		    	int tel2=Integer.valueOf(t2[0]);
		    	int qq2=Integer.valueOf(t2[1]);
		    	System.out.println("联系方式和号码分别为:"+tel2+","+qq2);
		    	System.out.println("输入学号");
		    	String n2=input.readLine();
		    	System.out.println(n2);
		    	//scanner.nextLine();//此位置将指针切换到下一行（需要注意处）
		        System.out.println("输入名字和年龄");
		       String s=input.readLine();
		       System.out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	System.out.println("请输入数组，其中第一行为数组的size:");
    	int n=0;
    	//Scanner读取控制台输入的数组
    	int  t;
    	t=scanner.nextInt();
    	String[] a = new String[t];
    	scanner.nextLine();
    	for(int i=0;i<t;i++)
    	{
    		if(scanner.hasNext())
    		{
    			a[i]=scanner.nextLine();
    		}
    	}
      System.out.println(t);
//    	for(int i=0;i<a.length;i++)
//    		System.out.println(a[i]);
//    	System.out.println("输入学号");
//    	int n2=scanner.nextInt();
//    	System.out.println(n2);
//    	scanner.nextLine();//此位置将指针切换到下一行（需要注意处）
//    System.out.println("输入名字和年龄");
//    String s=scanner.nextLine();
//    System.out.println(s);
    }
}
