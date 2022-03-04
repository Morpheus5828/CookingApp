package app.foodapp.controller.dataRequest;

import app.foodapp.controller.dataRequest.ApiDataRequest;
import app.foodapp.view.alert.AlertFound;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class SearchRecipesByIngredients extends ApiDataRequest {
    private ArrayList<String> listOfIngredient;
    private String ingredient = "";
    private String responseFromApi;

    public SearchRecipesByIngredients(ArrayList<String> listOfIngredient) {
        this.listOfIngredient = listOfIngredient;
        this.client = HttpClient.newHttpClient();
        conversion();
        // We launch data request to receive recipe information
        this.request = HttpRequest.newBuilder().uri(URI.create(
                "https://api.spoonacular.com/recipes/findByIngredients?ingredients="
                + this.ingredient
                + "&number=5&apiKey="
                + this.API_KEY
        )).build();

        checkForDataExtraction(client, request);
    }

    private void conversion() {
        for(String element : this.listOfIngredient) {
            this.ingredient += element + ",+";
        }
    }

    private void checkForDataExtraction(HttpClient client, HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            statusCode = response.statusCode();

            if(response.statusCode() == REQUEST_SUCCESSFUL)
                //We can begin data extraction
                this.responseFromApi = response.body();
            else
                AlertFound.connexionFailed();

        }

        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getResponseFromApi() {
        return this.responseFromApi;
    }
}
