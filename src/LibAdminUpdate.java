import library.LibBook;
import reader.CsvFileReader;
import requests.LibBorrow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibAdminUpdate extends JPanel {
    public LibAdminUpdate() {
        List<LibBook> bookList = new ArrayList<>();
        CsvFileReader.loadDataBook("src\\filebase\\books.csv", bookList);

        JFrame adminUpdate = new JFrame("Book updating");

        JButton updateBookButton = new JButton("Update");
        JButton returnButton = new JButton("Return");

        JLabel idLabel = new JLabel("ID to Update: ");
        JTextField idField = new JTextField();

        JLabel nameLabel = new JLabel("New Book Name: ");
        JTextField nameField = new JTextField();

        JLabel authorLabel = new JLabel("New Author: ");
        JTextField authorField = new JTextField();

        JLabel releaseDate = new JLabel("New Date (D/M/Y): ");
        JTextField dayField = new JTextField();
        JTextField monthField = new JTextField();
        JTextField yearField = new JTextField();

        JLabel stockAmount = new JLabel("New Amount: ");
        JTextField stockField = new JTextField();

        JLabel priceLabel = new JLabel("New Price: ");
        JTextField priceField = new JTextField();

        // Setting boundaries
        adminUpdate.setSize(450, 400);

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
        updateBookButton.setBounds(275,320,150,30);

        returnButton.setBounds(10, 320, 150,30);
        updateBookButton.setBounds(275,320,150,30);

        // Adding Components to Frame
        adminUpdate.add(returnButton);
        adminUpdate.add(updateBookButton);

        adminUpdate.add(idLabel);
        adminUpdate.add(idField);

        adminUpdate.add(nameLabel);
        adminUpdate.add(nameField);

        adminUpdate.add(authorLabel);
        adminUpdate.add(authorField);

        adminUpdate.add(releaseDate);
        adminUpdate.add(dayField);
        adminUpdate.add(monthField);
        adminUpdate.add(yearField);

        adminUpdate.add(stockAmount);
        adminUpdate.add(stockField);

        adminUpdate.add(priceLabel);
        adminUpdate.add(priceField);

        adminUpdate.setLayout(null);
        adminUpdate.setVisible(true);
        adminUpdate.setLocationRelativeTo(null);
        adminUpdate.setTitle("Book Updating");

        adminUpdate.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                adminUpdate.dispose();
            }
        });

        updateBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(idField.getText().equals("")) {
                    JOptionPane.showMessageDialog(adminUpdate,"Enter book ID to Update");
                } else {
                    int idUpdate = Integer.parseInt(idField.getText());
                    LibBook updatingBook = null;
                    String[] bookFields = new String[7];

                    for(LibBook updatedBook : bookList) {
                        if(idUpdate == updatedBook.getBookID()) {
                            updatingBook = updatedBook;

                            bookFields[0] = updatedBook.getBookName();
                            bookFields[1] = updatedBook.getBookAuthor();
                            bookFields[2] = String.valueOf(updatedBook.getIssGloDate().getDayOfMonth());
                            bookFields[3] = String.valueOf(updatedBook.getIssGloDate().getMonthValue());
                            bookFields[4] = String.valueOf(updatedBook.getIssGloDate().getYear());
                            bookFields[5] = String.valueOf(updatedBook.getStockAmount());
                            bookFields[6] = String.valueOf(updatedBook.getPrice());
                            break;
                        }
                    }

                    String[] newFields = {nameField.getText(), authorField.getText(), dayField.getText(),
                                          monthField.getText(), yearField.getText(), stockField.getText(),
                                          priceField.getText()};

                    for(int i = 0; i < bookFields.length; i++) {
                        if(!newFields[i].equals("")) {
                            bookFields[i] = newFields[i];
                        }
                    }

                    assert updatingBook != null;
                    updatingBook.setBookName(bookFields[0]);
                    updatingBook.setBookAuthor(bookFields[1]);
                    updatingBook.setIssGloDate(Integer.parseInt(bookFields[2]),Integer.parseInt(bookFields[3]),
                                               Integer.parseInt(bookFields[4]));
                    updatingBook.setStockAmount(Integer.parseInt(bookFields[5]));
                    updatingBook.setPrice(Integer.parseInt(bookFields[6]));

                    FileWriter writeBook = null;
                    try {
                        writeBook = new FileWriter("src\\filebase\\books.csv");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    for (LibBook books : bookList) {
                        try {
                            assert writeBook != null;
                            writeBook.write(books.toString());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                    try {
                        assert writeBook != null;
                        writeBook.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });

        returnButton.addActionListener(e -> adminUpdate.dispose());
    }
}
