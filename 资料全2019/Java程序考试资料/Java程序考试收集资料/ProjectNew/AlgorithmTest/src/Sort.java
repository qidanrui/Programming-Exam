import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

/**
 * 常用快速排序和集合排序
 * @author sinllychen
 *
 */
public class Sort {
	  public static int partition(int[] array,int i,int j)
	  {
		  int key=array[i];
		  int start=i+1;
		  int end=j;
		  while(start<=end)
		  {
			  while(start<=end&&array[start]<=key) start++;
			  while(start<=end&&array[end]>=key) end--;
			  if(start>end)
				  break;
			  swap(array,start,end);
		  }
		  array[i]=array[end];
		  array[end]=key;
		  return end;
	  }
	  public static void swap(int[] array,int i,int j)
	  {
		  int a=array[i];
		  array[i]=array[j];
		  array[j]=a;
	  }
	  
      public static void quickSort(int[] array,int i,int j)
      {
    	  if(i<j)
    	  {
    		  int p=partition(array,i,j);
    		  quickSort(array,i,p-1);
    		  quickSort(array,p+1,j);
    	  }
      }
      public static void main(String[] args)
      {
    	  //快速排序
    	  int[] array={1,2,4,3,2,0,34,5,8,6};  
    	  quickSort(array,0,array.length-1);
    	  for(int i=0;i<array.length;i++)
    	       System.out.print(array[i]+"  ");
    	  
    	  //集合排序自带方法，普通int list
    	  System.out.println();
    	  List<Integer> list=new ArrayList<Integer>();
    	  List<String> list2=new ArrayList<String>();
    	  int[] array1={1,2,4,3,2,0,34,5,8,6};
    	  String[] array2={"Hello","cxj","Nihao","happy","Ys","Abc"};
    	  for(int i=0;i<array1.length;i++)
    		    list.add(array1[i]);
    	  Collections.sort(list);
    	  for(int i=0;i<array.length;i++)
   	       System.out.print(list.get(i)+"  ");
    	  
    	  //集合排序字符串,若相同大小写可以直接用，否则需要加上一个Comparable对象
    	  System.out.println();
    	  for(int i=0;i<array2.length;i++)
  		    list2.add(array2[i]);
    	  System.out.println(list2.get(0).compareToIgnoreCase(list2.get(1)));
    	  Collections.sort(list2,new Comparator<String>()
    			  {

					@Override
					public int compare(String o1, String o2) {
						// TODO Auto-generated method stub
						return o1.compareToIgnoreCase(o2);
					}
    		  
    			  });
    	  for(int i=0;i<array2.length;i++)
      	       System.out.print(list2.get(i)+"  ");
    	  
    	  //Map进行排序，key排序直接用TreeMap，value排序需要将其转换为entrySet
    	  Map<String,Integer> map=new HashMap<String,Integer>();
    	  for(int i=9;i>=0;i--)
    		  map.put(String.valueOf(i), i);
    	  TreeMap<String,Integer> treeMap=new TreeMap<String,Integer>(map);
    	  Set<String> keys=treeMap.keySet();
    	  Iterator iter=keys.iterator();
    	  while(iter.hasNext())
    	  {
    		  String key=(String) iter.next();
    		  System.out.println(key+","+map.get(key));
    	  }
    	  
    	  List<Map.Entry<String, Integer>> entry=new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
    	  Collections.sort(entry,new Comparator<Map.Entry<String, Integer>>(){

			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				// TODO Auto-generated method stub
				return o1.getValue()-o2.getValue();
			}
    	  });
    	  
    	  for(int i=0;i<entry.size();i++)
    		  System.out.println(entry.get(i).getKey()+","+entry.get(i).getValue());
    		  
      }
}
