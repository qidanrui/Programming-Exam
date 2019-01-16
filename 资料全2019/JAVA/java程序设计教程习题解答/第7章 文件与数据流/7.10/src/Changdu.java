import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;


public class Changdu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int n = Integer.parseInt(br.readLine());
			double [] d = new double [n];
			Random r = new Random();
			PrintWriter pw = new PrintWriter("data.txt");
			for(int i=0;i<n;i++){
				d[i]=r.nextDouble();
				pw.println(d[i]);
			}
			
			
			
			File f = new File("data.txt");
			
			f.length();
			pw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
