package app.foodapp.model.node;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import app.foodapp.view.alert.AlertFound;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Favorite extends Node {
    private List<Recipe> listOfRecipe;

    public Favorite(){
        super();
        addNodes();
        listOfRecipe = new ArrayList<>();
         //if(!isSavedFavoritesExists())
           // saveFavorites();
    }

    public void launch() {
        if (this.isEmpty()) {
            System.out.println("\n" + "⚠ Sorry favorite list is empty" + "\n");
            Pane.setNextNodeNumber("WELCOME"); // If is empty user cannot go to the other node because it has no sense
        }
        else {
            this.displayFavoriteList();
            askToNextNode();
        }


    }

    // TODO check saveFavorite method
    /*public boolean addToFavorite(Recipe recipe) throws InstanceAlreadyExistsException {
        if(recipeIsInFavoriteList(recipe))
            throw new InstanceAlreadyExistsException("Recipe already in Favorites");
        else{
            this.listOfRecipe.add(recipe);
            saveFavorites();
            return true;
        }
    }*/

    public void askToNextNode() throws NoSuchElementException{
        try {
            System.out.println("\n" +
                "What do you want to do ?" + "\n" +
                "1. Menu" + "\n" +
                "2. Get a recipe details" + "\n" +
                "3. BACK"
            );
            Scanner sc = new Scanner(System.in);
            int answer = sc.nextInt();
            switch (answer) {
                case 1 -> Pane.setNextNodeNumber("WELCOME");
                case 2 -> Pane.setNextNodeNumber("GET_RECIPE_BY_INGREDIENT");
                case 3 -> Pane.back();
            }

        } catch (NoSuchElementException e) {
            AlertFound.invalidCharacter();
        }

    }

    public boolean recipeIsInFavoriteList(Recipe recipe){
        return listOfRecipe.contains(recipe);
    }

    public boolean removeFromFavorite(Recipe recipe) throws NoSuchElementException{
        if(!recipeIsInFavoriteList(recipe))
            throw new NoSuchElementException("Not in favorites list");
        else
            listOfRecipe.remove(recipe);
            saveListOfFavorite();
            return true;
    }

    public void saveListOfFavorite(){
        try {
            FileOutputStream favoritesSaved = new FileOutputStream("save/favoritesSaved");
            ObjectOutputStream objectOutput = new ObjectOutputStream(favoritesSaved);
            objectOutput.writeObject(listOfRecipe);
            objectOutput.close();
            favoritesSaved.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isEmpty() {
        if (this.listOfRecipe.isEmpty())
            return true;
        return false;
    }

    public void readSavedFavorites(){
        try {
            FileInputStream favoritesSaved = new FileInputStream("save/favoritesSaved");
            ObjectInputStream objectInput = new ObjectInputStream(favoritesSaved);
            listOfRecipe = (ArrayList<Recipe>) objectInput.readObject();
            objectInput.close();
            favoritesSaved.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean favoriteListIsSaved(){
        File favoritesSaved = new File("save/favoritesSaved");
        if(favoritesSaved.exists())
            return true;
        return false;
    }

    //Setter
    public boolean addToFavorite(Recipe recipe) {
        if(recipeIsInFavoriteList(recipe)) {
            System.out.println("Recipe already in Favorites");
            return false;
        }
        listOfRecipe.add(recipe);
        System.out.println("Recipe has been add successfully =)" + "\n");
        //saveFavorites();
        return true;

    }

    //Getter
    public Recipe getRecipe(int index) throws ArrayIndexOutOfBoundsException{
        if(index < 0)
            throw new ArrayIndexOutOfBoundsException("Index can't be under 0");
        else if(index > listOfRecipe.size()-1)
            throw new ArrayIndexOutOfBoundsException("Index not in list size");
        else
            return listOfRecipe.get(index);
    }

    public List<Recipe> getFavoriteList(){
        return this.listOfRecipe;
    }

    // Add Favorite class neighbours
    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.put(0, NodeName.WELCOME);
        this.neighborsList.put(1, NodeName.GET_RECIPE_BY_INGREDIENT);
        this.neighborsList.put(3, NodeName.MEASURE_SYSTEM);
        this.neighborsList.put(4, NodeName.RECIPE_DETAILS);
        this.neighborsList.put(5, NodeName.CLOSE_APP);
    }

    public void displayFavoriteList() {
        for(Recipe recipe : this.listOfRecipe) {
            recipe.displaySimpleCharacteristics();
        }
    }

}
