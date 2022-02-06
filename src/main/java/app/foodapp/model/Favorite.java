package app.foodapp.model;


import javax.management.InstanceAlreadyExistsException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Favorite {
    private List<Recipe> favorites;

    public Favorite(){
         favorites = new ArrayList<>();
    }

    public boolean addToFavorite(Recipe recipe) throws InstanceAlreadyExistsException {
        if(isFavorite(recipe))
            throw new InstanceAlreadyExistsException("Recipe already in Favorites");
        else{
            favorites.add(recipe);
            saveFavorites();
            return true;
        }
    }

    protected boolean isFavorite(Recipe recipe){
        return favorites.contains(recipe);
    }

    public boolean removeFromFavorite(Recipe recipe) throws NoSuchElementException{
        if(!isFavorite(recipe))
            throw new NoSuchElementException("Not in favorites list");
        else
            favorites.remove(recipe);
            saveFavorites();
            return true;
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

    protected boolean saveFavorites(){
        try {
            FileOutputStream favoritesSaved = new FileOutputStream("save/favoritesSaved");
            ObjectOutputStream objectOut = new ObjectOutputStream(favoritesSaved);
            objectOut.writeObject(favorites);
            objectOut.close();
            favoritesSaved.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void readSavedFavorites(){
        try {
            FileInputStream favoritesSaved = new FileInputStream("save/favoritesSaved");
            ObjectInputStream objectInput = new ObjectInputStream(favoritesSaved);
            favorites = (ArrayList<Recipe>) objectInput.readObject();
            objectInput.close();
            favoritesSaved.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
