package app.foodapp.view;

import app.foodapp.controller.checkInternetConnexion.InternetConnexion;
import app.foodapp.model.node.Pane;
import app.foodapp.model.node.login.Favorite;
import app.foodapp.model.recipe.Recipe;


import java.io.IOException;

public class FoodAppCLI {

    public static void main(String[] args) throws IOException {
        //if (InternetConnexion.checkStatus())
          //  new Pane();
        Favorite test = new Favorite();
        Favorite.username = "chloe";
        test.addToFavorite(new Recipe("5", "234567", 3, 2));


    }
}
