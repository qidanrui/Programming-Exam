public class MyCalendar {

	/**
	 * @param args
	 */
	int year ;
	int month ;
	int day ;
	int week ;
	
	public MyCalendar(){
		
	}
	
	public MyCalendar(int m,int d){
		month=m;
		day = d;
		week = calw();
	}
	public boolean yes(){
		if (day%10 == week)
			return true;
		else
			return false;
	}
	
	public int calw() {
		switch (month) {
		case 1:
			return (day+4)%7+1;
		case 2:
			return (31+day+4)%7+1;
		case 3:
			return (28+31+day+4)%7+1;
		case 4:
			return (31+28+31+day+4)%7+1;
		case 5:
			return (30+31+28+31+day+4)%7+1;
		case 6:
			return (31+30+31+28+31+day+4)%7+1;
		case 7:
			return (30+31+30+31+28+31+day+4)%7+1;
		case 8:
			return (31+30+31+30+31+28+31+day+4)%7+1;
		case 9:
			return (31+31+30+31+30+31+28+31+day+4)%7+1;
		case 10:
			return (30+31+31+30+31+30+31+28+31+day+4)%7+1;
		case 11:
			return (31+30+31+31+30+31+30+31+28+31+day+4)%7+1;
		case 12:
			return (30+31+30+31+31+30+31+30+31+28+31+day+4)%7+1;

		default:
			return 0;
			
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		MyCalendar m =new MyCalendar(3,22);
//		System.out.println(m.calw());
//		
		
		int result = 0;
		for(int m1=1;m1<13;m1++){
			if(m1==1||m1==3||m1==5||m1==7||m1==8||m1==10||m1==12){
				for(int d=1;d<31;d++){
					if(new MyCalendar(m1,d).yes()==true){
						result++;
					}
				}
			}
			else if(m1==2){
				for(int d=1;d<28;d++){
					if(new MyCalendar(m1,d).yes()==true){
						result++;
					}
				}
			}	
			else{
				for(int d=1;d<30;d++){
					if(new MyCalendar(m1,d).yes()==true){
						result++;
					}
				}
			}
				
		}	
		
		System.out.println("result:"+result);
	}

}
