package app.foodapp.view;

import app.foodapp.controller.apiHttpRequest.SearchRecipesByIngredients;
import app.foodapp.model.dataManipulation.recipe.RecipeInformation;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class InteractionCLI {

    private final ArrayList<String> listOfIngredient;
    private final int askMainChoiceReturnValue;
    private Scanner terminalScanner;

    public InteractionCLI() {
        this.listOfIngredient = new ArrayList<String>();
        System.out.println("Hello World !\n");

        this.askMainChoiceReturnValue = askMainChoice();
        switch (this.askMainChoiceReturnValue) {
            case 1: askIngredient();

        }
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

    public void sendRequest() {
        RecipeInformation test = new RecipeInformation(this.listOfIngredient);
        test.display();
    }






}
