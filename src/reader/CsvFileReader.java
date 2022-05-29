package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import library.*;
import requests.*;

import java.util.List;

/**
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
                bookList.add(new LibBook(constructorValues[0],
                                         constructorValues[1],
                                         Integer.parseInt(constructorValues[2]),
                                         Integer.parseInt(constructorValues[3]),
                                         Integer.parseInt(constructorValues[4]),
                                         Integer.parseInt(constructorValues[5]),
                                         Integer.parseInt(constructorValues[6]),
                                         Integer.parseInt(constructorValues[7]))
                            );
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
                studentList.add(new LibStudent(constructorValues[0], 
                                               Integer.parseInt(constructorValues[1]),
                                               Long.parseLong(constructorValues[2]),
                                               constructorValues[3]));
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

            String[] constructorValues; 
            while(sc.hasNextLine()) {
                constructorValues = sc.nextLine().split(",");
                adminList.add(new LibAdmin(constructorValues[0], 
                                           Integer.parseInt(constructorValues[1]),
                                           Long.parseLong(constructorValues[2]),
                                           constructorValues[3])
                            );

            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void loadDataBorrow(String filename, List<LibBorrow> borrowRequests) {
        //parsing a CSV file into Scanner class constructor  
        try {
            Scanner sc = new Scanner(new File(filename));

            String[] constructorValues; 
            while(sc.hasNextLine()) {
                constructorValues = sc.nextLine().split(",");
                borrowRequests.add(new LibBorrow(
                                        Integer.parseInt(constructorValues[0]),
                                        Integer.parseInt(constructorValues[1]))
                            );
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    public static void loadDataOrder(String filename, List<LibOrder> orderRequests) {
        //parsing a CSV file into Scanner class constructor  
        try {
            Scanner sc = new Scanner(new File(filename));

            String[] constructorValues; 
            while(sc.hasNextLine()) {
                constructorValues = sc.nextLine().split(",");
                orderRequests.add(new LibOrder(
                                        Integer.parseInt(constructorValues[0]),
                                        Integer.parseInt(constructorValues[1]))
                            );
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    public static void loadDataReturn(String filename, List<LibReturn> returnRequests) {
        //parsing a CSV file into Scanner class constructor  
        try {
            Scanner sc = new Scanner(new File(filename));

            String[] constructorValues; 
            while(sc.hasNextLine()) {
                constructorValues = sc.nextLine().split(",");
                returnRequests.add(new LibReturn(
                                        Integer.parseInt(constructorValues[0]),
                                        Integer.parseInt(constructorValues[1]))
                            );
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void loadDataReserve(String filename, List<LibReserve> reserveRequests) {
        //parsing a CSV file into Scanner class constructor
        try {
            Scanner sc = new Scanner(new File(filename));

            String[] constructorValues;
            while(sc.hasNextLine()) {
                constructorValues = sc.nextLine().split(",");
                reserveRequests.add(new LibReserve(
                                    Integer.parseInt(constructorValues[0]),
                                    Integer.parseInt(constructorValues[1]))
                );
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
