package app.foodapp.model.node;

import app.foodapp.model.recipe.Recipe;

public class Pane {
    private MainMenu mainMenu;
    private GetRecipeByIngredient getRecipeByIngredient;
    private static Favorite favorite = new Favorite();
    private MeasureSystem measureSystem;
    private RecipeDetails recipeDetails;
    public static boolean checkStatusCode = true;
    public static String currentNode = NodeName.MAIN_MENU.name();
    private static String backNode = null;

    public Pane() {
        this.mainMenu = new MainMenu();
        this.getRecipeByIngredient = new GetRecipeByIngredient();
        this.measureSystem = new MeasureSystem();
        this.recipeDetails = new RecipeDetails();
        choice();
    }

    public void choice() {
        while(checkStatusCode) {
            switch (currentNode) {
                case "MAIN_MENU" -> mainMenu.launch();
                case "GET_RECIPE_BY_INGREDIENT" -> getRecipeByIngredient.launch();
                case "FAVORITE" -> favorite.launch();
                case "RECIPE_DETAILS" -> recipeDetails.launch();
                case "MEASURE_SYSTEM" -> measureSystem.launch();
                case "CLOSE_APP" -> Pane.checkStatusCode = false;
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

    public static void back() {
        currentNode = backNode;
    }

}
