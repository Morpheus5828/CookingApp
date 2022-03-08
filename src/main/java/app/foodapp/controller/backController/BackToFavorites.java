package app.foodapp.controller.backController;

import app.foodapp.controller.FavoritesController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BackToFavorites implements BackController {
    private int pageIndex;

    public BackToFavorites(final int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public void goBack(final ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/favorites.fxml"));
            Parent root = loader.load();
            FavoritesController favoriteController = loader.getController();
            favoriteController.getFavoritesRecipes(this.pageIndex);
            favoriteController.update();

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
}
