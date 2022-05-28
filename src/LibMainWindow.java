// importing Java AWT class
import library.LibAdmin;
import library.LibStudent;
import library.LibUserInterface;
import reader.CsvFileReader;

import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * This class is used to create a GUI for the Library management system.
 * Prototype: LibGUI.java is made to replace the original GUI that used javafx.
 * The original GUI is harder to use and more complicated to install.
 */
public class LibMainWindow extends JPanel {
    String userInEmail;
    long userInPhone;
  
    // initializing using constructor  
    public LibMainWindow() {
        
        // creating frame
        JFrame login = new JFrame("LibGUI Login Window");

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

        login.add(userEmailLabel);
        login.add(userIDLabel);

        login.add(emailInField);
        login.add(phoneInField);

        login.add(registerButton);
        login.add(loginButton);

        // frame size 300 width and 300 height    
        login.setSize(300,300);
    
        // setting the title of Frame  
        login.setTitle("Login Window");
            
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

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login.dispose();
                new LibRegisterWindow().setVisible(true);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<LibStudent> studentList = new ArrayList<>();
                List<LibAdmin> adminList = new ArrayList<>();

                CsvFileReader.loadDataStudent("src\\filebase\\students.csv", studentList);
                CsvFileReader.loadDataAdmin("src\\filebase\\admins.csv", adminList);

                if(emailInField.getText() == null || phoneInField.getText() == null) {
                    JOptionPane.showMessageDialog(login,"Credentials Insufficient");
                } else {
                    userInEmail = emailInField.getText();
                    userInPhone = Long.parseLong(phoneInField.getText());

                    if(loginValidator(userInEmail, userInPhone, studentList)) {
                        login.dispose();
                        new LibStudentWindow().setVisible(true);
                    } else if(loginValidator(userInEmail, userInPhone, adminList)) {
                        login.dispose();
                        new LibAdminWindow().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(login,"User not found");
                    }
                }
            }
        });
    }

    // main method
    public static void main(String[] args) {
        // creating instance of Frame class
        LibMainWindow window = new LibMainWindow();
    }

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
        return loginValidator(email, phoneNum, adminList);
    }
}
