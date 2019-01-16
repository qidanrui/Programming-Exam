
public class GeneralSort {
   public static void main(String[] args)
   {
	   int a[]={5,4,3,2,1,0,4,2,3,1,23,54};
	   /*bubbleSort(a,0,a.length-1);
	   System.out.println("冒泡排序");
	   for(int i=0;i<a.length;i++)
		   System.out.println(a[i]+" ");
	   insertSort(a,0,a.length-1);
	   System.out.println("插入排序");
	   for(int i=0;i<a.length;i++)
		   System.out.println(a[i]+" ");*/
	   for(int i=1;i<4;i++)
		   shellInsert(a,0,a.length-1,i);
	   System.out.println("希尔排序");
	   for(int i=0;i<a.length;i++)
		   System.out.println(a[i]+" ");
	   
   }
   //插入排序,找到关键字不小于r[i].key的记录, 并在查找的同时实现记录向后移动:

   public static void insertSort(int[] a,int p,int q )
   {
	   int t,i,j;
	   for( i=p+1;i<=q;i++)
	   {
		   t=a[i];
		   for(j=i-1;j>=p&&t<a[j];j--)
			   a[j+1]=a[j];
		   a[j+1]=t;
	   }
	   
   }
   //普通冒泡排序
   public static void bubbleSort(int[] a,int p,int q)
   {
	   for(int i=p;i<q;i++)
	   {
		   for(int j=p;j<q-i;j++)  
		   {
			   int temp=a[j];
			   a[j]=a[j+1];
			   a[j+1]=temp;
		   }
	   }
   }
   //希尔排序,m表示划分范围将整个序列分成d个子序列，对每个子序列分别排序。逐步减少子序列列数d，直至将d=1为止。
   public static void shellInsert(int a[] ,int p,int q,int m)
   {
	   int i,j,k,key;
	   for(i=p;i<=q&&i<=p+m-1;i++)
	   {
		   
		   for( j=i+m;j<=q;j+=m)
		   {
			   key=a[j];
			   for(k=j-m;k>=p&&key<a[k];k-=m)
				    a[k+m]=a[k];
			   a[k+m]=key;
		   }
			   
	   }
   }

}
