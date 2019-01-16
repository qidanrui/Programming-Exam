import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Common {
      public static void main(String args[])
      {
    	  //日期获取
    	  Calendar c=Calendar.getInstance();
    	  int year=c.get(Calendar.YEAR);
    	  int month=c.get(Calendar.MONTH)+1;//注意月份需要加1
    	  int day=c.get(Calendar.DAY_OF_MONTH);
    	  System.out.println("year:"+year+","+"month:"+month+","+"day:"+day);
    	  Date d=c.getTime();
    	  
    	  //日期格式化及同String相互转换
    	  SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	  String data=format.format(d);
    	  System.out.println("转换后的字符串:"+data);
    	  String time="2011-12-12 11:23:23";
    	  try {
			Date d2=format.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//日期之间比较
    	  format.applyPattern("yyyy-MM-dd");
    	  String time1="2013-12-23";
    	  String time2="2012-12-12";
    	  try {
			Date t1=format.parse(time1);
	    	Date t2=format.parse(time2);
	    	if(t1.after(t2))
	    		System.out.println("t1 after t2");
	    	else 
	    		System.out.println("t1 before t2");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  
    	//浮点类型格式化输出
    	  double d1 = 234.3434343;
    	  DecimalFormat df=new DecimalFormat("#.00"); //#表示当前位置有字符则显示，没有字符则不显示
    	  String pattern="0000.00";
    	  System.out.println(df.format(d1));
    	  df.applyPattern(pattern);
    	  System.out.println(df.format(d1));
    	  
    	  //获得当前工程路径
    	  System.out.println(Common.class.getResource("/"));
    	  System.out.println(System.getProperty("user.dir"));
      }
}
