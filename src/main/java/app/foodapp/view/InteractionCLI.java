package app.foodapp.view;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import app.foodapp.model.dataManipulation.recipe.RecipeInformation;
import app.foodapp.model.node.GetRecipeByIngredient;
import app.foodapp.model.node.Pane;
import app.foodapp.model.node.RecipeDetails;

import java.util.ArrayList;

public class InteractionCLI {
    public InteractionCLI()  {
       // TODO Check internet connexion before begin
       //new Pane();
       Recipe recipe = new Recipe(
            "639303",
            "https://spoonacular.com/recipeImages/673463-312x231.jpg",
            "Slow Cooker Apple Pork Tenderloin",
            4,
            20
            );

        RecipeInformation test = new RecipeInformation(recipe.getId());
        //System.out.println(test.getStepRecipeInformation());

        GetRecipeByIngredient get = new GetRecipeByIngredient();
        get.launch();

    }


}
