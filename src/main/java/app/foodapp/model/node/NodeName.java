package app.foodapp.model.node;

import app.foodapp.model.dataManipulation.recipe.RecipeInformation;

public enum NodeName {
    WELCOME(0),
    GET_RECIPE_BY_INGREDIENT(1),
    FAVORITE(2),
    MEASURE_SYSTEM(3),
    RECIPE_DETAILS(4);


     NodeName(final int index) {}


}
