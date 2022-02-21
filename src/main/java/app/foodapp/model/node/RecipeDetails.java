package app.foodapp.model.node;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import app.foodapp.model.dataManipulation.recipe.RecipeInformation;

public class RecipeDetails extends Node {
    public static Recipe recipe;
    public RecipeDetails() {
        super();
    }

    public void launch() {
        recipe.displayDetailsCharacteristics();
        // Temporally
        Pane.checkStatusCode = false;
    }

    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.put(0, NodeName.WELCOME);
        this.neighborsList.put(1, NodeName.GET_RECIPE_BY_INGREDIENT);
        this.neighborsList.put(2, NodeName.FAVORITE);
        this.neighborsList.put(3, NodeName.MEASURE_SYSTEM);
        this.neighborsList.put(5, NodeName.CLOSE_APP);
    }
}
