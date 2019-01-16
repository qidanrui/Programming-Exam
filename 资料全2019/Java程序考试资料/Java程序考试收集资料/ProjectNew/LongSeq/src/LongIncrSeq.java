import javax.swing.JTextArea;
/**
 * 最长递增子序列算法（三种形式)
 * 1 动态规划
 * 2 转换为最大公共子序列问题
 * 3 二分查找
 * @author cxj
 *
 */
public class LongIncrSeq {
	  public static int[] p;//保存以i元素结尾的最长递增序列的长度
	  public static int[] maxEle;//保存长度为i+1的递增序列的最大元素的最小值
	  public static int[][] lcs;
	  public static int[][] mark;
	  public static int[] flag;
	  public static int[] mem;
	  /**
	   * 动态规划算法求解，时间复杂度为n2
	   * @param data
	   * @return
	   */
      public static int getLongIncrSeqByN2(int[] data)
      {
    	  int length=data.length;
    	  p=new int[length];//表示以下表为i的元素结尾的最长递增子序列
    	  int  max=0;
    	  p[0]=1;
    	  for(int i=1;i<data.length;i++)
    	  {
    		  p[i]=1;//初始均为1
    		  for(int j=0;j<i;j++)//寻找在i元素之前的最长递增序列
    		  {
    			//找到以j元素结尾的递增子序列最大长度的p[j]，则以i元素结尾的递增子序列的长度为p[j]+1
    			  if(data[j]<=data[i]&&p[j]+1>p[i])
    			  {
    				  p[i]=p[j]+1;
    				  if(p[i]>max)
    					  max=p[i];
    			  }
    		  }
    	  }
    	  return max;
      }
      /**
       * 动态规划算法求解的输出
       * @param textArea
       * @param maxLength
       * @param data
       * @param n
       */
      public static void outputByN2(JTextArea textArea,int maxLength,int [] data,int n)
      {
    	  boolean isEle=false;
    	  if(n<0||maxLength==0)
    		  return ;
    	  if(p[n]==maxLength)
    	  {
    		  --maxLength;
    		  isEle=true;
    	  }
    	  outputByN2(textArea,maxLength,data,--n);
    	  if(isEle)
    	  {
    		  textArea.setText(textArea.getText()+data[n+1]+",");
    	  }
      }
      /**
       * 通过将数组maxEle长度为i+1的最长子序列中最大元素的最小值。
       * 若data[i]>maxEle(length-1)，相当于此时data[i]极为比已有的最大长度最大值大，因此只需直接放置其后后面，增加其长度即可。
       * 当data[i]<=maxEle(length-1)，在数组maxEle中通过二分寻找第一个比data[i]大的数，将该data[i]替换该元素，相当于是让最长递增子序列最后一个数尽量
       * 小，使得最后的递增长度越大，类似于贪心算法。算法时间复杂度为nlgn
       * @param data
       * @return
       */
      public static int getLongIncrSeqByNlgn(int[] data)
      {
    	  maxEle=new int[data.length+1];
    	  maxEle[0]=-10000;
    	  maxEle[1]=data[0];//初始化第一个元素其递增子序列长度为1
    	  mem=new int[data.length];
    	  mem[0]=1;
    	  int length=1;
    	  int left,right,m;
    	  for(int i=1;i<data.length;i++)
    	  {
    		  if(data[i]>=maxEle[length])//当元素大于长度最大递增子序列最小值，无需查找
    		  {
    			  length=length+1;
    			  mem[i]=length;//保存以i元素结尾的子序列长度
    			  maxEle[length]=data[i];
    			  
    		  }
/*    		  else if(data[i]==maxEle[length])
    		      continue;*/
    		  else 
    		  {
        		  left=0;
        		  right=length;
    			  while(left<=right) //搜索递增序列最大值得最小值刚好大于data[i]的，则将其替换，相当于是保存最小值，让序列能够更容易增加
        		  {
        			  m=(left+right)/2;
        			  if(maxEle[m]<=data[i])
        				   left=m+1;
        			  else 
        				  right=m-1;
        		  }
    			 maxEle[left]=data[i];
   			     mem[i]=left;
    		  }
    	  }
    	  return length;
      }
      /**
       * nlgn算法输出
       * @param textArea
       * @param length
       * @param data
       */
      public static void outputByNlgn(JTextArea textArea,int length,int[] data)
      {
    	int[] p=new int[data.length];
    	int k=length-1;
    	int pos=0;
    	for(int i=0;i<mem.length;i++)//找到第一个长度为length的序列的尾元素
    	{
    		if(mem[i]==length)
    		{
    			pos=i;
    			break;
    		}
    	}
    	p[k]=data[pos];//长度为length的尾元素
    	for(int i=pos-1;i>=0;i--)//逆序寻找长度小于k并且值小于p[k]的元素
    		if(mem[i]==k&&data[i]<=p[k]) 
    		{
    			p[--k]=data[i];
    		}
    		for(int i=0;i<length;i++)
    		{
    			textArea.setText(textArea.getText()+p[i]+",");
    		}
      }
      /**
       * 通过将序列递增排序之后同该序列求最大公共子序列
       * @param data
       * @return
       */
      public static int getLongIncrSeqByLCS(JTextArea textArea,int[] data)
      {
    	  lcs=new int[data.length+1][data.length+1];
    	  mark=new int[data.length+1][data.length+1];
    	  int[] temp=new int[data.length];
    	  for(int i=0;i<data.length;i++)
    		  temp[i]=data[i];
    	  quickSort(temp,0,temp.length-1);
    	  for(int i=0;i<=temp.length;i++)
    	  {
    		  lcs[i][0]=0;
    	  }
    	  for(int i=0;i<=data.length;i++)
    		  lcs[0][i]=0;
    	  for(int i=1;i<=temp.length;i++)
    	  {
    		  for(int j=1;j<=data.length;j++)
    		  {
    			  if(temp[i-1]==data[j-1])
    			  {	  lcs[i][j]=lcs[i-1][j-1]+1;
    			      mark[i][j]=-2;
    			  }
    			  else if(lcs[i-1][j]>=lcs[i][j-1])
    			  {
    				  lcs[i][j]=lcs[i-1][j];
    				  mark[i][j]=-1;
    			  }
    			  else
    			  {
    				  lcs[i][j]=lcs[i][j-1];
    				  mark[i][j]=0;
    			  }
    		  }
    	  }
    	  flag=new int[1000];
    	  outputLcs(textArea,data,data.length,data.length);
    	  return lcs[temp.length][data.length];
      }
      /**
       * LCS字符串输出
       * @param textArea
       * @param data
       * @param xlen
       * @param ylen
       */
      public static void outputLcs(JTextArea textArea,int[] data,int xlen,int ylen)
      {
    	  if(xlen==0||ylen==0)
    		  return ;
    	  if(mark[xlen][ylen]==-2)
    	  {
    		  outputLcs(textArea,data,xlen-1,ylen-1);
/*    		  if(flag[data[ylen-1]]==0)
    		  {*/
    			  textArea.setText(textArea.getText()+data[ylen-1]+",");
/*    			  flag[data[ylen-1]]=1;*/
/*    		  }*/
/*    		  else
    			  lcs[xlen][ylen]--;*/
    	  }
    	  else if(mark[xlen][ylen]==-1)
    	  {
    		  outputLcs(textArea,data,xlen-1,ylen);
    	  }
    	  else
    	  {
    		  outputLcs(textArea,data,xlen,ylen-1);
    	  }
    	  
      }
      /**
       * 获得分割点主要函数
       * @param datas
       * @param i
       * @param j
       * @return
       */
      public static int partition(int[] datas,int i,int j)
      {
      	 int  key=datas[i];
          int m=i+1;
          int n=j;
          while(m<=n)
          {
          	while(m<=n&&datas[m]<=key) m++;
          	while(m<=n&&datas[n]>=key) n--;
          	if(m>=n)
          		break;
          	swap(datas,m,n);
          }
          datas[i]=datas[n];
          datas[n]= key;
          return n;
      }
      public static void swap(int[] data,int i,int j)
      {
    	  int key=data[i];
    	  data[i]=data[j];
    	  data[j]=key;
      }
      /**
       * 快速排序
       * @param datas
       * @param i
       * @param j
       */
      public static void quickSort(int[] datas,int i,int j)
      {
      	if(i<j)
      	{
      		int p=partition(datas,i,j);
      		quickSort(datas,i,p-1);
      		quickSort(datas,p+1,j);
      	}
      }
}
