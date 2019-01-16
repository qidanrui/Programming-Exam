import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class FileS {

	/**
	 * @param args
	 */
	static void search(String s,File f){
		try {
			FileReader fr = new FileReader(f);
			StringBuffer sb= new StringBuffer();
			for(int t=fr.read();t!=-1;t=fr.read()){
				sb.append((char)t);
			}
			sb.indexOf(s);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = args[0];
		File f= new File(".");
		File [] files=f.listFiles();
		for(int i=0;i<files.length;i++){
			if(files[i].isFile()){
				search(s,files[i]);
			}
		}
	}

}
