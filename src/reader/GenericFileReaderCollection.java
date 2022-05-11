package reader;

import java.io.*;
import java.util.Collection;

/**
 * A class that reads a file and adds it to whichever structure mentioned.
 * @author Icen Zeyada
 * @apiNote Still a prototype.
 * @version 1.0 Beta  
 */
public class GenericFileReaderCollection {

    /**
     * Reads a file and adds it to whichever structure mentioned.
     * @param <E> The type of the collection
     * @param fileName is the file we want to read
     * @param collection The collection we want to add the file contents to
     * @note delimiter A new line is the delimiter between each element
     *                  in the collection.
     */
    private static <E extends Collection> void readFileTo(String fileName, E collection) {
        
        // Declares a scanner to read the file.
        File file = new File(fileName);
        
        // Declares a buffered reader to read the file.
        BufferedReader reader = null;

        // Try block to read the file.
        try {
            
            // Creates a buffered reader to read the file.
            reader = new BufferedReader(new FileReader(file));
            String text = null;
            
            // While loop to read the file and parses it to an Integer
            while ((text = reader.readLine()) != null) {
                collection.add(text);
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
