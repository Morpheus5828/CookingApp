package app.foodapp.controller.dataRequest;

import app.foodapp.controller.dataRequest.ApiDataRequest;
import app.foodapp.view.alert.AlertFound;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class SearchRecipesByIngredients extends ApiDataRequest {
    private ArrayList<String> listOfIngredient;
    private String ingredient = "";

    public SearchRecipesByIngredients(ArrayList<String> listOfIngredient) {
        super();
        this.listOfIngredient = listOfIngredient;
        conversion();
        /*this.request = HttpRequest.newBuilder().uri(URI.create(
                "https://api.spoonacular.com/recipes/findByIngredients?ingredients="
                + this.ingredient
                + "&number=4&apiKey="
        )).build();

        checkForDataExtraction();*/
    }

    private void conversion() {
        for(String element : this.listOfIngredient) {
            this.ingredient += element + ",+";
        }
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getResponseFromApi() {
        return this.responseFromApi;
    }
}
