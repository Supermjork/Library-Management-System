import library.LibAdmin;
import library.LibBook;
import library.LibStudent;
import library.LibUserInterface;
import reader.CsvFileReader;
import requests.LibBorrow;
import requests.LibOrder;
import requests.LibReserve;
import requests.LibReturn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LibFrame extends JFrame {
    private JTextField emailField;
    private JTextField phoneInField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JPanel mainPanel;

    String usrEmail;
    long usrPhone;

    public LibFrame() {
        setContentPane(mainPanel);
        setTitle("Main Menu");
        setSize(500, 350);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usrEmail = emailField.getText();
                usrPhone = Long.parseLong(phoneInField.getText());
            }
        });
    }

    public static void main(String[] args) {
        LibFrame libraryUI = new LibFrame();

        List<LibBook> bookList = new ArrayList<>();
        List<LibStudent> studentList = new ArrayList<>();
        List<LibAdmin> adminList = new ArrayList<>();
        List<LibOrder> orderList = new ArrayList<>();
        List<LibBorrow> borrowList = new ArrayList<>();
        List<LibReturn> returnList = new ArrayList<>();
        List<LibReserve> reserveList = new ArrayList<>();

        CsvFileReader.loadDataBook("src\\filebase\\books.csv", bookList);
        CsvFileReader.loadDataStudent("src\\filebase\\students.csv", studentList);
        CsvFileReader.loadDataAdmin("src\\filebase\\admins.csv", adminList);
        CsvFileReader.loadDataOrder("src\\filebase\\orders.csv", orderList);
        CsvFileReader.loadDataBorrow("src\\filebase\\borrows.csv", borrowList);
        CsvFileReader.loadDataReturn("src\\filebase\\returns.csv", returnList);
        CsvFileReader.loadDataReserve("src\\filebase\\reservations.csv", reserveList);
    }

    public static <T extends LibUserInterface> boolean loginValidator(String email, long phoneNum, List<T> userList) {
        for(T user : userList) {
            if(email.equals(user.getUserEmail()) && phoneNum == user.getPhoneNum()) {
                return true;
            }
        }
        return false;
    }
}
