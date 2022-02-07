package app.foodapp.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class InteractionCLI {

    private ArrayList<String> listOfIngredient;

    public InteractionCLI() {
        this.listOfIngredient = new ArrayList<>();
        System.out.println("Hello World");
        System.out.println("Enter ingredient(s):");

        askIngredient();

        System.out.println(this.listOfIngredient);
    }

    public void askIngredient() {
        boolean addIngredient = true;
        while(addIngredient) {
            Scanner sc = new Scanner(System.in);
            String ingredient = sc.nextLine();
            if(Objects.equals(ingredient, "fin"))
                addIngredient = false;
            else
                this.listOfIngredient.add(ingredient);
        }
    }




}
