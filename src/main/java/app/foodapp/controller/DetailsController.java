package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class DetailsController extends MainController {
    @FXML private VBox detailsDisplay;
    @FXML private AnchorPane rootPane;

    public void getDetails(Recipe recipe) {
        ImageView favoritesButtonImage = new ImageView(new Image(getClass().getResourceAsStream("/app/foodapp/view/pictures/heartPictures/full-heart.png")));
        favoritesButtonImage.setPreserveRatio(true);
        favoritesButtonImage.setFitWidth(40);

        StackPane favoriteButtonStackPane = new StackPane();
        favoriteButtonStackPane.getChildren().add(favoritesButtonImage);

        Button favoritesButton = new Button("", favoriteButtonStackPane);
        manageFavoriteButton(favoritesButton, recipe, favoriteButtonStackPane, new HBox());
        favoritesButton.getStyleClass().add("button-favorite");

        rootPane.getChildren().add(favoritesButton);
        favoritesButton.setLayoutX(1220);
        favoritesButton.setLayoutY(100);

        HBox titleBox = new HBox();
        Label title = new Label(recipe.getTitle());
        title.getStyleClass().add("recipeTitle");
        titleBox.setAlignment(Pos.CENTER);
        titleBox.getStyleClass().add("recipeTitleBox");
        titleBox.getChildren().addAll(title);

        HBox informationBox = new HBox();
        Label cookingTime = createLabel((int) Math.round(recipe.getCookingTime()) + " min", "information");
        cookingTime.getStyleClass().add("cookingTime");
        Label servings = createLabel((int) Math.round(recipe.getServings()) + " servings", "information");
        informationBox.getChildren().addAll(cookingTime, servings);
        informationBox.setAlignment(Pos.CENTER);
        informationBox.getStyleClass().add("recipe-informationBox");

        this.detailsDisplay.getChildren().addAll(titleBox, informationBox);
    }

    @Override
    public EventHandler<ActionEvent> removeRecipeFromFavorites(Button button, Recipe recipe, StackPane stackPane, HBox box) {
        return event -> {
            ParallelTransition animation = removeRecipeFromFavoritesAnimation(button, recipe, stackPane, box);
            animation.setOnFinished(event1 -> {
                favoriteNode.removeFromFavorite(recipe);
                manageFavoriteButton(button, recipe, stackPane, box);
            });
            animation.play();
        };
    }
}
