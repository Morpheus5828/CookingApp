package app.foodapp;

import app.foodapp.controller.apiHttpRequest.MainInstructionsRequest;
import app.foodapp.model.DataManipulation.DataExtraction.RecipeInformation;

public class FoodAppCLI {
    public static void main(String[] args) {
        String ingredientID = "662458";
        RecipeInformation test = new RecipeInformation(ingredientID);
        System.out.println(test.getStepRecipeInformation());

    }
}
