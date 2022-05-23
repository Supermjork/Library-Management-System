import library.*;
import reader.*;
import requests.*;

import java.io.*;
import java.util.*;

/**
 * The main class that the objects will be used in.
 * @author Icen Zeyada
 */
public class Library {
    public static void main(String[] args) throws IOException {
        List<LibBook> bookList = new ArrayList<>();
        List<LibStudent> studentList = new ArrayList<>();
        List<LibAdmin> adminList = new ArrayList<>();
        List<LibOrder> orderList = new ArrayList<>();
        List<LibBorrow> borrowList = new ArrayList<>();
        List<LibReturn> returnList = new ArrayList<>();

    /**                     ########################
     *                      # Loading Data Section #
     *                      ########################
     */

        CsvFileReader.loadDataBook("src\\filebase\\books.csv", bookList);
        CsvFileReader.loadDataStudent("src\\filebase\\students.csv", studentList);
        CsvFileReader.loadDataAdmin("src\\filebase\\admins.csv", adminList);
        CsvFileReader.loadDataOrder("src\\filebase\\orders.csv", orderList);
        CsvFileReader.loadDataBorrow("src\\filebase\\borrows.csv", borrowList);
        CsvFileReader.loadDataReturn("src\\filebase\\returns.csv", returnList);

    /**                     #########################
     *                      #    Testing Section    #
     *                      #########################
     */

        System.out.println("\nBooks loaded: " + bookList.size());
        for(LibBook book : bookList) {
            System.out.println(
                        "Book Name: " + book.getBookName() +
                        " Author: " + book.getBookAuthor() +
                        " ID: " + book.getBookID() +
                        " Released Globally on: " + book.getIssGloDate() +
                        " Number in stock: " + book.getStockAmount() +
                        " Price: " + book.getPrice() + "EG£"
                        );
        }

        System.out.println("\nStudents loaded: " + studentList.size());
        for(LibStudent student : studentList) {
            System.out.println(
                        "Name: " + student.getUsrName() +
                        " ID: " + student.getUsrID() +
                        " Phone: " + student.getPhoneNum() +
                        " Email: " + student.getUserEmail()
                        );
        }

        System.out.println("\nAdmins loaded: " + adminList.size());
        for(LibAdmin admin : adminList) {
            System.out.println(
                        "Name: " + admin.getUsrName() +
                        " ID: " + admin.getUsrID() +
                        " Phone: " + admin.getPhoneNum() +
                        " Email: " + admin.getUserEmail()
                        );
        }

        System.out.println("\nOrders loaded: " + orderList.size());
        for(LibOrder order : orderList) {
            System.out.println(
                        "Book ID: " + order.getBookID() +
                        " Student ID: " + order.getStudentID() +
                        " Order Date: " + order.getOrderDate()
                        );
        }

        System.out.println("\nBorrows loaded: " + borrowList.size());
        for(LibBorrow borrow : borrowList) {
            System.out.println(
                        "Book ID: " + borrow.getBookID() +
                        " Student ID: " + borrow.getStudentID() +
                        " Borrow Date: " + borrow.getBorrowDate()
                        );
        }

        System.out.println("\nReturns loaded: " + returnList.size());
        for(LibReturn return1 : returnList) {
            System.out.println(
                        "Book ID: " + return1.getBookID() +
                        " Student ID: " + return1.getStudentID() +
                        " Return Date: " + return1.getReturnDate()
                        );
        }

        /**
         *                  #########################
         *                  #     Supposed Main     #
         *                  #########################
         */

        Scanner userChoiceIn = new Scanner(System.in);
        int userChoice;

        System.out.println("1.Login\n2.Register");
        userChoice = userChoiceIn.nextInt();

        switch(userChoice) {
            case 1:
                Object userInSession = null;

                Scanner userEmailIn = new Scanner(System.in);
                Scanner userNumIn = new Scanner(System.in);

                String userEmail;
                long userNum;

                do {
                    System.out.print("Enter your email: ");
                    userEmail = userEmailIn.nextLine();
                    System.out.print("Enter your mobile number: ");
                    userNum = userNumIn.nextLong();
                } while (!(loginValidator(userEmail, userNum, studentList) | loginValidator(userEmail, userNum, adminList)));

                if (isAdmin(userEmail, userNum, adminList)) {
                    System.out.println("Logged in as Admin");
                    for (LibAdmin libAdmin : adminList) {
                        if (libAdmin.getPhoneNum() == userNum) {
                            userInSession = libAdmin;
                            break;
                        }
                    }
                } else {
                    System.out.println("Logged in as Student");
                    for (LibStudent student : studentList) {
                        if (student.getPhoneNum() == userNum) {
                            userInSession = student;
                            break;
                        }
                    }
                }

                if(userInSession instanceof LibAdmin) {
                    Scanner adminInput = new Scanner(System.in);

                    System.out.println("Enter what operation you'd like to do: ");
                    System.out.println("1.Add Book\n2.Remove Book\n3.Update Book Info\n4.View Orders\n5.Search By ID");

                    int adminChoice = adminInput.nextInt();

                    switch(adminChoice) {
                        case 1:
                            Scanner newBookStringTypes = new Scanner(System.in);
                            Scanner newBookIntTypes    = new Scanner(System.in);

                            System.out.print("Book Name: ");
                            String bookName = newBookStringTypes.nextLine();
                            System.out.print("Book Author: ");
                            String bookAuthor = newBookStringTypes.nextLine();
                            System.out.print("Book ID: ");
                            int bookID = newBookIntTypes.nextInt();
                            System.out.print("Book release Day: ");
                            int bookReleaseDay = newBookIntTypes.nextInt();
                            System.out.print("Book release Month: ");
                            int bookReleaseMonth = newBookIntTypes.nextInt();
                            System.out.print("Book release Year: ");
                            int bookReleaseYear = newBookIntTypes.nextInt();
                            System.out.print("Book amount in Stock: ");
                            int bookStock = newBookIntTypes.nextInt();
                            System.out.print("Book Price: ");
                            int bookPrice = newBookIntTypes.nextInt();

                            newBookStringTypes.close();
                            newBookIntTypes.close();

                            LibBook addedBook = new LibBook(bookName, bookAuthor, bookID, bookReleaseDay,
                                                            bookReleaseMonth, bookReleaseYear, bookStock, bookPrice);

                            fileAppend(addedBook.toString(), "src\\filebase\\books.csv");
                            break;
                        case 2:
                            Scanner deleteBookID = new Scanner(System.in);
                            System.out.print("What is the ID of the book you'd like to delete? ");
                            int deletedID = deleteBookID.nextInt();

                            bookList.removeIf(delBook -> delBook.getBookID() == deletedID);

                            FileWriter writer = new FileWriter("src\\filebase\\books.csv");
                            for(LibBook books: bookList) {
                                writer.write(books.toString());
                            }
                            writer.close();
                            break;
                        case 3:
                            Scanner updatedBookID = new Scanner(System.in);
                            System.out.print("Enter ID of the book you'd like to update: ");
                            int updateID = updatedBookID.nextInt();

                            for(LibBook bookToUpdate : bookList) {
                                if(updateID == bookToUpdate.getBookID()) {

                                    Scanner fieldChoice = new Scanner(System.in);
                                    System.out.print("What field to update?\n1.Name\n2.Author\n3.Release Date" +
                                            "\n4.Stock Amount\n5.Price");
                                    int fieldSelect = fieldChoice.nextInt();

                                    switch(fieldSelect) {
                                        case 1:
                                            Scanner updateName = new Scanner(System.in);

                                            System.out.print("Enter new Name: ");
                                            String updatedName = updateName.nextLine();

                                            bookToUpdate.setBookName(updatedName);
                                            break;
                                        case 2:
                                            Scanner updateAuthor = new Scanner(System.in);

                                            System.out.print("Enter Author name: ");
                                            String updatedAuthor = updateAuthor.nextLine();

                                            bookToUpdate.setBookAuthor(updatedAuthor);
                                            break;
                                        case 3:
                                            Scanner updateDate = new Scanner(System.in);

                                            System.out.print("Enter new release day: ");
                                            int updatedDay = updateDate.nextInt();
                                            System.out.print("Enter new release month:");
                                            int updatedMonth = updateDate.nextInt();
                                            System.out.print("Enter new release year: ");
                                            int updatedYear = updateDate.nextInt();

                                            bookToUpdate.setIssGloDate(updatedDay, updatedMonth, updatedYear);
                                            break;
                                        case 4:
                                            Scanner updateStock = new Scanner(System.in);

                                            System.out.print("Enter updated amount in stock: ");
                                            int updatedStockAmount = updateStock.nextInt();

                                            bookToUpdate.setStockAmount(updatedStockAmount);
                                            break;
                                        case 5:
                                            Scanner updatePrice = new Scanner(System.in);

                                            System.out.print("Enter the new price: ");
                                            int updatedPrice = updatePrice.nextInt();

                                            bookToUpdate.setPrice(updatedPrice);
                                            break;
                                    }

                                    FileWriter writeBook = new FileWriter("src\\filebase\\books.csv");
                                    for(LibBook books: bookList) {
                                        writeBook.write(books.toString());
                                    }
                                    writeBook.close();
                                    break;
                                }
                            }
                            break;
                        case 4:
                            for(LibOrder order : orderList) {
                                System.out.println(
                                                "Book ID: " + order.getBookID() +
                                                " Student ID: " + order.getStudentID() +
                                                " Order Date: " + order.getOrderDate()
                                );
                            }
                            break;
                        case 5:
                            Scanner idSearch = new Scanner(System.in);
                            System.out.print("Enter book Name you'd like to find: ");
                            String searchName = idSearch.nextLine();

                            for(LibBook searchBook : bookList) {
                                if(searchName.equalsIgnoreCase(searchBook.getBookName())) {
                                    System.out.println(searchBook);
                                    break;
                                } else {
                                    System.out.println("Book not found.");
                                }
                            }
                            break;
                    }
                } else if (userInSession != null){
                    Scanner studentInput = new Scanner(System.in);

                    System.out.println(userInSession);
                    System.out.println(((LibStudent) userInSession).getUsrID());

                    System.out.println("Enter what operation to do: ");
                    System.out.println("1.View borrowed Books\n2.Search for Book\n3.Return a Book\n" +
                                       "4.Order a Book\n5.Borrow Book");

                    int studentChoice = studentInput.nextInt();

                    switch(studentChoice) {
                        case 1:
                            System.out.println("Showing borrowed books:");
                            ((LibStudent) userInSession).showBorrowed();
                            break;
                        case 2:
                            Scanner studentSearch = new Scanner(System.in);
                            System.out.print("Enter Name of book to search for: ");
                            String bookSearchName = studentSearch.nextLine();

                            for(LibBook bookSearch : bookList) {
                                if(bookSearchName.equalsIgnoreCase(bookSearch.getBookName())) {
                                    System.out.println(bookSearch);
                                    break;
                                } else {
                                    System.out.println("Book not found.");
                                }
                            }
                            break;
                        case 3:
                            System.out.println("Showing borrowed books: ");
                            ((LibStudent) userInSession).showBorrowed();

                            Scanner bookReturn = new Scanner(System.in);

                            System.out.println("Enter book ID to return: ");
                            int returningID = bookReturn.nextInt();

                            LibReturn requestToReturn = new LibReturn(returningID, ((LibStudent) userInSession).getUsrID());
                            returnList.add(requestToReturn);
                            fileAppend(requestToReturn.toString(), "src\\filebase\\returns.csv");

                            ((LibStudent) userInSession).removeBook(returningID);
                            break;
                        case 4:
                            System.out.println("Showing books in stock: ");
                            for(LibBook shownBooks : bookList) {
                                System.out.println(shownBooks.toString());
                            }

                            Scanner studentBuy = new Scanner(System.in);

                            System.out.print("Enter the ID of book you'd like to buy: ");
                            int buyingBookID = studentBuy.nextInt();

                            LibOrder newOrder = new LibOrder(buyingBookID, ((LibStudent) userInSession).getUsrID());

                            for(LibBook bookBought : bookList) {
                                if(buyingBookID == bookBought.getBookID()) {
                                    bookBought.setStockAmount(bookBought.getStockAmount() - 1);
                                }
                                break;
                            }

                            FileWriter writer = new FileWriter("src\\filebase\\books.csv");
                            for(LibBook books: bookList) {
                                writer.write(books.toString());
                            }
                            writer.close();
                            break;
                        case 5:
                            if(((LibStudent) userInSession).getCurrentAmountBorrowed() < 3) {
                                System.out.println("Showing books in stock: ");
                                for (LibBook shownBooks : bookList) {
                                    System.out.println(shownBooks.toString());
                                }

                                Scanner studentBorrow = new Scanner(System.in);

                                System.out.print("Enter ID of book to borrow: ");
                                int borrowID = studentBorrow.nextInt();

                                LibBorrow newBorrow = new LibBorrow(borrowID, ((LibStudent) userInSession).getUsrID());

                                for (LibBook bookBorrowed : bookList) {
                                    if (borrowID == bookBorrowed.getBookID()) {
                                        bookBorrowed.decStockAmount();
                                        ((LibStudent) userInSession).addBook(bookBorrowed);
                                    }
                                }
                            } else {
                                System.out.println("Cannot borrow more than 3 books");
                                break;
                            }
                            break;
                    }
                }
                break;
            case 2:
                Scanner userNameIn = new Scanner(System.in);
                Scanner userIDIn = new Scanner(System.in);
                Scanner userPhoneIn = new Scanner(System.in);
                Scanner userEmailRegIn = new Scanner(System.in);

                System.out.print("Enter your Name: ");
                String userName = userNameIn.nextLine();
                System.out.print("Enter your ID: ");
                int userID = userIDIn.nextInt();
                System.out.print("Enter your Phone Number: ");
                long userPhone = userPhoneIn.nextLong();
                System.out.print("Enter your Email: ");
                String userRegEmail = userEmailRegIn.nextLine();

                LibStudent regStudent = new LibStudent(userName, userID, userPhone, userRegEmail);

                fileAppend(regStudent.toString(), "src\\filebase\\students.csv");

                System.out.println("Registration Successful.");
                break;
        }
    }

    /** 
     *                  #########################
     *                  #    Utility Methods    # 
     *                  #########################
     */

    public static <T extends LibUserInterface> boolean loginValidator(String email, long phoneNum, List<T> userList) {
        for(T user : userList) {
            if(email.equals(user.getUserEmail()) && phoneNum == user.getPhoneNum()) {
                return true;
            }
        }
        return false;
    }

    public static void fileAppend(String dataStr, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(dataStr);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error appending to file");
        }
    }

    public static <T extends LibUserInterface> boolean isAdmin(String email, long phoneNum, List<T> adminList) {
        if(loginValidator(email, phoneNum, adminList)) {
            System.out.println("Admin found");
            return true;
        } else {
            System.out.println("Admin not found");
            return false;
        }
    }
}
