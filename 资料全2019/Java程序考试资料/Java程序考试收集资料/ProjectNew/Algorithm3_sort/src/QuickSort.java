import java.util.Random;
/**
 * 快速排序
 * @author sinllychen
 */
public class QuickSort {
	  /**
     * 随机获得分割点
     * @param datas
     * @param i   
     * @param j
     * @return
     */
    public static int randomPartition(long[] datas,int i,int j)
    {
    	Random rand=new Random();
    	int k=i+rand.nextInt(j-i+1);
    	Common.swap(datas,i,k);
    	return partition(datas,i,j);
    }
    /**
     * 获得分割点主要函数
     * @param datas
     * @param i
     * @param j
     * @return
     */
    public static int partition(long[] datas,int i,int j)
    {
    	long key=datas[i];
        int m=i+1;
        int n=j;
        while(m<=n)
        {
        	while(m<=n&&datas[m]<=key) m++;
        	while(m<=n&&datas[n]>=key) n--;
        	if(m>=n)
        		break;
        	Common.swap(datas,m,n);
        }
        datas[i]=datas[n];
        datas[n]= key;
        return n;
    }
    /**
     * 快速排序
     * @param datas
     * @param i
     * @param j
     */
    public static void quickSort(long[] datas,int i,int j)
    {
    	if(i<j)
    	{
    		int p=partition(datas,i,j);
    		quickSort(datas,i,p-1);
    		quickSort(datas,p+1,j);
    	}
    }
}
