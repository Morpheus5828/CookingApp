package app.foodapp.model;


import java.io.*;
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
            saveFavorites();
    }

    private boolean isFavorite(Recipe recipe){
        return favorites.contains(recipe);
    }

    public void removeFromFavorite(Recipe recipe) throws NoSuchElementException, IOException {
        if(!isFavorite(recipe))
            throw new NoSuchElementException("Not in favorites list");
        else
            favorites.remove(recipe);
            saveFavorites();
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

    private void saveFavorites(){
        try {
            FileOutputStream favoritesSaved = new FileOutputStream("save/favoritesSaved");
            ObjectOutputStream objectOut = new ObjectOutputStream(favoritesSaved);
            objectOut.writeObject(favorites);
            objectOut.close();
            favoritesSaved.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readSavedFavorites(){
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
