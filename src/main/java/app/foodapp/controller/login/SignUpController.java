package app.foodapp.controller.login;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SignUpController {
    @FXML private ComboBox regimeChoice;

    public SignUpController() throws IOException {
        signUpUser();
    }


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
        String regime = regimeChoice.getSelectionModel().getSelectedItem().toString();
        System.out.println(regime); // renvoie le regime choisie
    }

    public void signUpUser() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("userInformation.txt"));
        System.out.println(reader);
    }
}
