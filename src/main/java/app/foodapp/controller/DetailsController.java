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
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class DetailsController extends MainController {
    @FXML private VBox detailsDisplay;
    @FXML private AnchorPane rootPane;
    @FXML private Text subTitleText;

    public void getDetails(final Recipe recipe, final String currentButtonId, final String subTitle) {
        Button currentButton = (Button) this.rootPane.lookup(currentButtonId);
        currentButton.getStyleClass().remove("button-unselected");
        currentButton.getStyleClass().add("button-selected");

        subTitleText.setText(subTitle);

        ImageView favoritesButtonImage = new ImageView(new Image(getClass().getResourceAsStream("/app/foodapp/view/pictures/heartPictures/full-heart.png")));
        favoritesButtonImage.setPreserveRatio(true);
        favoritesButtonImage.setFitWidth(40);

        StackPane favoriteButtonStackPane = new StackPane();
        favoriteButtonStackPane.getChildren().add(favoritesButtonImage);

        Button favoritesButton = new Button("", favoriteButtonStackPane);
        manageFavoriteButton(favoritesButton, recipe, favoriteButtonStackPane, new HBox());
        favoritesButton.getStyleClass().add("button-favorite");

        rootPane.getChildren().add(favoritesButton);
        favoritesButton.setLayoutX(1200);
        favoritesButton.setLayoutY(110);

        HBox titleBox = new HBox();
        Text title = new Text(recipe.getTitle());
        title.setWrappingWidth(1100);
        title.setFill(Color.color(0.9, 0.9, 0.9));
        title.getStyleClass().add("recipe-title");
        titleBox.getStyleClass().add("recipe-titleBox");
        titleBox.getChildren().addAll(title);

        HBox informationBox = new HBox();
        Label cookingTime = createLabel((int) Math.round(recipe.getCookingTime()) + " min", "information");
        cookingTime.getStyleClass().add("cookingTime");
        Label servings = createLabel((int) Math.round(recipe.getServings()) + " servings", "information");
        informationBox.getChildren().addAll(cookingTime, servings);
        informationBox.setAlignment(Pos.CENTER);
        informationBox.getStyleClass().add("recipe-informationBox");

        Label ingredientsTitle = createLabel("Ingredients", "recipe-subTitle");

        HBox ingredientsBox = new HBox();
        VBox ingredientsDisplay = new VBox();
        ingredientsDisplay.getStyleClass().add("recipe-dataDisplay");
        ingredientsBox.getStyleClass().add("recipe-dataBox");

        ArrayList<String> ingredients = recipe.getIngredientsList();
        if (ingredients != null) {
            for (String ingredient : ingredients) {
                Text ingredientText = new Text(ingredient);
                ingredientText.getStyleClass().add("recipe-data");
                ingredientText.setWrappingWidth(1050);
                ingredientText.setFill(Color.color(0.765, 0.765, 0.765));
                ingredientsDisplay.getChildren().add(ingredientText);
            }
        } else {
            Text errorText = new Text("We are sorry but the ingredients are unavailable.");
            errorText.getStyleClass().add("recipe-data");
            errorText.setWrappingWidth(1050);
            errorText.setFill(Color.color(0.765, 0.765, 0.765));
            ingredientsDisplay.getChildren().add(errorText);
        }
        ingredientsBox.getChildren().add(ingredientsDisplay);

        Label stepsTitle = createLabel("Steps", "recipe-subTitle");

        HBox stepsBox = new HBox();
        VBox stepsDisplay = new VBox();
        stepsDisplay.getStyleClass().add("recipe-dataDisplay");
        stepsBox.getStyleClass().add("recipe-dataBox");

        ArrayList<String> steps = recipe.getStepsGUI();
        if (steps != null) {
            for (String step : steps) {
                Text stepText = new Text(step);
                stepText.getStyleClass().add("recipe-data");
                stepText.setWrappingWidth(1050);
                stepText.setFill(Color.color(0.765, 0.765, 0.765));
                stepsDisplay.getChildren().add(stepText);
            }
        } else {
            Text errorText = new Text("We are sorry but the steps are unavailable.");
            errorText.getStyleClass().add("recipe-data");
            errorText.setWrappingWidth(1050);
            errorText.setFill(Color.color(0.765, 0.765, 0.765));
            stepsDisplay.getChildren().add(errorText);
        }
        stepsBox.getChildren().add(stepsDisplay);

        this.detailsDisplay.getChildren().addAll(titleBox, informationBox, ingredientsTitle, ingredientsBox, stepsTitle, stepsBox);
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
