package app.foodapp.controller.login;

import app.foodapp.view.FoodApp;
import app.foodapp.view.alert.AlertFound;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;

public class LoginPage {
    @FXML private TextField usernameEntered;
    @FXML private PasswordField passwordEntered;
    private final BufferedReader reader;
    private boolean isTheSameUsername = false;
    private boolean isTheSamePassword = false;
    private final boolean condition = true;
    private String line = "";

    public LoginPage() throws IOException {
        this.reader = new BufferedReader(new FileReader("userInformation.txt"));
    }
    public void checkUserLogin(ActionEvent actionEvent) throws IOException {
        try {
            while ((line = reader.readLine()) != null) {
                int counter = 0;
                String[] tabOfRow = line.split(",");

                for(String index : tabOfRow) {
                    if(counter != 2) {
                        if (index.equals(usernameEntered.getText()))
                            isTheSameUsername = true;
                        if(index.equals(passwordEntered.getText()))
                            isTheSamePassword = true;
                    }
                    counter += 1;
                }
            }
            checkAttributionProperties();
            if(isTheSamePassword && isTheSameUsername)
                // Open next stage
                loginAccepted();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void creationOfAnAccount(javafx.event.ActionEvent actionEvent) throws IOException {
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/register/sign_up.fxml"));
        loginStage.setTitle("Cooking App");
        loginStage.setScene(new Scene(root));
        loginStage.show();
    }

    private void loginAccepted() throws IOException {
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/favorites.fxml"));
        loginStage.setTitle("Cooking App");
        loginStage.setScene(new Scene(root));
        loginStage.show();
    }

    private void checkAttributionProperties() throws IOException {
        if(!isTheSameUsername)
            AlertFound.usernameNotExist();
        if(!isTheSamePassword)
            AlertFound.passwordNotExist();
    }

}
