package app.foodapp.controller;

import app.foodapp.controller.backController.BackToFavorites;
import app.foodapp.model.recipe.Recipe;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.util.List;

public class FavoritesController extends recipeListController {

    @FXML private VBox recipeDisplay;
    @FXML private ImageView leftCornerLogo;

    public void getFavoritesRecipes(final int pageIndex) {
        setImage(leftCornerLogo, new Image("/app/foodapp/view/pictures/logo/logoApp.png"));

        List<Recipe> favoriteRecipes = favoriteNode.getFavorites();
        this.backController = new BackToFavorites(this.pageIndex);
        setRecipeList(favoriteRecipes, "#buttonFavorites", "Favorites");

        if (favoriteRecipes.isEmpty()) emptyFavoriteDisplay();
        else pageDisplay(pageIndex, this.recipeDisplay, favoriteRecipes);
    }

    @Override
    public EventHandler<ActionEvent> removeRecipeFromFavorites(final Button button, final Recipe recipe, final StackPane stackPane, final HBox box) {
        return event -> {
            ParallelTransition animation = removeRecipeFromFavoritesAnimation(button, recipe, stackPane, box);

            FadeTransition recipeFading = new FadeTransition(Duration.millis(500), box);
            recipeFading.setFromValue(1);
            recipeFading.setToValue(0);
            recipeFading.play();

            animation.setOnFinished(event1 -> {
                favoriteNode.removeFromFavorite(recipe);
                favoritesButtonList.remove(button);
                recipeBoxDisplayList.remove(box);
                update();
            });
            animation.play();
        };
    }

    public void emptyFavoriteDisplay() {
        Label message = new Label("It seems like you don't have any favorite recipe...");
        message.setId("text-empty-favorites");
        recipeDisplay.setAlignment(Pos.TOP_CENTER);
        recipeDisplay.getChildren().add(message);
    }

    public void update() {
        List<Recipe> favoriteRecipes = favoriteNode.getFavorites();
        int nbOfFavoriteRecipe = favoriteRecipes.size();
        int maxPageIndex = (int) Math.ceil(nbOfFavoriteRecipe / 10.0);

        if (nbOfFavoriteRecipe == 0) {
            emptyFavoriteDisplay();
        } else if (maxPageIndex < this.pageIndex) {
            pageDisplay(this.pageIndex-1, this.recipeDisplay, favoriteRecipes);
        } else {
            pageDisplay(this.pageIndex, this.recipeDisplay, favoriteRecipes);
        }
    }
}
