package app.foodapp.controller.login;

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

public final class LoginPage {
    @FXML private TextField usernameEntered;
    @FXML private PasswordField passwordEntered;
    private BufferedReader reader;


    public void checkUserLogin(ActionEvent actionEvent) throws IOException {
        try {
            reader = new BufferedReader(new FileReader("userInformation.txt"));
            boolean isTheSameUsername = false;
            boolean isTheSamePassword = false;
            String line = "";
            while ((line = reader.readLine()) != null) {
                int counter = 0;
                String[] tabOfRow = line.split(",");

                for (String index : tabOfRow) {
                    if (counter != 2) {
                        if (index.equals(usernameEntered.getText()))
                            isTheSameUsername = true;
                        if (index.equals(passwordEntered.getText()))
                            isTheSamePassword = true;
                    }
                    counter += 1;
                }
            }

            if(!isTheSameUsername)
                AlertFound.usernameNotExist();
            if(!isTheSamePassword)
                AlertFound.passwordNotExist();
            if(isTheSamePassword && isTheSameUsername)
                // Open next stage
                loginAccepted();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
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

}
