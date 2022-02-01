package app.foodapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class Favorite {
    private List<Recipe> favorites;

    public Favorite(){
        favorites = new ArrayList();
    }

    public void addToFavorite(Recipe recipe){
        if(!isFavorite(recipe))
            favorites.add(recipe);
    }

    private boolean isFavorite(Recipe recipe){
        return favorites.contains(recipe);
    }

    public void removeFromFavorite(Recipe recipe) throws NoSuchElementException {
        if(!isFavorite(recipe))
            throw new NoSuchElementException("Not in favorites list");
        else
            favorites.remove(recipe);
    }

    public List<Recipe> getFavorites(){
        return favorites;
    }

    public Recipe getFavorite(int index) throws ArrayIndexOutOfBoundsException{
        if(index < 0)
            throw new ArrayIndexOutOfBoundsException("Index can't be under 0");
        else if(index > favorites.size()-1)
            throw new ArrayIndexOutOfBoundsException("Index not in list size");
        else
            return favorites.get(index);
    }
}
