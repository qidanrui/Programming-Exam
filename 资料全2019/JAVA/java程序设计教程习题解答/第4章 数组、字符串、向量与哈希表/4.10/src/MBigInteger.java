
public class MBigInteger {

	/**
	 * @param args
	 */
	byte [] mb_data;
	public MBigInteger(){
		
	}
	public MBigInteger(String str){
		StringBuffer sb = new StringBuffer(str);
		sb.reverse();
		str=sb.toString();
		mb_data = str.getBytes();
	}
	
	MBigInteger add(MBigInteger a){  
		MBigInteger result = new MBigInteger();
	
		int c=0;
		
		
		if(this.mb_data.length >=a.mb_data.length){
			result.mb_data=new byte[this.mb_data.length+1];
			
			int i;
			for( i=0;i<a.mb_data.length;i++){
				int b=Integer.valueOf((char)this.mb_data[i])+Integer.valueOf((char)a.mb_data[i]+c)-96;
				int r=b%10;
				result.mb_data[i]=(byte)(r+48);
				if(b>9){
					c=1;
				}
				else{
					c=0;
				}
				
			}
			for( ;i<this.mb_data.length;i++){
				int x=Integer.valueOf((char)this.mb_data[i])+c-48;
				int y=x%10;
				result.mb_data[i]=(byte)(y+48);
				if(x>9){
					c=1;
				}
				else{
					c=0;
				}
				
			}
			
			result.mb_data[i]=(byte)(c+48);
			
			
		}
		return result;
	
		
	}
	
public byte [] minus(byte[] a){
		
		
		return a;
		
	}
public byte [] mul(byte[] a){
	
	
	return a;
	
}
public byte [] div(byte[] a){
	
	
	return a;
	
}
public String cts(){
	String s =new String();
	for(int i=0;i<this.mb_data.length;i++){
		s+=(char)this.mb_data[i];
		
	}
	StringBuffer sb = new StringBuffer(s);
	sb.reverse();
	s=sb.toString();
	return  s;
	
}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MBigInteger m= new MBigInteger("1258");
		MBigInteger n= new MBigInteger("456");
		MBigInteger r= new MBigInteger();
		r=m.add(n);
		System.out.print(r.cts());
	}

}
