import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FileOperate {
   private ObjectOutputStream writeObject;
   private ObjectInputStream  readObject;
   public FileOperate(){};
   public Object readObject(String fileName)
   {
	   Object o=null;
	   try {
		readObject=new ObjectInputStream(new FileInputStream(fileName));
		o=readObject.readObject();
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
   public boolean writeObject(Object object,String fileName)
   {
	   try {
		writeObject=new ObjectOutputStream(new FileOutputStream(fileName));
		writeObject.writeObject(object);
		return true;
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
	   
   }
}
