// importing Java AWT class
import library.LibAdmin;
import library.LibStudent;
import library.LibUserInterface;
import reader.CsvFileReader;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * This class is used to create a GUI for the Library management system.
 * Prototype: LibGUI.java is made to replace the original GUI that used javafx.
 * The original GUI is harder to use and more complicated to install.
 * @author Icen Zeyada
 */
public class LibMainWindow extends JPanel {
    static Object userInSession;
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
        login.setLocationRelativeTo(null);
    
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
                // Choice for using dispose: should a user create a new account, the data needs to be reloaded
                // thus from my understanding, when the login window gets instantiated it should load the new csv
                login.dispose();
                new LibRegisterWindow().setVisible(true);
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Loading users' data into an iterable list
                List<LibStudent> studentList = new ArrayList<>();
                List<LibAdmin> adminList = new ArrayList<>();

                CsvFileReader.loadDataStudent("src\\filebase\\students.csv", studentList);
                CsvFileReader.loadDataAdmin("src\\filebase\\admins.csv", adminList);

                // Checking login validity
                // Empty fields
                if(emailInField.getText() == null || phoneInField.getText() == null) {
                    JOptionPane.showMessageDialog(login,"Credentials Insufficient");
                } else {
                    // Should neither be empty, entered data will be stored in variables
                    userInEmail = emailInField.getText();
                    userInPhone = Long.parseLong(phoneInField.getText());

                    // Looping over the student list, whilst checking the variables' data with the student object's
                    if(loginValidator(userInEmail, userInPhone, studentList)) {
                        for(LibStudent student : studentList) {
                            if(userInEmail.equals(student.getUserEmail()) & userInPhone == student.getPhoneNum()) {
                                userInSession = student;
                            }
                        }
                        // Closing the login window, initialising a student window
                        login.dispose();
                        new LibStudentWindow().setVisible(true);
                    } else if(loginValidator(userInEmail, userInPhone, adminList)) {
                        for(LibAdmin admin : adminList) {
                            if(userInEmail.equals(admin.getUserEmail()) & userInPhone == admin.getPhoneNum()) {
                                userInSession = admin;
                            }
                        }
                        login.dispose();
                        new LibAdminWindow().setVisible(true);
                    } else {
                        // Should neither above conditions be true (being either a student or an admin)
                        // then the user does not exist to begin with
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

    // Generic-ish method to take in an email string, long phone number, and any list type
    // iterates over the user object type in the list (student or admin, as generic T extends their interface)
    // returns true if email and phone number input are same as T's fields
    public static <T extends LibUserInterface> boolean loginValidator(String email, long phoneNum, List<T> userList) {
        for(T user : userList) {
            if(email.equals(user.getUserEmail()) && phoneNum == user.getPhoneNum()) {
                return true;
            }
        }
        return false;
    }
}
