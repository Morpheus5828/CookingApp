package app.foodapp;

import app.foodapp.controller.apiHttpRequest.ApiDataRequestForMainInstructions;

public class FoodAppCLI {
    public static void main(String[] args) {
        String ingredientID = "716429";
        ApiDataRequestForMainInstructions test = new ApiDataRequestForMainInstructions(ingredientID);
    }
}
