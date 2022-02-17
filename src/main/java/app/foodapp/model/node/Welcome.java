package app.foodapp.model.node;

import java.util.Arrays;
import java.util.Scanner;

public class Welcome extends Node{
    private int choice;
    private NodeName nodeName;

    public Welcome() {
        super();
        addNodes();
        display();
        this.getNextInstance(this.choice);

    }

    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.add(NodeName.GET_RECIPE_BY_INGREDIENT);
        this.neighborsList.add(NodeName.FAVORITE);
        this.neighborsList.add(NodeName.MEASURE_SYSTEM);

    }

    public void display() {
        System.out.println(
                "\t 1. Get recipe by ingredients" + "\n" +
                "\t 2. Favorite list" + "\n" +
                "\t 3. System Measure" + "\n"
        );
        System.out.print( "\t\t--> Tap number: ");
        Scanner sc = new Scanner(System.in);
        this.choice = sc.nextInt();
        this.changeCurrentStateNode();
    }

    public void getNextInstance(int index) {
        switch (index) {
            case 0: new Welcome();
            case 1: new GetRecipeByIngredient();
            case 2: new Favorite();
            case 3: new MeasureSystem();
            case 4: new RecipeDetails();
        }
    }

    public void changeCurrentStateNode() {
        System.out.println(this.neighborsList.get(choice));
        System.out.println(NodeName.getNodeName(choice));
        try {
            if(this.neighborsList.get(choice) == NodeName.getNodeName(choice))
                System.out.println("good");
            else
                System.out.println("not yet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
