package requests;

import library.LibBook;
import library.LibStudent;

import java.time.LocalDate;

public class LibBorrow implements LibRequest {
    protected int bookId;          // Adds the ordered book's ID.
    protected int studentId;    // Adds the Student's ID (Who had ordered the book).
    protected LocalDate borrowDate;  // Assigns the current date when the request had been created.

    // Constructor for the Borrow object that holds the book ID, student ID, Date
    public LibBorrow(int bookId, int studentId) {
        this.bookId = bookId;
        this.studentId = studentId;
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

    @Override
    public void allowRequest() {}

    @Override
    public void denyRequest() {}

    @Override
    public String toString() {
        return  "The borrowed book ID: " + bookId + 
                ", the borrowing user ID: " + studentId +
                ", Borrowed on: " + borrowDate + 
                ", and should return on: " + borrowDate.plusDays(14);
    }

    // Getters and Setters for the book ID, student ID, and borrow date
    public int getBookID() {
        return bookId;
    }

    public int getStudentID() {
        return studentId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

}
