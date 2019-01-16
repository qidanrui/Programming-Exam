/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package library;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author student
 */
public class Book implements Serializable, Comparable {
    public long book_id = System.currentTimeMillis();
    public String book_name = "unknown";
    public String book_type = "unknown";
    public String author = "unknown";
    public double price = 0.0;
    public Date publishDate = null;
    public String publisher = "unknown";
    public Date boughtDate = null;
    public String Borrower = "unknown";

    public Object[] toArray(){
        Object[] objs = new Object[9];
        objs[0] = book_id;
        objs[1] = book_name;
        objs[2] = book_type;
        objs[3] = author;
        objs[4] = price;
        if(publishDate!=null){
            objs[5] = publishDate.toString();
        }else{
            objs[5] = "unknown";
        }
        objs[6] = publisher;
        if(boughtDate!=null){
            objs[7] = boughtDate.toString();
        }else{
            objs[7] = "unknown";
        }
        objs[8] = Borrower;

        return objs;
    }

    public static Book getBook(Object[] objs){
        Book book = new Book();
        book.book_id = (Long)objs[0];
        book.book_name = (String)objs[1];
        book.book_type = (String)objs[2];
        book.author = (String)objs[3];
        book.price = (Double)objs[4];
        try{
            book.publishDate = new Date(Date.parse((String) objs[5]));
        }catch(Exception ex){
            book.publishDate = null;
        }
        book.publisher = (String)objs[6];

        try{
            book.boughtDate = new Date(Date.parse((String) objs[7]));
        }catch(Exception ex){
            book.boughtDate = null;
        }
        book.Borrower = (String)objs[8];
        return book;
    }

    public int compareTo(Object o) {
        Book anotherBook = (Book)o;
        if(this.price>anotherBook.price){
            return 1;
        }else if(this.price==anotherBook.price){
            return 0;
        }else{
            return -1;
        }
    }
}
