package FiveChess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileOperate {
      private ObjectOutputStream writeObj;
      private ObjectInputStream readObj;
      public FileOperate(){};
      public boolean writeObj(Object o,String fileName)
      {
    	  boolean flag=false;
    	  try {
			writeObj=new ObjectOutputStream(new FileOutputStream(fileName));
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
      public Object readObject(String fileName)
      {
    	  Object o=null;
    	  try {
			readObj=new ObjectInputStream(new FileInputStream(fileName));
			o=readObj.readObject();
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
}
