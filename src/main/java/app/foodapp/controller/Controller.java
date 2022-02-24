package app.foodapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    /*
    @FXML private Button helloWorldButton;
    @FXML private Button goodByeWorldButton;
    @FXML private Label label;
    */
    @FXML private Button login;
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML private AnchorPane rootPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void userLogin(ActionEvent actionEvent) throws IOException{
        System.out.println("login");
    }

    public void goToFavorites(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app/foodapp/view/favorites.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        rootPane.getChildren().setAll(pane);
    }
}
