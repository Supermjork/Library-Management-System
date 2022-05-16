package requests;

import library.LibBook;
import library.LibStudent;

import java.time.LocalDate;

public class LibBorrow implements LibRequest {
    protected long bookID;           // Adds the ordered book's ID.
    protected int studentID;         // Adds the Student's ID (Who had ordered the book).
    protected LocalDate borrowDate;  // Assigns the current date when the request had been created.

    // Constructor for the Borrow object that holds the book ID, student ID, Date
    public LibBorrow(long bookID, int studentID) {
        this.bookID = bookID;
        this.studentID = studentID;
        this.borrowDate = LocalDate.now();
    }

    // Allow/Deny request methods

    // Addition/Removal of books from user's array of books
    public void addBorrowed(LibBook book, LibStudent student) {
        student.addBook(book);
    }

    public void removeBorrowed(LibBook book, LibStudent student) {
        student.removeBook(book);
    }

    // Creates a request of type Borrow, carries the book ID and user ID, also prints out the due date after 2 weeks
    @Override
    public LibRequest request(LibBook book, LibStudent student) {
        LibBorrow borrow = new LibBorrow(book.getBookID(), student.getUsrID());
        System.out.println("Your due date is: " + borrowDate.plusDays(14));
        student.incBorrowAmount();
        return borrow;
    }

    @Override
    public void allowRequest() {}

    @Override
    public void denyRequest() {}

    @Override
    public String toString() {
        return "The borrowed book ID: " + bookID + ", the borrowing user ID: " + studentID +
               ", Borrowed on: " + borrowDate + ", and should return on: " + borrowDate.plusDays(14);
    }

    // Getters and Setters for the book ID, student ID, and borrow date
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

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

}
