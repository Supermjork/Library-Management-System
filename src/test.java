import java.util.*;
import library.*;
import reader.*;

public class test {
    public static void main(String[] args) {
        List<LibBook> bookList = new ArrayList<LibBook>();
        CsvFileReader.loadDataBook("src/books.csv", bookList);
        for(LibBook book : bookList) {
            System.out.println(
                        "name: " + book.getBookName() + 
                        " author: " + book.getBookAuthor() + 
                        " id: " + book.getBookID() + 
                        " isslibdate: " + book.getIssLibDate() + 
                        " issgldate: " + book.getIssGloDate());
        }
    }
}
