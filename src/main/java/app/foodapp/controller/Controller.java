package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.FavoriteStamp;
import app.foodapp.model.dataManipulation.recipe.Recipe;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public abstract class Controller implements Initializable {
    protected final FavoriteStamp favoriteNode = new FavoriteStamp();

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void goToMenu(final ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
            Parent root = loader.load();
            ResearchController researchController = loader.getController();
            researchController.welcomePage();
            researchController.setRecipeResearch();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            scene.getStylesheets().add(this.getClass().getResource("/app/foodapp/view/stylesheet/globalStylesheet.css").toExternalForm());
            scene.getStylesheets().add(this.getClass().getResource("/app/foodapp/view/stylesheet/recipeListDisplayStylesheet.css").toExternalForm());

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToFavorites(javafx.event.ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/favorites.fxml"));
            Parent root = loader.load();
            FavoritesController favoriteController = loader.getController();
            favoriteController.getFavoritesRecipes();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            scene.getStylesheets().add(this.getClass().getResource("/app/foodapp/view/stylesheet/globalStylesheet.css").toExternalForm());
            scene.getStylesheets().add(this.getClass().getResource("/app/foodapp/view/stylesheet/recipeListDisplayStylesheet.css").toExternalForm());

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToProfile(final ActionEvent actionEvent) {
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

    public Label createLabel(final String content, final String styleClass) {
        Label label = new Label(content);
        label.getStyleClass().add(styleClass);
        return label;
    }

    public EventHandler<MouseEvent> setImage(final ImageView imageView, final Image image) {
        return event ->
                imageView.setImage(image);
    }

    public void manageFavoriteButton(Button button, Recipe recipe, StackPane stackPane, HBox box) {
        ArrayList<Recipe> favoriteRecipes = favoriteNode.getFavorites();
        ImageView favoritesImage = new ImageView();
        favoritesImage.setPreserveRatio(true);
        favoritesImage.setFitWidth(40);

        stackPane.getChildren().clear();
        stackPane.getChildren().add(favoritesImage);

        if (favoriteRecipes.contains(recipe)) {
            favoritesImage.setImage(new Image("/app/foodapp/view/pictures/heartPictures/full-heart.png"));
            Tooltip.install(button, new Tooltip("Remove from favorites"));

            button.addEventFilter(MouseEvent.MOUSE_ENTERED, setImage(favoritesImage, new Image("/app/foodapp/view/pictures/heartPictures/broken-heart.png")));
            button.addEventFilter(MouseEvent.MOUSE_EXITED, setImage(favoritesImage, new Image("/app/foodapp/view/pictures/heartPictures/full-heart.png")));
            button.setOnAction(removeRecipeFromFavorites(button, recipe, stackPane, box));
        } else {
            favoritesImage.setImage(new Image("/app/foodapp/view/pictures/heartPictures/empty-heart.png"));
            Tooltip.install(button, new Tooltip("Add to favorites"));

            button.addEventFilter(MouseEvent.MOUSE_ENTERED, setImage(favoritesImage, new Image("/app/foodapp/view/pictures/heartPictures/full-heart.png")));
            button.addEventFilter(MouseEvent.MOUSE_EXITED, setImage(favoritesImage, new Image("/app/foodapp/view/pictures/heartPictures/empty-heart.png")));
            button.setOnAction(addRecipeToFavorites(button, recipe, stackPane, box));
        }
    }

    public ParallelTransition removeRecipeFromFavoritesAnimation(final Button button, final Recipe recipe, final StackPane stackPane, final HBox box) {
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

        FadeTransition leftBrokenHeartFading = new FadeTransition(Duration.millis(500), leftBrokenHeart);
        leftBrokenHeartFading.setFromValue(1);
        leftBrokenHeartFading.setToValue(0);
        FadeTransition rightBrokenHeartFading = new FadeTransition(Duration.millis(500), rightBrokenHeart);
        rightBrokenHeartFading.setFromValue(1);
        rightBrokenHeartFading.setToValue(0);

        ParallelTransition parallelTransition = new ParallelTransition(
                leftBrokenHeartTranslation,
                rightBrokenHeartTranslation,
                leftBrokenHeartRotation,
                rightBrokenHeartRotation,
                leftBrokenHeartFading,
                rightBrokenHeartFading);

        return parallelTransition;
    }

    public abstract EventHandler<ActionEvent> removeRecipeFromFavorites(final Button button, final Recipe recipe, final StackPane stackPane, final HBox box);
    public abstract EventHandler<ActionEvent> addRecipeToFavorites(final Button button, final Recipe  recipe, final StackPane stackPane, final HBox box);
}
