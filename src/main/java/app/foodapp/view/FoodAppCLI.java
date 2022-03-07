package app.foodapp.view;

import app.foodapp.controller.checkInternetConnexion.InternetConnexion;
import app.foodapp.model.node.Pane;


import java.io.IOException;

public class FoodAppCLI {

    public static void main(String[] args) throws IOException {
        if (InternetConnexion.checkStatus())
            new Pane();
        //GetRecipeByIngredient test = new GetRecipeByIngredient();
        //test.launch();


    }
}
