
public class MyRandom {

	/**
	 * @param args
	 */
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			int [] result = new int [21];
			for(int i=0;i<21;i++){
				result[i]=0;
			}
		for(int i=0;i<10000;i++){
			int num = (int)(Math.random()*20+0.5);
			
			
			switch(num){
			case 1:
				result[1]++;
				break;
			case 2:
				result[2]++;
				break;
			case 3:
				result[3]++;
				break;
			case 4:
				result[4]++;
				break;	
			case 5:
				result[5]++;
				break;
			case 6:
				result[6]++;
				break;
			case 7:
				result[7]++;
				break;
			case 8:
				result[8]++;
				break;	
			case 9:
				result[9]++;
				break;
			case 10:
				result[10]++;
				break;
			case 11:
				result[11]++;
				break;
			case 12:
				result[12]++;
				break;	
			case 13:
				result[13]++;
				break;
			case 14:
				result[14]++;
				break;
			case 15:
				result[15]++;
				break;
			case 16:
				result[16]++;
				break;	
			case 17:
				result[17]++;
				break;
			case 18:
				result[18]++;
				break;
			case 19:
				result[19]++;
				break;
			case 20:
				result[20]++;
				break;	
			
			default:
				break;
			}
		}
		for(int i=1;i<21;i++){
		System.out.println(result[i]);
		}
	}

}
