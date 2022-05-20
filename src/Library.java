import library.*;
import reader.*;
import requests.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

/**
 * The main class that the objects will be used in.
 * @author Icen Zeyada
 */
public class Library {
    public static void main(String[] args) {
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
        CsvFileReader.loadDataBook("src\\books.csv", bookList);
        CsvFileReader.loadDataStudent("src\\students.csv", studentList);
        CsvFileReader.loadDataAdmin("src\\admins.csv", adminList);
        CsvFileReader.loadDataOrder("src\\orders.csv", orderList);
        CsvFileReader.loadDataBorrow("src\\borrows.csv", borrowList);
        CsvFileReader.loadDataReturn("src\\returns.csv", returnList);

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
                        " Issued in Library on: " + book.getIssLibDate() +
                        " Released Globally on: " + book.getIssGloDate() +
                        " Number in stock: " + book.getStockAmount()
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
        } while(!(loginValidator(userEmail, userNum, studentList) | loginValidator(userEmail, userNum, adminList)));

        if(isAdmin(userEmail, userNum, adminList)) {
            System.out.println("Logged in as Admin");
            for(int i = 0; i < adminList.size(); i++) {
                if(adminList.get(i).getPhoneNum() == userNum) {
                    userInSession = adminList.get(i);
                    break;
                }
            }
        } else {
            System.out.println("Logged in as Student");
            for(int i = 0; i < studentList.size(); i++) {
                if(studentList.get(i).getPhoneNum() == userNum) {
                    userInSession = studentList.get(i);
                }
            }
        }

        System.out.println(userInSession);

        if(userInSession instanceof LibAdmin) {
            System.out.println("User is instance of an admin");
        } else {
            System.out.println("User is instance of a student");
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
