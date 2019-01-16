package thu.hcguo.simplemath;

import java.util.ArrayList;

public class Combine {
	private static ArrayList<Character> list = new ArrayList<Character>();

	public static void main(String[] args) {
		String str = "asdf";
		char array[] = str.toCharArray();
		combine(array, 0, array.length - 1);
	}

	/**
	 * 组合算法
	 * 
	 * @param array
	 *            待组合数组
	 * @param cur
	 *            组合开始的下标
	 * @param count
	 *            组合中含有元素的个数
	 */
	public static void combine(char[] array, int cur, int count) {
		if (count == 0) {
			for (char ch : list) {
				System.out.print(ch);
			}
			System.out.println();
		} else {
			for (int i = cur; i < array.length; i++) {
				list.add(array[i]);
				combine(array, i + 1, count - 1);
				list.remove(list.indexOf(array[i]));
			}
		}
	}
}
