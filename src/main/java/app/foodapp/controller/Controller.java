package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.RecipeInformation;
import app.foodapp.model.node.Favorite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML private AnchorPane rootPane;
    @FXML private TextField searchByIngredient;
    @FXML private Text mainDisplay;
    @FXML private AnchorPane favoritesAnchorPane;
    @FXML private AnchorPane ingredientsAnchorPane;


    private ArrayList<Button> ingredientButtons = new ArrayList<Button>();
    private ArrayList<String> strings = new ArrayList<String>();
    private RecipeInformation recipeInformation;
    private ArrayList<Button> favoritesButtons = new ArrayList<Button>();
    protected Favorite favorites = new Favorite();

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
                    createIngredientsButton();
                    searchByIngredient.clear();
                }
            }
        });
    }

    public void displayApiInformations(ActionEvent actionEvent) {
        for(Button oneButton : favoritesButtons){
            favoritesAnchorPane.getChildren().remove(oneButton);
        }
        favoritesButtons.clear();
        recipeInformation = new RecipeInformation(strings);
        mainDisplay.setText(recipeInformation.displayGUI());
        favoritesButtons.clear();
        createFavoriteButtons();
    }

    private void createFavoriteButtons(){
        int positionY = 20;
        for(int index = 0 ; index != recipeInformation.listOfRecipe.size() ; index++){
            Button newFavoriteButton = new Button();
            newFavoriteButton.setPrefSize(75, 75);
            newFavoriteButton.setText("<3");
            newFavoriteButton.setLayoutX(40);
            newFavoriteButton.setLayoutY(positionY);
            favoritesAnchorPane.getChildren().add(newFavoriteButton);
            favoritesButtons.add(newFavoriteButton);
            newFavoriteButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    favorites.addToFavorite(recipeInformation.listOfRecipe.get(favoritesButtons.indexOf(newFavoriteButton)));
                    newFavoriteButton.setText("Added");
                }
            });
            positionY += 175;
        }
    }

    private int positionX = 898;
    private int positionY = 59;

    private void createIngredientsButton(){
        if(ingredientButtons.size() < 10) {
            Button newIngredientButton = new Button();
            newIngredientButton.setText(searchByIngredient.getText() + " x");
            newIngredientButton.setPrefSize(75, 25);
            if (ingredientButtons.size() == 5) {
                positionX = 898;
                positionY -= (10 + newIngredientButton.getPrefHeight());
            }
            ingredientButtons.add(newIngredientButton);
            newIngredientButton.setLayoutX(positionX);
            newIngredientButton.setLayoutY(positionY);
            ingredientsAnchorPane.getChildren().add(newIngredientButton);
            strings.add(ingredientButtons.indexOf(newIngredientButton), searchByIngredient.getText());
            newIngredientButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    strings.remove(ingredientButtons.indexOf(newIngredientButton));
                    ingredientButtons.remove(ingredientButtons.indexOf(newIngredientButton));
                    ingredientsAnchorPane.getChildren().remove(newIngredientButton);
                }
            });
            positionX -= (20 + newIngredientButton.getPrefWidth());
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Can't add more ingredients");
        }
    }


}
