package library;

import java.time.LocalDate;

/**
 * This file creates the parent class of the User on the library system, contains the user's:
 * - Name
 * - User ID
 * - 10-digit phone number
 * - Email ID
 * - Account's date of creation
 */
public interface LibUserInterface {
    //Basic info (Deleted all fields, assuming they are static and wrote them in the child class(es))

    void setName(String usrName);
    String getUsrName();
    void setUsrID(int usrID);
    int getUsrID();
    void setPhoneNum(long phoneNum);
    long getPhoneNum();
    void setUserEmail(String userEmail);
    String getUserEmail();
    void setCreationDate(int day, int month, int year);
    LocalDate getCreationDate();
}