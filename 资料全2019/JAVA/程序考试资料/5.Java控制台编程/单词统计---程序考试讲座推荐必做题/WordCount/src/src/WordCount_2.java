package src;
import java.util.*;

public class WordCount_2
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args)
	{
		Scanner s = new Scanner(System.in);
		System.out.print("请输入:");
		String text = s.nextLine();

		HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
		String[] words = text.split("[ .!?]");//更强大的分词
		
		for (int i = 0; i < words.length; i++)
		{
				if (hashMap.get(words[i]) != null)//和if (keys.contains(arr[i]))效果一样
				{
					int value = hashMap.get(words[i]).intValue();
					value++;
					hashMap.put(words[i], value);//或者直接result.put(arr[i], result.get(arr[i]) + 1);
				}
				else
					hashMap.put(words[i], 1);
		}

		/*System.out.println("无序输出单词和其计数：");
		System.out.println(hashMap);
		
		System.out.println("按照字典序（TreeMap按照key排序）输出单词和其计数：");
		Map<String, Integer> treeMap = new TreeMap<String, Integer>(hashMap);//用HashMap构造TreeMap
		System.out.println(treeMap);*/	
		
		ArrayList arrayList = new ArrayList(hashMap.entrySet());//定义一个ArrayList对象来存放键值对entrySet()		
		
		Collections.sort(arrayList, new Comparator()//这里new了一个比较器对象，该对象属于匿名类
		{
			public int compare(Object o1, Object o2)
			{
				Map.Entry obj1 = (Map.Entry) o1;
				Map.Entry obj2 = (Map.Entry) o2;
				return ((Integer) obj2.getValue()).compareTo((Integer) obj1.getValue());
				//String，Integer类型都实现了Comparable接口，所以有一个compareTo方法可以直接使用
			}
		});

		System.out.println("按照从大到小的顺序输出单词和其计数：");
		System.out.println(arrayList);

	}
}