// importing Java AWT class
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class is used to create a GUI for the Library management system.
 * Prototype: LibGUI.java is made to replace the original GUI that used javafx.
 * The original GUI is harder to use and more complicated to install.
 */
public class LibMainWindow extends JPanel{
  
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
        LibMainWindow window = new LibMainWindow();
        LibRegisterWindow regWindow = new LibRegisterWindow();
    }
}

class LibRegisterWindow extends JPanel{
    public LibRegisterWindow() {
        // Creating Frame for registration
        JFrame register = new JFrame("LibGUI Registration Window");

        // Creating Labels for required registration credentials
        JLabel regName  = new JLabel("Registration Name: ");
        JLabel regID    = new JLabel("Registration ID: ");
        JLabel regPhone = new JLabel("Registration Phone Number: ");
        JLabel regEmail = new JLabel("Registration Email: ");

        // Creating text fields for credentials respectively
        JTextField nameInReg  = new JTextField();
        JTextField IDInReg    = new JTextField();
        JTextField phoneInReg = new JTextField();
        JTextField emailInReg = new JTextField();

        // Buttons to either return to main window "login" or register the student by appending into students.csv
        JButton regButton   = new JButton("Register");
        JButton backButton  = new JButton("Back");

        // Setting components' bounds
        regName.setBounds(50,20,200,30);
        regID.setBounds(50,70,200, 30);
        regPhone.setBounds(50,120, 200, 30);
        regEmail.setBounds(50,170,200,30);

        nameInReg.setBounds(250,20,300,30);
        IDInReg.setBounds(250,70,300,30);
        phoneInReg.setBounds(250,120,300,30);
        emailInReg.setBounds(250,170,300,30);

        regButton.setBounds(50,220,120,30);
        backButton.setBounds(200,220,100,30);

        // Setting window size
        register.setSize(650, 300);

        // Adding components (fields, labels, buttons)
        register.add(regName);
        register.add(nameInReg);

        register.add(regID);
        register.add(IDInReg);

        register.add(regPhone);
        register.add(phoneInReg);

        register.add(regEmail);
        register.add(emailInReg);

        register.add(regButton);
        register.add(backButton);

        register.setLayout(null);
        register.setVisible(true);
        register.setTitle("Registration Window");

        register.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                register.dispose();
            }
        });


        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Should check for emptiness of text fields before appending to students.csv
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Needs to return to the main window "login"
                register.dispose();
            }
        });
    }
}

class LibStudentWindow {
    public LibStudentWindow() {
        // Creating frame for student UI
        JFrame studentUI = new JFrame("LibGUI Student Window");

        // Creating buttons for user

    }
}

class LibAdminWindow {
    public LibAdminWindow() {
        JFrame adminUI = new JFrame("LibGUI Admin Window");

        JButton adminAddBook = new JButton();
    }
}
