package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class recipeListController extends MainController {
    protected List<Button> favoritesButtonList = new ArrayList<>();
    protected List<HBox> recipeBoxDisplayList = new ArrayList<>();

    protected int pageIndex = 1;

    public void setRecipeList(final List<Recipe> recipeList, final String currentButtonId, final String subTitle) {
        for (Recipe recipe : recipeList) {
            HBox recipeBoxDisplay = new HBox();
            recipeBoxDisplay.getStyleClass().add("recipe-content");
            recipeBoxDisplayList.add(recipeBoxDisplay);

            Label title = createLabel(recipe.getTitle(), "recipe-title");
            Label cookingTime = createLabel((int) Math.round(recipe.getCookingTime()) + " min", "recipe-cookingTime");
            Label servings = createLabel((int) Math.round(recipe.getServings()) + " servings", "recipe-servings");

            ImageView favoriteButtonImage = new ImageView(new Image(getClass().getResourceAsStream("/app/foodapp/view/pictures/heartPictures/full-heart.png")));
            favoriteButtonImage.setPreserveRatio(true);
            favoriteButtonImage.setFitWidth(40);

            StackPane favoriteButtonStackPane = new StackPane();
            favoriteButtonStackPane.getChildren().add(favoriteButtonImage);

            Button favoritesButton = new Button("", favoriteButtonStackPane);
            manageFavoriteButton(favoritesButton, recipe, favoriteButtonStackPane, recipeBoxDisplay);
            favoritesButton.getStyleClass().add("button-favorite");
            favoritesButtonList.add(favoritesButton);

            recipeBoxDisplay.addEventFilter(MouseEvent.MOUSE_CLICKED, getRecipeDetails(recipe, currentButtonId, subTitle));
            recipeBoxDisplay.addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEnteredRecipeBoxDisplay(recipeBoxDisplay));
            recipeBoxDisplay.addEventFilter(MouseEvent.MOUSE_EXITED, mouseExitedRecipeBoxDisplay(recipeBoxDisplay));

            recipeBoxDisplay.getChildren().addAll(title, cookingTime, servings, favoritesButton);
        }
    }

    public EventHandler<MouseEvent> getRecipeDetails(final Recipe recipe, final String currentButtonId, final String subTitle) {
        return event -> goToRecipeDetails(event, recipe, currentButtonId, subTitle);
    }

    public void goToRecipeDetails(final MouseEvent event, final Recipe recipe, final String currentButtonId, final String subTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/recipeDetails.fxml"));
            Parent root = loader.load();
            DetailsController detailsController = loader.getController();
            detailsController.getDetails(recipe, currentButtonId, subTitle);

            Scene detailedRecipe = new Scene(root);
            detailedRecipe.getStylesheets().add(this.getClass().getResource("/app/foodapp/view/stylesheet/globalStylesheet.css").toExternalForm());
            detailedRecipe.getStylesheets().add(this.getClass().getResource("/app/foodapp/view/stylesheet/details.css").toExternalForm());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(detailedRecipe);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EventHandler<MouseEvent> mouseEnteredRecipeBoxDisplay(final HBox recipeBoxDisplay) {
        return event -> {
            Label cookingTime = (Label) recipeBoxDisplay.getChildren().get(1);
            Label servings = (Label) recipeBoxDisplay.getChildren().get(2);

            recipeBoxDisplay.getStyleClass().add("recipe-content-hover");
            cookingTime.getStyleClass().add("recipe-information-hover");
            servings.getStyleClass().add("recipe-information-hover");

            recipeBoxDisplay.getScene().setCursor(Cursor.HAND);
        };
    }

    public EventHandler<MouseEvent> mouseExitedRecipeBoxDisplay(final HBox recipeBoxDisplay) {
        return event -> {
            Label cookingTime = (Label) recipeBoxDisplay.getChildren().get(1);
            Label servings = (Label) recipeBoxDisplay.getChildren().get(2);

            recipeBoxDisplay.getStyleClass().remove("recipe-content-hover");
            cookingTime.getStyleClass().remove("recipe-information-hover");
            servings.getStyleClass().remove("recipe-information-hover");

            recipeBoxDisplay.getScene().setCursor(Cursor.DEFAULT);
        };
    }

    public EventHandler<ActionEvent> goToPage(final int pageIndex, final VBox recipeDisplay, final List<Recipe> recipeList) {
        return event -> pageDisplay(pageIndex, recipeDisplay, recipeList);
    }

    public void pageDisplay(final int pageIndex, final VBox recipeDisplay, final List<Recipe> recipeList) {
        this.pageIndex = pageIndex;
        recipeDisplay.getChildren().clear();

        for (int recipeIndex = (this.pageIndex-1)*10; recipeIndex < recipeList.size() && recipeIndex < pageIndex*10; recipeIndex++) {
            recipeDisplay.getChildren().add(this.recipeBoxDisplayList.get(recipeIndex));
        }

        HBox lastBox = new HBox();
        lastBox.getStyleClass().add("box-pagination");
        recipeDisplay.getChildren().add(lastBox);
        pageNavigationButtonDisplay(lastBox, recipeDisplay, recipeList);
    }

    public void pageNavigationButtonDisplay(final HBox lastBox, final VBox recipeDisplay, final List<Recipe> recipeList) {
        int nbOfElement = recipeList.size();
        if (this.pageIndex > 1) {
            ImageView knifeImage = new ImageView(new Image(getClass().getResourceAsStream("/app/foodapp/view/pictures/pagination/knifeWithoutSauce.png")));
            knifeImage.setPreserveRatio(true);
            knifeImage.setFitWidth(100);

            Button previousPage = new Button("", knifeImage);
            previousPage.addEventFilter(MouseEvent.MOUSE_ENTERED, setImage(knifeImage, new Image("/app/foodapp/view/pictures/pagination/knifeWithSauce.png")));
            previousPage.addEventFilter(MouseEvent.MOUSE_EXITED, setImage(knifeImage, new Image("/app/foodapp/view/pictures/pagination/knifeWithoutSauce.png")));
            previousPage.getStyleClass().add("button-pagination");

            lastBox.getChildren().add(previousPage);
            previousPage.setOnAction(goToPage(this.pageIndex-1, recipeDisplay, recipeList));
        }

        if (nbOfElement > this.pageIndex * 10) {
            ImageView forkImage = new ImageView(new Image(getClass().getResourceAsStream("/app/foodapp/view/pictures/pagination/forkWithoutSauce.png")));
            forkImage.setPreserveRatio(true);
            forkImage.setFitWidth(100);

            Button nextPage = new Button("", forkImage);
            nextPage.addEventFilter(MouseEvent.MOUSE_ENTERED, setImage(forkImage, new Image("/app/foodapp/view/pictures/pagination/forkWithSauce.png")));
            nextPage.addEventFilter(MouseEvent.MOUSE_EXITED, setImage(forkImage, new Image("/app/foodapp/view/pictures/pagination/forkWithoutSauce.png")));
            nextPage.getStyleClass().add("button-pagination");

            lastBox.getChildren().add(nextPage);
            nextPage.setOnAction(goToPage(this.pageIndex+1, recipeDisplay, recipeList));
        }
    }

    public abstract EventHandler<ActionEvent> removeRecipeFromFavorites(final Button button, final Recipe recipe, final StackPane stackPane, final HBox box);
}
