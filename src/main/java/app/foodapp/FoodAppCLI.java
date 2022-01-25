package app.foodapp;

import app.foodapp.controller.apiHttpRequest.ApiDataRequest;
import app.foodapp.model.DataManipulation.DataExtraction;

import java.io.IOException;
import java.util.Scanner;

public class FoodAppCLI {
    public static void main(String[] args) {
        String ingredient = "apples,+sugar&number=2";
        ApiDataRequest test = new ApiDataRequest(ingredient);
    }
}
