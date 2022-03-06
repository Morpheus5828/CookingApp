package app.foodapp.model.node;

import app.foodapp.model.recipe.RecipeInformation;
import app.foodapp.model.alert.AlertFound;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class GetRecipeByIngredient {
    private final ArrayList<String> listOfIngredient;
    public static boolean addIngredient = true;
    private RecipeInformation recipeInformation;
    private int choiceNumber;


    public GetRecipeByIngredient() {this.listOfIngredient = new ArrayList<>();}

    public void launch() {
        System.out.println(askToEnterIngredients());
        enterIngredient();
        System.out.println(sendRequest());
        askToChangeCurrentNode();
    }

    public String askToEnterIngredients() {
        return "\n Please enter ingredient(s) and type 'end' when you're finished \n" +
               "\t Type ingredient(s) : \n";
    }

    public void enterIngredient() {
        while(addIngredient) {
            System.out.print("\t-> ");
            Scanner sc = new Scanner(System.in);
            String ingredient = sc.nextLine();

            if(Objects.equals(ingredient, "end"))
                addIngredient = false;
            else
                this.listOfIngredient.add(ingredient);
        }
    }

    public String sendRequest() {
        recipeInformation = new RecipeInformation(this.listOfIngredient);
        return recipeInformation.display();
    }

    public void askToChangeCurrentNode() {
        try {
            System.out.print(askToNextNodePossibility());
            choiceNumberRecovered();
            changeCurrentNode();
        } catch (InputMismatchException e) {
            AlertFound.invalidCharacter();
            askToChangeCurrentNode();
        }

    }

    public String askToNextNodePossibility() {
        return "\t1. Do you wish to add a recipe to your favorite ? " + "\n" +
               "\t2. Get a recipe details ? " + "\n" +
               "\t3. BACK" + "\n\n\t" +
               "\t--> Please type choice number: ";
    }

    public void choiceNumberRecovered() {
        Scanner sc = new Scanner(System.in);
        choiceNumber =  sc.nextInt();
    }

    public void changeCurrentNode() {
        switch (choiceNumber) {
            case 1 -> {
                System.out.print("Enter menu number : ");
                Scanner numberRecover = new Scanner(System.in);
                int choiceNumber = numberRecover.nextInt();
                Pane.addRecipeToFavoriteList(RecipeInformation.listOfRecipe.get(choiceNumber));
                Pane.setNextNodeNumber("FAVORITE");
            }
            case 2 -> {
                System.out.print("Enter menu number : ");
                Scanner numberRecover2 = new Scanner(System.in);
                int choiceNumber2 = numberRecover2.nextInt();
                System.out.println("\n" + "you choose : " + RecipeInformation.listOfRecipe.get(choiceNumber2).getId());
                RecipeDetails.recipe = RecipeInformation.listOfRecipe.get(choiceNumber2);
                Pane.setNextNodeNumber("RECIPE_DETAILS");
            }
            case 3 -> {
                Pane.back();
                GetRecipeByIngredient.addIngredient = true;
            }
            default -> {
                AlertFound.invalidCharacter();
                askToChangeCurrentNode();
            }
        }
    }
}
