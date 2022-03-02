package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.FavoriteStamp;
import app.foodapp.model.dataManipulation.recipe.Recipe;
import app.foodapp.model.node.Favorite;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.VBox;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FavoriteController implements Initializable {

    @FXML private VBox recipeDisplay;
    @FXML private AnchorPane rootPane;
    @FXML private ImageView leftCornerLogo;
    @FXML private Button buttonMenu;
    @FXML private Button buttonFavorite;
    @FXML private Button buttonProfil;


    FavoriteStamp favoriteNode = new FavoriteStamp();
    ArrayList<Button> buttonsRemoveFavorite = new ArrayList<>();
    ArrayList<HBox> contents = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void goToFavorites(ActionEvent actionEvent) {

    }

    public void showFavorites() {
        ArrayList<Recipe> favorites = favoriteNode.getFavorites();
        Image logo = new Image(getClass().getResourceAsStream("/pictures/test3.png"));
        leftCornerLogo.setImage(logo);
        buttonFavorite.getStyleClass().remove("navigBarButton");
        buttonFavorite.getStyleClass().add("currentButton");
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
            EventHandler<MouseEvent> favoriteMouseExited = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Image fullHeart = new Image(getClass().getResourceAsStream("/pictures/full-heart.png"));
                    image.setImage(fullHeart);
                }
            };
            buttonFavorite.addEventFilter(MouseEvent.MOUSE_ENTERED, favoriteMouseEntered);
            buttonFavorite.addEventFilter(MouseEvent.MOUSE_EXITED, favoriteMouseExited);
            EventHandler<ActionEvent> removeRecipe = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int recipeIndex = buttonsRemoveFavorite.indexOf(buttonFavorite);
                    recipeDisplay.getChildren().remove(contents.get(recipeIndex));
                    favoriteNode.removeFromFavorite(favorites.get(recipeIndex));
                    buttonsRemoveFavorite.remove(recipeIndex);
                    contents.remove(recipeIndex);
                }
            };

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
            buttonFavorite.setOnAction(removeRecipe);
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
}
