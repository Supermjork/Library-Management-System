import library.LibBook;
import library.LibStudent;
import reader.CsvFileReader;
import requests.LibBorrow;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibStudentWindow extends JPanel {
    Object studentInSession = LibMainWindow.userInSession;

    public LibStudentWindow() {
        List<LibBorrow> borrowList = new ArrayList<>();
        List<LibBook> bookList = new ArrayList<>();
        CsvFileReader.loadDataBorrow("src\\filebase\\borrows.csv", borrowList);
        CsvFileReader.loadDataBook("src\\filebase\\books.csv", bookList);

        for(LibBorrow borrows : borrowList) {
            if (((LibStudent) studentInSession).getUsrID() == borrows.getStudentID()) {
                for (LibBook bookBorrowed : bookList) {
                    if (borrows.getBookID() == bookBorrowed.getBookID()) {
                        ((LibStudent) studentInSession).addBook(bookBorrowed);
                        break;
                    }
                }
            }
        }

            // Creating frame for student UI
        JFrame studentUI = new JFrame("LibGUI Student Window");

        // Creating buttons for user

        // Left side
        JButton searchBooks = new JButton("Search for Book");
        JTextField searchField = new JTextField();

        JButton returnBorrowed = new JButton("Return a Book");
        JTextField returnField = new JTextField();

        // Right Side
        JButton orderBook = new JButton("Order Book");
        JTextField orderField = new JTextField();

        JButton borrowBook = new JButton("Borrow Book");
        JButton displayAll = new JButton("Show Books");
        JTextField borrowField = new JTextField();

        // Middle
        JButton viewBorrowed = new JButton("View Borrowed Books");
        JButton exitSession = new JButton("Log out");

        // Display Panel for Search/Books in Stock/Borrowed Books
        JTextArea displayBooks = new JTextArea();
        displayBooks.setEditable(false);
        JScrollPane displayContainer = new JScrollPane(displayBooks);
        displayContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Setting boundaries
        studentUI.setSize(750, 750);

        displayContainer.setBounds(10,10,715,200);
        displayBooks.setBounds(10, 10, 715, 200);

        viewBorrowed.setBounds(292, 335, 175, 30);

        searchBooks.setBounds(10, 230, 175, 30);
        searchField.setBounds(10, 290, 175, 30);

        returnBorrowed.setBounds(10, 380, 175, 30);
        returnField.setBounds(10, 440, 175, 30);

        orderBook.setBounds(549, 230, 175, 30);
        orderField.setBounds(549, 290, 175, 30);

        borrowBook.setBounds(550, 380, 90, 30);
        displayAll.setBounds(550+90,380,90,30);
        borrowField.setBounds(550, 440, 175, 30);

        exitSession.setBounds(750 / 3, (3 * 750) / 4, 750 / 3, 70);

        studentUI.add(viewBorrowed);

        studentUI.add(searchBooks);
        studentUI.add(searchField);

        studentUI.add(returnBorrowed);
        studentUI.add(returnField);

        studentUI.add(displayContainer);

        studentUI.add(orderBook);
        studentUI.add(orderField);

        studentUI.add(borrowBook);
        studentUI.add(displayAll);
        studentUI.add(borrowField);

        studentUI.add(exitSession);

        studentUI.setLayout(null);
        studentUI.setVisible(true);
        studentUI.setLocationRelativeTo(null);
        studentUI.setTitle("Student View");

        studentUI.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                studentUI.dispose();
            }
        });

        viewBorrowed.addActionListener(e -> {
            List<LibBorrow> borrowList1 = new ArrayList<>();
            List<LibBook> bookList1 = new ArrayList<>();
            CsvFileReader.loadDataBorrow("src\\filebase\\borrows.csv", borrowList1);
            CsvFileReader.loadDataBook("src\\filebase\\books.csv", bookList1);

            displayBooks.setText(((LibStudent) studentInSession).showBorrowedStr());
        });

        searchBooks.addActionListener(e -> {
            List<LibBook> bookList12 = new ArrayList<>();
            CsvFileReader.loadDataBook("src\\filebase\\books.csv", bookList12);

            String searchBook = searchField.getText();

            for(LibBook searchingBook : bookList12) {
                if(searchingBook.getBookName().contains(searchBook)) {
                    displayBooks.setText(bookInfo(searchingBook));
                    break;
                }
            }
        });

        displayAll.addActionListener(e -> {
            List<LibBook> bookList13 = new ArrayList<>();
            CsvFileReader.loadDataBook("src\\filebase\\books.csv", bookList13);

            StringBuilder allBooks = new StringBuilder();
            String showAll = null;

            for(LibBook displayBook : bookList13) {
                showAll = allBooks.append(bookInfo(displayBook)).toString();
            }

            displayBooks.setText(showAll);
        });

        borrowBook.addActionListener(e -> {
            int borrowID = Integer.parseInt(borrowField.getText());

            if (((LibStudent) studentInSession).getCurrentAmountBorrowed() < 3) {
                System.out.println(((LibStudent) studentInSession).getCurrentAmountBorrowed());
                for (LibBook borrowingBook : bookList) {
                    if (borrowID == borrowingBook.getBookID() & borrowingBook.getStockAmount() > 0) {

                        ((LibStudent) studentInSession).addBook(borrowingBook);

                        borrowingBook.decStockAmount();

                        LibBorrow borrowRequest = new LibBorrow(borrowingBook.getBookID(), ((LibStudent) studentInSession).getUsrID());
                        fileAppend(borrowRequest.toString(), "src\\filebase\\borrows.csv");

                        FileWriter writeOrder = null;
                        try {
                            writeOrder = new FileWriter("src\\filebase\\books.csv");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        for (LibBook books : bookList) {
                            try {
                                assert writeOrder != null;
                                writeOrder.write(books.toString());
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        try {
                            assert writeOrder != null;
                            writeOrder.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(studentUI, "Cannot borrow more books");
            }
        });

        exitSession.addActionListener(e -> {
            studentUI.dispose();
            new LibMainWindow().setVisible(true);
        });
    }

    public static void main(String[] args) {
        LibStudentWindow studentWindow = new LibStudentWindow();
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
