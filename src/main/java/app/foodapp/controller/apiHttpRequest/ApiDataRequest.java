package app.foodapp.controller.apiHttpRequest;

import controller.DataManipulation.DataExtraction;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiDataRequest {
    private final String apiKeyValue = "d1ae0a965e2b4588b474f670ef3ca9ab";
    private final String ingredient;
    private final int REQUEST_SUCCESSFUL = 200;
    private int statusCode;
    private String response;


    public ApiDataRequest(String ingredient) {
        this.ingredient = ingredient;

        HttpClient client = HttpClient.newHttpClient();

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder().uri(URI.create(
             "https://api.spoonacular.com/recipes/findByIngredients?ingredients="
            + this.ingredient
            + "&apiKey="
            + this.apiKeyValue
        )).build();

        checkForDataExtraction(client, request);

    }

    private void checkForDataExtraction(HttpClient client, HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            statusCode = response.statusCode();

            if(response.statusCode() == REQUEST_SUCCESSFUL)
                //We can begin data extraction
                new DataExtraction(response.body());

            else
                System.out.println("problem");

        }

        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}








