/**
 * 插入排序
 * @author sinllychen
 *
 */
public class InsertSort {
    public static void main(String[] args)
    {
    	int[] a={5,2,6,7,1,3,9,0,10};
    	int i,j;
    	for(i=1;i<a.length;i++)
    	{
    		int key=a[i];
    		j=i-1;
    		while(j>=0&&a[j]>key)
    		{
    			a[j+1]=a[j];
    			j--;
    		}
    		a[j+1]=key;
    	}
    	for(i=0;i<a.length;i++)
    		System.out.print(a[i]+" ");
    }
}
