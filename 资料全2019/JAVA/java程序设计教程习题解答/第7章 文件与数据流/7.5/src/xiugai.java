import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class xiugai {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
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
		re[2]=result/5;
		ss[2]=String.valueOf(re[2]);
		FileWriter fw = new FileWriter("data.txt");
		for(int i=0;i<ss.length;i++){
			fw.write(ss[i]+" ");
			System.out.print(ss[i]+" ");
			
		}
		fw.close();
		fr.close();
		
	}

}
