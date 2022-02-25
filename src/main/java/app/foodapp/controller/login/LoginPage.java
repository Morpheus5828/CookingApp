package app.foodapp.controller.login;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.*;

public class LoginPage {
    public TextField usernameEntered;
    public PasswordField passwordEntered;
    private BufferedReader reader = null;
    private PrintWriter wrote = null;
    private String userInformation = "userInformation.txt";
    private String line = "";


    public LoginPage() throws IOException {
        this.reader = new BufferedReader(new FileReader(userInformation));
        checkUserLogin();
    }

    public void checkUserLogin() throws IOException {
        try {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] row = line.split(",");
                for(String index : row) {
                    System.out.println(index);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {
            reader.close();
        }
    }

}
