package app.foodapp.view;

import app.foodapp.controller.checkInternetConnexion.InternetConnexion;
import app.foodapp.model.dataManipulation.recipe.Recipe;
import app.foodapp.model.dataManipulation.recipe.RecipeInformation;
import app.foodapp.model.node.GetRecipeByIngredient;
import app.foodapp.model.node.Pane;
import app.foodapp.model.node.RecipeDetails;

import java.io.IOException;
import java.util.ArrayList;

public class InteractionCLI {
    public InteractionCLI() throws IOException {
        if(InternetConnexion.checkStatus())
            new Pane();
    }


}
