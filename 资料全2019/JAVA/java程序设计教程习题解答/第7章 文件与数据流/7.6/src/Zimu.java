import java.io.FileWriter;
import java.io.IOException;


public class Zimu {
	public static void main(String [] args ){
		try {
			FileWriter fw = new FileWriter("data.txt");
			fw.write("*********");fw.write('\n');
			fw.write("**     **");fw.write('\n');
			fw.write("**       ");fw.write('\n');
			fw.write("**       ");fw.write('\n');
			fw.write("*********");fw.write('\n');
			fw.write("       **");fw.write('\n');
			fw.write("       **");fw.write('\n');
			fw.write("**     **");fw.write('\n');
			fw.write("*********");
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
