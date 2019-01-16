/**
 * 归并排序
 * @author sinllychen
 *
 */
public class MergeSort {
	static public  long[] l;
	static public long[] r;
	  /**
     * 归并排序 
     * @param datas
     * @param i
     * @param j
     */
    public static void mergeSort(long[] datas,int i,int j)
    {
    	int q;
    	if(i<j)
    	{
    		l=new long[datas.length];
    		r=new long[datas.length];
    	    q=(int) Math.floor((i+j)/2);
    	    mergeSort(datas,i,q);
    	    mergeSort(datas,q+1,j);
    	    merge(datas,i,q,j);
    	}
    }
    /**
     * 将两个子数组进行合并
     * @param datas
     * @param i  
     * @param q  
     * @param j
     */
    public static void merge(long[] datas,int i,int q,int j)
    {
    	int n1=q-i+1;
    	int n2=j-q;
    	int m,n;
    	for(m=0;m<n1;m++)
    		 l[m]=datas[i+m];
    	for(n=0;n<n2;n++)
    		r[n]=datas[q+1+n];
     l[n1]=9223372036854775807l;
     r[n2]=9223372036854775807l;
     m=0;n=0;
     for(int p=i;p<=j;p++)
     {
    	 if(l[m]<=r[n])
    	 {
    		 datas[p]= l[m];
    		 m++;
    	 }
    	 else 
    	 {
    		 datas[p]=r[n];
    		 n++;
    	 }
     }
    }
}
