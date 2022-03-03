package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

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
}
