
public class Fibonnaci {
      public static int fibonnaci(int n)
      {
    	  if(n==0)
    		  return 0;
    	  if(n==1)
    		  return 1;
    	  return fibonnaci(n-1)+fibonnaci(n-2);
      }
      
      public static double fibonnaciGeneral(int n)
      {
    	  return (1/Math.sqrt(5))*(Math.pow((1+Math.sqrt(5))/2, n)-Math.pow(1-Math.sqrt(5)/2, n));
    	  
      }
      public static void main(String[] args)
      {
    	  System.out.println(fibonnaci(30));
      }
}
