package homeaccount;

import java.util.Date;
import java.io.Serializable;

public class Record implements Serializable{
	public final long id = System.currentTimeMillis();
	public String userName = "";
	public double amount = 0.0;
	public String destination = "";
	public Date date = null;
	
	public String toString(){
		return "id: " + id + " | " + userName + " | " + "$" + amount + " | " + destination + " | " + date; 
	}
}
