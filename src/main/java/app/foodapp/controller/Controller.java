package app.foodapp.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML private AnchorPane rootPane;
    @FXML private TextField searchByIngredient;
    @FXML private Button firstIngredient;
    @FXML private Button secondIngredient;
    @FXML private Button thirdIngredient;
    @FXML private Button fourthIngredient;
    @FXML private Button fifthIngredient;
    @FXML private Button sixthIngredient;
    @FXML private Button submitButton;
    @FXML private Label mainDisplay;

    private ArrayList<String> strings = new ArrayList<String>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void goToFavorites(javafx.event.ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app/foodapp/view/favorites.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void addIngredientToSearch(KeyEvent keyEvent) {
        searchByIngredient.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    if(!sixthIngredient.isVisible()){
                        sixthIngredient.setText(searchByIngredient.getText() + "  x");
                        sixthIngredient.setVisible(true);
                        strings.add(0, searchByIngredient.getText());
                    }
                    else if(sixthIngredient.isVisible() && !fifthIngredient.isVisible()){
                        fifthIngredient.setText(searchByIngredient.getText() + "  x");
                        fifthIngredient.setVisible(true);
                        strings.add(1, searchByIngredient.getText());
                    }
                    else if(fifthIngredient.isVisible() && !fourthIngredient.isVisible()){
                        fourthIngredient.setText(searchByIngredient.getText() + " x");
                        fourthIngredient.setVisible(true);
                        strings.add(2, searchByIngredient.getText());
                    }
                    else if(fourthIngredient.isVisible() && !thirdIngredient.isVisible()){
                        thirdIngredient.setText(searchByIngredient.getText() + " x");
                        thirdIngredient.setVisible(true);
                        strings.add(3, searchByIngredient.getText());
                    }
                    else if(thirdIngredient.isVisible() && !secondIngredient.isVisible()){
                        secondIngredient.setText(searchByIngredient.getText() + " x");
                        secondIngredient.setVisible(true);
                        strings.add(4, searchByIngredient.getText());
                    }
                    else if(secondIngredient.isVisible() && !firstIngredient.isVisible()){
                        firstIngredient.setText(searchByIngredient.getText() + " x");
                        firstIngredient.setVisible(true);
                        strings.add(5, searchByIngredient.getText());
                    }
                    searchByIngredient.clear();
                }
            }
        });
    }

    public void removeFirstIngredient(ActionEvent actionEvent) {
        strings.remove(5);
        firstIngredient.setVisible(false);
        firstIngredient.setText("");
    }

    public void removeSecondIngredient(ActionEvent actionEvent) {
        strings.remove(4);
        secondIngredient.setVisible(false);
        secondIngredient.setText("");
    }

    public void removeThirdIngredient(ActionEvent actionEvent) {
        strings.remove(3);
        thirdIngredient.setVisible(false);
        thirdIngredient.setText("");
    }

    public void removeFourthIngredient(ActionEvent actionEvent) {
        strings.remove(2);
        fourthIngredient.setVisible(false);
        fourthIngredient.setText("");
    }

    public void removeFifthIngredient(ActionEvent actionEvent) {
        strings.remove(1);
        fifthIngredient.setVisible(false);
        fifthIngredient.setText("");
    }

    public void removeSixthIngredient(ActionEvent actionEvent) {
        strings.remove(0);
        sixthIngredient.setVisible(false);
        sixthIngredient.setText("");
    }

    public void displayApiInformations(ActionEvent actionEvent) {
        mainDisplay.setText(strings.toString());
    }
}
