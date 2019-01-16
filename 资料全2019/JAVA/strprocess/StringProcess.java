package thu.hcguo.strprocess;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringProcess {

	public static void process(String str1, String str2) {
		// str1 < str2 返回负数; str1 = str2 返回0; str1 > str2 返回正数
		System.out.println(str1.compareTo(str2));

		// 忽略大小写的比较 str1.compareToIgnoreCase(str2);
		System.out.println(str1.compareToIgnoreCase(str2));
		
		// 将str2拼接到str1后
		System.out.println(str1.concat(str2));
		
		// str1是否包含str2
		System.out.println(str1.contains(str2));
		
		// str1是否与str2相等
		System.out.println(str1.equals(str2));
		
		// str1是否与str2相等(忽略大小写)
		System.out.println(str1.equalsIgnoreCase(str2));
		
		// 返回指定子字符串在此字符串中第一次出现处的索引
		System.out.println(str1.indexOf(str2));

		// 返回指定子字符串在此字符串中最右边出现处的索引
		System.out.println(str1.lastIndexOf(str2));

		// 根据给定正则表达式的匹配拆分此字符串
		for (String str : str1.split(" ")) {
			System.out.println(str);
		}

		// 使用默认语言环境的规则将此 String 中的所有字符都转换为小写
		System.out.println(str1.toLowerCase());
		
		// 使用默认语言环境的规则将此 String 中的所有字符都转换为大写
		System.out.println(str1.toUpperCase());

		// 返回字符串的副本，忽略前导空白和尾部空白
		System.out.println(str1.trim());

		// 返回一个新字符串，它是此字符串的一个子字符串。该子字符串从指定的 beginIndex 处开始，直到索引 endIndex - 1
		// 处的字符。因此，该子字符串的长度为 endIndex-beginIndex
		System.out.println(str1.substring(1,3));
		
		// 字符串替换
		System.out.println(str1.replace("a", "b"));
		
		// 返回boolean char int double float long等参数的字符串表示形式。
		// 其中str1属于上述的类型
		System.out.println(String.valueOf(str1));
		
		// 返回一个新的 double 值，该值被初始化为用指定 String 表示的值，这与 Double 类的 valueOf 方法一样。
		// 其中param属于String类型，类似的函数还有Integer.parseInt(param)等
		// 可以通过捕获这个函数的异常，来判断param是否是可以解析为double
		System.out.println(Double.parseDouble(str1));
		
		// 日期的处理
		Date date = new Date();
		System.out.println(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		System.out.println(format.format(date));
	}
	
	public static void main(String[] args) {
		String str1 = "23.5";
		String str2 = "asdfj";
		process(str1, str2);
	}
}
