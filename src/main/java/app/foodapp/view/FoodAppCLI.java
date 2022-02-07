package app.foodapp.view;

import app.foodapp.controller.apiHttpRequest.SearchRecipesByIngredients;

import java.util.ArrayList;

public class FoodAppCLI {

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("ananas");
        SearchRecipesByIngredients test = new SearchRecipesByIngredients(list);
        test.conversion();




    }
}
