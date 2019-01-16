import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Pingjun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
			String str=bf.readLine();
			String [] cj = str.split(" ");
			FileWriter fw = new FileWriter("data.txt");
			for(int i=0;i<cj.length;i++){
				fw.write(cj[i]+" ");
			}
			fw.close();
			
			FileReader fr = new FileReader("data.txt");
			String s = new String();
			for(int t=fr.read();t!=-1;t=fr.read()){
				s+=(char)t;
			}
			//System.out.print(s);
			String[] ss=s.split(" ");
			int [] re = new int [ss.length];
			int result =0;
			for (int i=0;i<ss.length;i++){
				re[i]=Integer.parseInt(ss[i]);
				result+=re[i];
			}
			System.out.println(result/5);
			Arrays.sort(re);
			for(int i=0;i<re.length;i++){
				System.out.print(" "+re[i]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
