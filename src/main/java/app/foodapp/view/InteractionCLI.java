package app.foodapp.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Scanner;

public class InteractionCLI implements KeyListener {

    private ArrayList<String> listOfIngredient;

    public InteractionCLI() {
        this.listOfIngredient = new ArrayList<>();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Hello World");
        System.out.println("Enter ingredient(s):");

        while(e.getKeyCode() != KeyEvent.VK_ENTER) {
            Scanner sc = new Scanner(System.in);
            listOfIngredient.add(sc.nextLine());
        }

    }


    public ArrayList<String> getListOfIngredient() {
        return this.listOfIngredient;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
