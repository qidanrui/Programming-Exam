import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 从标准输入读入任意多个字符串，字符串用#分割，最后输入一个固定的标示输入结束的字符串如";\*."，对输入的所有字符串
 * 按字典的顺序排列，并输出到一个文件中，同时统计出字符串个数，最长字符串，最短字符串
 * @author sinllychen
 *
 */
public class Test3 {
   public static void main(String[] args)
   {
	   BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
	   System.out.println("请输入字符串并以#键隔开，最后输入固定字符串\";\\*.\"结束");
	   List<String> list=new ArrayList<String>();
       String maxString="";
       String minString="888888888888888888888888888888";
       String inputs="";
	   try {
		while(true)
		{
		String input=reader.readLine();
		inputs=inputs.concat(input);//字符串拼接
    	if(input.contains(";\\*."))
		{
			break;
		}
		}
		String[] datas=inputs.split("#");
        for(int i=0;i<datas.length;i++)
        {
        	if(!datas[i].contains(";\\*."))
        	{	list.add(datas[i]);
        	   if(datas[i].length()>maxString.length())
        	    	maxString=datas[i];
        	   if(datas[i].length()<minString.length())
        		    minString=datas[i];
        	}
        }
	    Collections.sort(list,new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				String[] temp={o1.toLowerCase(),o2.toLowerCase()};
				Arrays.sort(temp);
				if(o1.equalsIgnoreCase(temp[0]))
				{
					return -1; //若o1<o2则返回-1
				}
				else if(temp[0].equalsIgnoreCase(temp[1]))
				{
					return 0;//若o1=o2则返回0
				}
				else  return 1;//若o1>o2则返回1
			}
	    	
	    });//字符串排序（从小到大)
        int size=list.size();
        File f=new File("data.txt");
        FileWriter writer=new FileWriter(f);
/*        BufferedWriter out = new BufferedWriter(writer);*/
        for(int i=0;i<list.size();i++)
        {
        	writer.write(list.get(i));
        	writer.write("\r\n");
        }
        writer.write("字符串的个数为:"+size+"\r\n");
        writer.write("最长字符串为:");
        for(int i=0;i<list.size();i++)
        {
        	if(list.get(i).length()==maxString.length())
        		writer.write(list.get(i)+"  ");
        }
        writer.write("\r\n最短字符串为:");
        for(int i=0;i<list.size();i++)
        {
        	if(list.get(i).length()==minString.length())
        		writer.write(list.get(i)+"  ");
        }
        writer.write("\r\n");
        writer.close();
    
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}
