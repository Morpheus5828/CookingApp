package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DetailsController extends MainController {
    @FXML private VBox detailsDisplay;

    public void getDetails(Recipe recipe) {
        HBox titleBox = new HBox();
        Label title = new Label(recipe.getTitle());
        title.getStyleClass().add("recipeTitle");
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getStyleClass().add("recipeTitleBox");
        titleBox.getChildren().addAll(title);

        HBox informationBox = new HBox();
        Label cookingTime = createLabel((int) Math.round(recipe.getCookingTime()) + " min", "information");
        cookingTime.setId("#cookingTime");
        Label servings = createLabel((int) Math.round(recipe.getServings()) + " servings", "information");
        informationBox.getChildren().addAll(cookingTime, servings);
        informationBox.setAlignment(Pos.CENTER);
        informationBox.getStyleClass().add("recipe-informationBox");

        this.detailsDisplay.getChildren().addAll(titleBox, informationBox);
    }

    @Override
    public EventHandler<ActionEvent> removeRecipeFromFavorites(Button button, Recipe recipe, StackPane stackPane, HBox box) {
        return null;
    }

    @Override
    public EventHandler<ActionEvent> addRecipeToFavorites(Button button, Recipe recipe, StackPane stackPane, HBox box) {
        return null;
    }
}
