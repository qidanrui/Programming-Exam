import javax.swing.JTextArea;

/**
 * 01背包的动态规划求解
 * @author sinllychen
 *
 */
public class Knapsack {
	 private static int[][] m;//m[i][j]表示i...n个物品中在容量为j的背包中可以存放的最大价值
	 private static int[] x;//x[i]用来存储物品i是否放入到背包中，x[i]=1时表示放入背包
	 public Knapsack(){ 
	 };
     public static void knapsack(int n,int c,int[] v,int[] w,JTextArea output)
     {
    	 m=new int[n+1][c+1];
    	 x=new int[n+1];
    	 int jmax=Math.max(w[n]-1, c);
    	 for(int j=0;j<=jmax;j++)//当只有一个物品n时，若w[n]>c则不放入背包，此时价值为0
    		 m[n][j]=0;
    	 for(int j=w[n];j<=c;j++)//当w[n]<=c时，此时放入背包，价值即为v[n];
    		 m[n][j]=v[n];
    	 for(int i=n-1;i>1;i--)
    	 {
    		 jmax=Math.max(w[i]-1, c);
    		 for(int j=0;j<=jmax;j++)//当w[i]>=c的时候，则该物品不加入相当于是从i+1个物品开始计算
    			 m[i][j]=m[i+1][j];
    		 for(int j=w[i];j<=c;j++)//当w[i]<c时则i个物品到n个物品之间的最大价值为i+1个物品的最大价值，或者是将该i物品放入之后的价值
    			 m[i][j]=Math.max(m[i+1][j], m[i+1][j-w[i]]+v[i]);
    	 }
    	 m[1][c]=m[2][c];
    	 if(w[1]<=c)
    		 m[1][c]=Math.max(m[1][c], m[2][c-w[1]]+v[1]);
    	 
    	 int temp=c;
    	 //输出最终数据
    	 for(int i=1;i<n;i++)
    	 {
    		 if(m[i][c]==m[i+1][c]) 
    			 x[i]=0;
    		 else
    		 {
    			 x[i]=1;c=c-w[i];
    		 }
    	 }
    	 if(m[n][c]>0)
    		 x[n]=1;
    	 else 
    		 x[n]=0;
    	 StringBuffer data=new StringBuffer();
    	 data.append("重量,价值\n");
    	 for(int i=1;i<=n;i++)
    	 {
    		 if(x[i]==1)
    			 data.append(w[i]+","+v[i]+"\n");
    	 }
    	 data.append("最大价值为:"+m[1][temp]);
    	 output.setText(data.toString());
     }
	/**
	 * @return the m
	 */
	public static int[][] getM() {
		return m;
	}
	/**
	 * @param m the m to set
	 */
	public static void setM(int[][] m) {
		Knapsack.m = m;
	}
	/**
	 * @return the x
	 */
	public static int[] getX() {
		return x;
	}
	/**
	 * @param x the x to set
	 */
	public static void setX(int[] x) {
		Knapsack.x = x;
	}
}
