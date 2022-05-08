import java.time.LocalDate;

/**
 * This file creates the parent class of the User on the library system, contains the user's:
 * - Name
 * - User ID
 * - 10 digit phone number
 * - Email ID
 * - Account's date of creation
 */

abstract class LibUserAbstract {
    //Basic info
    private String usrName;
    private int usrID;
    private long phoneNum;
    private String userEmail;
    private LocalDate creationDate;

    protected LibUserAbstract(String usrName, int usrID, long phoneNum, String userEmail) {
        this.usrName = usrName;
        this.usrID = usrID;
        this.phoneNum = phoneNum;
        this.userEmail = userEmail;
        this.creationDate = LocalDate.now();
    }

    protected void setName(String usrName) {
        this.usrName = usrName;
    }

    public String getUsrName() {
        return usrName;
    }

    protected void setUsrID(int usrID) {
        this.usrID = usrID;
    }

    public int getUsrID() {
        return usrID;
    }

    protected void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public long getPhoneNum() {
        return phoneNum;
    }

    protected void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    protected void setCreationDate(int day, int month, int year) {
        this.creationDate = LocalDate.of(year, month, day);
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    abstract void placeOrder();
    abstract void viewUserRequests();
    abstract void viewUserRequests(int userId);




}