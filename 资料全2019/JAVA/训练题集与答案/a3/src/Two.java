import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Two {

	public static void main(String[] args) throws IOException {
		// ArrayList<Integer> test = readFile();
		// System.out.println(listToString(test));

		process(readFile());
	}

	public static ArrayList<Integer> readFile() throws IOException {
		File f = new File("data.txt");
		BufferedReader br = new BufferedReader(new FileReader(f));

		String s = br.readLine();

		String[] line = s.split(" ");
		ArrayList<Integer> nums = new ArrayList<Integer>();

		for (String unit : line) {

			int value = 0;
			try {
				value = Integer.parseInt(unit);
			} catch (Exception e) {
				continue;
			}
			nums.add(value);
		}

		return nums;
	}

	public static ArrayList<ArrayList<Integer>> resultSet = new ArrayList<ArrayList<Integer>>();

	public static void process(ArrayList<Integer> nums) {
		int[] a = new int[nums.size()];

		System.out.println("所有数和为" + isSumEven(nums));

		generate(nums, a, nums.size(), 0, isSumEven(nums));
	}

	public static boolean isSumEven(ArrayList<Integer> nums) {
		int sum = getSum(nums);

		if (sum % 2 == 0) {
			return true;
		}
		return false;
	}

	public static int getSum(ArrayList<Integer> nums) {
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
		return sum;
	}

	public static void generate(ArrayList<Integer> nums, int[] a, int n,
			int index, boolean flag) {

		if (index == a.length) {
			spiltList(nums, a, flag);
			return;
		}

		for (int value = 0; value < 2; value++) {
			a[index] = value;
			generate(nums, a, n, index + 1, flag);
		}
	}

	public static void spiltList(ArrayList<Integer> nums, int[] a, boolean flag) {
		ArrayList<Integer> partA = new ArrayList<Integer>();
		ArrayList<Integer> partB = new ArrayList<Integer>();

		for (int i = 0; i < a.length; ++i) {
			if (a[i] == 0) {
				partA.add(nums.get(i));
			} else {
				partB.add(nums.get(i));
			}
		}

		int sumA = getSum(partA);
		int sumB = getSum(partB);

		if (flag) {// 和相等

			if (getSum(partA) != getSum(partB)) {
				return;
			}
		} else {// 和差一
			if (getSum(partA) + 1 == getSum(partB)// || getSum(partB) + 1 ==
													// getSum(partA)
			) {
				System.out.println(listToString(partA) + listToString(partB));
				writeFile(partA, partB);
				return;
			} else {
				return;
			}
		}
		System.out.println(listToString(partA) + listToString(partB));
		writeFile(partA, partB);
	}

	public static String listToString(ArrayList<Integer> list) {
		String s = "{";
		for (int i = 0; i < list.size(); ++i) {
			s += list.get(i);

			if (i != list.size() - 1) {
				s += ",";
			}
		}
		s += "}";
		return s;
	}

	public static void writeFile(ArrayList<Integer> a, ArrayList<Integer> b) {
		String sa = listToString(a);
		String sb = listToString(b);

		File f = new File("result.txt");

		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(f, true));
			bw.write(sa + sb + "\r\n");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("文件输出错误，空间不足？");
			System.exit(1);
		}


	}
}
