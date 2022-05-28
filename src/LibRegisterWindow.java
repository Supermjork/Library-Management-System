import library.LibStudent;
import reader.CsvFileReader;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibRegisterWindow extends JPanel {
    String studentName;
    int studentID;
    long studentNum;
    String studentEmail;

    LibRegisterWindow() {
        // Creating Frame for registration
        JFrame register = new JFrame("LibGUI Registration Window");

        // Creating Labels for required registration credentials
        JLabel regName = new JLabel("Registration Name: ");
        JLabel regID = new JLabel("Registration ID: ");
        JLabel regPhone = new JLabel("Registration Phone Number: ");
        JLabel regEmail = new JLabel("Registration Email: ");

        // Creating text fields for credentials respectively
        JTextField nameInReg = new JTextField();
        JTextField IDInReg = new JTextField();
        JTextField phoneInReg = new JTextField();
        JTextField emailInReg = new JTextField();

        // Buttons to either return to main window "login" or register the student by appending into students.csv
        JButton regButton = new JButton("Register");
        JButton backButton = new JButton("Back");

        // Setting components' bounds
        regName.setBounds(50, 20, 200, 30);
        regID.setBounds(50, 70, 200, 30);
        regPhone.setBounds(50, 120, 200, 30);
        regEmail.setBounds(50, 170, 200, 30);

        nameInReg.setBounds(250, 20, 300, 30);
        IDInReg.setBounds(250, 70, 300, 30);
        phoneInReg.setBounds(250, 120, 300, 30);
        emailInReg.setBounds(250, 170, 300, 30);

        regButton.setBounds(50, 220, 120, 30);
        backButton.setBounds(200, 220, 100, 30);

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


        regButton.addActionListener(e -> {
            // Should check for emptiness of text fields before appending to students.csv
            if(nameInReg.getText().equals("") || emailInReg.getText().equals("") || IDInReg.getText().equals("") || phoneInReg.getText().equals("")) {
                System.out.println("Empty Fields");
            } else {
               studentName = nameInReg.getText();
               studentEmail = emailInReg.getText();
               studentNum = Long.parseLong(phoneInReg.getText());
               studentID = Integer.parseInt(IDInReg.getText());

                LibStudent registerStudent = new LibStudent(studentName, studentID, studentNum, studentEmail);

                fileAppend(registerStudent.toString(), "src\\filebase\\students.csv");
            }
        });

        backButton.addActionListener(e -> {
            // Needs to return to the main window "login"
            register.dispose();
            new LibMainWindow().setVisible(true);
        });
    }

    public static void main(String[] args) {
        LibRegisterWindow registrationWindow = new LibRegisterWindow();

        List<LibStudent> studentList = new ArrayList<>();
        CsvFileReader.loadDataStudent("src\\filebase\\students.csv", studentList);
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
