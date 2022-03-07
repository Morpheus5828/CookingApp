package app.foodapp.model.node;

import app.foodapp.model.recipe.Recipe;
import app.foodapp.model.alert.AlertFound;
import app.foodapp.model.recipe.RecipeInformation;


import javax.management.InstanceAlreadyExistsException;
import java.io.*;
import java.lang.reflect.Array;
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




   public boolean removeFromFavorite(Recipe recipe) throws IOException {
       reader = new BufferedReader(new FileReader("favorite.txt"));
       String line;
       String[] tabOfRow;
       String[] tab;
       while ((line = reader.readLine()) != null) {
           tabOfRow = line.split("=");
           if(tabOfRow[USERNAME].equals(username)){
               tab = tabOfRow[1].split(",");
               for(String s : tab) {
                   if(s.equals(recipe.getId())) s = "";
               }
               return true;
           }
       }
       return false;
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
        File file = new File("favorite.txt");
        FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
        String value = "";

        reader = new BufferedReader(new FileReader("favorite.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            List<String> myList = new ArrayList<String>(Arrays.asList(line.split(",")));
            if(myList.get(USERNAME).equals(username)) {
                for(String m : myList) {
                    if (recipe.getId().equals(m)) {
                        System.out.println("Recipe already in the favorite\n");
                        return false;
                    }
                }
                myList.add(recipe.getId() + "\n");
            }
            value += myList;
        }

        value = value.replace(" ", "");
        value = value.replace("[", "");
        value = value.replace("]", "");
        file.delete();
        FileWriter fw2 = new FileWriter(file.getAbsoluteFile(), true);
        fw2.append(value);
        fw2.close();
        return true;
    }

    /*
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
        fw.close();
        return false;
     */




}
