import java.util.ArrayList;
/**
 * 插入排序
 * @author sinllychen
 *
 */
public class InsertSort {
	/**
	 * 插入排序
	 * @param datas
	 */
    public static void insertSort(long[] datas)
    {
    	for(int j=1;j<datas.length;j++)
    	{
    		long key=datas[j];
    		int i=j-1;
    		while(i>=0&&datas[i]>key)
    		{
    			datas[i+1]=datas[i];
    			i=i-1;
    		}
    		datas[i+1]= key;
    	}
    }
}
