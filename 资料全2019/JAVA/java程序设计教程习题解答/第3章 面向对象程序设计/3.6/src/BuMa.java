import javax.swing.JOptionPane;


public class BuMa {

	/**
	 * @param args
	 */
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str;
		str = JOptionPane.showInputDialog("请输入一个整数","0");
		int num = Integer.parseInt(str);
		String result2 = Integer.toBinaryString(num);
		String result8 = Integer.toOctalString(num);
		String result16 = Integer.toHexString(num);
		
		
		
		System.out.println("二进制数为:"+result2);
		System.out.println("八进制数为:"+result8);
		System.out.println("十六进制数为:"+result16);
	}

}
