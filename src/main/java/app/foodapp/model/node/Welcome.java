package app.foodapp.model.node;

import app.foodapp.view.alert.AlertFound;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Welcome extends Node{
    private int choice;

    public Welcome() {
        super();
        addNodes();
    }

    public void launch() {
        askFirstUserChoices();
    }

    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.put(1, NodeName.GET_RECIPE_BY_INGREDIENT);
        this.neighborsList.put(2, NodeName.FAVORITE);
        this.neighborsList.put(3, NodeName.MEASURE_SYSTEM);

    }

    public void askFirstUserChoices() {
        try {
            System.out.println(
                    "\t 1. Get recipe by ingredients" + "\n" +
                            "\t 2. Favorite list" + "\n" +
                            "\t 3. System Measure" + "\n"
            );
            System.out.print( "\t\t--> Tap number: ");
            Scanner sc = new Scanner(System.in);
            this.choice = sc.nextInt();
            this.changeCurrentStateNode();
        } catch (InputMismatchException e) {
            AlertFound.invalidCharacter();
        }
    }

    public void changeCurrentStateNode() {
        // Check if choicefields is accepted or not
        try {
            if(this.neighborsList.get(choice) == NodeName.getNodeName(choice)) {
                // We change current node
                Pane.setNextNodeNumber(this.neighborsList.get(choice).name());
            }

            else
                // Node choice's not accepted
                System.out.println("Node doesn't exist");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
