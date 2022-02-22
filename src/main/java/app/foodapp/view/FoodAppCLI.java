package app.foodapp.view;

import app.foodapp.controller.apiHttpRequest.SearchRecipesByIngredients;
import app.foodapp.model.dataManipulation.recipe.RecipeInformation;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;

public class FoodAppCLI {

    public static void main(String[] args) {
        new InteractionCLI();
    }
}
