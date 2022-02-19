package app.foodapp.view;

import app.foodapp.model.dataManipulation.recipe.Recipe;

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

        recipe.getIngredients();


    }


}
