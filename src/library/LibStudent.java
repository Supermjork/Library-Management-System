package library;

import java.time.LocalDate;
import requests.*;
import java.lang.*;
import java.util.LinkedList;

public class LibStudent implements LibUserInterface {
    //Basic Info for student, created constructor as there cannot be super() due to interface
    private String usrName;
    private int usrID;
    private long phoneNum;
    private String userEmail;
    private final int maxBorrowBooks = 3;
    private int currentAmountBorrowed;
    private LinkedList<LibBook> borrowedBooks = new LinkedList<>();
    private LinkedList<LocalDate> borrowDates = new LinkedList<>();

    public LibStudent(String studentName, int studentID, long studentPhone, String studentEmail) {
        usrName = studentName;
        usrID = studentID;
        phoneNum = studentPhone;
        userEmail = studentEmail;
    }

    // Reminder function
    public void remind() {
        LocalDate dateToday = LocalDate.now();

        for(int i = 0; i < maxBorrowBooks; i++) {
            if(borrowDates.get(i).minusDays(4) == dateToday) {
                System.out.println("You have 4 days to return: " + borrowedBooks.get(i));
            }
        }
    }

    // Two functions to update/get the student's borrowed amount of books
    public void incBorrowAmount() {
        currentAmountBorrowed++;
    }

    public void decBorrowAmount() {
        currentAmountBorrowed--;
    }

    public int getCurrentAmountBorrowed() {
        return currentAmountBorrowed;
    }

    // Intellij created such methods and their @Override, I filled in the returns after copying the info fields

    @Override
    public void setName(String usrName) {
        this.usrName = usrName;
    }

    @Override
    public String getUsrName() {
        return usrName;
    }

    @Override
    public void setUsrID(int usrID) {
        this.usrID = usrID;
    }

    @Override
    public int getUsrID() {
        return usrID;
    }

    @Override
    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public long getPhoneNum() {
        return phoneNum;
    }

    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String getUserEmail() {
        return userEmail;
    }

    @Override
    public String toString() {
        return getUsrName() + "," + getUsrID() + "," + getPhoneNum() + "," + getUserEmail() + "\n";
    }

    // Creates a request of the type order using objects, and returns it
    public LibOrder createOrder(LibBook book, LibStudent student) {
        return new LibOrder(book.getBookID(),student.getUsrID());
    }

    // Creates a request of the type order using ids, and returns it
    public LibOrder createOrder(int bookId, int studentId) {
        return new LibOrder(bookId,studentId);
    }

    // Creates a request of the type borrow with objects, and returns it
    public LibBorrow createBorrow(LibBook book, LibStudent student) {
        return new LibBorrow(book.getBookID(),student.getUsrID());
    }

    
    // Creates a request of the type borrow with ids, and returns it
    public LibBorrow createBorrow(int bookId, int studentId) {
        return new LibBorrow(bookId,studentId);
    }

    // Creates a request of the type return using objects, and returns it
    public LibReturn createReturn(LibBook book, LibStudent student) {
        return new LibReturn(book.getBookID(),student.getUsrID());
    }

    // Creates a request of the type return using ids, and returns it
    public LibReturn createReturn(int bookId, int studentId) {
        return new LibReturn(bookId,studentId);
    }

    // Add/Remove books into parallel arrays of Book/Date

    public void addBook(LibBook book) {
        for(int i = 0; i < maxBorrowBooks; i++) {
            if(borrowedBooks.get(i) == null) {
                borrowedBooks.set(i, book);
                borrowDates.set(i, LocalDate.now());
            }
        }
    }

    public void removeBook(int bookID) {
        borrowedBooks.removeIf(removedBook -> bookID == removedBook.getBookID());
    }

    // Allowing the user to view their borrowed books and when their borrowed said books and when should they return

    public void showBorrowed() {
        for(LibBook borrowedBook : borrowedBooks) {
            System.out.println("Book Name: " + borrowedBook.getBookName() +
                               "\nBook Author: " + borrowedBook.getBookAuthor() +
                               "\nBook ID: " + borrowedBook.getBookID());
        }
    }
}
