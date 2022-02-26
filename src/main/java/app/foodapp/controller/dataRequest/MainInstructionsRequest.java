package app.foodapp.controller.dataRequest;

import java.net.URI;
import java.net.http.HttpRequest;

public class MainInstructionsRequest extends ApiDataRequest {

    public MainInstructionsRequest(String recipeId) {
        super();
        /*this.request = HttpRequest.newBuilder().uri(URI.create(
             "https://api.spoonacular.com/recipes/"
             + recipeId
             + "/information?analyzedInstructions"
             + "&number=4&apiKey="
        )).build();

        checkForDataExtraction();*/
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getResponseFromApi() {
        return this.responseFromApi;
    }
}








