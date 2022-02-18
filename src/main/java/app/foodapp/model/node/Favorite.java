package app.foodapp.model.node;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import app.foodapp.model.node.Node;
import app.foodapp.model.node.NodeName;


import javax.management.InstanceAlreadyExistsException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Favorite extends Node {
    private List<Recipe> favorites;

    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.put(0, NodeName.WELCOME);
        this.neighborsList.put(1, NodeName.GET_RECIPE_BY_INGREDIENT);
        this.neighborsList.put(3, NodeName.MEASURE_SYSTEM);
        this.neighborsList.put(4, NodeName.RECIPE_DETAILS);
    }

    public Favorite(){
        super();
        addNodes();
         favorites = new ArrayList<>();
         if(!isSavedFavoritesExists())
            saveFavorites();
    }

    public void launch() {
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

    public boolean isFavorite(Recipe recipe){
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

    public boolean isSavedFavoritesExists(){
        File favoritesSaved = new File("save/favoritesSaved");
        if(favoritesSaved.exists())
            return true;
        return false;
    }
}
