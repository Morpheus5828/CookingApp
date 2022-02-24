package app.foodapp.controller.login;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage implements ActionListener {

    private HashMap<String, String> loginInfo;

    public LoginPage(HashMap<String, String> loginInfo, Stage stage) {
        this.loginInfo = loginInfo;

    }


    protected HashMap<String, String> getLoginInfo() {
        return this.loginInfo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
