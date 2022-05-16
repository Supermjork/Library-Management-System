package requests;

import library.LibBook;
import library.LibStudent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LibReturn implements LibRequest{
    protected final int fineRate = 50;
    protected int fine;
    protected long bookID;
    protected int studentID;
    protected LocalDate returnDate;

    public LibReturn(long bookID, int studentID) {
        this.bookID = bookID;
        this.studentID = studentID;
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
    public LibRequest request(LibBook book, LibStudent student) {
        return new LibReturn(book.getBookID(), student.getUsrID());
    }

    @Override
    public void allowRequest() {}

    @Override
    public void denyRequest() {}

    @Override
    public String toString() {
        return "The returning book ID: " + bookID + ", the returning user ID: " + studentID +
                ", returned on: " + returnDate;
    }


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
