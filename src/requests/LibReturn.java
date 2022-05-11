package requests;

import library.LibBook;
import library.LibStudent;
import java.time.LocalDate;

public class LibReturn implements LibRequest{
    protected final int fineRate = 50;
    protected long bookID;
    protected int studentID;
    protected LocalDate returnDate;

    public LibReturn(long bookID, int studentID) {
        this.bookID = bookID;
        this.studentID = studentID;
        returnDate = LocalDate.now();
    }

    @Override
    public LibRequest createRequest(LibBook book, LibStudent student) {
        LibReturn returnReq = new LibReturn(book.getBookID(), student.getUsrID());
        return returnReq;
    }

    @Override
    public String toString() {
        return "The returning book ID: " + bookID + ", the returning user ID: " + studentID +
                ", returned on: " + returnDate;
    }
}
