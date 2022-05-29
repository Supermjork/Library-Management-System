package requests;

import java.time.LocalDate;

public class LibOrder implements LibRequest {
    protected int bookId;           //Adds the ordered book's ID.
    protected int studentId;        //Adds the Student's ID (Who had ordered the book).
    protected LocalDate orderDate;  //Assigns the current date when the request had been created.

    //Constructor for the Order object that holds the book ID, student ID, Date (Will be same for Borrow)
    public LibOrder(int bookId, int studentId) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.orderDate = LocalDate.now();
    }

    @Override
    public void allowRequest() {}

    @Override
    public void denyRequest() {}

    @Override
    public String toString() {
        return  bookId + "," + studentId + "," + orderDate.getDayOfMonth() + "," + orderDate.getMonthValue() +
                "," + orderDate.getYear() + "\n";
    }

    //Getters and Setters for the book ID, student ID, and order date
    public int getBookID() {
        return bookId;
    }

    public int getStudentID() {
        return studentId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }
}
