package app.foodapp.view;

import app.foodapp.model.dataManipulation.recipe.Recipe;
import app.foodapp.model.dataManipulation.recipe.RecipeInformation;
import app.foodapp.model.node.Pane;
import app.foodapp.model.node.Welcome;

import javax.management.InstanceAlreadyExistsException;
import java.net.InetAddress;

public class InteractionCLI {
    public InteractionCLI()  {
       // TODO Check internet connexion before begin
       //Pane pane = new Pane();//
        Recipe recipe = new Recipe(
                639303,
                "https://spoonacular.com/recipeImages/673463-312x231.jpg",
                "Slow Cooker Apple Pork Tenderloin",
                4,
                20
                );

        recipe.displayDetailsCharacteristics();


    }


}
