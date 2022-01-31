package app.foodapp.controller.apiHttpRequest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public class ApiDataRequestForStepInstructions extends ApiDataRequest{
    public ApiDataRequestForStepInstructions(String recipeId) {
        this.client = HttpClient.newHttpClient();

        // We launch data request to receive recipe information
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
                "https://api.spoonacular.com/recipes/"
                        + recipeId
                        + "/information?includeNutrition=false"
                        + "&apiKey="
                        + this.API_KEY
        )).build();
    }
}
