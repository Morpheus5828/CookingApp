package app.foodapp.model.node;

import app.foodapp.model.alert.AlertFound;
import app.foodapp.model.node.login.LoginPage;
import app.foodapp.model.node.login.SignUp;
import app.foodapp.model.recipe.Recipe;

import java.io.IOException;
import java.util.Scanner;

public class Pane {
    private MainMenu mainMenu;
    private GetRecipeByIngredient getRecipeByIngredient;
    private static Favorite favorite = new Favorite();
    private MeasureSystem measureSystem;
    private RecipeDetails recipeDetails;
    public static boolean checkStatusCode = true;
    public static String currentNode = NodeName.MAIN_MENU.name();
    private static String backNode = null;
    private SignUp signUp;
    private LoginPage login;
    private int userChoice;

    public Pane() throws IOException {
        this.mainMenu = new MainMenu();
        this.signUp = new SignUp();
        this.login = new LoginPage();
        this.getRecipeByIngredient = new GetRecipeByIngredient();
        this.measureSystem = new MeasureSystem();
        this.recipeDetails = new RecipeDetails();
        launch();
    }

    public void choice() throws IOException {
        signUp.launch();
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

    public void launch() throws IOException {
        System.out.println(displayWelcomeText());
        scanUserChoice();
        userChoiceTreatment();
    }

    public String displayWelcomeText() {
        return "Hey welcome to CookingAppCli ! \n " +
                " Did you have an account ? \n" +
                "\t1. Yes \n" +
                "\t2. No \n";
    }

    public void scanUserChoice() {
        Scanner sc = new Scanner(System.in);
        userChoice = sc.nextInt();
    }

    public void userChoiceTreatment() throws IOException {
        switch (userChoice) {
            case 1 -> login.checkUserLogin();
            case 2 -> signUp.launch();
            default -> {
                AlertFound.invalidNode();
                displayWelcomeText();
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
