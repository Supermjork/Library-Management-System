package library;

import java.time.LocalDate;

/**
 * Child class of the libUser which inherits basic fields, uses super constructor, also overrides privileges
 */
public class LibAdmin implements LibUserInterface {    //Basic Info for student, created constructor as there cannot be super() due to interface
    private String usrName;
    private int usrID;
    private long phoneNum;
    private String userEmail;

    public LibAdmin(String studentName, int studentID, long studentPhone, String studentEmail) {
        usrName = studentName;
        usrID = studentID;
        phoneNum = studentPhone;
        userEmail = studentEmail;
    }

    //Intellij created such methods and their @Override, I filled in the returns after copying the info fields
    //This is an entire copy-paste of LibStudent.java

    @Override
    public void setName(String usrName) {
        this.usrName = usrName;
    }

    @Override
    public String getUsrName() {
        return usrName;
    }

    @Override
    public void setUsrID(int usrID) {
        this.usrID = usrID;
    }

    @Override
    public int getUsrID() {
        return usrID;
    }

    @Override
    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public long getPhoneNum() {
        return phoneNum;
    }

    @Override
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String getUserEmail() {
        return userEmail;
    }

}
