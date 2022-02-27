package app.foodapp.controller.login;

import app.foodapp.view.alert.AlertFound;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.*;

public class SignUpController {
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

    public void addRegimeString() {
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
        String content = username.getText() + "," + password.getText() + "," + regime + ",\n";
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
        }
    }

    private Boolean userAlreadyExist(String content) throws IOException {
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

}
