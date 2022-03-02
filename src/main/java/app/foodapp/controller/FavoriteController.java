package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.FavoriteStamp;
import app.foodapp.model.dataManipulation.recipe.Recipe;
import javafx.animation.TranslateTransition;
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
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void goToMenu(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToProfile(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/details.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showFavorites() {
        ArrayList<Recipe> favorites = favoriteNode.getFavorites();
        Image logo = new Image(getClass().getResourceAsStream("/pictures/test3.png"));
        leftCornerLogo.setImage(logo);
        if (favorites.isEmpty()) emptyFavoriteDisplay();

        for (Recipe recipe : favorites) {
            HBox recipeBoxDisplay = new HBox();
            recipeBoxDisplay.getStyleClass().add("recipe-content");
            recipeBoxDisplayList.add(recipeBoxDisplay);

            Label title = createLabel(recipe.getTitle(), "recipe-title");
            Label cookingTime = createLabel((int) Math.round(recipe.getCookingTime()) + " min", "recipe-cookingTime");
            Label servings = createLabel((int) Math.round(recipe.getServings()) + " servings", "recipe-servings");

            ImageView removeFromFavoriteImage = new ImageView(new Image(getClass().getResourceAsStream("/pictures/full-heart.png")));
            removeFromFavoriteImage.setPreserveRatio(true);
            removeFromFavoriteImage.setFitWidth(30);

            Button removeFromFavoriteButton = new Button("", removeFromFavoriteImage);
            removeFromFavoriteButton.getStyleClass().add("button-favorite");
            removeFromFavoriteButtonList.add(removeFromFavoriteButton);

            removeFromFavoriteButton.addEventFilter(MouseEvent.MOUSE_ENTERED, setBrokenHeartImage(removeFromFavoriteImage));
            removeFromFavoriteButton.addEventFilter(MouseEvent.MOUSE_EXITED, setFullHeartImage(removeFromFavoriteImage));
            removeFromFavoriteButton.setOnAction(removeRecipeFromFavorite(removeFromFavoriteButton));
            recipeBoxDisplay.addEventFilter(MouseEvent.MOUSE_CLICKED, getRecipeDetails(recipe));
            recipeBoxDisplay.addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEnteredRecipeBoxDisplay(recipeBoxDisplay));
            recipeBoxDisplay.addEventFilter(MouseEvent.MOUSE_EXITED, mouseExitedRecipeBoxDisplay(recipeBoxDisplay));

            recipeBoxDisplay.getChildren().add(title);
            recipeBoxDisplay.getChildren().add(cookingTime);
            recipeBoxDisplay.getChildren().add(servings);
            recipeBoxDisplay.getChildren().add(removeFromFavoriteButton);
            recipeDisplay.getChildren().add(recipeBoxDisplay);
        }
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
                imageView.setImage(new Image(getClass().getResourceAsStream("/pictures/broken-heart.png")));
            }
        };
    }

    public EventHandler<MouseEvent> setFullHeartImage(ImageView imageView) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                imageView.setImage(new Image(getClass().getResourceAsStream("/pictures/full-heart.png")));
            }
        };
    }

    public EventHandler<ActionEvent> removeRecipeFromFavorite(Button removeFromFavoriteButton) {
        return new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                int index = removeFromFavoriteButtonList.indexOf(removeFromFavoriteButton);

                recipeDisplay.getChildren().remove(recipeBoxDisplayList.get(index));
                favoriteNode.removeFromFavorite(favoriteNode.getFavorites().get(index));
                removeFromFavoriteButtonList.remove(index);
                recipeBoxDisplayList.remove(index);

                if (favoriteNode.getFavorites().isEmpty()) emptyFavoriteDisplay();
            }
        };
    }

    public EventHandler<MouseEvent> getRecipeDetails(Recipe recipe) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                goToRecipeDetails(event, recipe);
            }
        };
    }

    public void goToRecipeDetails(MouseEvent event, Recipe recipe) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/details.fxml"));
            Parent root = loader.load();
            DetailsController detailsController = new DetailsController();
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
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                Label title = (Label) recipeBoxDisplay.getChildren().get(0);
                Label cookingTime = (Label) recipeBoxDisplay.getChildren().get(1);
                Label servings = (Label) recipeBoxDisplay.getChildren().get(2);

                recipeBoxDisplay.getStyleClass().add("recipe-content-hover");
                cookingTime.getStyleClass().add("recipe-information-hover");
                servings.getStyleClass().add("recipe-information-hover");
            }
        };
    }

    public EventHandler<MouseEvent> mouseExitedRecipeBoxDisplay(HBox recipeBoxDisplay) {
        return new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                Label title = (Label) recipeBoxDisplay.getChildren().get(0);
                Label cookingTime = (Label) recipeBoxDisplay.getChildren().get(1);
                Label servings = (Label) recipeBoxDisplay.getChildren().get(2);

                recipeBoxDisplay.getStyleClass().remove("recipe-content-hover");
                cookingTime.getStyleClass().remove("recipe-information-hover");
                servings.getStyleClass().remove("recipe-information-hover");
            }
        };
    }

    public void emptyFavoriteDisplay() {
        Label message = new Label("It seems that you don't have any favorite recipe...");
        message.setId("text-empty-favorites");
        recipeDisplay.setAlignment(Pos.TOP_CENTER);
        recipeDisplay.getChildren().add(message);
    }
}
