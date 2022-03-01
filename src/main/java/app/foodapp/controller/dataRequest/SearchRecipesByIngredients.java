package app.foodapp.controller.dataRequest;

import java.util.ArrayList;

public class SearchRecipesByIngredients extends ApiDataRequest {
    private ArrayList<String> listOfIngredient;
    private String ingredient = "";

    public SearchRecipesByIngredients(ArrayList<String> listOfIngredient) {
        super();
        this.listOfIngredient = listOfIngredient;
        conversion();
        String rawRequest = "https://api.spoonacular.com/recipes/findByIngredients?ingredients="
                + this.ingredient
                + "&number=4&apiKey=";

        checkForDataExtraction(rawRequest);
    }

    private void conversion() {
        for(String element : this.listOfIngredient) {
            this.ingredient += element + ",+";
        }
    }
}
