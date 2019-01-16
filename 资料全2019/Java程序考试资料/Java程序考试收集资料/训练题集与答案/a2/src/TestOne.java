import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;


public class TestOne {
     public static void main(String[] args)
     {
    	 int n;
    	 System.out.println("请输入n:");
    	 Scanner input=new Scanner(System.in);
    	 n=input.nextInt();
    	 HashMap<Integer,Integer> map=new HashMap<Integer,Integer>();
    	 Random rand=new Random();
    	 for(int i=0;i<n;i++)
    	 {
    		 int num=rand.nextInt(100)+1;
    		 if(map.containsKey(num))
    			 map.put(num, map.get(num)+1);
    		 else
    			 map.put(num, 1);
    	 }
    	 List<Entry<Integer,Integer>> list=new ArrayList<Entry<Integer,Integer>>(map.entrySet());
    	 Collections.sort(list,new Comparator<Entry<Integer,Integer>>(){

			@Override
			public int compare(Entry<Integer, Integer> o1,
					Entry<Integer, Integer> o2) {
				// TODO Auto-generated method stub
				return o2.getValue()-o1.getValue();
			}
    	 });
    	 int value=list.get(0).getValue();
    	 System.out.println("出现次数最多的数及其次数如下:");
    	 for(int i=0;i<list.size();i++)
    	 {
    		 if(list.get(i).getValue()==value)
    			 System.out.println(list.get(i).getKey()+" "+list.get(i).getValue()+"次");
    		 else
    			 break;
    	 }
     }
}
