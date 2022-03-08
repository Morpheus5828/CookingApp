package app.foodapp.model.node.login;

import app.foodapp.model.node.GetRecipeByIngredient;
import app.foodapp.model.node.Pane;
import app.foodapp.model.recipe.Recipe;
import app.foodapp.model.alert.AlertFound;

import java.io.IOException;
import java.util.Scanner;

public class RecipeDetails {
    private int choiceNumber;
    public static Recipe recipe;
    public RecipeDetails() {}

    public void launch() {
        try {
            System.out.print(askNextCurrentNode());
            choiceNumber();
            changeCurrentNode();
        } catch (Exception e) {
            AlertFound.invalidCharacter();
            //TODO boucler
        }

    }

    public String askNextCurrentNode() {
        return
            "1. Do you want to do a new request ?" + "\n" +
            "2. Add recipe to favorite list ? " + "\n" +
            "3. See favorite list ?" + "\n" +
            "4. BACK" + "\n\n\t" +
            "-> Please type choice number: ";
    }

    public void choiceNumber() {
        Scanner sc = new Scanner(System.in);
        choiceNumber =  sc.nextInt();
    }

    public void changeCurrentNode() throws IOException {
        switch (choiceNumber) {
            case 1 -> {
                GetRecipeByIngredient.addIngredient = true;
                Pane.setNextNodeNumber("GET_RECIPE_BY_INGREDIENT");
            }
            case 2 -> Pane.addRecipeToFavoriteList(recipe);
            case 3 -> Pane.setNextNodeNumber("FAVORITE");
            case 4 -> Pane.back();
            default -> {
                AlertFound.invalidCharacter();
            }
        }
    }

}
