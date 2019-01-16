import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Test3 {
     public static void main(String[] args)
     {
    	 BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	 String line;
    	 int[] count=new int[100];
    	 int index=0;
    	 try {
			while((line=reader.readLine())!=null)
			{
				 String[] temp=line.split(",");
				 for(int i=0;i<temp.length;i++)
				 {
					 for(int j=i+1;j<temp.length;j++)
					 {
						 if(Integer.valueOf(temp[i])>Integer.valueOf(temp[j]))
							       count[index]=count[index]+1;
					 }
				 }
				 index++;
			 }
		for(int i=0;i<index;i++)
		{
			System.out.println(count[i]);
		}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
     }
     
}
