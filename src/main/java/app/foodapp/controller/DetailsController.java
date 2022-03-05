package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class DetailsController {
    @FXML private Text detailsDisplay;

    public void showDetails(Recipe recipe) {
        String steps = recipe.getSteps();
        System.out.println(recipe.getTitle());
        System.out.println(steps);
        display(recipe);
    }

    private void display(Recipe recipe){
        detailsDisplay.setText(recipe.displayDetailsCharacteristicsGUI());
    }

    public void goToMenu(ActionEvent actionEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/app/foodapp/view/foodapp.fxml"));

            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            String css = this.getClass().getResource("/app/foodapp/view/stylesheet.css").toExternalForm();

            scene.getStylesheets().add(css);
            stage.setScene(scene);
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

    public void goToFavorites(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/favorites.fxml"));
        Parent root = loader.load();
        FavoriteController favoriteController = loader.getController();
        favoriteController.getFavoritesRecipes();

        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        String css = this.getClass().getResource("/app/foodapp/view/globalStylesheet.css").toExternalForm();

        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
}
