package app.foodapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class Favorite {
    private List<RecipeTmp> favorites;

    public Favorite(){
        favorites = new ArrayList();
    }

    public void addToFavorite(RecipeTmp recipe){
        if(!isFavorite(recipe))
            favorites.add(recipe);
    }

    private boolean isFavorite(RecipeTmp recipe){
        return favorites.contains(recipe);
    }

    public void removeFromFavorite(RecipeTmp recipe) throws NoSuchElementException {
        if(!isFavorite(recipe))
            throw new NoSuchElementException("Not in favorites list");
        else
            favorites.remove(recipe);
    }

    public List<RecipeTmp> getFavorites(){
        return favorites;
    }

}
