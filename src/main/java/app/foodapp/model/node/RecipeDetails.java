package app.foodapp.model.node;

import app.foodapp.model.dataManipulation.recipe.Recipe;

public class RecipeDetails extends Node{
    public RecipeDetails() {
        super();

    }

    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.add(NodeName.FAVORITE);
        this.neighborsList.add(NodeName.GET_RECIPE_BY_INGREDIENT);
        this.neighborsList.add(NodeName.WELCOME);
        this.neighborsList.add(NodeName.MEASURE_SYSTEM);
    }
}
