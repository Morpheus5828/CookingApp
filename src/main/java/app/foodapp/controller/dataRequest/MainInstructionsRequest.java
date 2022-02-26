package app.foodapp.controller.dataRequest;

import java.net.URI;
import java.net.http.HttpRequest;

public class MainInstructionsRequest extends ApiDataRequest {

    public MainInstructionsRequest(String recipeId) {
        super();
        String rawIndex = "https://api.spoonacular.com/recipes/"
             + recipeId
             + "/information?analyzedInstructions"
             + "&number=4&apiKey=";

        checkForDataExtraction(rawIndex);
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getResponseFromApi() {
        return this.responseFromApi;
    }
}








