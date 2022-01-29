package app.foodapp.model;

import java.util.ArrayList;
import java.util.List;


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

}
