import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 基本文件读写，包括字符串读写和对象读写,其中对象读写需要序列化
 * @author sinllychen
 *
 */
public class FileOperate {
      private   BufferedReader  reader;
      private  BufferedWriter  writer;
      private  ObjectInputStream objReader;
      private  ObjectOutputStream  objWriter;
      private  List<String> datas;
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
	}

	public FileOperate(){ datas=new ArrayList<String>();}
      
      /**
       * 读取文件
       * @param srcName  文件名称
       * @return  boolean
       */
      public   boolean  readFile(String srcName)
      {
    	  try {
			reader=new BufferedReader(new FileReader(srcName));
			String data;
			while((data=reader.readLine())!=null)
			{
				datas.add(data.trim());
			}
			reader.close();
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
      
      /**
       * 写入数据到文件
       * @param data   数据，LIst类型
       * @param destName   写入的文件名
       * @return
       */
      public boolean writeFile(List<String> data,String destName)
      {
    	  try {
			writer=new BufferedWriter(new FileWriter(destName,false));
			StringBuffer s=new StringBuffer();
			for(int i=0;i<data.size();i++)
				s.append(data.get(i)+"\r\n");
			writer.write(s.toString());
			writer.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
      }
      
      /**
       * 读取文件对象
       * @param fileName  文件名
       * @return
       */
      public  TestObj readObj(String fileName)
      {
    	  TestObj obj=new TestObj();
    	  try {
			objReader=new ObjectInputStream(new FileInputStream(fileName));
			obj=(TestObj) objReader.readObject();
			objReader.close();
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
		return obj;
      }
      
      /**
       * 向文件中写入对象
       * @param obj
       * @param fileName
       * @return
       */
      public  boolean writeObj(TestObj obj, String fileName)
      {
    	  try {
			objWriter=new ObjectOutputStream(new FileOutputStream(fileName,false));
			objWriter.writeObject(obj);
			objWriter.close();
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
 	 public static void main(String[] args)
     {
   	  FileOperate operate=new FileOperate();
   	  List<String> datas=new ArrayList<String>();
   	  for(int i=0;i<10;i++)
   		  datas.add(new String(i+"hello"));
   	  if(operate.writeFile(datas, "file.txt"))
   		  System.out.println("普通字符数组写入成功!");
   	  List<String> data2=new ArrayList<String>();
   	  if(operate.readFile("file.txt"))
   		  System.out.println("普通字符数组读取成功!");
   	  System.out.println("数组如下:");
   	  data2=operate.getDatas();
   	  for(int i=0;i<data2.size();i++)
   		  System.out.println(data2.get(i));
   	  System.out.println("*******************************");
   	  TestObj obj=new TestObj();
   	  obj.setAge(20);
   	  obj.setName("cxj");
   	  obj.setDatas(datas);
   	  if(operate.writeObj(obj, "obj.txt"))
   	     System.out.println("对象写入成功！");
   	  TestObj obj2=new TestObj();
   	  obj2=operate.readObj("obj.txt");
   	  if(obj2!=null)
   	  {
   		  System.out.println("对象读取成功！");
   		  System.out.println("对象如下所示:");
   		  System.out.println("姓名:"+obj2.getName());
   		  System.out.println("年龄:"+obj2.getAge());
   		  System.out.print("Data:");
   		  for(int i=0;i<obj2.getDatas().size();i++)
   			  System.out.print(obj2.getDatas().get(i)+" ");
   	  }
     } 
}
