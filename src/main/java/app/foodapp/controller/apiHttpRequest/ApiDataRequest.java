package app.foodapp.controller.apiHttpRequest;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;

public abstract class ApiDataRequest {
    protected final String API_KEY = "d1ae0a965e2b4588b474f670ef3ca9ab";
    protected final int REQUEST_SUCCESSFUL = 200;
    protected int statusCode = 0;
    protected HttpClient client;
    protected HttpRequest request;




}
//https://api.spoonacular.com/recipes/324694/analyzedInstructions&apiKey=dfe74a73708e4afe81611ce3c399fc31

//dfe74a73708e4afe81611ce3c399fc31
// d1ae0a965e2b4588b474f670ef3ca9ab
// cf77a65bfa1f44559362ef7b150e0700