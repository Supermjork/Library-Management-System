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

public class LibAdminWindow {
    public LibAdminWindow() {
        JFrame adminUI = new JFrame("LibGUI Admin Window");

        JButton adminAddBook = new JButton();
    }

    public static void main(String[] args) {
        List<LibBook> bookList = new ArrayList<>();
        List<LibOrder> orderList = new ArrayList<>();
        List<LibBorrow> borrowList = new ArrayList<>();
        List<LibReturn> returnList = new ArrayList<>();
        List<LibReserve> reserveList = new ArrayList<>();

        CsvFileReader.loadDataBook("src\\filebase\\books.csv", bookList);
        CsvFileReader.loadDataOrder("src\\filebase\\orders.csv", orderList);
        CsvFileReader.loadDataBorrow("src\\filebase\\borrows.csv", borrowList);
        CsvFileReader.loadDataReturn("src\\filebase\\returns.csv", returnList);
        CsvFileReader.loadDataReserve("src\\filebase\\reservations.csv", reserveList);
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
