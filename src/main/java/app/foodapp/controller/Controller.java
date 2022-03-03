package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import app.foodapp.model.dataManipulation.recipe.RecipeInformation;
import app.foodapp.model.node.Favorite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {

    @FXML private AnchorPane rootPane;
    @FXML private TextField searchByIngredient;
    @FXML private Text mainDisplay;
    @FXML private AnchorPane buttonsAnchorPane;
    @FXML private AnchorPane ingredientsAnchorPane;


    private ArrayList<Button> ingredientButtons = new ArrayList<Button>();
    private ArrayList<String> strings = new ArrayList<String>();
    private RecipeInformation recipeInformation;
    private ArrayList<Button> favoritesButtons = new ArrayList<Button>();
    protected Favorite favorites = new Favorite();
    private ArrayList<Button> detailsButtons = new ArrayList<Button>();
    protected Recipe recipeSelectedForDetails;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void goToFavorites(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/favorites.fxml"));
        Parent root = loader.load();
        FavoriteController favoriteController = loader.getController();
        favoriteController.showFavorites();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/app/foodapp/view/favorites.css").toExternalForm();

        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void addIngredientToSearch(KeyEvent keyEvent) {
        searchByIngredient.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.ENTER){
                    createIngredientsButtons();
                    searchByIngredient.clear();
                }
            }
        });
    }

    public void displayApiInformations(ActionEvent actionEvent) {
        buttonsAnchorPane.getChildren().clear();
        favoritesButtons.clear();
        detailsButtons.clear();
        recipeInformation = new RecipeInformation(strings);
        mainDisplay.setText(recipeInformation.displayGUI());
        createFavoriteButtons();
        createDetailsButtons();
    }

    private void createFavoriteButtons(){
        int positionY = 20;
        for(int index = 0 ; index != recipeInformation.listOfRecipe.size() ; index++){
            Button newFavoriteButton = new Button();
            newFavoriteButton.setPrefSize(50, 50);
            newFavoriteButton.setText("<3");
            newFavoriteButton.setLayoutX(0);
            newFavoriteButton.setLayoutY(positionY);
            buttonsAnchorPane.getChildren().add(newFavoriteButton);
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

    private void createIngredientsButtons(){
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
                    displayApiInformations(event);
                }
            });
            positionX -= (20 + newIngredientButton.getPrefWidth());
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Can't add more ingredients");
        }
    }

    private void goToDetails(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/details.fxml"));
        Parent root = loader.load();
        DetailsController detailsController = loader.getController();
        detailsController.showDetails(recipeSelectedForDetails);

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/app/foodapp/view/details.css").toExternalForm();

        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    private void createDetailsButtons(){
        int positionY = 20;
        for(int index = 0 ; index != recipeInformation.listOfRecipe.size() ; index++){
            Button newDetailsButton = new Button();
            newDetailsButton.setPrefSize(50, 50);
            newDetailsButton.setText("Details");
            newDetailsButton.setLayoutX(80);
            newDetailsButton.setLayoutY(positionY);
            buttonsAnchorPane.getChildren().add(newDetailsButton);
            detailsButtons.add(newDetailsButton);
            newDetailsButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    recipeSelectedForDetails = recipeInformation.listOfRecipe.get(detailsButtons.indexOf(newDetailsButton));
                    try {
                        goToDetails(event);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void goToProfile(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/profile.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}