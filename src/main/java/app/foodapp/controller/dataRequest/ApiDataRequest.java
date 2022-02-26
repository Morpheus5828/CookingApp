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
    protected String API_KEY = KeyManagement.getCurrentKey();
    protected final int REQUEST_SUCCESSFUL = 200;
    protected final int INVALID_KEY = 402;
    protected int statusCode = 0;
    protected HttpClient client;
    protected String responseFromApi;

    protected ApiDataRequest() {
        this.client = HttpClient.newHttpClient();
    }

    protected void checkForDataExtraction(String rawRequest) {
        try {
            HttpResponse<String> response = this.client.send(
                    createRequest(rawRequest),
                    HttpResponse.BodyHandlers.ofString()
            );
            this.statusCode = response.statusCode();
            boolean findInvalidKey = false;

            while(this.statusCode == INVALID_KEY) {
                if (!findInvalidKey) {
                    findInvalidKey = true;
                    KeyManagement.setKeyIndexLoopStart();
                }

                this.API_KEY = KeyManagement.getNextKey();
                response = this.client.send(
                        createRequest(rawRequest),
                        HttpResponse.BodyHandlers.ofString()
                );
            }
            System.out.println(this.API_KEY);
            if (this.statusCode == REQUEST_SUCCESSFUL) {
                this.responseFromApi = response.body();
                if (findInvalidKey) KeyManagement.resetKeyIndexLoopStart();
            }

            else AlertFound.connexionFailed();

        }
        catch (InvalidKeyException e) {
            AlertFound.invalidKey();
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private HttpRequest createRequest(String rawRequest) {
        return HttpRequest.newBuilder().uri(URI.create(rawRequest + this.API_KEY)).build();
    }
}
// https://api.spoonacular.com/recipes/324694/analyzedInstructions&apiKey=dfe74a73708e4afe81611ce3c399fc31
// https://api.spoonacular.com/recipes/633547/information?includeNutrition=false&apiKey=165550d477004117b084d6a175685e39

//Test unitaire use this one : 165550d477004117b084d6a175685e39

// b6d3a80d82844526b6808686b17c0b63 done
//dfe74a73708e4afe81611ce3c399fc31
// d1ae0a965e2b4588b474f670ef3ca9ab
// cf77a65bfa1f44559362ef7b150e0700
// 9df62fafe6774b74b1f820202c05975b
// 612714f9a4f449b98d81bb8e5c95a835
// 4ba4d2774096480aa78d008736b5f79c done