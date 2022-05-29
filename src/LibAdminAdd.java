import library.LibBook;
import reader.CsvFileReader;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibAdminAdd extends JPanel {
    public LibAdminAdd() {
        List<LibBook> bookList = new ArrayList<>();
        CsvFileReader.loadDataBook("src\\filebase\\books.csv", bookList);

        JFrame adminAdd = new JFrame("Admin Adding");

        JButton addBookButton = new JButton("Add");
        JButton returnButton = new JButton("Return");

        JLabel idLabel = new JLabel("Book ID: ");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("Book Name: ");
        JTextField nameField = new JTextField();

        JLabel authorLabel = new JLabel("Author: ");
        JTextField authorField = new JTextField();

        JLabel releaseDate = new JLabel("Day, Month, Year: ");
        JTextField dayField = new JTextField();

        JTextField monthField = new JTextField();

        JTextField yearField = new JTextField();

        JLabel stockAmount = new JLabel("Amount: ");
        JTextField stockField = new JTextField();

        JLabel priceLabel = new JLabel("Price: ");
        JTextField priceField = new JTextField();


        // Setting boundaries
        adminAdd.setSize(450,400);

        idLabel.setBounds(10,10, 200,30);
        idField.setBounds(110,10,200,30);

        nameLabel.setBounds(10,60,200,30);
        nameField.setBounds(110,60,200,30);

        authorLabel.setBounds(10,110,200,30);
        authorField.setBounds(110,110,200,30);

        releaseDate.setBounds(10,160,200, 30);
        dayField.setBounds(110,160,50,30);
        monthField.setBounds(180,160,50,30);
        yearField.setBounds(250, 160, 60, 30);

        stockAmount.setBounds(10,210,200,30);
        stockField.setBounds(110,210,200,30);

        priceLabel.setBounds(10,260,200,30);
        priceField.setBounds(110,260,200,30);

        returnButton.setBounds(10, 320, 150,30);
        addBookButton.setBounds(275,320,150,30);

        // Adding components to frame
        adminAdd.add(returnButton);
        adminAdd.add(addBookButton);

        adminAdd.add(idLabel);
        adminAdd.add(idField);

        adminAdd.add(nameLabel);
        adminAdd.add(nameField);

        adminAdd.add(authorLabel);
        adminAdd.add(authorField);

        adminAdd.add(releaseDate);
        adminAdd.add(dayField);
        adminAdd.add(monthField);
        adminAdd.add(yearField);

        adminAdd.add(stockAmount);
        adminAdd.add(stockField);

        adminAdd.add(priceLabel);
        adminAdd.add(priceField);

        adminAdd.setLayout(null);
        adminAdd.setVisible(true);
        adminAdd.setLocationRelativeTo(null);
        adminAdd.setTitle("Book Addition");

        adminAdd.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                adminAdd.dispose();
            }
        });

        addBookButton.addActionListener(e -> {
            if(idField.getText().equals("") | nameField.getText().equals("") | authorField.getText().equals("") |
                 dayField.getText().equals("") | monthField.getText().equals("") | yearField.getText().equals("")
                 | stockField.getText().equals("") | priceField.getText().equals("")) {
                JOptionPane.showMessageDialog(adminAdd, "Fill in credentials");
            } else {
                int addBookID = Integer.parseInt(idField.getText());
                String addBookName = nameField.getText();
                String addAuthor = authorField.getText();
                int addDay = Integer.parseInt(dayField.getText());
                int addMonth = Integer.parseInt(monthField.getText());
                int addYear = Integer.parseInt(yearField.getText());
                int addStock = Integer.parseInt(stockField.getText());
                int addPrice = Integer.parseInt(priceField.getText());

                LibBook addBook = new LibBook(addBookName, addAuthor, addBookID, addDay, addMonth, addYear,
                                              addStock, addPrice);

                fileAppend(addBook.toString(), "src\\filebase\\books.csv");
                CsvFileReader.loadDataBook("src\\filebase\\books.csv", bookList);
            }
        });

        returnButton.addActionListener(e -> adminAdd.dispose());
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

