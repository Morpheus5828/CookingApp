package app.foodapp.controller.dataRequest;

import app.foodapp.controller.dataRequest.ApiDataRequest;
import app.foodapp.view.alert.AlertFound;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainInstructionsRequest extends ApiDataRequest {
    private String responseFromApi;

    public MainInstructionsRequest(String recipeId) {
        this.client = HttpClient.newHttpClient();

        // We launch data request to receive recipe information
        this.request = HttpRequest.newBuilder().uri(URI.create(
             "https://api.spoonacular.com/recipes/"
             + recipeId
             + "/information?analyzedInstructions"
             + "&number=2&apiKey="
            + this.API_KEY
        )).build();

        checkForDataExtraction(client, request);
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getResponseFromApi() {
        return this.responseFromApi;
    }
}








