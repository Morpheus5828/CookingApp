package app.foodapp.model.node;

import app.foodapp.model.recipe.Recipe;
import app.foodapp.model.alert.AlertFound;
import app.foodapp.model.recipe.RecipeInformation;


import javax.management.InstanceAlreadyExistsException;
import java.io.*;
import java.util.*;

public class Favorite {
    public static String username;
    private List<Recipe> listOfRecipe;
    private int choice;
    private final int USERNAME = 0;
    private BufferedReader reader;

    public void launch() {
       /* if (this.isEmpty()) {
            System.out.println("\n" + "⚠ Sorry favorite list is empty" + "\n");
            Pane.setNextNodeNumber("WELCOME"); // If is empty user cannot go to the other node because it has no sense
        }
        else {
            this.displayFavoriteList();
            askToNextNode();
            choiceNumberRecovered();
            changeCurrentNode();
        }*/
    }

    public String askToNextNode() {
        return "What do you want to do ?" + "\n" +
                "1. Menu" + "\n" +
                "2. Get a recipe details" + "\n" +
                "3. BACK";
    }

    public void choiceNumberRecovered() {
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
    }

    public void changeCurrentNode() {
        switch (choice) {
            case 1 -> Pane.setNextNodeNumber("WELCOME");
            case 2 -> Pane.setNextNodeNumber("RECIPE_DETAILS");
            case 3 -> Pane.back();
        }
    }

    public void displayFavoriteList() {
        try {
            reader = new BufferedReader(new FileReader("favorite.txt"));
            String line;
            String[] tabOfRow;
            String[] tab = new String[0];
            while ((line = reader.readLine()) != null) {
                tabOfRow = line.split("=");
                String result = tabOfRow[1];
                for(int i = 0; i < result.length(); i++) {
                    tab = result.split(",");
                }
            }

            for(String s: tab) {
                System.out.println(s);
                RecipeInformation test = new RecipeInformation(s);
                System.out.println(test.displaySimple());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public boolean recipeIsInFavoriteList(Recipe recipe){
        return listOfRecipe.contains(recipe);
    }




   /* public boolean removeFromFavorite(Recipe recipe) throws NoSuchElementException{
        if(!recipeIsInFavoriteList(recipe))
            throw new NoSuchElementException("Not in favorites list");
        else
            listOfRecipe.remove(recipe);
        saveListOfFavorite();
        return true;
    }*/





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





    public boolean isEmpty() throws IOException {
        reader = new BufferedReader(new FileReader("favorite.txt"));
        String line;
        String[] tabOfRow;
        while ((line = reader.readLine()) != null) {
            tabOfRow = line.split("=");
            if(tabOfRow[USERNAME].equals(username) && tabOfRow[1].equals(" ,")){
                return true;
            }
        }
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


    public boolean addToFavorite(Recipe recipe) throws IOException {
        reader = new BufferedReader(new FileReader("favorite.txt"));
        String line;
        String[] tabOfRow;
        while ((line = reader.readLine()) != null) {
            tabOfRow = line.split("=");
            if(tabOfRow[USERNAME].equals(username)){  //TODO already in the favorite
                tabOfRow[1] += recipe.getId() + ",";
                return true;
            }
        }
        return false;
    }




}
