package reader;

import java.io.*;
import java.util.Map;

/**
 * A class that reads a file and adds it to a Map based structure mentioned.
 * @apiNote Still a prototype.
 * @version 1.0 Beta  
 */
public class GenericFileReaderMap {
    private static <E extends Map> void readFileTo(String fileName, E map) {
        
        // Declares a scanner to read the file.
        File file = new File(fileName);
        
        // Declares a buffered reader to read the file.
        BufferedReader reader = null;

        // Counter to serve as the key for the map
        int counter = 0;

        // Try block to read the file.
        try {
            
            // Creates a buffered reader to read the file.
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            
            // While loop to read the file and parses it to an Integer
            while ((text = reader.readLine()) != null) {
                map.put(counter,text);
                counter++;
            }
        
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        
        } catch (IOException e) {
            e.printStackTrace();
        
        // Catch block to close the file.
        } finally {
            
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
