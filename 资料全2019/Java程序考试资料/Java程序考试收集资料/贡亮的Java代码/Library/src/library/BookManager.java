/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package library;

import java.io.*;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author student
 */
public class BookManager {

    public BookManager(){
        try {
            this.load();
            this.updateTable();
        } catch (Exception ex) {
            Logger.getLogger(BookManager.class.getName()).log(Level.SEVERE, null, ex);
            this.displayList = books;
        }
    }
    
    private ArrayList<Book> books = new ArrayList<Book>();

    private ArrayList<Book> displayList = null;

    private ArrayList<Book> queryResult = new ArrayList<Book>();

    JTable table = null;
    DefaultTableModel model = null;

    public void setTable(JTable table){
        this.table = table;
        model = (DefaultTableModel)this.table.getModel();
    }

    private boolean response = true;

    public void updateTable(){
        if(table==null){
            return ;
        }
        response = false;
        DefaultTableModel model = (DefaultTableModel)table.getModel();


        this.clearTable(model);

        for(int i=0;i<displayList.size();i++){
            model.addRow(displayList.get(i).toArray());
        }

        table.updateUI();
        response = true;
    }

    protected void clearTable(DefaultTableModel model){
        while(model.getRowCount()>0){
            model.removeRow(0);
        }
    }

    public void addNewRow(){
        if(table==null){
            return ;
        }
        Book book = new Book();
        books.add(book);
        this.updateTable();
    }

    /**
     * update inner data structure according to the ui change
     */
    public void updateFromUI(int firstRow, int lastRow) {
        if(response == false){
            return ;
        }
        System.out.println(firstRow + "," + lastRow);
        for(int i=firstRow;i<=lastRow;i++){
            Vector vector = (Vector)(model.getDataVector().elementAt(i));
            Book book = Book.getBook(vector.toArray());
            //System.out.println(Arrays.toString(vector.toArray()));
            if(book.book_id==books.get(i).book_id){
                books.set(i, book);
            }
        }
        try {
            save();
        } catch (Exception ex) {
            Logger.getLogger(BookManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateFromUI() {
         if(response == false){
            return ;
        }
        for(int i=0;i<model.getRowCount();i++){
            Vector vector = (Vector)model.getDataVector();
            Book book = Book.getBook(vector.toArray());
            if(book.book_id==books.get(i).book_id){
                books.set(i, book);
            }
        }

        try {
            save();
        } catch (Exception ex) {
            Logger.getLogger(BookManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void save() throws Exception{
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("c:\\book.ser"));
        out.writeObject(books);
        out.close();
    }

    public void load() throws Exception{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("c:\\book.ser"));
        books = (ArrayList<Book>)in.readObject();
        displayList = books;
        in.close();
    }

    public void query(String content, String type){
        queryResult.clear();
        for(int i=0;i<books.size();i++){
            if(type.equals("Book Type")){
                if(books.get(i).book_type.contains(content)){
                    queryResult.add(books.get(i));
                }
            }else if(type.equals("Author")){
                if(books.get(i).author.contains(content)){
                    queryResult.add(books.get(i));
                }
            }else if(type.equals("Publisher")){
                if(books.get(i).publisher.contains(content)){
                    queryResult.add(books.get(i));
                }
            }else if(type.equals("Borrower")){
                if(books.get(i).Borrower.contains(content)){
                    queryResult.add(books.get(i));
                }
            }
        }

        System.out.println(queryResult.size());
        this.displayList = queryResult;
        this.updateTable();
    }

    public void showAll(){
        this.displayList = books;
        this.updateTable();
    }

    void deleteBook(int selectedRow) {
        if(displayList!=books){
            JOptionPane.showConfirmDialog(null, "You can only delete on Show All Model. \r\n Click \"Show All\" button to switch.");
            return ;
        }

        books.remove(selectedRow);
        this.updateTable();
    }

}
