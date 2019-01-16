
/**
 * 一些公共函数
 * @author sinllychen
 *
 */
public class Common {
	   /**
     * 交换两个数
     * @param datas
     * @param i
     * @param j
     */
    public static void swap(long[] datas,int i,int j)
    {
        long k=datas[i];
       datas[i]=datas[j];
        datas[j]= k;
    }
}
