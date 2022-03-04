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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FavoriteController implements Initializable {

    @FXML private VBox recipeDisplay;
    @FXML private ImageView leftCornerLogo;


    private final FavoriteStamp favoriteNode = new FavoriteStamp();
    private final ArrayList<Button> removeFromFavoriteButtonList = new ArrayList<>();
    private final ArrayList<HBox> recipeBoxDisplayList = new ArrayList<>();
    //private final Scene scene;
    private int pageIndex = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void goToMenu(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("/app/foodapp/view/stylesheet.css").toExternalForm();

            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
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

    public void getFavoritesRecipes() {
        ArrayList<Recipe> favorites = favoriteNode.getFavorites();
        Image logo = new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/test3.png"));
        leftCornerLogo.setImage(logo);
        for (Recipe recipe : favorites) {
            HBox recipeBoxDisplay = new HBox();
            recipeBoxDisplay.getStyleClass().add("recipe-content");
            recipeBoxDisplayList.add(recipeBoxDisplay);

            Label title = createLabel(recipe.getTitle(), "recipe-title");
            Label cookingTime = createLabel((int) Math.round(recipe.getCookingTime()) + " min", "recipe-cookingTime");
            Label servings = createLabel((int) Math.round(recipe.getServings()) + " servings", "recipe-servings");

            ImageView removeFromFavoritesImage = new ImageView(new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/full-heart.png")));
            removeFromFavoritesImage.setPreserveRatio(true);
            removeFromFavoritesImage.setFitWidth(40);

            StackPane removeFromFavoritesStackPane = new StackPane();
            removeFromFavoritesStackPane.getChildren().add(removeFromFavoritesImage);

            Button removeFromFavoritesButton = new Button("", removeFromFavoritesStackPane);
            removeFromFavoritesButton.getStyleClass().add("button-favorite");
            removeFromFavoriteButtonList.add(removeFromFavoritesButton);

            removeFromFavoritesButton.addEventFilter(MouseEvent.MOUSE_ENTERED, setBrokenHeartImage(removeFromFavoritesImage));
            removeFromFavoritesButton.addEventFilter(MouseEvent.MOUSE_EXITED, setFullHeartImage(removeFromFavoritesImage));
            removeFromFavoritesButton.setOnAction(removeRecipeFromFavorite(removeFromFavoritesButton, removeFromFavoritesStackPane));
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

    public Label createLabel(String content, String styleClass) {
        Label label = new Label(content);
        label.getStyleClass().add(styleClass);
        return label;
    }

    public EventHandler<MouseEvent> setBrokenHeartImage(ImageView imageView) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                imageView.setImage(new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/broken-heart.png")));
            }
        };
    }

    public EventHandler<MouseEvent> setFullHeartImage(ImageView imageView) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                imageView.setImage(new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/full-heart.png")));
            }
        };
    }

    public EventHandler<MouseEvent> setKnifeWithoutSauceImage(ImageView imageView) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                imageView.setImage(new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/knifeWithoutSauce.png")));
            }
        };
    }

    public EventHandler<MouseEvent> setKnifeWithSauceImage(ImageView imageView) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                imageView.setImage(new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/knifeWithSauce.png")));
            }
        };
    }

    public EventHandler<MouseEvent> setForkWithoutSauceImage(ImageView imageView) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                imageView.setImage(new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/forkWithoutSauce.png")));
            }
        };
    }

    public EventHandler<MouseEvent> setForkWithSauceImage(ImageView imageView) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                imageView.setImage(new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/forkWithSauce.png")));
            }
        };
    }

    public EventHandler<ActionEvent> removeRecipeFromFavorite(Button removeFromFavoriteButton, StackPane stackPane) {
        return event -> {
            stackPane.getChildren().clear();

            ImageView leftBrokenHeart = new ImageView(new Image("/app/foodapp/view/images/picturesForFavorites/broken-heart-left.png"));
            ImageView rightBrokenHeart = new ImageView(new Image("/app/foodapp/view/images/picturesForFavorites/broken-heart-right.png"));
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
            leftBrokenHeartTranslation.setByY(10);
            rightBrokenHeartTranslation.setByY(10);

            FadeTransition leftBrokenHeartFading = new FadeTransition(Duration.millis(500), leftBrokenHeart);
            FadeTransition rightBrokenHeartFading = new FadeTransition(Duration.millis(500), rightBrokenHeart);
            leftBrokenHeartFading.setFromValue(1);
            rightBrokenHeartFading.setFromValue(1);
            leftBrokenHeartFading.setToValue(0);
            rightBrokenHeartFading.setToValue(0);

            RotateTransition leftBrokenHeartRotation = new RotateTransition(Duration.millis(500), leftBrokenHeart);
            RotateTransition rightBrokenHeartRotation = new RotateTransition(Duration.millis(500), rightBrokenHeart);
            leftBrokenHeartRotation.setByAngle(-10);
            rightBrokenHeartRotation.setByAngle(10);

            ParallelTransition parallelTransition = new ParallelTransition(
                    leftBrokenHeartTranslation,
                    rightBrokenHeartTranslation,
                    leftBrokenHeartFading,
                    rightBrokenHeartFading,
                    leftBrokenHeartRotation,
                    rightBrokenHeartRotation);

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

    public EventHandler<MouseEvent> getRecipeDetails(Recipe recipe) {
        return event -> goToRecipeDetails(event, recipe);
    }

    public void goToRecipeDetails(MouseEvent event, Recipe recipe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/details.fxml"));
            Parent root = loader.load();
            DetailsController detailsController = loader.getController();
            detailsController.showDetails(recipe);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public EventHandler<MouseEvent> mouseEnteredRecipeBoxDisplay(HBox recipeBoxDisplay) {
        return event -> {
            Label cookingTime = (Label) recipeBoxDisplay.getChildren().get(1);
            Label servings = (Label) recipeBoxDisplay.getChildren().get(2);

            recipeBoxDisplay.getStyleClass().add("recipe-content-hover");
            cookingTime.getStyleClass().add("recipe-information-hover");
            servings.getStyleClass().add("recipe-information-hover");

        };
    }

    public EventHandler<MouseEvent> mouseExitedRecipeBoxDisplay(HBox recipeBoxDisplay) {
        return event -> {
            Label cookingTime = (Label) recipeBoxDisplay.getChildren().get(1);
            Label servings = (Label) recipeBoxDisplay.getChildren().get(2);

            recipeBoxDisplay.getStyleClass().remove("recipe-content-hover");
            cookingTime.getStyleClass().remove("recipe-information-hover");
            servings.getStyleClass().remove("recipe-information-hover");
        };
    }

    public EventHandler<ActionEvent> goToPage(int pageIndex) {
        return event -> pageDisplay(pageIndex);
    }

    public void emptyFavoriteDisplay() {
        Label message = new Label("It seems like you don't have any favorite recipe...");
        message.setId("text-empty-favorites");
        recipeDisplay.setAlignment(Pos.TOP_CENTER);
        recipeDisplay.getChildren().add(message);
    }

    public void pageNavigationButtonDisplay(int nbOfElement, HBox lastBox) {
        if (this.pageIndex > 1) {
            ImageView knifeImage = new ImageView(new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/knifeWithoutSauce.png")));
            knifeImage.setPreserveRatio(true);
            knifeImage.setFitWidth(100);

            Button previousPage = new Button("", knifeImage);
            previousPage.addEventFilter(MouseEvent.MOUSE_ENTERED, setKnifeWithSauceImage(knifeImage));
            previousPage.addEventFilter(MouseEvent.MOUSE_EXITED, setKnifeWithoutSauceImage(knifeImage));
            previousPage.getStyleClass().add("button-pagination");

            lastBox.getChildren().add(previousPage);
            previousPage.setOnAction(goToPage(this.pageIndex-1));
        }

        if (nbOfElement > this.pageIndex * 10) {
            ImageView forkImage = new ImageView(new Image(getClass().getResourceAsStream("/app/foodapp/view/images/picturesForFavorites/forkWithoutSauce.png")));
            forkImage.setPreserveRatio(true);
            forkImage.setFitWidth(100);

            Button nextPage = new Button("", forkImage);
            nextPage.addEventFilter(MouseEvent.MOUSE_ENTERED, setForkWithSauceImage(forkImage));
            nextPage.addEventFilter(MouseEvent.MOUSE_EXITED, setForkWithoutSauceImage(forkImage));
            nextPage.getStyleClass().add("button-pagination");

            lastBox.getChildren().add(nextPage);
            nextPage.setOnAction(goToPage(this.pageIndex+1));
        }
    }

    public void pageDisplay(int pageIndex) {
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
