import java.io.Serializable;
import java.util.List;


public class TestObj  implements Serializable{
      /**
	 * 
	 */
	private static final long serialVersionUID = 3108596690908496001L;
	private int num;
      private String name;
      private List<String> datas;
      private int age;
      public TestObj(){}
      public TestObj(int num,String name,int age,List<String> datas)
      {
    	  this.num=num;
    	  this.name=name;
    	  this.age=age;
    	  this.setDatas(datas);
      }
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
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
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the datas
	 */
	public List<String> getDatas() {
		return datas;
	}
	/**
	 * @param datas the datas to set
	 */
	public void setDatas(List<String> datas) {
		this.datas = datas;
	};
      
}
