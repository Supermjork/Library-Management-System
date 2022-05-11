package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import library.LibAdmin;
import library.LibBook;
import library.LibStudent;
import java.util.List;

/**
 * 
 * @author Icen Zeyada
 * @apiNote Still a prototype.
 * @version 1.0 Beta
 */
public class CsvFileReader {
    public static void loadDataBook(String filename, List<LibBook> bookList) {
        //parsing a CSV file into Scanner class constructor  
        try {
            Scanner sc = new Scanner(new File(filename));

            //splitting the data by space
            // sc.useDelimiter(" ");

            String[] constructorValues; 
            while(sc.hasNextLine()) {
                constructorValues = sc.nextLine().split(",");
                bookList.add(new LibBook(constructorValues[0],constructorValues[1],Integer.parseInt(constructorValues[2]),Integer.parseInt(constructorValues[3])
                ,Integer.parseInt(constructorValues[4]),Integer.parseInt(constructorValues[5]),Integer.parseInt(constructorValues[6]),Integer.parseInt(constructorValues[7])
                ,Integer.parseInt(constructorValues[8])));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void loadDataStudent(String filename, List<LibStudent> studentList) {
        //parsing a CSV file into Scanner class constructor  
        try {
            Scanner sc = new Scanner(new File(filename));

            //splitting the data by space
            // sc.useDelimiter(" ");

            String[] constructorValues; 
            while(sc.hasNextLine()) {
                constructorValues = sc.nextLine().split(",");
                studentList.add(new LibStudent(constructorValues[0], Integer.parseInt(constructorValues[1]), Long.parseLong(constructorValues[2]),constructorValues[3]));
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void loadDataAdmin(String filename, List<LibAdmin> adminList) {
        //parsing a CSV file into Scanner class constructor  
        try {
            Scanner sc = new Scanner(new File(filename));

            //splitting the data by space
            // sc.useDelimiter(" ");

            String[] constructorValues; 
            while(sc.hasNextLine()) {
                constructorValues = sc.nextLine().split(",");
                adminList.add(new LibAdmin(constructorValues[0], Integer.parseInt(constructorValues[1]), Long.parseLong(constructorValues[2]),constructorValues[3]));

            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
