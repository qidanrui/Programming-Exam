package thu.hcguo.simplemath;

/**
 * »ÊºóÎÊÌâ
 */
public class Queen {
	private static int count = 0;

	public static void queen(int[][] array, int i) {
		if (i == array.length) {
			count++;
			System.out.println("====" + count + "====");
			for (int j = 0; j < array.length; j++) {
				for (int k = 0; k < array.length; k++) {
					System.out.print(array[j][k]+" ");
				}
				System.out.println();
			}
		} else {
			for(int j = 0; j < array.length; j++) {
				boolean flag = true;
				for (int s = 0; s < array.length; s++) {
					for (int t = 0; t < array.length; t++) {
						if(s == i || t==j || Math.abs(s-i) == Math.abs(t-j)) {
							if(array[s][t]== 1 ){
								flag = false;
								break;
							}
						}
					}
				}
				if(flag) {
					array[i][j] = 1;
					queen(array, i+1);
					array[i][j] = 0;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int [][] array = new int[8][8];
		queen(array, 0);
	}
}
