package app.foodapp.controller.backController;

import app.foodapp.controller.ResearchController;
import app.foodapp.model.recipe.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class BackToMenu implements BackController{
    private final ArrayList<Recipe> recipeList;
    private final ArrayList<String> ingredients;
    private int pageIndex;

    public BackToMenu(final ArrayList<Recipe> recipeList, final ArrayList<String> ingredients, final int pageIndex) {
        this.recipeList = recipeList;
        this.ingredients = ingredients;
        this.pageIndex = pageIndex;
    }

    @Override
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    @Override
    public void goBack(final ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/foodapp/view/foodapp.fxml"));
            Parent root = loader.load();
            ResearchController researchController = loader.getController();
            researchController.setRecipeResearch();
            researchController.makeResearch(this.recipeList, this.pageIndex, this.ingredients, this);

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
