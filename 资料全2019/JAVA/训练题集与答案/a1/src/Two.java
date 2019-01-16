import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Two {

	public static void main(String[] args) {

		int n = getN();
		int ways = calculateSteps(n);

		System.out.println("总共可能的步数：" + ways);
	}

	// 获得用户输入的N
	public static int getN() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = new String();
		try {
			s = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("读取错误");
			System.exit(1);
		}

		int n = Integer.parseInt(s);
		if (n < 1) {
			System.out.println("n输入错误");
			System.exit(1);
		}

		return n;
	}

	// 计算可能的步数
	public static int calculateSteps(int n) {

		if(n<0){
			return 0;
		}
		
		if (n == 1) {
			return 1;
		} else if (n == 0) {
			return 1;
		}

		return calculateSteps(n - 1) + calculateSteps(n - 2);
	}
}
