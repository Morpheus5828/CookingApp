package app.foodapp.model.node.login;

import app.foodapp.model.alert.AlertFound;
import app.foodapp.model.node.Pane;
import app.foodapp.model.recipe.Recipe;
import app.foodapp.model.recipe.RecipeInformation;
import com.google.gson.internal.bind.util.ISO8601Utils;

import java.io.*;
import java.util.*;

public class Favorite {
    public static String username;
    private List<Recipe> listOfRecipe;
    private int choice;
    private final int USERNAME = 0;
    private BufferedReader reader;

    public Favorite() {
        listOfRecipe = new ArrayList<>();
    }


    public void launch() throws IOException {
        if (this.isEmpty()) {
            System.out.println("\n" + "⚠ Sorry favorite list is empty" + "\n");
            Pane.setNextNodeNumber("MAIN_MENU"); // If is empty user cannot go to the other node because it has no sense
        }
        else {
            displayFavoriteList();
            System.out.println(askToNextNode());
            choiceNumberRecovered();
            changeCurrentNode();
        }
    }

    public String askToNextNode() {
        return "What do you want to do ?" + "\n" +
                "\t1. Menu" + "\n" +
                "\t2. Get a recipe details" + "\n" +
                "\t3. BACK";
    }

    public void choiceNumberRecovered() {
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
    }

    public void changeCurrentNode() {
        switch (choice) {
            case 1 -> Pane.setNextNodeNumber("MAIN_MENU");
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
                String result = tabOfRow[0];
                for (int i = 0; i < result.length(); i++) {
                    tab = result.split(",");
                }
            }
            for (int i = 1; i < tab.length; i++) {
                RecipeInformation test = new RecipeInformation(tab[i]);
                System.out.println(test.displaySimple());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean removeFromFavorite(Recipe recipe) throws IOException {
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
                        System.out.println("Recipe isn't in favorite\n");
                        return false;
                    }
                }
            }
            System.out.println(value);
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


    public boolean isEmpty() throws IOException {
        reader = new BufferedReader(new FileReader("favorite.txt"));
        String line;
        String[] tabOfRow;
        String[] tab;
        while ((line = reader.readLine()) != null) {
            tabOfRow = line.split(";");
            String s = tabOfRow[0];
            tab = s.split(",");
            if(tab[USERNAME].equals(username) && tab[1].equals("none")) {
                return true;
            }
        }
        return false;
    }

    public boolean addToFavorite(Recipe recipe) throws IOException {
        String value = "";
        reader = new BufferedReader(new FileReader("favorite.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            List<String> myList = new ArrayList<String>(Arrays.asList(line.split(",")));
            if(myList.get(1).equals("none"))
                myList.remove("none");
            if(myList.get(USERNAME).equals(username)) {
                for(String m : myList) {
                    if (recipe.getId().equals(m)) {
                        System.out.println("Recipe already in the favorite\n");
                        return false;
                    }
                }

                myList.add(recipe.getId() + ",");
            }
            value += myList + "\n";
        }
        BufferedWriter fw = new BufferedWriter(new FileWriter("favorite.txt"));
        value = value.replace(" ", "");
        value = value.replace("[", "");
        value = value.replace("]", "");
        fw.write(value);
        fw.close();
        return true;
    }
}