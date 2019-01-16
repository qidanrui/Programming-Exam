import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Tihuan {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileReader fr = new FileReader("data.txt");
			int n=0;
			ArrayList <Character> al = new ArrayList<Character>();
			for(int t=fr.read();t!=-1;t=fr.read()){
				al.add((char)t);
				if((char)t=='*'){
					n++;
				}
			}
			fr.close();
			System.out.println("*的个数为"+n);
			System.out.println("请输入"+n+"个字符");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			char [] c = new char [n];
			for (int i=0;i<n;i++){
				c [i]=(char)br.read();
			}
			FileWriter fw = new FileWriter("data.txt");
			for(int i=0,j=0;i<al.size();i++){
				if(al.get(i)=='*'){
					al.set(i, c[j]);
					j++;
				}
				fw.append(al.get(i));
			}
			fw.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
