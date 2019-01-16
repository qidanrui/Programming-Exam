import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class One {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		find(getInputString());
		System.out.println("longest:\t" + longest);
	}

	public static String longest = "";

	public static String getInputString() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		try {
			s = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return s;
	}

	public static boolean check(String s) {

		for (int i = 0; i < s.length() / 2; i++) {
			if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
				return false;
			}
		}

		return true;
	}

	public static void find(String input) {

		for (int i = 0; i < input.length(); ++i) {
			for (int j = i; j < input.length(); ++j) {
				String sub = input.substring(i, j+1);
				if (check(sub)) {
					if (sub.length() >= longest.length()&&sub.length()%2!=0) {
						longest = sub;
					}
				}
			}
		}
	}

}
