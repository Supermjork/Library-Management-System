package requests;

import java.time.LocalDate;

public class LibReserve implements LibRequest{
    protected int bookID;
    protected int studentID;
    protected LocalDate reserveDate;

    public LibReserve(int bookID, int studentID) {
        this.bookID = bookID;
        this.studentID = studentID;
        reserveDate = LocalDate.now();
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public LocalDate getReserveDate() {
        return reserveDate;
    }

    public String toString() {
        return getBookID() + "," + getStudentID() + "," + reserveDate.getDayOfMonth() + "," +
               reserveDate.getMonthValue() + "," + reserveDate.getYear();
    }

    @Override
    public void allowRequest() {}

    @Override
    public void denyRequest() {}
}
