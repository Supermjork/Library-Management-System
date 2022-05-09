import java.time.LocalDate;

public class LibStudent implements LibUserInterface {
    //Basic Info for student, created constructor as there cannot be super() due to interface
    private String usrName;
    private int usrID;
    private long phoneNum;
    private String userEmail;
    private LocalDate creationDate;

    LibStudent(String studentName, int studentID, long studentPhone, String studentEmail) {
        usrName = studentName;
        usrID = studentID;
        phoneNum = studentPhone;
        userEmail = studentEmail;
        creationDate = LocalDate.now();
    }

    //Intellij created such methods and their @Override, I filled in the returns after copying the info fields

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

    @Override
    public void setCreationDate(int day, int month, int year) {
        this.creationDate = LocalDate.of(year, month, day);
    }

    @Override
    public LocalDate getCreationDate() {
        return creationDate;
    }
}