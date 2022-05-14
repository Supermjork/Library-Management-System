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
    public LibRequest createRequest(LibBook book, LibStudent student) {
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
}
