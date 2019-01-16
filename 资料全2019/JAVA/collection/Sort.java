package thu.hcguo.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Map.Entry;
import java.util.Set;

public class Sort {
	public static void main(String[] args) {
		sortArray();
		sortList();
		sortMap();
	}

	public static void sortArray() {
		double array[] = new double[5];
		System.out.println("Test Arrays: Before sorting...");
		for(int i = 0; i < array.length; i++) {
			array[i] = Math.random();
			System.out.println(array[i]);
		}
		System.out.println("Test Arrays: After sorting...");
		Arrays.sort(array);
		for(int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}

	public static void sortList() {
		List<String> list = new ArrayList<String>();
		list.add("Bob");
		list.add("Amry");
		list.add("Carl");
		
		System.out.println("Test ArrayList(Build-in type): Before sorting...");
		for(String name : list)
			System.out.println(name);
		
		Collections.sort(list, Collections.reverseOrder());
		System.out.println("Test ArrayList(Build-in type): After sorting...");
		for(String name : list)
			System.out.println(name);
		
		System.out.println("Test ArrayList(User-defined type): Before sorting...");
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(new Item("Bob",10));
		itemList.add(new Item("Amry",40));
		itemList.add(new Item("Carl",20));
		for(Item item : itemList)
			System.out.println(item.getName()+" "+item.getAge());
		
		Collections.sort(itemList, new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				// TODO Auto-generated method stub
				return o1.getAge() - o2.getAge();
			}
		});
		System.out.println("Test ArrayList(User-defined type): After sorting...");
		for(Item item : itemList)
			System.out.println(item.getName()+" "+item.getAge());
	}

	public static void sortMap() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Bob", 10);
		map.put("Amry", 20);
		map.put("Carl", 30);
		System.out.println("Test Hashmap: Before sorting...");
		for(Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		
		List<Map.Entry<String, Integer>> entryList = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		
		Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});
		System.out.println("Test Hashmap: After sorting...");
		for(Map.Entry<String, Integer> entry : entryList) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
}

class Item {
	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Item() {
		name = "name";
		age = 10;
	}

	public Item(String name, int age) {
		this.name = name;
		this.age = age;
	}
}
