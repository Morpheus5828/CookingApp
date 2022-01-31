package app.foodapp.controller.apiHttpRequest;

import app.foodapp.model.DataManipulation.DataExtraction.RecipeInformation;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiDataRequestForMainInstructions extends ApiDataRequest{

    public ApiDataRequestForMainInstructions(String recipeId) {
        this.client = HttpClient.newHttpClient();

        // We launch data request to receive recipe information
        this.request = HttpRequest.newBuilder().uri(URI.create(
             "https://api.spoonacular.com/recipes/"
             + recipeId
             + "/information?includeNutrition=false"
             + "&apiKey="
            + this.API_KEY
        )).build();

        checkForDataExtraction(client, request);
    }

    private void checkForDataExtraction(HttpClient client, HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            statusCode = response.statusCode();

            if(response.statusCode() == REQUEST_SUCCESSFUL)
                //We can begin data extraction
                new RecipeInformation(response.body());

            else
                System.out.println("problem");

        }

        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}








