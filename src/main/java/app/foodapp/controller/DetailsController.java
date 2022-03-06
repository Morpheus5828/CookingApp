package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import javafx.animation.ParallelTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class DetailsController extends Controller {
    @FXML private Text detailsDisplay;

    public void showDetails(Recipe recipe) {
        String steps = recipe.getSteps();
        display(recipe);
    }

    private void display(Recipe recipe){
        detailsDisplay.setText(recipe.displayDetailsCharacteristicsGUI());
    }

    @Override
    public EventHandler<ActionEvent> removeRecipeFromFavorites(Button button, Recipe recipe, StackPane stackPane, HBox box) {
        return null;
    }

    @Override
    public EventHandler<ActionEvent> addRecipeToFavorites(Button button, Recipe recipe, StackPane stackPane, HBox box) {
        return null;
    }
}
