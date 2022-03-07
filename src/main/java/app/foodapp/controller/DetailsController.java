package app.foodapp.controller;

import app.foodapp.controller.backController.BackController;
import app.foodapp.model.dataManipulation.recipe.Recipe;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class DetailsController extends MainController {
    @FXML private VBox detailsDisplay;
    @FXML private AnchorPane rootPane;
    @FXML private Text subTitleText;

    private BackController backController;

    public void setBackController(final BackController backController) {
        this.backController = backController;
    }

    public void getDetails(final Recipe recipe, final String currentButtonId, final String subTitle) {
        initDetailsPage(currentButtonId, subTitle);

        Button favoritesButton = createFavoritesButton(recipe, new HBox());
        rootPane.getChildren().add(favoritesButton);
        favoritesButton.setLayoutX(1200);
        favoritesButton.setLayoutY(110);

        ImageView backButtonImage = new ImageView(new Image(getClass().getResourceAsStream("/app/foodapp/view/pictures/researchRecipe/backButton.png")));
        backButtonImage.setPreserveRatio(true);
        backButtonImage.setFitWidth(40);

        Button backButton = new Button("", backButtonImage);
        backButton.getStyleClass().add("button-back");
        Tooltip.install(backButton, new Tooltip("Go back"));
        backButton.setCursor(Cursor.HAND);
        rootPane.getChildren().add(backButton);
        backButton.setLayoutX(120);
        backButton.setLayoutY(110);
        backButton.setOnAction(goBack());

        Text title = new Text(recipe.getTitle());
        title.setWrappingWidth(1100);
        title.setFill(Color.color(0.9, 0.9, 0.9));
        title.getStyleClass().add("recipe-title");

        HBox titleBox = new HBox();
        titleBox.getStyleClass().add("recipe-titleBox");
        titleBox.getChildren().addAll(title);

        Label cookingTime = createLabel((int) Math.round(recipe.getCookingTime()) + " min", "information");
        cookingTime.getStyleClass().add("cookingTime");
        Label servings = createLabel((int) Math.round(recipe.getServings()) + " servings", "information");

        HBox informationBox = new HBox(cookingTime, servings);
        informationBox.getStyleClass().add("recipe-informationBox");
        informationBox.setAlignment(Pos.CENTER);

        this.detailsDisplay.getChildren().addAll(titleBox, informationBox);

        displayData(recipe.getIngredientsList(), "Ingredients","We are sorry but ingredients are unavailable.");
        displayData(recipe.getStepsGUI(), "Steps", "We are sorry but steps are unavailable.");
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

    public void initDetailsPage(final String currentButtonId, final String subTitle) {
        Button currentButton = (Button) this.rootPane.lookup(currentButtonId);
        currentButton.getStyleClass().remove("button-unselected");
        currentButton.getStyleClass().add("button-selected");

        subTitleText.setText(subTitle);
    }

    public void displayData(final ArrayList<String> dataList, final String subTitle,final String errorMessage) {
        Label dataSubTitle = createLabel(subTitle, "recipe-subTitle");

        VBox dataDisplay = new VBox();
        dataDisplay.getStyleClass().add("recipe-dataDisplay");

        HBox dataBox = new HBox(dataDisplay);
        dataBox.getStyleClass().add("recipe-dataBox");

        this.detailsDisplay.getChildren().addAll(dataSubTitle, dataBox);

        if (dataList != null) {
            for (String data : dataList) {
                addNewData(dataDisplay, data);
            }
        } else {
            addNewData(dataDisplay, errorMessage);
        }
    }

    public void addNewData(final VBox dataDisplay, final String data) {
        Text dataText = new Text(data);
        dataText.getStyleClass().add("recipe-data");
        dataText.setWrappingWidth(1050);
        dataText.setFill(Color.color(0.765, 0.765, 0.765));
        dataDisplay.getChildren().add(dataText);
    }

    public EventHandler<ActionEvent> goBack() {
        return event -> this.backController.goBack(event);
    }
}
