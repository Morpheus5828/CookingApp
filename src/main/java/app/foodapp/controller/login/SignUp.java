package app.foodapp.controller.login;

import app.foodapp.model.alert.AlertFound;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;

public final class SignUp {
    @FXML private ComboBox regimeChoice;
    @FXML private TextField username;
    @FXML private PasswordField password;
    private String regime = "None";

    public void isCheck(javafx.event.ActionEvent actionEvent) {
        if(regimeChoice.isDisable()) {
            regimeChoice.setDisable(false);
            regimeChoice.setOpacity(1);
            addRegimeString();

        } else {
            regimeChoice.setDisable(true);
            regimeChoice.setOpacity(0.25);
        }
    }

    private void addRegimeString() {
        ObservableList<String> list = FXCollections.observableArrayList(
                "vegetarian",
                "vegan"
        );
        regimeChoice.setItems(list);
    }

    public void regimeSelected(javafx.event.ActionEvent actionEvent) {
        String regimeChoice = this.regimeChoice.getSelectionModel().getSelectedItem().toString();
        this.regime = regimeChoice;
    }

    public void userRegister() throws IOException {
        String content = username.getText() + "," + password.getText() + "," + regime + "," + "favorite=null" + ",\n";
        if(userAlreadyExist(content))
            AlertFound.usernameAlreadyExist();
        if(username.getText().isEmpty())
            AlertFound.usernameNotWrite();
        if(password.getText().isEmpty())
            AlertFound.passwordNotWrite();
        else {
            File file = new File("userInformation.txt");
            if(!file.exists())
                file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            fw.append(content);
            fw.close();
            loginAccepted();
        }
    }

    public Boolean userAlreadyExist(String content) throws IOException {
        Boolean usernameAlreadyExist = false;
        File userInformation = new File("userInformation.txt");
        BufferedReader obj = new BufferedReader(new FileReader(userInformation));
        String strng;
        while ((strng = obj.readLine()) != null) {
            strng = strng + "\n";
            if (strng.equals(content)) {
                usernameAlreadyExist = true;
            }
        }

        return usernameAlreadyExist;
    }

    private void loginAccepted() throws IOException {
        Stage loginStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        loginStage.setTitle("Cooking App");
        loginStage.setScene(new Scene(root));
        loginStage.show();
    }

    public void userRegister(String user, String userPassword, String userRegime) throws IOException {
        if(userAlreadyExist(user + "," + userPassword + "," + userRegime + ",\n"))
            System.out.println("Username already exist, please try again");
        if(userPassword.isEmpty())
            System.out.println("You forget to enter password, please try again");
        if(user.isEmpty())
            System.out.println("You forget to enter username, please try again");
        if(!userAlreadyExist(user + "," + userPassword + "," + userRegime + ",\n") && !userPassword.isEmpty() && !user.isEmpty()) {
            File file = new File("userInformation.txt");
            if(!file.exists())
                file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            fw.append(user + "," + userPassword + "," + userRegime + ",\n");
            System.out.println("User has been add successfully");
            fw.close();
        }

    }

}
