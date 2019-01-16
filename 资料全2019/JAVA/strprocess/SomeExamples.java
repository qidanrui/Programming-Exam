package thu.hcguo.strprocess;

public class SomeExamples {
	public static boolean palindromes(char[] ch) {
		boolean flag = true;
		for (int i = 0, j = ch.length - 1; i <= j; i++, j--) {
			if (ch[i] != ch[j]) {
				return false;
			}
		}
		return flag;
	}

	public static String delRepeat(String str) {
		String newStr = "";
		for(char ch : str.toCharArray()) {
			if(!newStr.contains(ch+"")) {
				newStr += ch;
			}
		}
		return newStr;
	}
	
	public static void main(String[] args) {
		String str = "asdffdsa";
		System.out.println(palindromes(str.toCharArray()));
		System.out.println(delRepeat(str));
	}
}
