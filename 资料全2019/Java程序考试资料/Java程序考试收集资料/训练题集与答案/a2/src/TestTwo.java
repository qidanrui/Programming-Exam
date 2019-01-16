import java.util.ArrayList;
import java.util.List;


public class TestTwo {
	private static List<String> result=new ArrayList<String>();
	private static List<String> totalResult=new ArrayList<String>();
	/**
	 * 重复的排列
	 * @param k
	 * @param str
	 * @param index
	 */
	public static void compute(int[] k,char[] str,int index)
	{
		if(index>=k.length)
		{
			result.add(new String(str));
		}
		else
		{
			for(int i=0;i<k.length;i++)
			{
				str[index]=String.valueOf(k[i]).charAt(0);
				compute(k,str,index+1);
			}
		}
	}
	 
	/**
	 * 不可重复排列
	 * @param array
	 * @param i
	 * @param j
	 */
	public static void compute(int[] array,int index)
	{
		if(index>=array.length)
		{
			 String str="";
			 for(int m=0;m<array.length;m++)
			 {
				 str=str.concat(String.valueOf(array[m]));
			 }
			 totalResult.add(str);
		}
		else
		{
			for(int i=index;i<array.length;i++)
			{
			    swap(array,i,index);
			    compute(array,index+1);
			    swap(array,index,i);
			}
		}
	}
	public static void swap(int[] array,int i,int j)
	{
		int key=array[i];
		array[i]=array[j];
		array[j]=key;
	}
    public static void main(String[] args)
    {
    	int[] k={1,2,3}; 	
    	compute(k,new char[k.length],0);
    	System.out.println("可重复排列共有"+result.size()+"种");
    	for(int i=0;i<result.size();i++)
    	{
    		System.out.println(result.get(i));
    	}
    	compute(k,0);
    	System.out.println("不可重复排列共有"+totalResult.size()+"种");
    	for(int i=0;i<totalResult.size();i++)
    	{
    		System.out.println(totalResult.get(i));
    	}
    }
}
