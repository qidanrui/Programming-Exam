package thu.hcguo.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectSerialize {
	public static void main(String[] args) {
		Employee emps[] = new Employee[2];
		emps[0] = new Employee("E1", 10);
		emps[1] = new Employee("E2", 20);

		String filename = "test";
		writeObject(filename, emps);
		readObject(filename);
	}

	public static void writeObject(String filename, Employee[] emps) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(filename));
			oos.writeObject(emps);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void readObject(String filename) {
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(filename));
			Employee employee[] = (Employee[]) ois.readObject();
			for (Employee emp : employee) {
				System.out.println(emp.getName());
				System.out.println(emp.getAge());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

// 对象必须实现 Serializable 才可以进行序列化和反序列化
class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;

	public Employee() {
		name = "haha";
		age = 20;
	}

	public Employee(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
