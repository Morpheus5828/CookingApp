package app.foodapp.model.dataManipulation.recipe;

import app.foodapp.model.node.Favorite;

import java.util.ArrayList;

public class FavoriteStamp {
    ArrayList<Recipe> favorites = new ArrayList<>();

    public FavoriteStamp() {
        favorites.add(new Recipe("73420", "apple", 30, 4));
        favorites.add(new Recipe("632660", "banana ", 2, 40));
        favorites.add(new Recipe("9003", "avocado", 3, 300));
        favorites.add(new Recipe("73420", "pear", 30, 4));
        favorites.add(new Recipe("632660", "cherry", 2, 40));
        favorites.add(new Recipe("9003", "lemon", 3, 300));
        favorites.add(new Recipe("73420", "tomato", 30, 4));
        favorites.add(new Recipe("632660", "strawberry", 2, 40));
        favorites.add(new Recipe("9003", "cranberry", 3, 300));
        favorites.add(new Recipe("73420", "chicken", 30, 4));
        favorites.add(new Recipe("632660", "steak", 2, 40));
        favorites.add(new Recipe("9003", "pasta", 3, 300));
        favorites.add(new Recipe("9003", "rabbit", 3, 300));
        favorites.add(new Recipe("9003", "pepper", 3, 300));
        favorites.add(new Recipe("9003", "salt", 3, 300));
        favorites.add(new Recipe("9003", "orange", 3, 300));
        favorites.add(new Recipe("9003", "pineapple", 3, 300));
        favorites.add(new Recipe("9003", "berry", 3, 300));
        favorites.add(new Recipe("9003", "cream", 3, 300));
        favorites.add(new Recipe("9003", "sour cream", 3, 300));
        favorites.add(new Recipe("9003", "rice", 3, 300));
        favorites.add(new Recipe("9003", "tuna", 3, 300));
    }

    public void removeFromFavorite(Recipe recipe) {
        favorites.remove(recipe);
    }

    public void addToFavorite(Recipe recipe) {
        favorites.add(recipe);
    }

    public ArrayList<Recipe> getFavorites() {
        return favorites;
    }

    public boolean isEmpty() {
        if (favorites.isEmpty()) return true;
        return false;
    }
}
