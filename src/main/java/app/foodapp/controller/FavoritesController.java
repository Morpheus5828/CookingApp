package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.FavoriteStamp;
import app.foodapp.model.dataManipulation.recipe.Recipe;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FavoritesController extends Controller implements Initializable {

    @FXML private VBox recipeDisplay;
    @FXML private ImageView leftCornerLogo;

    private final FavoriteStamp favoriteNode = new FavoriteStamp();
    private final ArrayList<Button> removeFromFavoriteButtonList = new ArrayList<>();
    private final ArrayList<HBox> recipeBoxDisplayList = new ArrayList<>();
    private int pageIndex = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void getFavoritesRecipes() {
        ArrayList<Recipe> favorites = favoriteNode.getFavorites();

        for (Recipe recipe : favorites) {
            HBox recipeBoxDisplay = new HBox();
            recipeBoxDisplay.getStyleClass().add("recipe-content");
            recipeBoxDisplayList.add(recipeBoxDisplay);

            Label title = createLabel(recipe.getTitle(), "recipe-title");
            Label cookingTime = createLabel((int) Math.round(recipe.getCookingTime()) + " min", "recipe-cookingTime");
            Label servings = createLabel((int) Math.round(recipe.getServings()) + " servings", "recipe-servings");

            ImageView removeFromFavoritesImage = new ImageView(new Image(getClass().getResourceAsStream("/app/foodapp/view/pictures/heartPictures/full-heart.png")));
            removeFromFavoritesImage.setPreserveRatio(true);
            removeFromFavoritesImage.setFitWidth(40);

            StackPane removeFromFavoritesStackPane = new StackPane();
            removeFromFavoritesStackPane.getChildren().add(removeFromFavoritesImage);

            Button removeFromFavoritesButton = new Button("", removeFromFavoritesStackPane);
            removeFromFavoritesButton.getStyleClass().add("button-favorite");
            Tooltip.install(removeFromFavoritesButton, new Tooltip("Remove from favorites"));
            removeFromFavoriteButtonList.add(removeFromFavoritesButton);

            removeFromFavoritesButton.addEventFilter(MouseEvent.MOUSE_ENTERED, setImage(removeFromFavoritesImage, new Image("/app/foodapp/view/pictures/heartPictures/broken-heart.png")));
            removeFromFavoritesButton.addEventFilter(MouseEvent.MOUSE_EXITED, setImage(removeFromFavoritesImage, new Image("/app/foodapp/view/pictures/heartPictures/full-heart.png")));
            removeFromFavoritesButton.setOnAction(removeRecipeFromFavorite(removeFromFavoritesButton, removeFromFavoritesStackPane, recipeBoxDisplay));
            recipeBoxDisplay.addEventFilter(MouseEvent.MOUSE_CLICKED, getRecipeDetails(recipe));
            recipeBoxDisplay.addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEnteredRecipeBoxDisplay(recipeBoxDisplay));
            recipeBoxDisplay.addEventFilter(MouseEvent.MOUSE_EXITED, mouseExitedRecipeBoxDisplay(recipeBoxDisplay));

            recipeBoxDisplay.getChildren().add(title);
            recipeBoxDisplay.getChildren().add(cookingTime);
            recipeBoxDisplay.getChildren().add(servings);
            recipeBoxDisplay.getChildren().add(removeFromFavoritesButton);
        }
        if (favorites.isEmpty()) emptyFavoriteDisplay();
        else pageDisplay(1);
    }

    public Label createLabel(final String content, final String styleClass) {
        Label label = new Label(content);
        label.getStyleClass().add(styleClass);
        return label;
    }

    public EventHandler<MouseEvent> setImage(final ImageView imageView, final Image image) {
        return event ->
                imageView.setImage(image);
    }

    public EventHandler<ActionEvent> removeRecipeFromFavorite(final Button removeFromFavoriteButton, final StackPane stackPane, final HBox box) {
        return event -> {
            stackPane.getChildren().clear();

            ImageView leftBrokenHeart = new ImageView(new Image("/app/foodapp/view/pictures/heartPictures/broken-heart-left.png"));
            ImageView rightBrokenHeart = new ImageView(new Image("/app/foodapp/view/pictures/heartPictures/broken-heart-right.png"));
            leftBrokenHeart.setPreserveRatio(true);
            rightBrokenHeart.setPreserveRatio(true);
            leftBrokenHeart.setFitWidth(40);
            rightBrokenHeart.setFitWidth(40);
            stackPane.getChildren().add(leftBrokenHeart);
            stackPane.getChildren().add(rightBrokenHeart);

            TranslateTransition leftBrokenHeartTranslation = new TranslateTransition(Duration.millis(500), leftBrokenHeart);
            TranslateTransition rightBrokenHeartTranslation = new TranslateTransition(Duration.millis(500), rightBrokenHeart);
            leftBrokenHeartTranslation.setByX(-20);
            rightBrokenHeartTranslation.setByX(20);
            leftBrokenHeartTranslation.setByY(15);
            rightBrokenHeartTranslation.setByY(15);

            RotateTransition leftBrokenHeartRotation = new RotateTransition(Duration.millis(500), leftBrokenHeart);
            RotateTransition rightBrokenHeartRotation = new RotateTransition(Duration.millis(500), rightBrokenHeart);
            leftBrokenHeartRotation.setByAngle(-20);
            rightBrokenHeartRotation.setByAngle(20);

            FadeTransition recipeFading = new FadeTransition(Duration.millis(500), box);
            recipeFading.setFromValue(1);
            recipeFading.setToValue(0);

            ParallelTransition parallelTransition = new ParallelTransition(
                    leftBrokenHeartTranslation,
                    rightBrokenHeartTranslation,
                    leftBrokenHeartRotation,
                    rightBrokenHeartRotation,
                    recipeFading);

            parallelTransition.setOnFinished(event1 -> {
                int index = removeFromFavoriteButtonList.indexOf(removeFromFavoriteButton);
                favoriteNode.removeFromFavorite(favoriteNode.getFavorites().get(index));
                removeFromFavoriteButtonList.remove(index);
                recipeBoxDisplayList.remove(index);
                update();
            });
            parallelTransition.play();
        };
    }

    public EventHandler<MouseEvent> getRecipeDetails(final Recipe recipe) {
        return event -> {
            goToRecipeDetails(event, recipe);
        };
    }

    public void goToRecipeDetails(final MouseEvent event, final Recipe recipe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/details.fxml"));
            Parent root = loader.load();
            DetailsController detailsController = loader.getController();
            detailsController.showDetails(recipe);

            Scene detailedRecipe = new Scene(root);
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

    public EventHandler<ActionEvent> goToPage(final int pageIndex) {
        return event -> pageDisplay(pageIndex);
    }

    public void emptyFavoriteDisplay() {
        Label message = new Label("It seems like you don't have any favorite recipe...");
        message.setId("text-empty-favorites");
        recipeDisplay.setAlignment(Pos.TOP_CENTER);
        recipeDisplay.getChildren().add(message);
    }

    public void pageNavigationButtonDisplay(final int nbOfElement, final HBox lastBox) {
        if (this.pageIndex > 1) {
            ImageView knifeImage = new ImageView(new Image(getClass().getResourceAsStream("/app/foodapp/view/pictures/pagination/knifeWithoutSauce.png")));
            knifeImage.setPreserveRatio(true);
            knifeImage.setFitWidth(100);

            Button previousPage = new Button("", knifeImage);
            previousPage.addEventFilter(MouseEvent.MOUSE_ENTERED, setImage(knifeImage, new Image("/app/foodapp/view/pictures/pagination/knifeWithSauce.png")));
            previousPage.addEventFilter(MouseEvent.MOUSE_EXITED, setImage(knifeImage, new Image("/app/foodapp/view/pictures/pagination/knifeWithoutSauce.png")));
            previousPage.getStyleClass().add("button-pagination");

            lastBox.getChildren().add(previousPage);
            previousPage.setOnAction(goToPage(this.pageIndex-1));
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
            nextPage.setOnAction(goToPage(this.pageIndex+1));
        }
    }

    public void pageDisplay(final int pageIndex) {
        this.pageIndex = pageIndex;
        this.recipeDisplay.getChildren().clear();

        ArrayList<Recipe> favoritesRecipes = favoriteNode.getFavorites();
        for (int recipeIndex = (this.pageIndex-1)*10; recipeIndex < favoritesRecipes.size() && recipeIndex < pageIndex*10; recipeIndex++) {
            recipeDisplay.getChildren().add(recipeBoxDisplayList.get(recipeIndex));
        }

        HBox lastBox = new HBox();
        lastBox.getStyleClass().add("box-pagination");
        this.recipeDisplay.getChildren().add(lastBox);
        pageNavigationButtonDisplay(favoritesRecipes.size(), lastBox);
    }

    public void update() {
        int nbOfFavoriteRecipe = favoriteNode.getFavorites().size();
        int maxPageIndex = (int) Math.ceil(nbOfFavoriteRecipe / 10.0);

        if (nbOfFavoriteRecipe == 0) {
            emptyFavoriteDisplay();
        } else if (maxPageIndex < this.pageIndex) {
            pageDisplay(this.pageIndex-1);
        } else {
            pageDisplay(this.pageIndex);
        }
    }
}
