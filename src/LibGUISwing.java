// importing Java AWT class  
import library.LibAdmin;
import library.LibBook;
import library.LibStudent;
import reader.CsvFileReader;
import requests.LibBorrow;
import requests.LibOrder;
import requests.LibReserve;
import requests.LibReturn;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * This class is used to create a GUI for the Library management system.
 * Prototype: LibGUI.java is made to replace the original GUI that used javafx.
 * The original GUI is harder to use and more complicated to install.
 */
public class LibGUISwing {
  
    // initializing using constructor  
    LibGUISwing() {  
        
        // creating frame
        JFrame login = new JFrame("LibGUI Login Window");
        JFrame register = new JFrame("LibGUI Registration Window");
        JFrame adminUI = new JFrame("LibGUI Admin Window");
        JFrame studentUI = new JFrame("LibGUI Student Window");

        // creating a label
        JLabel userEmailLabel = new JLabel("Email: ");
        JLabel userIDLabel = new JLabel("Phone: ");

        // creating a text field
        JTextField emailInField = new JTextField();
        JTextField phoneInField = new JTextField();

        
        // creating a button   
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Registration");

        // setting button position on screen for all the above components
        loginButton.setBounds(30,200,80,30);
        registerButton.setBounds(120, 200, 120, 30);

        userEmailLabel.setBounds(40,80,80,30);
        userIDLabel.setBounds(40,120,80,30);

        emailInField.setBounds(100,80,150,30);
        phoneInField.setBounds(100,120,150,30);

        // adding the components to frame    
        login.add(loginButton);

        login.add(userEmailLabel);
        login.add(userIDLabel);

        login.add(emailInField);
        login.add(phoneInField);

        login.add(registerButton);
        // frame size 300 width and 300 height    
        login.setSize(300,300);
    
        // setting the title of Frame  
        login.setTitle("LibGUI Login Window");
            
        // no layout manager   
        login.setLayout(null);
    
        // now frame will be visible, by default it is not visible    
        login.setVisible(true);

        // closing the frame on exit
        login.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                login.dispose();
            }
        });
    }    
   
    // main method  
    public static void main(String[] args) {
    
        // creating instance of Frame class   
        LibGUISwing window = new LibGUISwing();

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
   
}    