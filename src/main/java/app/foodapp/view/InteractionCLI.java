package app.foodapp.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class InteractionCLI {

    private ArrayList<String> listOfIngredient;
    private Scanner terminalScanner;

    public InteractionCLI() {
        this.listOfIngredient = new ArrayList<>();
        System.out.println("Hello World !\n");

        askMainChoice();
        //askIngredient();

        System.out.println(this.listOfIngredient);
    }

    public void askMainChoice() {
        System.out.println("What do you want to see ?\n");
        terminalScanner = new Scanner(System.in);
        System.out.println("1\tRecipe research");
        System.out.println("2\tFavorite list");
        System.out.println("3\tSystem measures");
        int answer = terminalScanner.nextInt();
        System.out.println(answer);
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
    }




}
