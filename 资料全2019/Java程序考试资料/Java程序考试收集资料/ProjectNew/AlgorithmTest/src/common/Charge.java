package common;

import java.io.Serializable;
import java.util.Date;

public class Charge  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long num;
	private String name;//姓名
	private String from;//来源
	private String  time;//发生的时间
	private double money;//收支金额
	private int type;//1表示收入，-1表示支出
	public Charge(){};
	public Charge(long num,String name,String from,String time,double money,int type){
		this.num=num;
		this.name=name;
		this.from=from;
		this.time=time;
		this.money=money;
		this.type=type;
	};
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the money
	 */
	public double getMoney() {
		return money;
	}
	/**
	 * @param money the money to set
	 */
	public void setMoney(double money) {
		this.money = money;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the num
	 */
	public long getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(long num) {
		this.num = num;
	}
	

}
