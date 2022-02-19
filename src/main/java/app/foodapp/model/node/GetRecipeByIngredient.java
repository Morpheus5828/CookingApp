package app.foodapp.model.node;

import app.foodapp.model.dataManipulation.recipe.RecipeInformation;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GetRecipeByIngredient extends Node{
    private final ArrayList<String> listOfIngredient;
    private boolean addIngredient = true;
    private RecipeInformation recipeInformation;

    public GetRecipeByIngredient() {
        super();
        addNodes();
        this.listOfIngredient = new ArrayList<>();
    }

    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.put(0, NodeName.WELCOME);
        this.neighborsList.put(2, NodeName.FAVORITE);
        this.neighborsList.put(3, NodeName.MEASURE_SYSTEM);
        this.neighborsList.put(4, NodeName.RECIPE_DETAILS);

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
            System.out.print("-> ");
            Scanner sc = new Scanner(System.in);
            String ingredient = sc.nextLine();

            if(Objects.equals(ingredient, "end"))
                addIngredient = false;
            else
                this.listOfIngredient.add(ingredient);
        }
    }

    public void askToAddRecipeFavorite() {

        System.out.print(
             "1. Do you wish to add a recipe to your favorite ? " + "\n" +
             "2. Get a recipe details ? " + "\n\n\t" + 
             "-> Please type choice number: "
        );

        Scanner choiceRecover = new Scanner(System.in);
        int addFavoriteQuestion = choiceRecover.nextInt();
        //Configuration of favorite node way
        if(addFavoriteQuestion == 1) {
            System.out.print("Enter menu number : ");
            Scanner numberRecover = new Scanner(System.in);
            int choiceNumber = numberRecover.nextInt();
            Pane.addRecipeToFavoriteList(RecipeInformation.listOfRecipe.get(choiceNumber));
            Pane.setNextNodeNumber("FAVORITE");
        }

        // Configuration of RecipeDetails way
        else if(addFavoriteQuestion == 2) {
            System.out.print("Enter menu number : ");
            Scanner numberRecover = new Scanner(System.in);
            int choiceNumber = numberRecover.nextInt();
            RecipeDetails.recipe = RecipeInformation.listOfRecipe.get(choiceNumber);

            Pane.checkStatusCode = false;
            //Pane.setNextNodeNumber("RECIPE_DETAILS");
        }
        
        else {
            // TODO configuration of BACK possibility
        }
    }

    public void sendRequest() {
        recipeInformation = new RecipeInformation(this.listOfIngredient);
        recipeInformation.display();
    }

}
