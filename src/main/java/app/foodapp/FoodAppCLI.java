package app.foodapp;

import app.foodapp.controller.apiHttpRequest.ApiDataRequest;

public class FoodAppCLI {
    public static void main(String[] args) {
        String ingredient = "apples,+sugar&number=2";
        ApiDataRequest test = new ApiDataRequest(ingredient);
    }
}
