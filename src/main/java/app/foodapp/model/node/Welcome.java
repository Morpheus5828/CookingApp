package app.foodapp.model.node;

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



}
