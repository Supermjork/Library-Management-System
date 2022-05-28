import library.LibUserInterface;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LibAdminWindow {
    public LibAdminWindow() {
        JFrame adminUI = new JFrame("LibGUI Admin Window");

        JButton adminAddBook = new JButton();
    }

    public static <T extends LibUserInterface> boolean loginValidator(String email, long phoneNum, List<T> userList) {
        for(T user : userList) {
            if(email.equals(user.getUserEmail()) && phoneNum == user.getPhoneNum()) {
                return true;
            }
        }
        return false;
    }

    public static void fileAppend(String dataStr, String fileName) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(dataStr);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error appending to file");
        }
    }

    public static <T extends LibUserInterface> boolean isAdmin(String email, long phoneNum, List<T> adminList) {
        return loginValidator(email, phoneNum, adminList);
    }
}
