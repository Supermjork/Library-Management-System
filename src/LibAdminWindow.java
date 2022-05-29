import library.LibBook;
import reader.CsvFileReader;
import requests.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibAdminWindow extends JPanel {
    Object adminInSession = LibMainWindow.userInSession;

    public LibAdminWindow() {
        List<LibBook> bookList       = new ArrayList<>();
        List<LibOrder> orderList     = new ArrayList<>();
        List<LibBorrow> borrowList   = new ArrayList<>();
        List<LibReturn> returnList   = new ArrayList<>();
        List<LibReserve> reserveList = new ArrayList<>();

        CsvFileReader.loadDataBook("src\\filebase\\books.csv", bookList);
        CsvFileReader.loadDataOrder("src\\filebase\\orders.csv", orderList);
        CsvFileReader.loadDataBorrow("src\\filebase\\borrows.csv", borrowList);
        CsvFileReader.loadDataReturn("src\\filebase\\returns.csv", returnList);
        CsvFileReader.loadDataReserve("src\\filebase\\reservations.csv", reserveList);

        // Creating Frame for Admin
        JFrame adminUI = new JFrame("LibGUI Admin Window");

        // Creating Buttons and Fields
            // Left Side
        JButton addBookButton = new JButton("Add");
        JButton updateBookButton = new JButton("Update");

            // Right Side
        JButton removeBookButton = new JButton("Remove Book");
        JTextField removeIDIn    = new JTextField();
        JButton searchBookButton = new JButton("Search");
        JTextField searchInField = new JTextField();

            // Middle Side
        JButton viewAllRequests  = new JButton("View Requests");
        JButton viewAllBooks     = new JButton("View Books");
        JButton exitSession      = new JButton("Log out");

            // Display panel to show data on
        JTextArea displayBooks = new JTextArea();
        displayBooks.setEditable(false);

        JScrollPane displayContainer = new JScrollPane(displayBooks); // takes the text area as param
        displayContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Setting Boundaries
        adminUI.setSize(750,750);

        addBookButton.setBounds(10, 260, 175, 30);
        updateBookButton.setBounds(10, 410, 175, 30);

        displayContainer.setBounds(10,10,715,200);
        displayBooks.setBounds(10, 10, 715, 200);

        searchBookButton.setBounds(549, 230, 175, 30);
        searchInField.setBounds(549, 290, 175, 30);

        removeBookButton.setBounds(550, 380, 175, 30);
        removeIDIn.setBounds(550, 440, 175, 30);

        viewAllBooks.setBounds(292, 260, 175, 30);
        viewAllRequests.setBounds(292, 410, 175, 30);

        exitSession.setBounds(750 / 3, (3 * 750) / 4, 750 / 3, 70);


        // Adding components to Frame
        adminUI.add(addBookButton);
        adminUI.add(updateBookButton);

        adminUI.add(displayContainer);

        adminUI.add(searchBookButton);
        adminUI.add(searchInField);

        adminUI.add(removeIDIn);
        adminUI.add(removeBookButton);

        adminUI.add(viewAllBooks);
        adminUI.add(viewAllRequests);

        adminUI.add(exitSession);

        adminUI.setLayout(null);
        adminUI.setVisible(true);
        adminUI.setLocationRelativeTo(null);
        adminUI.setTitle("Admin Window");

        // Adding button Functionality

        adminUI.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                adminUI.dispose();
            }
        });

        searchBookButton.addActionListener(e -> {
            String searchBook = searchInField.getText();

            if(!searchInField.getText().equals("")) {
                StringBuilder searchBuild = new StringBuilder();
                String searchStr = null;

                for (LibBook searchingBook : bookList) {
                    if (searchingBook.getBookName().contains(searchBook)) {
                        searchStr = searchBuild.append(bookInfo(searchingBook)).toString();
                    }
                }
                displayBooks.setText(searchStr);
            } else {
                JOptionPane.showMessageDialog(adminUI,"Enter a substring of book title to search for");
            }
        });

        viewAllBooks.addActionListener(e -> {
            StringBuilder allBooks = new StringBuilder();
            String showAll = null;

            for(LibBook displayBook : bookList) {
                showAll = allBooks.append(bookInfo(displayBook)).toString();
            }

            displayBooks.setText(showAll);
        });

        addBookButton.addActionListener(e -> new LibAdminAdd().setVisible(true));

        removeBookButton.addActionListener(e -> {
            if(!removeIDIn.getText().equals("")) {
                int removeID = Integer.parseInt(removeIDIn.getText());

                bookList.removeIf(bookRemoved -> removeID == bookRemoved.getBookID());

                FileWriter writer = null;
                try {
                    writer = new FileWriter("src\\filebase\\books.csv");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                for (LibBook books : bookList) {
                    try {
                        assert writer != null;
                        writer.write(books.toString());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    assert writer != null;
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(adminUI, "Please enter ID to remove");
            }
        });

        viewAllRequests.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            String allRequests;

            if(orderList.size() == 0) {
                allRequests = sb.append("No Orders \n").toString();
            } else {
                allRequests = sb.append("Orders: \n").toString();
                for (LibOrder orderRequest : orderList) {
                    String bookID = "Book ID: " + orderRequest.getBookID();
                    String studentID = ", Student ID: " + orderRequest.getBookID();
                    String orderDate = ", Order Date: " + orderRequest.getOrderDate().getDayOfMonth() +
                            ", " + orderRequest.getOrderDate().getMonthValue() +
                            ", " + orderRequest.getOrderDate().getYear() + "\n";

                    allRequests = sb.append(bookID).append(studentID).append(orderDate).toString();
                }
            }

            if(borrowList.size() == 0) {
                allRequests = sb.append("\nNo Borrows \n").toString();
            } else {
                allRequests = sb.append("\nBorrows: \n").toString();
                for (LibBorrow borrowRequest : borrowList) {
                    String bookID = "Book ID: " + borrowRequest.getBookID();
                    String studentID = ", Student ID: " + borrowRequest.getStudentID();
                    String borrowDate = ", Borrow Date: " + borrowRequest.getBorrowDate().getDayOfMonth() +
                            ", " + borrowRequest.getBorrowDate().getMonthValue() +
                            ", " + borrowRequest.getBorrowDate().getYear() + "\n";

                    allRequests = sb.append(bookID).append(studentID).append(borrowDate).toString();
                }
            }

            if(returnList.size() == 0) {
                allRequests = sb.append("\nNo returns \n").toString();
            } else {
                allRequests = sb.append("\nReturns: \n").toString();
                for (LibReturn returnRequest : returnList) {
                    String bookID = "Book ID: " + returnRequest.getBookID();
                    String studentID = ", Student ID: " + returnRequest.getStudentID();
                    String borrowDate = ", Return Date: " + returnRequest.getReturnDate().getDayOfMonth() +
                            ", " + returnRequest.getReturnDate().getMonthValue() +
                            ", " + returnRequest.getReturnDate().getYear();
                    String fine = ", Fine: " + returnRequest.getFine() + "\n";

                    allRequests = sb.append(bookID).append(studentID).append(borrowDate).append(fine).toString();
                }
            }

            if(reserveList.size() == 0) {
                allRequests = sb.append("\nNo Reservations \n").toString();
            } else {
                allRequests = sb.append("\nReservations: \n").toString();
                for (LibReserve reserveRequest : reserveList) {
                    String bookID = "Book ID" + reserveRequest.getBookID();
                    String studentID = ", Reserved By: " + reserveRequest.getStudentID();
                    String reserveDate = ", Reserved On: " + reserveRequest.getReserveDate().getDayOfMonth() +
                                         ", " + reserveRequest.getReserveDate().getMonthValue() +
                                         ", " + reserveRequest.getReserveDate().getYear() + "\n";
                    allRequests = sb.append(bookID).append(studentID).append(reserveDate).toString();
                }
            }

            displayBooks.setText(allRequests);
        });

        updateBookButton.addActionListener(e -> new LibAdminUpdate().setVisible(true));

        exitSession.addActionListener(e -> {
            adminUI.dispose();
            new LibMainWindow().setVisible(true);
        });
    }

    public static void main(String[] args) {
        LibAdminWindow adminWindow = new LibAdminWindow();
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

    public static String bookInfo(LibBook book) {
        StringBuilder bookDetails = new StringBuilder();

        String bookName     = "Book Name: " + book.getBookName();
        String bookAuthor   = "\nBook Author: " + book.getBookAuthor();
        String bookID       = "\nBook ID: " + book.getBookID();
        String bookRelease  = "\nRelease Date: " + book.getIssGloDate().getDayOfMonth() +
                ", " + book.getIssGloDate().getMonthValue() +
                ", " + book.getIssGloDate().getYear();
        String bookStock    = "\nStock Amount: " + book.getStockAmount();
        String bookPrice    = "\nPrice: " + book.getPrice() + "EG£\n\n";

        return bookDetails.append(bookName).append(bookAuthor).append(bookID)
                .append(bookRelease).append(bookStock).append(bookPrice).toString();
    }
}
