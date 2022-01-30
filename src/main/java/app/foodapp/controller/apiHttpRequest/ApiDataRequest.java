package app.foodapp.controller.apiHttpRequest;

import app.foodapp.model.DataManipulation.DataExtraction.RecipeInformation;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiDataRequest {
    private final String API_KEY = "dfe74a73708e4afe81611ce3c399fc31";
    private final String recipeId;
    private final int REQUEST_SUCCESSFUL = 200;
    private int statusCode;

    public ApiDataRequest(String recipeId) {
        this.recipeId = recipeId;

        HttpClient client = HttpClient.newHttpClient();

        // We launch data request to receive recipe information
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(
             "https://api.spoonacular.com/recipes/"
             + this.recipeId
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








