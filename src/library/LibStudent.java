package library;

import java.time.LocalDate;
import requests.*;
import java.lang.*;

public class LibStudent implements LibUserInterface {
    //Basic Info for student, created constructor as there cannot be super() due to interface
    private String usrName;
    private int usrID;
    private long phoneNum;
    private String userEmail;
    private final int maxBorrowBooks = 3;
    private int currentAmountBorrowed;
    private LibBook[] borrowedBooks = new LibBook[maxBorrowBooks];
    private LocalDate[] borrowDates = new LocalDate[maxBorrowBooks];

    public LibStudent(String studentName, int studentID, long studentPhone, String studentEmail) {
        usrName = studentName;
        usrID = studentID;
        phoneNum = studentPhone;
        userEmail = studentEmail;
    }

    // Reminder function
    public void remind() {
        LocalDate dateToday = LocalDate.now();
        if(borrowedBooks[0] == null && borrowedBooks[1] == null && borrowedBooks[2] == null) {
            System.out.println("No borrowed Books");
        } else {
            for (int i = 0; i < getCurrentAmountBorrowed(); i++) {
                if (borrowDates[i].minusDays(4) == dateToday) {
                    System.out.println("You have 4 days to return: " + borrowedBooks[i]);
                }
            }
        }
    }

    // Two functions to update/get the student's borrowed amount of books
    public void incBorrowAmount() {
        currentAmountBorrowed++;
    }

    public void decBorrowAmount() {
        currentAmountBorrowed--;
    }

    public int getCurrentAmountBorrowed() {
        return currentAmountBorrowed;
    }

    // Intellij created such methods and their @Override, I filled in the returns after copying the info fields

    @Override
    public void setName(String usrName) {
        this.usrName = usrName;
    }

    @Override
    public String getUsrName() {
        return usrName;
    }

    @Override
    public void setUsrID(int usrID) {
        this.usrID = usrID;
    }

    @Override
    public int getUsrID() {
        return usrID;
    }

    @Override
    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public long getPhoneNum() {
        return phoneNum;
    }

    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String getUserEmail() {
        return userEmail;
    }

    // toString overridden into outputting in csv format (no blanks)
    @Override
    public String toString() {
        return getUsrName() + "," + getUsrID() + "," + getPhoneNum() + "," + getUserEmail() + "\n";
    }

    // Creates a request of the type order using objects, and returns it
    public LibOrder createOrder(LibBook book, LibStudent student) {
        return new LibOrder(book.getBookID(),student.getUsrID());
    }

    // Creates a request of the type order using ids, and returns it
    public LibOrder createOrder(int bookId, int studentId) {
        return new LibOrder(bookId,studentId);
    }

    // Creates a request of the type borrow with objects, and returns it
    public LibBorrow createBorrow(LibBook book, LibStudent student) {
        return new LibBorrow(book.getBookID(),student.getUsrID());
    }

    
    // Creates a request of the type borrow with ids, and returns it
    public LibBorrow createBorrow(int bookId, int studentId) {
        return new LibBorrow(bookId,studentId);
    }

    // Creates a request of the type return using objects, and returns it
    public LibReturn createReturn(LibBook book, LibStudent student) {
        return new LibReturn(book.getBookID(),student.getUsrID());
    }

    // Creates a request of the type return using ids, and returns it
    public LibReturn createReturn(int bookId, int studentId) {
        return new LibReturn(bookId,studentId);
    }

    // Add/Remove books into parallel arrays of Book/Date

    public void addBook(LibBook book) {
        // Checks if the list is full by making sure no place is empty
        if(borrowedBooks[0] != null & borrowedBooks[1] != null & borrowedBooks[2] != null) {
            System.out.println("Cannot borrow any more books");
        } else {
            // otherwise, if empty at any given space of the 3, adds the book to it
            // and the borrow date is a parallel array, so inherently must be empty due to parallel operations on both
            for (int i = 0; i <= maxBorrowBooks; i++) {
                if (borrowedBooks[i] == null & borrowDates[i] == null) {
                    borrowedBooks[i] = book;
                    borrowDates[i] = LocalDate.now();
                    System.out.println("Book added, due date: " + borrowDates[i].plusDays(14));
                    incBorrowAmount();
                    break;
                }
            }
        }
    }

    public void removeBook(int bookID) {
        for(int i = 0; i < maxBorrowBooks; i++) {
            if(bookID == borrowedBooks[i].getBookID()) {
                borrowedBooks[i] = null;
                borrowDates[i] = null;
                decBorrowAmount();
                break;
            }
        }
    }

    // Allowing the user to view their borrowed books, when they borrowed said books, and when should they return

    public void showBorrowed() {
        // Checking for emptiness
        if(borrowedBooks[0] == null & borrowedBooks[1] == null & borrowedBooks[2] == null) {
            System.out.println("You don't have any borrowed books");
        } else {
            // prints the book in the given space in the array (skips nulls)
            System.out.println("Showing borrowed books: ");
            for(LibBook borrowedBook : borrowedBooks) {
                if(borrowedBook != null) {
                    System.out.println("Book Name: " + borrowedBook.getBookName() +
                                       "\nBook Author: " + borrowedBook.getBookAuthor() +
                                       "\nBook ID: " + borrowedBook.getBookID());
                }
            }
        }
    }

    // Method to show the user's borrowed books as a whole string to be put into the GUI's display field

    public String showBorrowedStr() {
        String borrowString = "";
        if(borrowedBooks[0] == null & borrowedBooks[1] == null & borrowedBooks[2] == null) {
            return "You don't have any borrowed books";
        } else {
            StringBuilder sb = new StringBuilder();

            for(LibBook borrowedBook : borrowedBooks) {
                if(borrowedBook != null) {
                    String str1 = "Book Name: " + borrowedBook.getBookName();
                    String str2 = "\nBook Author: " + borrowedBook.getBookAuthor();
                    String str3 = "\nBook ID: " + borrowedBook.getBookID() + "\n";
                    borrowString = sb.append(str1).append(str2).append(str3).toString();
                }
            }
        }
        return borrowString;
    }
}
