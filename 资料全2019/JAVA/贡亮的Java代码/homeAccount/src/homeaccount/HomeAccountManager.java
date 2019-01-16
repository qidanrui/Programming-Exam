package homeaccount;

import java.io.Serializable;
import java.util.*;
import java.io.*;

public class HomeAccountManager implements Serializable{
	
	public HomeAccountManager(){
		try{
			this.load();
		}catch(Exception ex){
			try {
				this.save();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private ArrayList<Record> records = new ArrayList<Record>();
	
	/**
	 * 保存信息
	 * @throws Exception
	 */
	public void save() throws Exception{
		FileOutputStream fout = new FileOutputStream("C:\\test.ser");
		ObjectOutputStream out = new ObjectOutputStream(fout);
		
		out.writeObject(this);
		out.close();
	}
	
	/**
	 * 从文件中载入信息
	 * @throws Exception
	 */
	public void load() throws Exception{
		FileInputStream fin = new FileInputStream("C:\\test.ser");
		ObjectInputStream in = new ObjectInputStream(fin);
		
		HomeAccountManager temp = (HomeAccountManager) in.readObject();
		this.records = temp.records;
		
		fin.close();
	}
	
	public void add(Record record){
		this.records.add(record);
		try {
			this.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(Record record){
		this.records.remove(record);
		try {
			this.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(Record record){
		for(int i=0;i<records.size();i++){
			if(records.get(i).id==record.id){
				this.records.set(i, record);
			}
		}
		try {
			this.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the records
	 */
	public ArrayList<Record> getRecords() {
		return records;
	}

	public void delete(int selectedIndex) {
		this.records.remove(selectedIndex);
		try {
			this.save();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}


