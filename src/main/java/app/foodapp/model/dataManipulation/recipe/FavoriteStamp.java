package app.foodapp.model.dataManipulation.recipe;

import app.foodapp.model.node.Favorite;

import java.util.ArrayList;

public class FavoriteStamp {
    ArrayList<Recipe> favorites = new ArrayList<>();

    public FavoriteStamp() {
        favorites.add(new Recipe("73420", "apple", 30, 4));
        favorites.add(new Recipe("632660", "banana recipe", 2, 40));
        favorites.add(new Recipe("9003", "apple recipe", 3, 300));
    }

    public void removeFromFavorite(Recipe recipe) {
        favorites.remove(recipe);
    }

    public ArrayList<Recipe> getFavorites() {
        return favorites;
    }
}
