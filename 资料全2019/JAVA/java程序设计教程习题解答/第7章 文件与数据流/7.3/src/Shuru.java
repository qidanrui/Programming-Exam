import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Shuru {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("请输入第一个整数：");
			int a = Integer.parseInt(bf.readLine());
		
			System.out.print("请输入第二个整数：");
			int b = Integer.parseInt(bf.readLine());
			
			System.out.println("计算结果："+a+"+"+b+"="+(a+b));
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
