package app.foodapp.view;

import app.foodapp.controller.apiHttpRequest.ApiDataRequest;

import java.util.Scanner;

public class FoodAppCLI {
    public static void main(String[] args) {
        String ingredient = "apples,+sugar&number=2";
        ApiDataRequest test = new ApiDataRequest(ingredient);
    }
}
