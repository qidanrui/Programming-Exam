import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Random;

public class MainTest {
	/**
	 * 时间格式转换
	 * @param time
	 * @return
	 */
	public static String timeFormat(long time)
	{
		DecimalFormat df=new DecimalFormat(".####");
    	if(String.valueOf((double)(time)/1000000).substring(0,1).equals("0"))
    		return "0"+df.format((double)(time)/1000000)+"ms";
    	else 
    		return df.format((double)(time)/1000000)+"ms";
	}
	/**
	 * 生成随机数集合，数的范围在1-2的32次方-1
	 * @param n
	 * @return
	 */
	public static long[] generateRandom(int n)
	{
		long[] datas=new long[n];
		Random rand=new Random();
		for(int i=0;i<n;i++)
		{
			Long data=((long) (rand.nextInt((int) (Math.pow(2, 16))))<<16)+rand.nextInt((int) (Math.pow(2, 16)));
			datas[i]=data;
		}
		return datas;	
	}
	
	
    public static void main(String[] args)
    {
     	InputStreamReader input =new InputStreamReader(System.in);
     	BufferedReader reader=new BufferedReader(input);
     	int value;
     	long startTime,endTime;
     	long[] datas;
     	try {
     		while(true)
     		{
     	     	System.out.println("*******************************************");
     	     	System.out.println("1、插入排序");
     	     	System.out.println("2、快速排序");
     	     	System.out.println("3、归并排序");
     	     	System.out.println("4、基数排序");
     	     	System.out.println("5、结束");
     	     	System.out.println("请选择序号:");
			int num=Integer.valueOf(reader.readLine());
			if(num==5)
				break;
			switch(num)
			{
			case 1:
				   System.out.println("*****************插入排序******************");
				   System.out.println("请输入规模(0-2的31次方-1):");
				    value=Integer.valueOf(reader.readLine());
				    datas=generateRandom(value);
					startTime=System.nanoTime();
			    	InsertSort.insertSort(datas);
			    	endTime=System.nanoTime();
			    	System.out.println("插入排序的时间为:"+timeFormat(endTime-startTime));
			    	break;
			case 2:
				   System.out.println("*****************快速排序******************");
				   System.out.println("请输入规模(0-2的31次方-1):");
				    value=Integer.valueOf(reader.readLine());
				   datas=generateRandom(value);
					startTime=System.nanoTime();
					QuickSort.quickSort(datas,0,datas.length-1);
			    	endTime=System.nanoTime();
			    	System.out.println("快速排序的时间为:"+timeFormat(endTime-startTime));
			    	break;
			case 3:
				   System.out.println("*****************归并排序******************");
				   System.out.println("请输入规模(0-2的31次方-1):");
				    value=Integer.valueOf(reader.readLine());
				   datas=generateRandom(value);
					startTime=System.nanoTime();
					MergeSort.mergeSort(datas,0,datas.length-1);
			    	endTime=System.nanoTime();
			    	System.out.println("归并排序的时间为:"+timeFormat(endTime-startTime));
			    	break;
			case 4:
				   System.out.println("******************基数排序*****************");
				   System.out.println("请输入规模(0-2的31次方-1):");
				    value=Integer.valueOf(reader.readLine());
    			    System.out.println("请输入r大小:");
				    int r=Integer.valueOf(reader.readLine());
				   datas=generateRandom(value);
					startTime=System.nanoTime();
					RadixSort.radixSort(datas,datas.length,r);
			    	endTime=System.nanoTime();
			    	System.out.println("基数排序的时间为:"+timeFormat(endTime-startTime));
			    	break;
			 default:
				  break;
			}
     		}
		    
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
