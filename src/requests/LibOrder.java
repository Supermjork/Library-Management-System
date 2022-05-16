package requests;

import library.LibBook;
import library.LibStudent;
import java.time.LocalDate;

public class LibOrder implements LibRequest {
    protected long bookID;          //Adds the ordered book's ID.
    protected int studentID;        //Adds the Student's ID (Who had ordered the book).
    protected LocalDate orderDate;  //Assigns the current date when the request had been created.

    //Constructor for the Order object that holds the book ID, student ID, Date (Will be same for Borrow)
    public LibOrder(long bookID, int studentID) {
        this.bookID = bookID;
        this.studentID = studentID;
        this.orderDate = LocalDate.now();
    }

    /*Method from interface to create the request, takes in the book object, and the student object
      Then creates a new order object within that holds the book ID and the user ID
     */
    @Override
    public LibRequest request(LibBook book, LibStudent student) {
        return new LibOrder(book.getBookID(), student.getUsrID());
    }

    @Override
    public void allowRequest() {}

    @Override
    public void denyRequest() {}

    @Override
    public String toString() {
        return "The ordered book ID: " + bookID + ", the ordering user ID: " + studentID +
                ", ordered on: " + orderDate;
    }

    //Getters and Setters for the book ID, student ID, and order date
    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
