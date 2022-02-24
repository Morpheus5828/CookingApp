package app.foodapp.controller.dataRequest;

import app.foodapp.controller.KeyManagement;
import app.foodapp.controller.exception.InvalidKeyException;
import app.foodapp.view.alert.AlertFound;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class ApiDataRequest {
    protected String API_KEY = KeyManagement.getNextKey();
    protected final int REQUEST_SUCCESSFUL = 200;
    protected final int INVALID_KEY = 402;
    protected int statusCode = 0;
    protected HttpClient client;
    protected HttpRequest request;
    protected String responseFromApi;

    protected void checkForDataExtraction(HttpClient client, HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            this.statusCode = response.statusCode();

            if(this.statusCode == REQUEST_SUCCESSFUL)
                this.responseFromApi = response.body();

            else if (this.statusCode == INVALID_KEY) {
                this.API_KEY = KeyManagement.getNextKey();
            } else
                AlertFound.connexionFailed();

        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
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