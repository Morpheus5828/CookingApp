package app.foodapp;

import app.foodapp.controller.apiHttpRequest.ApiDataRequestForMainInstructions;
import app.foodapp.model.Favorite;
import app.foodapp.model.Recipe;

import javax.management.InstanceAlreadyExistsException;
import java.io.IOException;

public class FoodAppCLI {
    public static void main(String[] args) throws InstanceAlreadyExistsException{
        Favorite favorites = new Favorite();
        Recipe recipe1 = new Recipe(1180, null, null, 151, 90);
        Recipe recipe2 = new Recipe(1740, null, null, 854, 45);
        favorites.addToFavorite(recipe1);
        favorites.addToFavorite(recipe2);
        //favorites.addToFavorite(recipe1);
        favorites.removeFromFavorite(recipe1);


        Favorite newFavorites = new Favorite();
        newFavorites.readSavedFavorites();
    }
}
