package app.foodapp.controller.login;

import app.foodapp.view.FoodApp;
import app.foodapp.view.alert.AlertFound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;

public class LoginPage {
    @FXML private TextField usernameEntered;
    private BufferedReader reader = null;
    private PrintWriter wrote = null;
    private String userInformation = "userInformation.txt";
    private String line = "";


    public LoginPage() throws IOException {
        this.reader = new BufferedReader(new FileReader(userInformation));
    }


    public void checkUserLogin(ActionEvent actionEvent) throws IOException {
        boolean isTheSameUsername = false;
        try {
            while ((line = reader.readLine()) != null) {
                int compteur = 0;
                String[] row = line.split(",");
                for(String index : row) {
                    if(compteur == 0) {
                        if (index.equals(usernameEntered.getText()))
                            isTheSameUsername = true;
                    }
                    compteur += 1;
                }
            }
            if(isTheSameUsername)
                AlertFound.usernameAlreadyExist();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            reader.close();
        }
    }

    public void creationOfAnAccount(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/register/sign_up.fxml"));
        loginStage.setTitle("Cooking App");
        loginStage.setScene(new Scene(root));
        loginStage.show();
    }

}
