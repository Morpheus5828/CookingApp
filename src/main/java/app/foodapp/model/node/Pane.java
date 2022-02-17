package app.foodapp.model.node;

import java.util.ArrayList;

public class Pane {
    private ArrayList<Node> nodeList;
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
        while (checkStatusCode) {
            switch (currentNode) {
                case "GET_RECIPE_BY_INGREDIENT" -> new GetRecipeByIngredient();
                case "WELCOME" -> new Welcome();
                case "FAVORITE" -> new Favorite();
                case "MEASURE_SYSTEM" -> new MeasureSystem();
                case "RECIPE_DETAILS" -> new RecipeDetails();
            }
        }

        /*this.instance = new GetRecipeByIngredient();
        this.favorite = new Favorite();
        this.measureSystem = new MeasureSystem();
        this.recipeDetails = new RecipeDetails();*/



        /*this.nodeList = new ArrayList<>();
        this.nodeList.add(welcome);
        this.nodeList.add(instance);
        this.nodeList.add(favorite);
        this.nodeList.add(measureSystem);
        this.nodeList.add(recipeDetails);*/



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
