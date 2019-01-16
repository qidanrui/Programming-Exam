package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;


/**
 * Created with IntelliJ IDEA.
 * User: Lusar
 * Date: 12-8-9
 * To change this template use File | Settings | File Templates.
 */
public class Test1 {
	
    public static void main(String args[]) throws IOException{
//    	testReadFromConsole();
//    	testBigIntegerOpt();
//    	testGcdByBruteForce();
//    	testStringSplit();
    }
    
    //Test-1 从控制台读取数据
    public static void testReadFromConsole(){
    	Scanner scanner = new Scanner(System.in);
    	int 		i 		  = scanner.nextInt();	//读取Int类型
    	String 		str 	  = scanner.next();		//读取String类型...
    	double 		dtest 	  = scanner.nextDouble();
    	BigInteger 	bgIntTest = scanner.nextBigInteger();
    	
    	System.out.println("int:" + i);
    	System.out.println("str:" + str);
    	System.out.println("dob:" + dtest);
    	System.out.println("bint:" + bgIntTest);
    }
  
    //Test-2 从文本文件读取数据
    public static void testReadFromFile() throws IOException{
    	Scanner scanner = new Scanner(new FileInputStream("E:\\课件\\Java程序考试辅导\\test.txt"));
    	int 		i 		  = scanner.nextInt();	//读取Int类型
    	String 		str 	  = scanner.next();		//读取String类型...
    	double 		dtest 	  = scanner.nextDouble();
    	BigInteger 	bgIntTest = scanner.nextBigInteger();
    	
    	System.out.println("int:" + i);
    	System.out.println("str:" + str);
    	System.out.println("dob:" + dtest);
    	System.out.println("bint:" + bgIntTest);
    }
    
    
    //Test-3 大数的操作
    public static void testBigIntegerOpt(){
    	Scanner scanner = new Scanner(System.in);
    	BigInteger big1 = scanner.nextBigInteger();
    	BigInteger big2 = scanner.nextBigInteger();
    	BigInteger big3 = big1.add(big2);
    	System.out.println(big3);
    }
    
    //Test-4 暴力方法之求解最大公约数
    public static void testGcdByBruteForce(){
    	Scanner scanner = new Scanner(System.in);
    	int x = scanner.nextInt();
    	int y = scanner.nextInt();
    	for(int i = Math.min(x, y); i > 0; i--){
    		if(((x % i) == 0) &&
    				((y % i) == 0)){
    			System.out.println("gcd = " + i);
    			break;
    		}
    	}
    }
    //Test-5 test string split.
    public static void testStringSplit(){
    	Scanner scanner = new Scanner(System.in);
    	scanner.useDelimiter(" |,|\\.");	//空格，逗号，点号，进行作为读取分割符.
    	String  content  = scanner.nextLine();
    	
    	for(String sub : content.split(" ")){
    		System.out.println(sub);
    	}
    }
}
