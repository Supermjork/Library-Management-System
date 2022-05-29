import library.LibBook;
import reader.CsvFileReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class LibAdminUpdate extends JPanel {
    public LibAdminUpdate() {
        List<LibBook> bookList = new ArrayList<>();
        CsvFileReader.loadDataBook("src\\filebase\\books.csv", bookList);

        JFrame adminUpdate = new JFrame("Admin Updating");

        JButton updateBookButton = new JButton("Update");
        JButton returnButton = new JButton("Return");

        JLabel idLabel = new JLabel("Book ID: ");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Book Name: ");
        JTextField nameField = new JTextField();

        JLabel authorLabel = new JLabel("Author: ");
        JTextField authorField = new JTextField();

        JLabel releaseDateDay = new JLabel("Day: ");
        JTextField dayField = new JTextField();

        JLabel releaseDateMonth = new JLabel("Month: ");
        JTextField monthField = new JTextField();

        JLabel releaseDateYear = new JLabel("Year: ");
        JTextField yearField = new JTextField();

        JLabel stockAmount = new JLabel("Amount: ");
        JTextField stockField = new JTextField();

        JLabel priceLabel = new JLabel("Price: ");
        JTextField priceField = new JTextField();

        adminUpdate.setSize(450, 400);

        returnButton.setBounds(10, 320, 150,30);
        updateBookButton.setBounds(275,320,150,30);

        adminUpdate.add(returnButton);
        adminUpdate.add(updateBookButton);

        adminUpdate.setLayout(null);
        adminUpdate.setVisible(true);
        adminUpdate.setLocationRelativeTo(null);
        adminUpdate.setTitle("Book Updating");

        adminUpdate.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                adminUpdate.dispose();
            }
        });

        returnButton.addActionListener(e -> adminUpdate.dispose());
    }
}
