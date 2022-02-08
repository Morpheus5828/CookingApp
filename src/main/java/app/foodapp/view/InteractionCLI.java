package app.foodapp.view;

import app.foodapp.model.dataManipulation.recipe.RecipeInformation;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class InteractionCLI {

    private final ArrayList<String> listOfIngredient;
    private RecipeInformation recipeInformation;
    private final int askMainChoiceReturnValue;
    private Scanner terminalScanner;

    public InteractionCLI() {
        this.listOfIngredient = new ArrayList<String>();
        System.out.println("Hello World !\n");

        this.askMainChoiceReturnValue = askMainChoice();
        switch (this.askMainChoiceReturnValue) {
            case 1: askIngredient();

        }
        askQuestionToGetDetails();


    }

    public int askMainChoice() {
        System.out.println("What do you want to see ?\n");
        terminalScanner = new Scanner(System.in);
        System.out.println("1\tRecipe research");
        System.out.println("2\tFavorite list");
        System.out.println("3\tSystem measures");
        return terminalScanner.nextInt();
    }

    public void askIngredient() {
        System.out.println("Enter ingredient(s):");
        boolean addIngredient = true;
        while(addIngredient) {
            terminalScanner = new Scanner(System.in);
            String ingredient = terminalScanner.nextLine();
            if(Objects.equals(ingredient, "fin"))
                addIngredient = false;
            else
                this.listOfIngredient.add(ingredient);
        }
        sendRequest();
    }

    public void askDetailsOfRecipe() {
        System.out.println("Type recipe's number");
        terminalScanner = new Scanner(System.in);
        int number = terminalScanner.nextInt();
        //recipeInformation.getListOfRecipe().get(number).bigDisplay();
    }

    public void askQuestionToGetDetails() {
        System.out.println("More details about a recipe ? \n (Yes or No)");
        terminalScanner = new Scanner(System.in);
        String answer = terminalScanner.nextLine();
        if(answer.equals("Yes"))
            askDetailsOfRecipe();

    }

    public void sendRequest() {
        recipeInformation = new RecipeInformation(this.listOfIngredient);
        recipeInformation.simpleDisplay();
    }






}
