package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.FavoriteStamp;
import app.foodapp.model.dataManipulation.recipe.Recipe;
import app.foodapp.model.node.Favorite;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FavoriteController implements Initializable {

    @FXML private AnchorPane centerPane;
    @FXML private AnchorPane rootPane;

    FavoriteStamp favoriteNode = new FavoriteStamp();
    ArrayList<Button> buttons = new ArrayList<>();
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
            contents.add(content);
            Label title = new Label(recipe.getTitle());
            Label cookingTime = new Label(String.valueOf(recipe.getCookingTime()) + " min");
            Label servings = new Label(String.valueOf(recipe.getServings()) + "people(s)");
            Button buttonDetails = new Button("Details");
            Button buttonFavorite = new Button("Remove from Favorite");
            content.getChildren().add(title);
            content.getChildren().add(cookingTime);
            content.getChildren().add(servings);
            content.getChildren().add(buttonDetails);
            content.getChildren().add(buttonFavorite);
            centerPane.getChildren().add(content);
        }
    }
}
