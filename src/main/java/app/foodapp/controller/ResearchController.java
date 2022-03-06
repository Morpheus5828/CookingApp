package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import app.foodapp.model.dataManipulation.recipe.RecipeInformation;
import app.foodapp.model.node.Favorite;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;

public class ResearchController extends recipeListController {

    @FXML private TextField searchByIngredient;
    @FXML private AnchorPane ingredientsAnchorPane;
    @FXML private VBox recipeDisplay;
    @FXML private ImageView leftCornerLogo;

    private ArrayList<Button> ingredientButtons = new ArrayList<>();
    private ArrayList<String> stringsListOfRecipes = new ArrayList<>();
    private RecipeInformation recipeInformation;
    private Favorite favorites = new Favorite();

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void welcomePage() {
        setImage(leftCornerLogo, new Image("/app/foodapp/view/pictures/logo/logoApp.png"));

        Label title = new Label("Welcome to COOKING APP !\n");
        title.setId("text-welcome");
        Label message = new Label("Let's start and search a recipe by using the text field in the top right corner !");
        message.setId("text-message");
        ImageView image = new ImageView(new Image("/app/foodapp/view/pictures/logo/logoApp.png"));
        image.setPreserveRatio(true);
        image.setFitWidth(400);

        recipeDisplay.setAlignment(Pos.TOP_CENTER);
        recipeDisplay.getChildren().add(title);
        recipeDisplay.getChildren().add(image);
        recipeDisplay.getChildren().add(message);
    }

    public void addIngredientToSearch() {
        createIngredientsButtons();
        this.isSearchLunched = false;
        this.searchByIngredient.clear();
    }

    private boolean isSearchLunched = false;

    public void displayApiInformation(ActionEvent actionEvent) {
        recipeDisplay.getChildren().clear();
        recipeBoxDisplayList.clear();
        favoritesButtonList.clear();

        recipeInformation = new RecipeInformation(stringsListOfRecipes);
        List<Recipe> recipeList = recipeInformation.listOfRecipe;
        setRecipeList(recipeList);

        isSearchLunched = true;
        pageDisplay(1, this.recipeDisplay, recipeList);
    }

    @Override
    public EventHandler<ActionEvent> removeRecipeFromFavorites(final Button button, final Recipe recipe, final StackPane stackPane, final HBox box) {
        return event -> {
            ParallelTransition animation = removeRecipeFromFavoritesAnimation(button, recipe, stackPane, box);
            animation.setOnFinished(event1 -> {
                favoriteNode.removeFromFavorite(recipe);
                manageFavoriteButton(button, recipe, stackPane, box);
            });
            animation.play();
        };
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
            stringsListOfRecipes.add(ingredientButtons.indexOf(newIngredientButton), searchByIngredient.getText());
            newIngredientButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    stringsListOfRecipes.remove(ingredientButtons.indexOf(newIngredientButton));
                    ingredientButtons.remove(ingredientButtons.indexOf(newIngredientButton));
                    ingredientsAnchorPane.getChildren().remove(newIngredientButton);
                    positionX += (20 + newIngredientButton.getPrefWidth());
                    if(isSearchLunched) displayApiInformation(event);
                }
            });
            positionX -= (20 + newIngredientButton.getPrefWidth());
        }
        else{
            throw new ArrayIndexOutOfBoundsException("Can't add more ingredients");
        }
    }
}