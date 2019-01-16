/**
 * 基数排序
 * @author sinllychen
 *
 */
public class RadixSort {
   public static void radixSort(long[] datas,int n,int r)
   {
	   int b=32;
       int d=(int) Math.ceil((double)b/(double)(r));
       int dataRange=(int) (Math.pow(2, r));
       int base=(int) (Math.pow(2, r)-1);
	   long[]  c=new long[dataRange];
	   long[] output=new long[datas.length];
	   for(int k=1;k<=d;k++)
	   {
		   int i=0;int j=0;
		   for(;i<dataRange;i++)
			   c[i]=0;
		   for(;j<datas.length;j++)
		   {
			   int block=(int) ((datas[j]>>(int)(r*(k-1)))&base);
			   c[block]=c[block]+1;
		   }
		   for(i=1;i<dataRange;i++)
			   c[i]=c[i]+c[i-1];
		   for(j=datas.length-1;j>=0;j--)
		   {
			   int block=(int) ((datas[j]>>(int)(r*(k-1)))&base);
			   output[(int) c[block]-1]=datas[j];
			   c[block]=c[block]-1;
		   }
		   for(i=0;i<datas.length;i++)
			   datas[i]=output[i];
	   }
   } 
}
