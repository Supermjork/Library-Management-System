package library;

import java.time.LocalDate;

/**
 * Creating the object for Book, which will contain:-
 * - Book name
 * - Book ID
 * - Book Author
 * - Issue Date in Library (dayL, monthL, yearL)
 * - Issue Date Globally when the book was released (dayG, monthG, yearG)
 */
public class LibBook {
    private String bookName;
    private String bookAuthor;
    private int bookID;
    private LocalDate issLibDate;  //Issuing date for when the book was added to the library
    private LocalDate issGloDate;  //   ~      ~   ~    ~   ~   ~    ~  released globally

    public LibBook(String bookName, String bookAuthor, int bookID, int dayL, int monthL, int yearL, int dayG,
                   int monthG, int yearG) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookID = bookID;
        issLibDate = LocalDate.of(yearL, monthL, dayL);
        issGloDate = LocalDate.of(yearG, monthG, dayG);
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getBookID() {
        return bookID;
    }

    public void setIssLibDate(int dayL, int monthL, int yearL) {
        this.issLibDate = LocalDate.of(yearL, monthL, dayL);
    }

    public LocalDate getIssLibDate() {
        return issLibDate;
    }

    public void setIssGloDate(int dayG, int monthG, int yearG) {
        this.issGloDate = LocalDate.of(yearG, monthG, dayG);
    }

    public LocalDate getIssGloDate() {
        return issGloDate;
    }
}