public class MySort {

	/**
	 * @param args
	 */

	char[] mc = new char[10];

	public MySort() {
		for (int i = 0; i < 10; i++) {
			mc[i] = ' ';
		}
	}

	public boolean overlap(char c) {
		for (int i = 0; i < 10; i++) {
			if (mc[i] == c) {
				return true;
			}
		}
		return false;
	}

	public void sort() {
		for (int i = 0; i < 10; i++) {
			for (int j = i + 1; j < 10; j++) {
				if (mc[i] > mc[j]) {
					char c = mc[i];
					mc[i] = mc[j];
					mc[j] = c;
				}
			}
		}
	}

	
		

		
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MySort m = new MySort();
		for (int i = 0; i < 10; i++) {

			int num = (int) (Math.random() * 20 + 0.5);
			char c = (char) (num + 97);
			while (m.overlap(c) == true) {
				num = (int) (Math.random() * 20 + 0.5);
				c = (char) (num + 97);
			}

			m.mc[i] = c;

		}
		for (int i = 0; i < 10; i++) {
			System.out.println(m.mc[i]);

		}
		m.sort();
		for (int i = 0; i < 10; i++) {
			System.out.println(m.mc[i]);

		}

	}

}
