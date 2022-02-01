package app.foodapp.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Favorite {
    private List<Recipe> favorites;

    public Favorite(){
        favorites = new ArrayList();
    }

    public Favorite(File saveFile){
    }

    public void addToFavorite(Recipe recipe) throws IOException {
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

    public void saveFavorites() throws IOException {
        File userFavoritesSave = new File("save/user_favorite_save.txt");
        FileWriter fileWriter = new FileWriter(userFavoritesSave);
        fileWriter.write(favorites.toString());
        fileWriter.flush();
        fileWriter.close();
    }

    private List<Recipe> readSavedFavorites(File file) throws IOException {
        List<Recipe> extractedSavedFavorites = new ArrayList<>();
        String savedFavoritesString = String.valueOf(Files.readString(Paths.get("save/user_favorite_save.txt")));
        //List<Recipe> list = new ArrayList<Recipe>(String.as);

        return extractedSavedFavorites;
    }
}
