import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 文件操作对象读写
 * @author sinllychen
 *
 */
public class FileOperate {
     private ObjectOutputStream writeObj;
     private ObjectInputStream readObj;
     public FileOperate(){};
     public Object readObject(String filePath)
     {
    	 Object o=null;
    	 try {
			readObj=new ObjectInputStream(new FileInputStream(filePath));
			o=readObj.readObject();
			readObj.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return o;
     }
     public boolean writeObject(Object o,String filePath)
     {
    	 boolean flag=false;
    	 try {
			writeObj=new ObjectOutputStream(new FileOutputStream(filePath));
			writeObj.writeObject(o);
			flag=true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 return flag;
    	 
     }
}
