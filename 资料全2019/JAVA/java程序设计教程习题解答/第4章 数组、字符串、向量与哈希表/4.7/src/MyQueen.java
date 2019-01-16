public class MyQueen {

	/**
	 * @param args
	 */
	int num;
	boolean col[] = new boolean[9];
	boolean x[] = new boolean[16];
	boolean ux[] = new boolean[16];
	int result[] = new int[9];

	public MyQueen() {
		for (int i = 1; i < 9; i++) {
			col[i] = true;
		}
		for (int i = 1; i < 16; i++) {
			x[i] = true;
			ux[i] = true;
		}
		num = 0;

	}

	public void solve(int i) {

		for (int j = 1; j < 9; j++) {

			if (col[j] && x[j - i + 8] && ux[j + i - 1]) {
				result[i] = j;
				col[j] = false;
				x[j - i + 8] = false;
				ux[j + i - 1] = false;

				if (i < 8) {
					solve(i + 1);
				} else {
					num++;

					System.out.print("第" + num + "种方法：");
					for (int k = 1; k < 9; k++) {
						System.out.print(" " + result[k]);
					}
					System.out.println();
				}
			
			col[j] = true;
			x[j - i + 8] = true;
			ux[j + i - 1] = true;
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyQueen m = new MyQueen();
		m.solve(1);
	}

}
