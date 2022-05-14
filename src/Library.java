import library.*;
import reader.*;
import requests.*;
import java.util.*;

/**
 * The main class that the objects will be used in.
 * @author Icen Zeyada
 */
public class Library {
    public static void main(String[] args) {
        List<LibBook> bookList = new ArrayList<LibBook>();
        List<LibStudent> studentList = new ArrayList<LibStudent>();
        List<LibAdmin> adminList = new ArrayList<LibAdmin>();
        List<LibOrder> orderList = new ArrayList<LibOrder>();
        List<LibBorrow> borrowList = new ArrayList<LibBorrow>();
        List<LibReturn> returnList = new ArrayList<LibReturn>();


        CsvFileReader.loadDataBook("books.csv", bookList);
        System.out.println("\nBooks loaded: " + bookList.size());
        for(LibBook book : bookList) {
            System.out.println(
                        "name: " + book.getBookName() + 
                        " author: " + book.getBookAuthor() + 
                        " id: " + book.getBookID() + 
                        " isslibdate: " + book.getIssLibDate() + 
                        " issgldate: " + book.getIssGloDate() +
                        " Number in stock: " + book.getStockAmount()
                        );
        }

        CsvFileReader.loadDataStudent("students.csv", studentList);
        System.out.println("\nStudents loaded: " + studentList.size());
        for(LibStudent student : studentList) {
            System.out.println(
                        "name: " + student.getUsrName() + 
                        " id: " + student.getUsrID() + 
                        " phone: " + student.getPhoneNum() + 
                        " email: " + student.getUserEmail()
                        );
        }

        CsvFileReader.loadDataAdmin("admins.csv", adminList);
        System.out.println("\nAdmins loaded: " + adminList.size());
        for(LibAdmin admin : adminList) {
            System.out.println(
                        "name: " + admin.getUsrName() + 
                        " id: " + admin.getUsrID() + 
                        " phone: " + admin.getPhoneNum() + 
                        " email: " + admin.getUserEmail()
                        );
        }

        CsvFileReader.loadDataOrder("orders.csv", orderList);
        System.out.println("\nOrders loaded: " + orderList.size());
        for(LibOrder order : orderList) {
            System.out.println(
                        " bookid: " + order.getBookID() + 
                        " studentid: " + order.getStudentID() + 
                        " orderdate: " + order.getOrderDate()
                        );
        }

        CsvFileReader.loadDataBorrow("borrows.csv", borrowList);
        System.out.println("\nBorrows loaded: " + borrowList.size());
        for(LibBorrow borrow : borrowList) {
            System.out.println(
                        " bookid: " + borrow.getBookID() + 
                        " studentid: " + borrow.getStudentID() + 
                        " borrowdate: " + borrow.getBorrowDate()
                        );
        }

        CsvFileReader.loadDataReturn("returns.csv", returnList);
        System.out.println("\nReturns loaded: " + returnList.size());
        for(LibReturn return1 : returnList) {
            System.out.println(
                        " bookid: " + return1.getBookID() + 
                        " studentid: " + return1.getStudentID() + 
                        " returndate: " + return1.getReturnDate()
                        );
        }
    }
}
