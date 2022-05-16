package library;

import java.time.LocalDate;
import requests.*;
import java.lang.*;

public class LibStudent implements LibUserInterface {
    //Basic Info for student, created constructor as there cannot be super() due to interface
    private String usrName;
    private int usrID;
    private long phoneNum;
    private String userEmail;
    private final int maxBorrowBooks = 3;
    private int currentAmountBorrowed;
    private LibBook[] borrowedBooks = new LibBook[maxBorrowBooks];
    private LocalDate[] borrowDates = new LocalDate[maxBorrowBooks];

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
            if(borrowDates[i].minusDays(4) == dateToday) {
                System.out.println("You have 4 days to return: " + borrowedBooks[i]);
            }
        }
    }

    // Two functions to update the student's borrowed amount of books
    public void incBorrowAmount() {
        currentAmountBorrowed++;
    }

    public void decBorrowAmount() {
        currentAmountBorrowed--;
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

    // Creates a request of the type order, and returns it
    public LibOrder createOrder(LibBook book, LibStudent student) {
        return new LibOrder(book.getBookID(),student.getUsrID());

    }

    // Creates a request of the type borrow, and returns it
    public LibBorrow createBorrow(LibBook book, LibStudent student) {
        return new LibBorrow(book.getBookID(),student.getUsrID());
    }

    // Creates a request of the type return, and returns it
    public LibReturn createReturn(LibBook book, LibStudent student) {
        return new LibReturn(book.getBookID(),student.getUsrID());
    }

    public void addBook(LibBook book) {
        for(int i = 0; i < maxBorrowBooks; i++) {
            if(borrowedBooks[i] == null) {
                borrowedBooks[i] = book;
                borrowDates[i] = LocalDate.now();
            }
        }
    }

    public void removeBook(LibBook book) {
        for(int i = 0; i < maxBorrowBooks; i++) {
            if(borrowedBooks[i].getBookID() == book.getBookID()) {
                borrowedBooks[i] = null;
                borrowDates[i] = null;
            }
        }
    }
}
