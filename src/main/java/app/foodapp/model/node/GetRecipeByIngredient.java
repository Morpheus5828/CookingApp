package app.foodapp.model.node;

import app.foodapp.model.dataManipulation.recipe.RecipeInformation;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GetRecipeByIngredient extends Node{
    private final ArrayList<String> listOfIngredient;
    private boolean addIngredient = true;
    private RecipeInformation recipeInformation;

    public GetRecipeByIngredient() {
        super();
        addNodes();
        this.listOfIngredient = new ArrayList<>();
    }

    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.put(0, NodeName.WELCOME);
        this.neighborsList.put(2, NodeName.FAVORITE);
        this.neighborsList.put(3, NodeName.MEASURE_SYSTEM);
        this.neighborsList.put(4, NodeName.RECIPE_DETAILS);

    }

    public void launch()  {
        display();
        System.out.println("Please hold on ..." + "\n");
        sendRequest();
    }

    public void display() {
        System.out.println("\n" + "Please ingredient(s) and type 'end' when you're finished" + "\n");
        System.out.println("\t Enter ingredient(s) : ");
        while(addIngredient) {
            System.out.print("-> ");
            Scanner sc = new Scanner(System.in);
            String ingredient = sc.nextLine();

            if(Objects.equals(ingredient, "end"))
                addIngredient = false;
            else
                this.listOfIngredient.add(ingredient);
        }

    }


    public void sendRequest() {
        recipeInformation = new RecipeInformation(this.listOfIngredient);
        recipeInformation.display();
    }

}
