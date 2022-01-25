package app.foodapp.controller.apiHttpRequest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiDataRequest {
    private final String apiKeyValue = "dfe74a73708e4afe81611ce3c399fc31";
    private final String ingredient;
    private final int REQUEST_SUCCESSFUL = 200;
    private int statusCode;


    public ApiDataRequest(String ingredient) {
        this.ingredient = ingredient;

        HttpClient client = HttpClient.newHttpClient();

        java.net.http.HttpRequest request = java.net.http.HttpRequest.newBuilder().uri(URI.create(
             "https://api.spoonacular.com/recipes/findByIngredients?ingredients="
            + this.ingredient
            + "&apiKey="
            + this.apiKeyValue
        )).build();

        testForDataExtraction(client, request);

    }

    private void testForDataExtraction(HttpClient client, HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            statusCode = response.statusCode();

            if(response.statusCode() == REQUEST_SUCCESSFUL)
                System.out.println(response.body());

            else
                System.out.println("problem");

        }

        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }










// https://api.spoonacular.com/recipes/findByIngredients?ingredients=apples,+flour,+sugar&number=2&apiKey=dfe74a73708e4afe81611ce3c399fc31


}








