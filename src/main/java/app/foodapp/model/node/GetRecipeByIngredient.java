package app.foodapp.model.node;

import app.foodapp.model.dataManipulation.recipe.RecipeInformation;
import app.foodapp.model.alert.AlertFound;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GetRecipeByIngredient{
    private final ArrayList<String> listOfIngredient;
    public static boolean addIngredient = true;
    private RecipeInformation recipeInformation;

    public GetRecipeByIngredient() {
        super();
        this.listOfIngredient = new ArrayList<>();
    }

    public void launch()  {
        askToEnterIngredients();
        System.out.println("Please hold on ..." + "\n");
        sendRequest();
        askToAddRecipeFavorite();
    }

    public void askToEnterIngredients() {
        System.out.println("\n" + "Please enter ingredient(s) and type 'end' when you're finished" + "\n");
        System.out.println("\t Type ingredient(s) : ");
        while(addIngredient) {
            System.out.print("--> ");
            Scanner sc = new Scanner(System.in);
            String ingredient = sc.nextLine();

            if(Objects.equals(ingredient, "end"))
                addIngredient = false;
            else
                this.listOfIngredient.add(ingredient);
        }

    }

    public void askToAddRecipeFavorite() {
        try {
            System.out.print(
                "1. Do you wish to add a recipe to your favorite ? " + "\n" +
                "2. Get a recipe details ? " + "\n" +
                "3. BACK" + "\n\n\t" +
                "-> Please type choice number: "
            );

            Scanner choiceRecover = new Scanner(System.in);
            int addFavoriteQuestion = choiceRecover.nextInt();
            switch (addFavoriteQuestion) {
                case 1:
                    System.out.print("Enter menu number : ");
                    Scanner numberRecover = new Scanner(System.in);
                    int choiceNumber = numberRecover.nextInt();
                    Pane.addRecipeToFavoriteList(RecipeInformation.listOfRecipe.get(choiceNumber));
                    Pane.setNextNodeNumber("FAVORITE");
                    break;
                case 2:
                    System.out.print("Enter menu number : ");
                    Scanner numberRecover2 = new Scanner(System.in);
                    int choiceNumber2 = numberRecover2.nextInt();
                    System.out.println("\n" + "you choose : " + RecipeInformation.listOfRecipe.get(choiceNumber2).getId());
                    RecipeDetails.recipe = RecipeInformation.listOfRecipe.get(choiceNumber2);
                    Pane.setNextNodeNumber("RECIPE_DETAILS");
                    break;
                case 3:
                    Pane.back();
                    GetRecipeByIngredient.addIngredient = true;
                    break;
                default:
                    AlertFound.invalidCharacter();
                    askToAddRecipeFavorite();
                    break;
            }
        } catch (Exception e) {
            AlertFound.invalidCharacter();
            askToAddRecipeFavorite();
        }


    }

    public void sendRequest() {
        recipeInformation = new RecipeInformation(this.listOfIngredient);
        recipeInformation.display();
    }

}
