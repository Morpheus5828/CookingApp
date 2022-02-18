package app.foodapp.model.node;

import app.foodapp.model.dataManipulation.recipe.Recipe;


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
         //if(!isSavedFavoritesExists())
           // saveFavorites();
    }

    public void launch() {
        this.displayFavoriteList();
        if (this.isEmpty())
            Pane.setNextNodeNumber("WELCOME");
        else {
            Pane.checkStatusCode = false;
        }

    }

    /*public boolean addToFavorite(Recipe recipe) throws InstanceAlreadyExistsException {
        if(isFavorite(recipe))
            throw new InstanceAlreadyExistsException("Recipe already in Favorites");
        else{
            favorites.add(recipe);
            saveFavorites();
            return true;
        }
    }*/

    public boolean addToFavorite(Recipe recipe) {
        if(isFavorite(recipe)) {
            System.out.println("Recipe already in Favorites");
            return false;
        }
        favorites.add(recipe);
        System.out.println("Recipe has been correctly added to Favorite" + "\n");
        //saveFavorites();
        return true;

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

    public List<Recipe> getFavoriteList(){
        return this.favorites;
    }

    public Recipe getFavorite(int index) throws ArrayIndexOutOfBoundsException{
        if(index < 0)
            throw new ArrayIndexOutOfBoundsException("Index can't be under 0");
        else if(index > favorites.size()-1)
            throw new ArrayIndexOutOfBoundsException("Index not in list size");
        else
            return favorites.get(index);
    }

    public boolean saveFavorites(){
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

    private boolean isEmpty() {
        if (this.favorites.isEmpty())
            return true;
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

    public void displayFavoriteList() {
        if (this.isEmpty())
            System.out.println("Sorry: Favorite list is empty");
        for(Recipe recipe : this.favorites) {
            recipe.display();
        }
    }
}
