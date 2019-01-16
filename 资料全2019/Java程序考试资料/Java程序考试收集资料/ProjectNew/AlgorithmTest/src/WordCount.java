import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * 单词统计,按照单词出现的个数，或者按照单词在字母表中的顺序输出
 * @author sinllychen
 *
 */
public class WordCount {
     public static void main(String[] args)
     {
    	 System.out.println("请输入一段话:");
    	 String regex="[?|,|.|\\s|!]+";
    	 Scanner input=new Scanner(System.in);
    	 String str=input.nextLine().trim();
    	 String[] words=str.split(regex);
    	 HashMap<String,Integer> map=new HashMap<String,Integer>();
    	 for(int i=0;i<words.length;i++)
    	 {
    		 if(map.containsKey(words[i]))
    			 map.put(words[i], map.get(words[i])+1);
    		 else
    			 map.put(words[i], 1);
    	 }
    	 List<Entry<String,Integer>> list=new ArrayList<Entry<String,Integer>>(map.entrySet());
//         Collections.sort(list,new Comparator<Entry<String,Integer>>(){
//
//			@Override
//			public int compare(Entry<String, Integer> o1,
//					Entry<String, Integer> o2) {
//				// TODO Auto-generated method stub
//				return o2.getValue()-o1.getValue();
//			}
//        	 
//         });
         Collections.sort(list,new Comparator<Entry<String,Integer>>(){

			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o1.getKey().toLowerCase().compareTo(o2.getKey().toLowerCase());
			}
        	 
         });
         System.out.println("共有单词数为:"+words.length);
         for(int i=0;i<list.size();i++)
         {
        	 System.out.println(list.get(i).getKey()+"数量为:"+list.get(i).getValue());
         }
     }
}
