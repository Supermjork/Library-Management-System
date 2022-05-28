import library.LibBook;
import library.LibStudent;
import reader.CsvFileReader;
import requests.LibBorrow;
import requests.LibReturn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        JTextField borrowField = new JTextField();

        // Middle
        JButton viewBorrowed = new JButton("View Borrowed Books");
        JButton exitSession = new JButton("Log out");

        // Display Panel for Search/Books in Stock/Borrowed Books
        JTextArea displayBooks = new JTextArea();
        displayBooks.setEditable(false);

        // Setting boundaries
        studentUI.setSize(750, 750);

        displayBooks.setBounds(10, 10, 715, 200);

        viewBorrowed.setBounds(292, 335, 175, 30);

        searchBooks.setBounds(10, 230, 175, 30);
        searchField.setBounds(10, 290, 175, 30);

        returnBorrowed.setBounds(10, 380, 175, 30);
        returnField.setBounds(10, 440, 175, 30);

        orderBook.setBounds(549, 230, 175, 30);
        orderField.setBounds(549, 290, 175, 30);

        borrowBook.setBounds(550, 380, 175, 30);
        borrowField.setBounds(550, 440, 175, 30);

        exitSession.setBounds(750 / 3, (3 * 750) / 4, 750 / 3, 70);

        studentUI.add(viewBorrowed);

        studentUI.add(searchBooks);
        studentUI.add(searchField);

        studentUI.add(returnBorrowed);
        studentUI.add(returnField);

        studentUI.add(displayBooks);

        studentUI.add(orderBook);
        studentUI.add(orderField);

        studentUI.add(borrowBook);
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

        viewBorrowed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<LibBorrow> borrowList = new ArrayList<>();
                List<LibBook> bookList = new ArrayList<>();
                CsvFileReader.loadDataBorrow("src\\filebase\\borrows.csv", borrowList);
                CsvFileReader.loadDataBook("src\\filebase\\books.csv", bookList);

                displayBooks.setText(((LibStudent) studentInSession).showBorrowedStr());
            }
        });

        exitSession.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentUI.dispose();
                new LibMainWindow().setVisible(true);
            }
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
}
