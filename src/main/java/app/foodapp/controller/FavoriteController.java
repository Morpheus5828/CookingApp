package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.FavoriteStamp;
import app.foodapp.model.dataManipulation.recipe.Recipe;
import app.foodapp.model.node.Favorite;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FavoriteController implements Initializable {

    @FXML private VBox recipeDisplay;
    @FXML private AnchorPane rootPane;

    FavoriteStamp favoriteNode = new FavoriteStamp();
    ArrayList<Button> buttonsRemoveFavorite = new ArrayList<>();
    ArrayList<HBox> contents = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void goToMenu(ActionEvent actionEvent) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    public void showFavorites() {
        ArrayList<Recipe> favorites = favoriteNode.getFavorites();
        for (Recipe recipe : favorites) {
            HBox content = new HBox();
            content.getStyleClass().add("recipe-content");
            contents.add(content);
            Label title = new Label(recipe.getTitle());
            title.getStyleClass().add("recipe-title");
            Label cookingTime = new Label(String.valueOf(recipe.getCookingTime()) + " min");
            cookingTime.getStyleClass().add("recipe-cookingTime");
            Label servings = new Label(String.valueOf(recipe.getServings()) + " people");
            servings.getStyleClass().add("recipe-servings");
            Button buttonFavorite = new Button("Remove from Favorite");
            buttonsRemoveFavorite.add(buttonFavorite);
            EventHandler<ActionEvent> removeRecipe = new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int recipeIndex = buttonsRemoveFavorite.indexOf(buttonFavorite);
                    favoriteNode.removeFromFavorite(favorites.get(recipeIndex));
                    recipeDisplay.getChildren().remove(contents.get(recipeIndex));
                    buttonsRemoveFavorite.remove(recipeIndex);
                    contents.remove(recipeIndex);
                }
            };
            EventHandler<MouseEvent> mouseClick = new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                }
            };
            buttonFavorite.setOnAction(removeRecipe);
            content.getChildren().add(title);
            content.getChildren().add(cookingTime);
            content.getChildren().add(servings);
            content.getChildren().add(buttonFavorite);
            recipeDisplay.getChildren().add(content);
        }
    }

    public void goToDetails(MouseEvent event, Recipe recipe) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/favorites.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
