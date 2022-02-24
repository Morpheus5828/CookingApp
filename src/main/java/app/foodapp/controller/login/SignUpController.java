package app.foodapp.controller.login;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.awt.*;
import java.awt.event.ActionEvent;

public class SignUpController {
    @FXML private ComboBox regimeChoice;


    public void isCheck(javafx.event.ActionEvent actionEvent) {
        if(regimeChoice.isDisable()) {
            regimeChoice.setDisable(false);
            regimeChoice.setOpacity(1);
        } else {
            regimeChoice.setDisable(true);
            regimeChoice.setOpacity(0.25);
        }

    }
}
