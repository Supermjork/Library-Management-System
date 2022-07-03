package library;

import requests.*;

/**
 * Child class of the libUser which inherits basic fields, uses super constructor, also overrides privileges
 */
public class LibAdmin implements LibUserInterface {
    // Basic Info for admin, created constructor as there cannot be super() due to interface
    private String usrName;
    private int usrID;
    private long phoneNum;
    private String userEmail;

    public LibAdmin(String studentName, int studentID, long studentPhone, String studentEmail) {
        usrName = studentName;
        usrID = studentID;
        phoneNum = studentPhone;
        userEmail = studentEmail;
    }

    // Intellij created such methods and their @Override, I filled in the returns after copying the info fields
    // This is an entire copy-paste of LibStudent.java

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
        return getUsrName() + "," +  getUsrID() + "," + getPhoneNum() + "," + getUserEmail();
    }

    // Request Creation methods are not used, may be deleted
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

}
