package app.foodapp.model.node;

import app.foodapp.model.dataManipulation.recipe.Recipe;

public class Pane {
    private Welcome welcome;
    private GetRecipeByIngredient getRecipeByIngredient;
    private static Favorite favorite = new Favorite();
    private MeasureSystem measureSystem;
    private RecipeDetails recipeDetails;
    public static boolean checkStatusCode = true;
    public static String currentNode = NodeName.WELCOME.name();
    private static String backNode = null;

    public Pane(){
        this.welcome = new Welcome();
        this.getRecipeByIngredient = new GetRecipeByIngredient();
        this.measureSystem = new MeasureSystem();
        this.recipeDetails = new RecipeDetails();
        choice();
    }


    public void choice() {
        while(checkStatusCode) {
            switch (currentNode) {
                case "WELCOME" -> welcome.launch();
                case "GET_RECIPE_BY_INGREDIENT" -> getRecipeByIngredient.launch();
                case "FAVORITE" -> favorite.launch();
                case "RECIPE_DETAILS" -> recipeDetails.launch();
                /*case "MEASURE_SYSTEM" ->*/

            }
        }
    }


    public static void addRecipeToFavoriteList(Recipe recipe) {
        favorite.addToFavorite(recipe);
    }

    public static void setNextNodeNumber(String nextNode) {
        backNode = currentNode;
        currentNode = nextNode;

    }

}
