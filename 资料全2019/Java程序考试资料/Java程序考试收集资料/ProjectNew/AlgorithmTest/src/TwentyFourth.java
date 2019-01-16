import java.util.Scanner;

/**
 * 24点游戏
 * @author sinllychen
 *
 */
public class TwentyFourth {
	 public static void compute(int[] nums,int index)
	 {
		 if(index>=nums.length)
		 {
			   int[] opt=new int[3];
			   operate(nums,opt,0);
		 }
		 else
		 {
			 for(int i=index;i<nums.length;i++)
			 {
			      swap(nums,i,index);
			      compute(nums,index+1);
			      swap(nums,index,i);
			 }
		 }
	 }
	 public static void swap(int[] nums,int i,int j)
	 {
		 int key=nums[i];
		 nums[i]=nums[j];
		 nums[j]=key;
	 }
	 public static void operate(int[] nums,int[] opts,int index)
	 {
		 if(index>=opts.length)
		 {
			 check(nums,opts);
		 }
		 else
		 {
			 for(int i=0;i<4;i++)
			 {
				 opts[index]=i;
				 operate(nums,opts,index+1);
			 }
		 }
	 }
	 public static void check(int[] nums,int[] opts)
	 {
		 int sum=nums[0];
		 for(int i=0;i<opts.length;i++)
		 {
			 switch(opts[i])
			 {
			 case 0:
				   sum+=nums[i+1];
			      break;
			 case 1:
				    sum-=nums[i+1];
				    break;
			 case 2:
				    sum*=nums[i+1];
				    break;
			 case 3:
				    if(nums[i+1]!=0)
				    {
				    	sum/=nums[i+1];
					    break;
				    }
				    else return;
			 }
		 }
		 if(sum==24)
		 {
			 System.out.println("Sucess1!");
			 print(nums,opts);
		 }
		 
		 int part1=nums[0];
		 switch(opts[0])
		 {
		 case 0:  part1+=nums[1]; break;
		 case 1:  part1-=nums[1]; break;
		 case 2: part1*=nums[1]; break;
		 case 3: part1/=nums[1];break;
		 }
		 
		 int part2=nums[2];
		 switch(opts[2])
		 {
		 case 0:  part2+=nums[3]; break;
		 case 1:  part2-=nums[3]; break;
		 case 2: part2*=nums[3]; break;
		 case 3: part2/=nums[3];break;
		 }
		
		 int  s=0;
		 switch(opts[1])
		 {
		 case 0: s=part1+part2;break;
		 case 1: s=part1-part2;break;
		 case 2: s=part1*part2;break;
		 case 3: 
			  if(part2!=0)
			  {
				  s=part1/part2;
				  break;
			  }
			  else return;
		 }
		 if(s==24)
		 {
			 System.out.println("Success2!");
			 String[] os = new String[3];

				for (int j = 0; j < 3; ++j) {
					if (opts[j] == 0) {
						os[j] = "+";
					} else if (opts[j] == 1) {
						os[j] = "-";
					} else if (opts[j] == 2) {
						os[j] = "*";
					} else {
						os[j] = "/";
					}
				}
			 String str="("+nums[0]+os[0]+nums[1]+")"+os[1]+"("+nums[2]+os[2]+nums[3]+")";
			 System.out.println(str);
		 }
	 }
	 public static void print(int[] nums,int[] opts)
	 {
		 String str="((("+nums[0];
		 for(int i=0;i<opts.length;i++)
		 {
			 switch(opts[i])
			 {
			 case 0: str=str.concat("+"+nums[i+1]);break;
			 case 1: str=str.concat("-"+nums[i+1]); break;
			 case 2: str=str.concat("*"+nums[i+1]); break;
			 case 3:str=str.concat("/"+nums[i+1]);break;
			 }
			 str=str.concat(")");
		 }
		 System.out.println(str);
	 }

	 
     public static void main(String[] args)
     {
    	 Scanner input=new Scanner(System.in);
    	 System.out.println("请输入4个数字，用空格分开");
    	 int[] nums=new int[4];
    	 for(int i=0;i<4;i++)
    	 {
    		 nums[i]=input.nextInt(); 
    	 }
    	 compute(nums,0);
     }
}
