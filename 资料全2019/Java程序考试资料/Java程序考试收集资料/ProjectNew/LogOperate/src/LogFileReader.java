import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class LogFileReader {
    private  HashMap<String, Integer> mostLineModify;
    private HashMap<String, Integer> authors;
    public   static List< Entry<String,Integer> > mostLineList; 
	public   static List< Entry<String,Integer> > mostCountList;
	private    HashMap<String,Integer> mostModify;
	private BufferedReader br;
   
     public LogFileReader(String path)
     {
    	setMostLineModify(new HashMap<String,Integer>());
    	authors=new HashMap<String,Integer>();
    	mostModify=new HashMap<String,Integer>();
    	mostLineList=new ArrayList< Entry<String,Integer>>();
    	mostCountList=new ArrayList<Entry<String,Integer> >();
    	try {
			br=new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
     public void getFinalData()
     {
         String lineData=null;
         try {
			while((lineData=br.readLine())!=null)
			{
				if(lineData.contains("|")&&lineData.contains("/"))
				{
					lineData=new String(trim(lineData));
					System.out.println(lineData);
					String[] temp=lineData.split("\\|");
					temp[0]=trim(temp[0]);
					temp[1]=trim(temp[1]);
					temp[1]=temp[1].replaceAll("\\+", "");
					temp[1]=temp[1].replaceAll("-", "");
					temp[1]=trim(temp[1]);
					
					if(isNum(temp[1]))
					{	
					if(mostLineModify.containsKey(temp[0]))
				     	mostLineModify.put(temp[0], Integer.valueOf(temp[1])+mostLineModify.get(temp[0]));
					else
						mostLineModify.put(temp[0],Integer.valueOf(temp[1]));
					}
					
					if(mostModify.containsKey(temp[0]))
						mostModify.put(temp[0], mostModify.get(temp[0])+1); 	
					else
						mostModify.put(temp[0], 1);
				}
				
/*				if(lineData.contains("Author"))
				{
					lineData.replace("Author:", "");
					if(!authors.containsKey(lineData))
						authors.put(lineData, 1);
				}*/
			}
			List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(mostLineModify.entrySet());
			Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {  
			    public int compare(Map.Entry<String, Integer> o1,  
			            Map.Entry<String, Integer> o2) {  
			        return ( o2.getValue()-o1.getValue());  
			    }  
			});
			
			List<Map.Entry<String, Integer>> infoId2s = new ArrayList<Map.Entry<String, Integer>>(mostModify.entrySet());
			Collections.sort(infoId2s, new Comparator<Map.Entry<String, Integer>>() {  
			    public int compare(Map.Entry<String, Integer> o1,  
			            Map.Entry<String, Integer> o2) {  
			        return ( o2.getValue()-o1.getValue());  
			    }  
			});
			
			for (int i = 0; i < 10; i++) {  
			    Entry<String,Integer> ent=infoIds.get(i);  
			    Entry<String,Integer> ent2=infoId2s.get(i);
			    mostLineList.add(ent);
			    mostCountList.add(ent2);
			} 
/*			List<Map.Entry<String, Integer>> infoId3s = new ArrayList<Map.Entry<String, Integer>>(authors.entrySet());
			for (int i = 0; i < infoId3s.size(); i++) {  
			    Entry<String,Integer> ent=infoId3s.get(i);  
			    System.out.println(ent.getKey());
			} */
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
     /**
      * 判断一个字符是否是数字
      * @param str
      * @return
      */
     public static boolean isNum(String str){
 		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
 	}
     /**
      * 删除首尾字符
      * @param s
      * @return
      */
    public static String trim(String s) {
		  int i = s.length();// 字符串最后一个字符的位置
		  int j = 0;// 字符串第一个字符
		  int k = 0;// 中间变量
		  char[] arrayOfChar = s.toCharArray();// 将字符串转换成字符数组
		  while ((j < i) && (arrayOfChar[(k + j)] <= ' '))
		   ++j;// 确定字符串前面的空格数
		  while ((j < i) && (arrayOfChar[(k + i - 1)] <= ' '))
		   --i;// 确定字符串后面的空格数
		  return (((j > 0) || (i < s.length())) ? s.substring(j, i) : s);// 返回去除空格后的字符串
		 }
	/**
	 * @return the mostLineModify
	 */
	public HashMap<String, Integer> getMostLineModify() {
		return mostLineModify;
	}
	/**
	 * @param mostLineModify the mostLineModify to set
	 */
	public void setMostLineModify(HashMap<String, Integer> mostLineModify) {
		this.mostLineModify = mostLineModify;
	}
    /**
	 * @return the mostLineList
	 */
	public static List<Entry<String, Integer>> getMostLineList() {
		return mostLineList;
	}
	/**
	 * @param mostLineList the mostLineList to set
	 */
	public static void setMostLineList(List<Entry<String, Integer>> mostLineList) {
		LogFileReader.mostLineList = mostLineList;
	}
    /**
	 * @return the mostCountList
	 */
	public static List<Entry<String, Integer>> getMostCountList() {
		return mostCountList;
	}
	/**
	 * @param mostCountList the mostCountList to set
	 */
	public static void setMostCountList(List<Entry<String, Integer>> mostCountList) {
		LogFileReader.mostCountList = mostCountList;
	}

}
