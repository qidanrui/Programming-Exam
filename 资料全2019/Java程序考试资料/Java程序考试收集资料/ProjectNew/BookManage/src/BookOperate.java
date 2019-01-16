import java.util.ArrayList;
import java.util.List;


public class BookOperate {
    public static  List<Book> search(List<Book> books,String type,String author,String publisher,String receiver)
    {
    	List<Book> data=new ArrayList<Book>();
    	for(int i=0;i<books.size();i++)
    	{
    		String t=books.get(i).getType();
    		String a=books.get(i).getAuthor();
    		String p=books.get(i).getPublisher();
    		String r=books.get(i).getReceiver();
    		boolean flag=true;
    		if(!type.trim().equals(""))
    		{
    			if(!type.equals(t))
    				flag=false;
    		}
    		if(!author.trim().equals(""))
    		{
    			if(!author.equals(a))
    				flag=false;
    		}
    		if(!publisher.trim().equals(""))
    		{
    			if(!publisher.equals(p))
    				flag=false;
    		}
    		if(! receiver.trim().equals(""))
    		{
    			if(! receiver.equals(r))
    				flag=false;
    		}
    		if(flag)
    			data.add(books.get(i));
    	}
    	return data;
    }
    
    public static List<Book> deleteById(List<Book> books,  int id)
    {
    	List<Book> temp=new ArrayList<Book>();
    	for(int i=0;i<books.size();i++)
    	{
    		if(books.get(i).getId()!=id)
    			temp.add(books.get(i));
    	}
    	return temp;
    }
} 
