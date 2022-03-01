package app.foodapp.model.node;

import app.foodapp.model.alert.AlertFound;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private int choice;

    public Menu() {
    }

    public void launch() {
        askFirstUserChoices();
    }


    public void askFirstUserChoices() {
        try {
            System.out.println(
                "\t 1. Get recipe by ingredients" + "\n" +
                "\t 2. Favorite list" + "\n" +
                "\t 3. System Measure" + "\n" +
                "\t 4. Close CookingApp" + "\n"
            );
            System.out.print( "\t\t--> Tap number: ");
            Scanner sc = new Scanner(System.in);
            this.choice = sc.nextInt();

            switch (this.choice) {
                case 1 -> {
                    GetRecipeByIngredient.addIngredient = true;
                    Pane.setNextNodeNumber("GET_RECIPE_BY_INGREDIENT");
                }
                case 2 -> Pane.setNextNodeNumber("FAVORITE");
                case 3 -> Pane.setNextNodeNumber("MEASURE_SYSTEM");
                case 4 -> Pane.checkStatusCode = false;
                default -> AlertFound.invalidNode();

            }
        } catch (InputMismatchException e) {
            AlertFound.invalidCharacter();
        }
    }


}
