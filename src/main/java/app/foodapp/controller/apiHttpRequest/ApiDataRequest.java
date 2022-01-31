package app.foodapp.controller.apiHttpRequest;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public abstract class ApiDataRequest {
    protected final String API_KEY = "dfe74a73708e4afe81611ce3c399fc31";
    protected final int REQUEST_SUCCESSFUL = 200;
    protected int statusCode = 0;
    protected HttpClient client;
    protected HttpRequest request;




}
//https://api.spoonacular.com/recipes/324694/analyzedInstructions&apiKey=dfe74a73708e4afe81611ce3c399fc31