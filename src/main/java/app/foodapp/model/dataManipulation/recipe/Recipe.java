package app.foodapp.model.dataManipulation.recipe;

import app.foodapp.model.dataManipulation.MeasureSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Recipe {
    private final int id;
    private final String image;
    private final String title;
    private final int servings;
    private final int cookingTime;

    private final RecipeInformation recipeInformation;

    public Recipe (final int id, final String image, final String title, final int servings, final int cookingTime) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.servings = servings;
        this.cookingTime = cookingTime;

        recipeInformation = new RecipeInformation(String.valueOf(id));
    }

    public int getId() {
        return this.id;
    }

    public String getImage() {
        return this.image;
    }

    public String getTitle() {
        return this.title;
    }

    public int getServings() {
        return this.servings;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    /*
    Returns a map of the recipe's steps. Keys are the step's number, and values are the step's description.
     */
    public Map<Integer, String> getSteps() {
        return this.recipeInformation.getStepRecipeInformation();
    }

    /*
    Returns a list of the recipe's ingredients.
    The amount of each ingredient is calculated according to the measure system saved.
     */
    public ArrayList<String> getIngredientsList() {
        ArrayList<String> ingredientsList = new ArrayList<>();
        ArrayList<Map<String, String>> ingredientsInformation = this.recipeInformation.getIngredientsInformation();

        for (int index = 0; index < ingredientsInformation.size(); index++) {
            Map<String, String> information = ingredientsInformation.get(index);
            String unit = information.get("unit");

            //For special units like pinches or servings, it's better to use the basic ingredient's description.
            if (unit.equals("pinch") || unit.equals("pinches") || unit.equals("serving") || unit.equals("servings") || unit.equals("")) {
                ingredientsList.add(information.get("fullDescription"));

            } else {
                try {
                    String measureSystem = MeasureSystem.getMeasureSystem().toString();

                    //Building the ingredient's description according to the measure system.
                    String ingredient = information.get(measureSystem + "Amount")
                            + " "
                            + information.get(measureSystem + "Unit")
                            + " "
                            + information.get("description");
                    ingredientsList.add(ingredient);

                } catch (IOException exception) {
                    //If measure system isn't found, it adds the basic ingredient's description without any conversion.
                    ingredientsList.add(information.get("fullDescription"));
                }
            }
        }
        return ingredientsList;
    }

    /*
    This function returns the Spoonacular score of the recipe.
     */
    public double getScore() {
        return this.recipeInformation.getScore();
    }
}
