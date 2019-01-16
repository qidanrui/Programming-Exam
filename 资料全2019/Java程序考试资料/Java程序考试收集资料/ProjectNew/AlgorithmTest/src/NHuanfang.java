import java.util.Scanner;

/**
 * N幻方问题，在正方形中所有的横的和，竖的和，以及斜对角线的和都相等
 * @author sinllychen
 *
 */
public class NHuanfang {
	 private static int count=0;
	 private static int n;
     public static void permulate(int[] m,int i,int j)
     {
    	 if(i>j)
    	 {
    		 int[][] k=new int[n][n];
    		 int index=0;
    		 for(int a=0;a<n;a++)
    		 {
    			 for(int b=0;b<n;b++)
    			 {
    				 k[a][b]=m[index];
    				 index++;
    			 }
    		 }
    		 if(checkTrue(k))
    		 {
    			 count++;
    			 System.out.println("第"+count+"种解法:");
    			 print(k);
    		 }
    	 }
    	 else
    	 {
    		 for(int k=i;k<m.length;k++)
    		 {
    			 swap(m,i,k);
    			 permulate(m,i+1,j);
    			 swap(m,k,i);
    		 }
    	 }
     }
     public static void swap(int[] m,int i,int j)
     {
    	 int key=m[i];
    	 m[i]=m[j];
    	 m[j]=key;
     }
     public static void print(int[][] k)
     {
    	 for(int i=0;i<n;i++)
    	 {
    		 for(int j=0;j<n;j++)
    			 System.out.print(k[i][j]+"  ");
    		 System.out.println();
    	 }
    	 System.out.println();
     }
     public static boolean checkTrue(int[][] k)
     {
    	 int sum=0;
    	 for(int i=0;i<n;i++)
    	 {
    		 sum=sum+k[0][i];
    	 }
    	 for(int i=1;i<n;i++)
    	 {
    		 int s=0;
    		 for(int j=0;j<n;j++)
    		 {
    			 s=s+k[i][j];
    		 }
    		 if(s!=sum)
    			 return false;
    	 }
    	 for(int i=0;i<n;i++)
    	 {
    		 int s=0;
    		 for(int j=0;j<n;j++)
    		 {
    			 s=s+k[j][i];
    		 }
    		 if(s!=sum)
    			 return false;
    	 }
    	 int s=0;
    	 for(int i=0;i<n;i++)
    	 {
    		 s=s+k[i][i];
    	 }
		 if(s!=sum)
			 return false;
		 
    	 s=0;
    	 for(int i=0;i<n;i++)
    	 {
    		 s=s+k[i][n-i-1];
    	 }
		 if(s!=sum)
			 return false;
		 
    	 return true;
     }
     public static void main(String[] args)
     {
    	 Scanner input=new Scanner(System.in);
    	 System.out.println("请输入n:");
    	 n=input.nextInt();
    	 int[] m=new int[n*n];
    	 for(int i=0;i<m.length;i++)
    	 {
    		 m[i]=i+1;
    	 }
    	 permulate(m,0,m.length-1);
    	 System.out.println("总数为"+count);
     }
}
