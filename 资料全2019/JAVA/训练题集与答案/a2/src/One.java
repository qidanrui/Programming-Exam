import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class One {

	public static void main(String[] args) {
		generate(getN());
	}

	public static void generate(int n) {
		System.out.println("----------------");
		Random r = new Random();
		int range = 10;
		int[] count = new int[range]; //计数器，用来记录1-100之间的每个数出现的次数
		int maxCount = 0;
		for (int i = 0; i < n; ++i) {
			int index = r.nextInt(range);
			System.out.println(index);
			++count[index];

			if (maxCount < count[index]) { //记录最大的出现次数
				maxCount = count[index];
			}
		}
		System.out.println("----------------");

		for (int value = 0; value < range; ++value) {
			if (count[value] == maxCount) { //判断某个数出现的次数等于所有数中出现的最大次数
				System.out.println(value + "  |   count:" + count[value]);
			}
		}

	}

	//获得用户输入的数
	public static int getN() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = 0;
		try {
			String s = br.readLine();
			n = Integer.parseInt(s);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return n;
	}
}
