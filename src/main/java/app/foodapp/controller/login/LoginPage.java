package app.foodapp.controller.login;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;

public class LoginPage {
    public Button exitButton;
    public TextField username;
    public PasswordField password;
    public Button login;
    private BufferedReader reader = null;
    private PrintWriter wrote = null;
    private String userInformation = "userInformation.txt";
    private String line = "";
    private String regime;

    public LoginPage(TextField userName, PasswordField password, String regime) throws IOException {
        this.username = userName;
        this.password = password;
        this.regime = regime;
        this.reader = new BufferedReader(new FileReader(userInformation));
        checkUserLogin();
    }

    public LoginPage() throws IOException {
        wrote = new PrintWriter(new File(userInformation));
        wrote.println("gaga");
        wrote.close();

        reader = new BufferedReader(new FileReader(userInformation));
        System.out.println(reader.readLine());

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
