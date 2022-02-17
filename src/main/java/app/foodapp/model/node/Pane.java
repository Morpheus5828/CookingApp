package app.foodapp.model.node;

import java.util.ArrayList;

public class Pane {
    private Welcome welcome;
    private GetRecipeByIngredient getRecipeByIngredient;
    private Favorite favorite;
    private MeasureSystem measureSystem;
    private RecipeDetails recipeDetails;
    private static boolean checkStatusCode = true;
    private static String currentNode = NodeName.WELCOME.name();
    private static String backNode = null;

    public Pane() {
        display();
        this.welcome = new Welcome();
        this.getRecipeByIngredient = new GetRecipeByIngredient();
        this.favorite = new Favorite();
        this.measureSystem = new MeasureSystem();
        this.recipeDetails = new RecipeDetails();

        while (checkStatusCode) {
            switch (currentNode) {
                case "WELCOME" -> welcome.launch();
                case "GET_RECIPE_BY_INGREDIENT" -> getRecipeByIngredient.launch();
                /*case "FAVORITE" ->
                case "MEASURE_SYSTEM" ->
                case "RECIPE_DETAILS" ->*/
            }
        }

        /*this.instance = new GetRecipeByIngredient();
        this.favorite = new Favorite();
        this.measureSystem = new MeasureSystem();
        this.recipeDetails = new RecipeDetails();*/


    }

    public static String getCurrentNodeNumber() {
        return currentNode;
    }

    public static void setNextNodeNumber(String nextNode) {
        backNode = currentNode;
        currentNode = nextNode;
    }

    public void display() {
        System.out.println("Current node is : " + currentNode + " and the back node is : " + backNode);
    }

}
