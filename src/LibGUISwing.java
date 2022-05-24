// importing Java AWT class  
import java.awt.event.*;
import javax.swing.*;

/**
 * This class is used to create a GUI for the Library management system.
 * Prototype: LibGUI.java is made to replace the original GUI that used javafx.
 * The original GUI is harder to use and more complicated to install.
 * @author Icen Zeyada
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
        JLabel userEmailLabel = new JLabel("User Email: ");

        // creating a text field
        JTextField emailInField = new JTextField();

        
        // creating a button   
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e) {
                    emailInField.setText("nope");
            }
        }
        );
        // setting button position on screen for all the above components
        loginButton.setBounds(105,140,80,30);
        userEmailLabel.setBounds(55,100,80,30);
        emailInField.setBounds(140,100,100,30);
    
        // adding the components to frame    
        login.add(loginButton);
        login.add(userEmailLabel);
        login.add(emailInField);
    
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
        }
);
    }    
   
    // main method  
    public static void main(String[] args) {
    
        // creating instance of Frame class   
        LibGUISwing window = new LibGUISwing();    
    
    }  
   
}    