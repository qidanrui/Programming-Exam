package thu.hcguo.simplemath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给定一个数组求排列
 */
public class Permutation {
	/**
	 * 对于不包含重复元素数组的全排列,并且可以指定排列中含有元素的个数
	 * 
	 * @param array
	 *            待排列数组
	 * @param cur
	 *            排列开始的下标
	 * @param count
	 *            排列中含有元素的个数
	 */
	public static void subPerm(char[] array, int cur, int count) {
		if (cur == count) {
			for (int i = 0; i < count; i++) {
				System.out.print(array[i]);
			}
			System.out.println();
		} else {
			for (int i = cur; i < array.length; i++) {
				swap(array, cur, i);
				subPerm(array, cur + 1, count);
				swap(array, cur, i);
			}
		}
	}

	/**
	 * 对于不包含重复元素数组的全排列
	 * 
	 * @param array
	 *            待排列的数组
	 * @param cur
	 *            当前的下标
	 */
	public static void totalPerm(char[] array, int cur) {
		if (cur == array.length - 1) {
			for (char ch : array)
				System.out.print(ch);
			System.out.println();
		} else {
			for (int i = cur; i < array.length; i++) {
				swap(array, cur, i);
				totalPerm(array, cur + 1);
				swap(array, cur, i);
			}
		}
	}

	/**
	 * 对于包含重复元素数组的全排列,并且可以指定排列中含有元素的个数
	 * 
	 * @param array
	 *            待排列的数组
	 * @param cur
	 *            当前的下标
	 * @param count
	 *            排列中含有元素的个数
	 */
	public static void perm(char[] array, int cur, int count) {
		if (cur == count) {
			for (int i = 0; i < count; i++)
				System.out.print(array[i]);
			System.out.println();
		} else {
			for (int i = cur; i < array.length; i++) {
				if (!contains(array, cur, i)) {
					swap(array, cur, i);
					perm(array, cur + 1, count);
					swap(array, cur, i);
				}
			}
		}
	}

	private static void swap(char[] array, int cur, int i) {
		char temp = array[cur];
		array[cur] = array[i];
		array[i] = temp;
	}

	private static boolean contains(char[] array, int cur, int i) {
		for (int j = cur; j < i; j++) {
			if (array[j] == array[i])
				return true;
		}
		return false;
	}

	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			String string = reader.readLine();
			char[] array = string.toCharArray();
			// 进行全排列时，从0下标开始
			totalPerm(array, 0);
			subPerm(array, 0, 2);
			perm(array, 0, array.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
