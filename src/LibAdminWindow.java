import library.LibBook;
import reader.CsvFileReader;
import requests.LibBorrow;
import requests.LibOrder;
import requests.LibReserve;
import requests.LibReturn;

import javax.swing.*;
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
        JButton viewAllRequest   = new JButton();
        JButton viewAllBooks     = new JButton();
        JButton exitSession      = new JButton();

            // Display panel to show data on
        JTextArea displayBooks = new JTextArea();
        displayBooks.setEditable(false);

        JScrollPane displayContainer = new JScrollPane(displayBooks); // takes the text area as param
        displayContainer.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Setting Boundaries
        adminUI.setSize(750,750);

        displayContainer.setBounds(10,10,715,200);
        displayBooks.setBounds(10, 10, 715, 200);

        adminUI.setLocationRelativeTo(null);
        adminUI.setVisible(true);
        adminUI.setLayout(null);
        adminUI.setTitle("Admin Window");
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
}
