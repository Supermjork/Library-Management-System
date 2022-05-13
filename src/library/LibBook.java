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
    private LocalDate issLibDate;  // Issuing date for when the book was added to the library
    private LocalDate issGloDate;  //    ~      ~   ~    ~   ~   ~    ~  released globally
    private int bookID;
    private int stockAmount;

    public LibBook(String bookName, String bookAuthor, int bookID, int dayL, int monthL, int yearL, int dayG,
                   int monthG, int yearG, int stockAmount) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookID = bookID;
        issLibDate = LocalDate.of(yearL, monthL, dayL);
        issGloDate = LocalDate.of(yearG, monthG, dayG);
        this.stockAmount = stockAmount;
    }

    // Accessors and Modifiers for each private field above.

    // Book Name
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookName() {
        return bookName;
    }

    // Book Author
    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    // Date when book got issued in the library
    public void setIssLibDate(int dayL, int monthL, int yearL) {
        this.issLibDate = LocalDate.of(yearL, monthL, dayL);
    }

    public LocalDate getIssLibDate() {
        return issLibDate;
    }

    // Global release date
    public void setIssGloDate(int dayG, int monthG, int yearG) {
        this.issGloDate = LocalDate.of(yearG, monthG, dayG);
    }

    public LocalDate getIssGloDate() {
        return issGloDate;
    }

    // Book unique ID
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getBookID() {
        return bookID;
    }

    // The amount of this book in stock with increment/decrement functions
    public void setStockAmount(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public int getStockAmount() {
        return stockAmount;
    }

    public void incStockAmount() {
        stockAmount++;
    }

    public void decStockAmount() {
        stockAmount--;
    }
}
