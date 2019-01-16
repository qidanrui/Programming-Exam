import java.util.Scanner;

/**
 * 篮球错排问题,思路，先进行全排列之后，然后进行剪枝，根据同一个篮球不在同一个编号的篮子里，以及相邻两个篮球编号不能相同作为原则
 * @author sinllychen
 *
 */
public class Permulate {
	private static int num=0;
//    public static int[] geneRandom(int num)
//    {
//    	Random rand=new Random();
//    	int[] a=new int[num];
//    	for(int i=0;i<num;i++)
//    		a[i]=rand.nextInt(num)+1;//0-10不包括10，所以要产生1-10的伪随机数则需要+1
//    	return a;
//    }
    public static void permulate(int[] array,int i,int j)
    {
    	if(i>j)
    	{
    		if(verify(array))
    		{
    			num++;
    			System.out.print("第"+num+"种放置方法:");
        		printPermulate(array);
        		System.out.println();
    		}		
    	}
    	else
    	{
    		for(int k=i;k<array.length;k++)
    		{
    			swap(array,i,k);
    			permulate(array,i+1,j);
    			swap(array,k,i);
    		}
    	}
    }
    public static void printPermulate(int[] array)
    {
    	for(int i=0;i<array.length;i++)
            System.out.print(array[i]+" ");
     }
    public static void swap(int[] array,int i,int j)
    {
    	int a=array[i];
    	array[i]=array[j];
    	array[j]=a;
    }
    public static boolean verify(int[] array)
    {
    	for(int i=0;i<array.length;i++)
    	{
    		if(array[i]==i+1)
    			return false;
    		else if(i+1<array.length&&Math.abs(array[i]-array[i+1])==1)
    			return false;
    	}
    	return true;
    }
    public static void main(String[] args)
    {
    	System.out.println("请输入数据规模:");
    	Scanner input=new Scanner(System.in);
    	int n=input.nextInt();
//    	int[] array=geneRandom(num);
        int[] array=new int[n];
        for(int i=0;i<n;i++)
        	array[i]=i+1;
    	permulate(array,0,array.length-1);
    	if(num==0)
    		System.out.println("对不起，不存在符合要求的放置方式");
    	
    }
}
