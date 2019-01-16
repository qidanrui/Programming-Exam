package thu.hcguo.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class IOProcess {
	public static void main(String[] args) {
		read("test");
		write("test");
		console1();
		console2();
	}

	private static void console2() {
		Scanner scanner = new Scanner(System.in);
		
		for (int i = 0; i < 3; i++) {
			System.out.println(scanner.nextLine());
		}
	}

	private static void console1() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String line = "";
		try {
			line = reader.readLine();
			System.out.println(line);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static boolean write(String filename) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(filename));
			for (int i = 0; i < 10; i++) {
				writer.write("hello\n");
			}
			writer.flush();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				return false;
			}
		}
		return true;
	}

	public static boolean read(String filename) {
		boolean suc = true;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = "";
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			suc = false; // 找不到文件
		} catch (IOException e) {
			suc = false; // 文件读取错误
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				suc = false; // 关闭错误
			}
		}
		return suc;
	}
}
