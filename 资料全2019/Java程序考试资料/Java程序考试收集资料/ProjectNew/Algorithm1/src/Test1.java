import java.util.Scanner;
class Test1 {
    public static void main(String[] args)
    {
    	Scanner input=new Scanner(System.in);
    	int d2=87;
    	int d1=48;
    	String regex="[0-9a-z]*";
        String str=input.next();
        if(!str.matches(regex))
        	System.out.println("invalid input string");
        else
        {
        int[] count=new int[36];
        for(int i=0;i<str.length();i++)
        {
        	if(str.charAt(i)>=48 && str.charAt(i)<=57)
        	{
        		int index=str.charAt(i)-d1;
        		count[index]=count[index]+1;
        	}
        	else
        	{
        		int index=str.charAt(i)-d2;
        		count[index]=count[index]+1;
        	}
        }
        StringBuffer buf=new StringBuffer();

        for(int i=0;i<count.length;i++)
        {
            int flag=0;
            for(int j=0;j<count.length;j++)
           {
                 if(count[j]!=0)
                 {
                	 if(j<10)
                		 buf.append(String.valueOf((char)(j+d1)));
                	 else
                		 buf.append(String.valueOf((char)(j+d2)));
                	 count[j]=count[j]-1;
                	 flag=1;
                 }
                 else
                	 continue;
           }
            if(flag==0)
            	break;
        }
        System.out.println(buf.toString());
        }
    }

}
