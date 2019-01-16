import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Two {

	public static void main(String[] args) throws IOException {
		
		ArrayList<Integer> valueList = readFile("data.txt");
		ArrayList<String> result = new ArrayList<String>();
		char[] str = new char[valueList.size()];
		generate(valueList, 0, str, result);
		writeFile(result);
	}
	
	public static void writeFile(ArrayList<String> r) throws IOException{
		File f = new File("result.txt");
		BufferedWriter br = new BufferedWriter(new FileWriter(f));
		
		for(String s:r){
			br.write(s+"\r\n");
		}
		br.flush();
		br.close();
	}

	public static ArrayList<Integer> readFile(String fileName)
			throws IOException {
		File file = new File(fileName);

		BufferedReader br = new BufferedReader(new FileReader(file));
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		int code = br.read();
		while (code != -1) {
			list.add(code);
			code = br.read();
		}

		return list;
	}


	//递归生成所有可能的组合
	public static void generate(ArrayList<Integer> valueList, int index,
			char[] str, ArrayList<String> result) {

		if (index >= valueList.size()) {
			String s = new String(str); 
			result.add(s);
			return;
		}
		for (int valueIndex = 0; valueIndex < valueList.size(); ++valueIndex) {
			int asc = valueList.get(valueIndex);
			char c = (char)asc;
			str[index] = c;			
			generate(valueList,index+1,str,result);
		}
	}
}
