package requests;

import library.LibBook;
import library.LibStudent;

import java.time.LocalDate;

public class LibBorrow implements LibRequest {
    protected long bookID;          //Adds the ordered book's ID.
    protected int studentID;        //Adds the Student's ID (Who had ordered the book).
    protected LocalDate borrowDate;  //Assigns the current date when the request had been created.

    //Constructor for the Order object that holds the book ID, student ID, Date
    public LibBorrow(long bookID, int studentID) {
        this.bookID = bookID;
        this.studentID = studentID;
        this.borrowDate = LocalDate.now();
    }

    //Creates a request of type Borrow, carries the book ID and user ID, also prints out the due date after 2 weeks
    @Override
    public LibRequest createRequest(LibBook book, LibStudent student) {
        LibBorrow borrow = new LibBorrow(book.getBookID(), student.getUsrID());
        System.out.println("Your due date is: " + borrowDate.plusDays(14));
        student.addAmountBorrowed();
        return borrow;
    }

    @Override
    public String toString() {
        return "The borrowed book ID: " + bookID + ", the borrowing user ID: " + studentID +
               ", Borrowed on: " + borrowDate + ", and should return on: " + borrowDate.plusDays(14);
    }
}
