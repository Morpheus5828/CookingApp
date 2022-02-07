package app.foodapp.view;

import app.foodapp.controller.apiHttpRequest.SearchRecipesByIngredients;
import app.foodapp.model.dataManipulation.recipe.RecipeInformation;

import java.util.ArrayList;

public class FoodAppCLI {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("ananas");
        //RecipeInformation test = new RecipeInformation(list);
        new InteractionCLI();


    }
}
