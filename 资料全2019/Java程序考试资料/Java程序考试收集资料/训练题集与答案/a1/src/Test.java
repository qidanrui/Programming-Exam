import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Test {
      public static void main(String[] args)
      {
    	  BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
    	  System.out.println("请输入一个数字:");
    	  int n;
		try {
			n = Integer.valueOf(reader.readLine().trim());
	    	  System.out.println("n="+n);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

      }
}
