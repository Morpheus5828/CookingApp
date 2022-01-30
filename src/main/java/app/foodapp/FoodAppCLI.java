package app.foodapp;

import app.foodapp.controller.apiHttpRequest.ApiDataRequest;

public class FoodAppCLI {
    public static void main(String[] args) {
        String ingredientID = "716429";
        ApiDataRequest test = new ApiDataRequest(ingredientID);
    }
}
