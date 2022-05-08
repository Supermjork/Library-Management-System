/**
 * Child class of the libUser which inherits basic fields, uses super constructor, also overrides privileges
 */

class libAdmin extends libUser {
    protected boolean isAdmin = true;

    public libAdmin(String usrName, int usrID, long phoneNum, String userEmail) {
        super(usrName, usrID, phoneNum, userEmail);
    }
}