import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Two {

	public static void main(String[] args) {
		permNums(getNum(), 0);
	}

	// 获得用户输入的数
	public static int[] getNum() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nums = new int[4];
		try {
			String s = br.readLine();
			String[] sub = s.split(" ");
			for (int i = 0; i < 4; ++i) {
				nums[i] = Integer.parseInt(sub[i]);
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return nums;
	}

	static boolean check(int[] nums, int[] ops) {

		// print(nums, ops);
		// printString(ops);

		int sum = nums[0];
		for (int i = 0; i < ops.length; ++i) {

			switch (ops[i]) {
			case 0:
				sum += nums[i + 1];
				break;
			case 1:
				sum -= nums[i + 1];
				break;
			case 2:
				sum *= nums[i + 1];
				break;
			case 3:
				if (nums[i + 1] == 0) {
					return false;
				}
				sum /= nums[i + 1];
				break;
			default:
				System.out.println("errr" + ops[i]);
				break;
			}
		}

		// System.out.println("sum:" + sum);
		if (sum == 24) {
			System.out.println("won.");

			print(nums, ops);

			// printString(nums);
			// printString(ops);
			return true;
		}

		// 两个括号的判断
		int part1 = nums[0];
		int i = 0;
		switch (ops[i]) {
		case 0:
			part1 += nums[i + 1];
			break;
		case 1:
			part1 -= nums[i + 1];
			break;
		case 2:
			part1 *= nums[i + 1];
			break;
		case 3:
			part1 /= nums[i + 1];
			break;
		default:
			System.out.println("errr" + ops[i]);
			break;
		}

		int part2 = nums[2];
		i = 2;
		switch (ops[i]) {
		case 0:
			part2 += nums[i + 1];
			break;
		case 1:
			part2 -= nums[i + 1];
			break;
		case 2:
			part2 *= nums[i + 1];
			break;
		case 3:
			part2 /= nums[i + 1];
			break;
		default:
			System.out.println("errr" + ops[i]);
			break;
		}

		switch (ops[1]) {
		case 0:
			sum = part1 + part2;
			break;
		case 1:
			sum = part1 - part2;
			break;
		case 2:
			sum = part1 * part2;
			break;
		case 3:
			if (part2 == 0)
				return false;
			sum = part1 / part2;
			break;
		default:
			System.out.println("errr" + ops[i]);
			break;
		}

		if (sum == 24) {
			System.out.println("won.");
			String[] os = new String[3];

			for (int j = 0; j < 3; ++j) {
				if (ops[j] == 0) {
					os[j] = "+";
				} else if (ops[j] == 1) {
					os[j] = "-";
				} else if (ops[j] == 2) {
					os[j] = "*";
				} else {
					os[j] = "/";
				}
			}

			String s = "(" + nums[0] + os[0] + nums[1] + ")" + os[1] + "("
					+ nums[2] + os[2] + nums[3] + ")";
			System.out.println(s);

			// printString(nums);
			// printString(ops);
			return true;
		}

		return false;
	}

	static void permNums(int[] nums, int index) {
		if (index == nums.length) {
			int[] ops = new int[3];
			allOps(nums, ops, 0);
			return;
		}

		for (int i = index; i < nums.length; ++i) {
			int temp = nums[index];
			nums[index] = nums[i];
			nums[i] = temp;

			permNums(nums, index + 1);

			temp = nums[index];
			nums[index] = nums[i];
			nums[i] = temp;

		}
	}

	static void allOps(int[] nums, int[] ops, int index) {
		if (index == ops.length) {
			check(nums, ops);
			return;
		}

		for (int i = 0; i < 4; ++i) {
			ops[index] = i;
			allOps(nums, ops, index + 1);
		}
	}

	static void printString(int[] a) {
		for (int i : a) {
			System.out.print(i + "  ");
		}
		System.out.println();
	}

	static void print(int[] nums, int[] ops) {
		String s = "(((" + nums[0];
		for (int i = 0; i < ops.length; ++i) {

			switch (ops[i]) {
			case 0:
				s += "+";
				break;
			case 1:
				s += "-";
				break;
			case 2:
				s += "*";
				break;
			case 3:
				s += "/";
				break;
			default:
				break;
			}
			s += nums[i + 1] + ")";
		}
		System.out.println(s);
	}
}
