package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.FavoriteStamp;
import app.foodapp.model.dataManipulation.recipe.Recipe;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FavoriteController implements Initializable {

    @FXML private VBox recipeDisplay;
    @FXML private ImageView leftCornerLogo;


    private FavoriteStamp favoriteNode = new FavoriteStamp();
    private ArrayList<Button> buttonsRemoveFavorite = new ArrayList<>();
    private ArrayList<HBox> contents = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void goToMenu(ActionEvent actionEvent) throws IOException {
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
            HBox content = new HBox();
            content.getStyleClass().add("recipe-content");
            contents.add(content);
            Label title = new Label(recipe.getTitle());
            title.getStyleClass().add("recipe-title");
            Label cookingTime = new Label((int) Math.round(recipe.getCookingTime()) + " min");
            cookingTime.getStyleClass().add("recipe-cookingTime");
            Label servings = new Label((int) Math.round(recipe.getServings()) + " servings");
            servings.getStyleClass().add("recipe-servings");
            Image favoriteImage = new Image(getClass().getResourceAsStream("/pictures/full-heart.png"));
            ImageView image = new ImageView();
            image.setImage(favoriteImage);
            image.setPreserveRatio(true);
            image.setFitWidth(30);
            Button buttonFavorite = new Button("", image);
            buttonFavorite.getStyleClass().add("button-favorite");
            buttonsRemoveFavorite.add(buttonFavorite);
            EventHandler<MouseEvent> favoriteMouseEntered = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Image brokenHeart = new Image(getClass().getResourceAsStream("/pictures/broken-heart.png"));
                    image.setImage(brokenHeart);
                }
            };
            buttonFavorite.addEventFilter(MouseEvent.MOUSE_ENTERED, favoriteMouseEntered);

            EventHandler<MouseEvent> favoriteMouseExited = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Image fullHeart = new Image(getClass().getResourceAsStream("/pictures/full-heart.png"));
                    image.setImage(fullHeart);
                }
            };

            buttonFavorite.addEventFilter(MouseEvent.MOUSE_EXITED, favoriteMouseExited);
            EventHandler<ActionEvent> removeRecipe = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int recipeIndex = buttonsRemoveFavorite.indexOf(buttonFavorite);
                    recipeDisplay.getChildren().remove(contents.get(recipeIndex));
                    favoriteNode.removeFromFavorite(favorites.get(recipeIndex));
                    buttonsRemoveFavorite.remove(recipeIndex);
                    contents.remove(recipeIndex);
                    if (favoriteNode.getFavorites().isEmpty()) emptyFavoriteDisplay();
                }
            };
            buttonFavorite.setOnAction(removeRecipe);

            EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        goToDetails(event, recipe);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            content.addEventFilter(MouseEvent.MOUSE_CLICKED, mouseClick);

            content.getChildren().add(title);
            content.getChildren().add(cookingTime);
            content.getChildren().add(servings);
            content.getChildren().add(buttonFavorite);
            recipeDisplay.getChildren().add(content);
        }
    }

    public void goToDetails(MouseEvent event, Recipe recipe) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/details.fxml"));
        Parent root = loader.load();
        DetailsController detailsController = new DetailsController();
        detailsController.showDetails(recipe);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void emptyFavoriteDisplay() {
        Label message = new Label("It seems that you don't have any favorite recipe...");
        message.setId("text-empty-favorites");
        recipeDisplay.setAlignment(Pos.TOP_CENTER);
        recipeDisplay.getChildren().add(message);
    }
}
