package requests;

import library.LibBook;
import library.LibStudent;

/**
 * Request Object, holds the ID of the user that borrows or orders a book, As well as the book's ID,
 * and the date of the request.
 */

public interface LibRequest {
    LibRequest request(LibBook book, LibStudent student);
    void allowRequest();
    void denyRequest();
}