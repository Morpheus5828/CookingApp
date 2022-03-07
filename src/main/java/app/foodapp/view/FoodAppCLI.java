package app.foodapp.view;

import app.foodapp.controller.checkInternetConnexion.InternetConnexion;
import app.foodapp.model.node.Favorite;
import app.foodapp.model.node.Pane;
import app.foodapp.model.node.login.LoginPage;
import app.foodapp.model.node.login.SignUp;
import app.foodapp.model.recipe.Recipe;
import app.foodapp.model.recipe.RecipeInformation;

import java.io.IOException;

public class FoodAppCLI {

    public static void main(String[] args) throws IOException {
        /*if (InternetConnexion.checkStatus())
            new Pane();*/
        Favorite test = new Favorite();
        Favorite.username = "morpheus5828";
        //test.addToFavorite(new Recipe("416429", "", "te", 15, 25));
        System.out.println(test.isEmpty());


    }
}
