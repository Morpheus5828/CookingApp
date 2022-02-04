package app.foodapp;

import app.foodapp.controller.apiHttpRequest.ApiDataRequestForMainInstructions;

public class FoodAppCLI {
    public static void main(String[] args) {
        String ingredientID = "662458";
        ApiDataRequestForMainInstructions test = new ApiDataRequestForMainInstructions(ingredientID);


    }
}
