package requests;

import library.LibBook;
import library.LibStudent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LibReturn implements LibRequest{
    protected final int fineRate = 50;
    protected int fine;
    protected int bookId;
    protected int studentId;
    protected LocalDate returnDate;

    //Constructor for the Order object that holds the book ID, student ID, Date (Will be same for Borrow)
    public LibReturn(int bookId, int studentId) {
        this.bookId = bookId;
        this.studentId = studentId;
        returnDate = LocalDate.now();
    }

    public int calculateFine(LibBorrow borrowRequest) {
        int daysAfterDue = (int) ChronoUnit.DAYS.between(borrowRequest.borrowDate, returnDate);
        if(daysAfterDue == 0) {
            return 0;
        } else {
            fine = daysAfterDue * fineRate;
            return fine;
        }
    }

    @Override
    public void allowRequest() {}

    @Override
    public void denyRequest() {}

    @Override
    public String toString() {
        return "The returning book ID: " + bookId + 
                ", the returning user ID: " + studentId +
                ", returned on: " + returnDate;
    }


    //Getters and Setters for the book ID, student ID, and order date
    public int getBookID() {
        return bookId;
    }

    public int getStudentID() {
        return studentId;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public int getFineRate() {
        return fineRate;
    }

}
