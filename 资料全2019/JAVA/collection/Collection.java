package thu.hcguo.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Collection {
	public static void main(String[] args) {
		testArrayList();
		testLinkedList();
		testHashmap();
		testTreemap();
		testHashset();
		testTreeset();
	}

	/**
	 * A sorted set
	 */
	public static void testTreeset() {
		Set<String> set = new TreeSet<String>();
		set.add("Bob");
		set.add("Amry");
		set.add("Carl");
		Iterator<String> it = set.iterator();
		while(it.hasNext()) 
			System.out.println(it.next());
	}

	/**
	 * An unordered collection that rejects duplicates
	 */
	public static void testHashset() {
		Set<String> set = new HashSet<String>();
		set.add("Bob");
		set.add("Amry");
		set.add("Carl");
		System.out.println(set.add("Amry"));
		Iterator<String> it = set.iterator();
		while(it.hasNext()) 
			System.out.println(it.next());
	}

	/**
	 * A map in which the keys are sorted
	 */
	public static void testTreemap() {
		Map<String, Integer> map = new TreeMap<String, Integer>();
		map.put("Bob", 10);
		map.put("Amry", 40);
		map.put("Carl", 30);
		Set<Map.Entry<String, Integer>> entrySet = map.entrySet();
		for(Map.Entry<String, Integer> entry : entrySet)
			System.out.println(entry.getKey() + " " + entry.getValue());
	}

	/**
	 * A data structure that stores key/value associations
	 */
	public static void testHashmap() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Bob", 10);
		map.put("Amry", 40);
		map.put("Carl", 30);
		System.out.println(map.containsKey("Bob"));
		System.out.println(map.containsValue(10));
		Set<String> keys = map.keySet();
		for(String key : keys) 
			System.out.println(key + ":" + map.get(key));
		
		for(Map.Entry<String, Integer> entry : map.entrySet())
			System.out.println(entry.getKey() + " " + entry.getValue());
		
	}

	/**
	 * An ordered sequence that allows efficient insertions and removal at any
	 * location
	 */
	public static void testLinkedList() {
		List<String> list = new LinkedList<String>();
		list.add("Bob");
		list.add("Amy");
		list.add("Carl");
		for (String name : list)
			System.out.println(name);
	}

	/**
	 * An indexed sequence that grows and shrinks dynamically
	 */
	public static void testArrayList() {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 10; i++) {
			list.add((int) (Math.random() * i));
			System.out.println(list.get(i));
		}
		System.out.println(list.contains(3));
		System.out.println(list.indexOf(3));
		System.out.println(list.remove(3));
		System.out.println(list.set(3, 11));
		list.clear();
		System.out.println(list.isEmpty());
		for (Integer value : list) {
			System.out.println(value);
		}
	}
}
