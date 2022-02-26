package app.foodapp.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML private AnchorPane rootPane;
    @FXML private TextField searchByIngredient;
    @FXML private Button firstIngredient;
    @FXML private Button secondIngredient;
    @FXML private Button thirdIngredient;
    @FXML private Button fourthIngredient;
    @FXML private Button fifthIngredient;
    @FXML private Button sixthIngredient;

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
                    }
                    else if(sixthIngredient.isVisible() && !fifthIngredient.isVisible()){
                        fifthIngredient.setText(searchByIngredient.getText() + "  x");
                        fifthIngredient.setVisible(true);
                    }
                    else if(fifthIngredient.isVisible() && !fourthIngredient.isVisible()){
                        fourthIngredient.setText(searchByIngredient.getText() + " x");
                        fourthIngredient.setVisible(true);
                    }
                    else if(fourthIngredient.isVisible() && !thirdIngredient.isVisible()){
                        thirdIngredient.setText(searchByIngredient.getText() + " x");
                        thirdIngredient.setVisible(true);
                    }
                    else if(thirdIngredient.isVisible() && !secondIngredient.isVisible()){
                        secondIngredient.setText(searchByIngredient.getText() + " x");
                        secondIngredient.setVisible(true);
                    }
                    else if(secondIngredient.isVisible() && !firstIngredient.isVisible()){
                        firstIngredient.setText(searchByIngredient.getText() + " x");
                        firstIngredient.setVisible(true);
                    }
                    searchByIngredient.clear();
                }
            }
        });
    }


    public void removeIngredient(ActionEvent actionEvent) {
        
    }
}
