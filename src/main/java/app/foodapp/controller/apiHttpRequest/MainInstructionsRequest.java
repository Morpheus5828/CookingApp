package app.foodapp.controller.apiHttpRequest;

import app.foodapp.view.alert.Error;

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
             + "&number=3&apiKey="
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
                this.responseFromApi = response.body();
            else
                Error.connexionFailed();

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








