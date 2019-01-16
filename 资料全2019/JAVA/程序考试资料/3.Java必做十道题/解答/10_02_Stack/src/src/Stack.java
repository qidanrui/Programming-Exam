package src;

import java.util.ArrayList;

public class Stack
{
	private ArrayList list = new ArrayList();

	private Object pop()
	{
		return list.remove(list.size() - 1);
	}

	private void push(Object o)
	{
		list.add(o);
	}

	private int size()
	{
		return list.size();
	}

	private boolean full()
	{
		return false;
	}

	private boolean empty()
	{
		if (list.size() == 0)
			return true;
		else
			return false;
	}

	private void print()
	{
		for (int i = list.size() - 1; i >= 0; i--)
		{
			System.out.println(list.get(i));
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
	// TODO Auto-generated method stub

	}

}
