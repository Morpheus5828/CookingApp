package app.foodapp;

import app.foodapp.controller.apiHttpRequest.ApiDataRequestForMainInstructions;
import app.foodapp.model.Favorite;
import app.foodapp.model.Recipe;

import javax.management.InstanceAlreadyExistsException;

public class FoodAppCLI {
    public static void main(String[] args) throws InstanceAlreadyExistsException {
        Favorite favorites = new Favorite();
        Recipe recipe1 = new Recipe();
        Recipe recipe2 = new Recipe();
        favorites.addToFavorite(recipe1);
        favorites.addToFavorite(recipe2);

        /*Favorite newFavorites = new Favorite();
        newFavorites.readSavedFavorites();*/
    }
}
