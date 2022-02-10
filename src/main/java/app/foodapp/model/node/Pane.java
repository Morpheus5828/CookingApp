package app.foodapp.model.node;

import java.util.ArrayList;

public class Pane {
    private ArrayList<Node> nodeList;
    private Welcome welcome;
    private GetRecipeByIngredient instance;
    private Favorite favorite;
    private MeasureSystem measureSystem;
    private RecipeDetails recipeDetails;

    public Pane() {
        this.welcome = new Welcome();
        this.instance = new GetRecipeByIngredient();
        this.favorite = new Favorite();
        this.measureSystem = new MeasureSystem();
        this.recipeDetails = new RecipeDetails();

        this.nodeList = new ArrayList<>();
        this.nodeList.add(welcome);
        this.nodeList.add(instance);
        this.nodeList.add(favorite);
        this.nodeList.add(measureSystem);
        this.nodeList.add(recipeDetails);



    }
}
