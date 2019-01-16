import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class One {

	public static void main(String[] args) {
		int n = getN();
		int[][] m = new int[n][n];
		int[] a = new int[n * n];

		for (int i = 0; i < a.length; ++i) {
			a[i] = i + 1;
		}
		permArray(a, n, 0, m);
	}

	public static int getN() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = new String();
        System.out.println("请输入数字:");
		try {
			s = br.readLine();
			System.out.println("s="+s);
		} catch (IOException e) {
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

	public static void writeFile(int[][] m, int n) {
		File f = new File("matrix.txt");

		String out = "";
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				out += m[i][j] + " ";
			}
			out += "\r\n";
		}

		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(f));
			bw.write(out);
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("文件输出错误，空间不足？");
			System.exit(1);
		}
	}

	public static void permArray(int[] a, int n, int index, int[][] m) {
		if (index == a.length) {
			fillMatrixWithArray(a, n, m);
			if (checkMatrix(m, n)) {
				System.out.println("foud one solution!");
				printMatrix(m, n);
				System.out.println("----");
				writeFile(m, n);
				return;
			}
		}

		for (int i = index; i < a.length; ++i) {
			// swap
			int temp = a[i];
			a[i] = a[index];
			a[index] = temp;

			permArray(a, n, index + 1, m);

			temp = a[i];
			a[i] = a[index];
			a[index] = temp;
		}
	}

	public static boolean checkMatrix(int[][] m, int n) {

		int sum = 0;
		for (int j = 0; j < n; ++j) {
			sum += m[0][j];
		}

		// check rows
		for (int i = 1; i < n; ++i) {
			int rowTemp = 0;
			for (int j = 0; j < n; ++j) {
				rowTemp += m[i][j];
			}

			if (rowTemp != sum) {
				return false;
			}
		}

		// check cols
		for (int j = 0; j < n; ++j) {
			int colTemp = 0;
			for (int i = 0; i < n; ++i) {
				colTemp += m[i][j];
			}

			if (colTemp != sum) {
				return false;
			}
		}

		// 斜向检查1
		int temp = 0;
		for (int i = 0, j = 0; i < n && j < n; ++i, ++j) {

			temp += m[i][j];
		}
		if (temp != sum)
			return false;

		// 斜向检查2
		temp = 0;
		for (int i = n - 1, j = 0; i >= 0; --i, ++j) {
			temp += m[i][j];
		}
		if (temp != sum)
			return false;

		return true;
	}

	public static void fillMatrixWithArray(int[] a, int n, int[][] m) {

		for (int j = 0, rowCount = 0; j < a.length; ++j) {
			if (j != 0 && ((j) % n == 0)) {
				// 填满了一行
				++rowCount;
			}
			m[rowCount][(j) % n] = a[j];
		}

	}

	public static void printMatrix(int[][] m, int n) {
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
}
