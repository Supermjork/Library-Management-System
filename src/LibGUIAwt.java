// importing Java AWT class  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * This class is used to create a GUI for the Library management system.
 * Prototype: LibGUI.java is made to replace the original GUI that used javafx.
 * The original GUI is harder to use and more complicated to install.
 * @author Icen Zeyada
 */
public class LibGUIAwt {    
  
    // initializing using constructor  
    LibGUIAwt() {  
        
        // creating frame
        Frame f = new Frame("LibGUI Main Window");

        // creating a label
        Label l = new Label("Student ID: ");

        // creating a text field
        TextField t = new TextField();

        
        // creating a button   
        Button b = new Button("Login");  
        b.addActionListener(new ActionListener() {    
            public void actionPerformed (ActionEvent e) {    
                    t.setText("gay.");    
                }    
            }
        );
        // setting button position on screen for all the above components
        b.setBounds(105,140,80,30);  
        l.setBounds(55,100,80,30);
        t.setBounds(140,100,100,30);
    
        // adding the components to frame    
        f.add(b);  
        f.add(l);
        f.add(t);
    
        // frame size 300 width and 300 height    
        f.setSize(300,300);  
    
        // setting the title of Frame  
        f.setTitle("Trying AWT");   
            
        // no layout manager   
        f.setLayout(null);   
    
        // now frame will be visible, by default it is not visible    
        f.setVisible(true);  

        // closing the frame on exit
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                f.dispose();
            }
        }
);
    }    
   
    // main method  
    public static void main(String args[]) {   
    
        // creating instance of Frame class   
        LibGUIAwt window = new LibGUIAwt();    
    
    }  
   
}    