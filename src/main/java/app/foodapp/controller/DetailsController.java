package app.foodapp.controller;

import app.foodapp.model.dataManipulation.recipe.Recipe;

public class DetailsController {
    public void showDetails(Recipe recipe) {
        String steps = recipe.getSteps();
        System.out.println(steps);
    }
}
