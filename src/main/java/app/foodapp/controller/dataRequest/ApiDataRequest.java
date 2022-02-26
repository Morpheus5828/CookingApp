package app.foodapp.controller.dataRequest;

import app.foodapp.controller.KeyManagement;
import app.foodapp.controller.exception.InvalidKeyException;
import app.foodapp.view.alert.AlertFound;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;

public abstract class ApiDataRequest {
    protected String apiKey;
    protected static final int REQUEST_SUCCESSFUL = 200;
    protected static final int INVALID_KEY = 402;
    protected int statusCode = 0;
    protected HttpClient client;
    protected String responseFromApi;

    protected ApiDataRequest() {
        this.client = HttpClient.newHttpClient();
        this.apiKey = KeyManagement.getCurrentKey();
    }

    protected void checkForDataExtraction(String rawRequest) {
        try {
            HttpResponse<String> response = sendRequest(rawRequest);
            this.statusCode = response.statusCode();
            boolean findInvalidKey = false;

            while(this.statusCode == INVALID_KEY) {
                if (!findInvalidKey) {
                    findInvalidKey = true;
                    KeyManagement.setKeyIndexLoopStart();
                }

                this.apiKey = KeyManagement.getNextKey();
                response = sendRequest(rawRequest);
                this.statusCode = response.statusCode();
            }

            if (this.statusCode == REQUEST_SUCCESSFUL) {
                this.responseFromApi = response.body();
                if (findInvalidKey) KeyManagement.resetKeyIndexLoopStart();
            }
            else AlertFound.connexionFailed();

        }
        catch (InvalidKeyException e) {
            AlertFound.invalidKey();
        }
    }

    private HttpRequest createRequest(String rawRequest) {
        return HttpRequest.newBuilder().uri(URI.create(rawRequest + this.apiKey)).build();
    }

    private HttpResponse<String> sendRequest(String rawRequest) {
        HttpResponse<String> response = null;
        try {
            response = this.client.send(
                    createRequest(rawRequest),
                    HttpResponse.BodyHandlers.ofString()
            );
        } catch (IOException | InterruptedException e) {e.printStackTrace();}
        return response;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getResponseFromApi() {
        return this.responseFromApi;
    }
}
// https://api.spoonacular.com/recipes/324694/analyzedInstructions&apiKey=dfe74a73708e4afe81611ce3c399fc31
// https://api.spoonacular.com/recipes/633547/information?includeNutrition=false&apiKey=165550d477004117b084d6a175685e39