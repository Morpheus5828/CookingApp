package app.foodapp.model.node;

import app.foodapp.model.alert.AlertFound;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainMenu {
    private int choice;

    public MainMenu() {}

    public void launch() {
        try {
            System.out.println(askFirstChoices());
            System.out.print(askChoiceNumber());
            choice = choiceNumberRecovered();
            changeCurrentNode();
        } catch (InputMismatchException e) {
            AlertFound.invalidCharacter();
        }
    }

    public String askFirstChoices() {
        return "\nWhat do you want to do ?\n" +
                "\n\t 1. Get recipe by ingredients \n" +
                "\t 2. Favorite list \n" +
                "\t 3. System Measure \n" +
                "\t 4. Close CookingApp \n";
    }

    public String askChoiceNumber() {
        return "\t\t--> Tap number: ";
    }

    public int choiceNumberRecovered() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public void changeCurrentNode() throws ExceptionInInitializerError {
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
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }
}