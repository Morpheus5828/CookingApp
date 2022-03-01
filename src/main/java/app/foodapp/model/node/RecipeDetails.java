package app.foodapp.model.node;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import app.foodapp.model.alert.AlertFound;

import java.util.Scanner;

public class RecipeDetails extends Node {
    public static Recipe recipe;
    public RecipeDetails() {
        super();
    }

    public void launch() {
        recipe.displayDetailsCharacteristics();
        askToAddRecipeFavorite();
    }

    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.put(0, NodeName.WELCOME);
        this.neighborsList.put(1, NodeName.GET_RECIPE_BY_INGREDIENT);
        this.neighborsList.put(2, NodeName.FAVORITE);
        this.neighborsList.put(3, NodeName.MEASURE_SYSTEM);
        this.neighborsList.put(5, NodeName.CLOSE_APP);
    }

    public void askToAddRecipeFavorite() {
        try {
            System.out.print(
                    "1. Do you want to do a new request ?" + "\n" +
                    "2. Add recipe to favorite list ? " + "\n" +
                    "3. See favorite list ?" + "\n" +
                    "4. BACK" + "\n\n\t" +
                    "-> Please type choice number: "
            );

            Scanner choiceRecover = new Scanner(System.in);
            int addFavoriteQuestion = choiceRecover.nextInt();
            switch (addFavoriteQuestion) {
                case 1 -> {
                    GetRecipeByIngredient.addIngredient = true;
                    Pane.setNextNodeNumber("GET_RECIPE_BY_INGREDIENT");
                }
                case 2 -> Pane.addRecipeToFavoriteList(recipe);
                case 3 -> Pane.setNextNodeNumber("FAVORITE");
                case 4 -> Pane.back();
                default -> {
                    AlertFound.invalidCharacter();
                    askToAddRecipeFavorite();
                }
            }
        } catch (Exception e) {
            AlertFound.invalidCharacter();
            askToAddRecipeFavorite();
        }
    }
}
