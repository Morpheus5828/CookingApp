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

    public Recipe (final int id, final String image, final String title, final int servings, final int cookingTime) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.servings = servings;
        this.cookingTime = cookingTime;
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

    public Map<Integer, String> getSteps() {
        RecipeInformation recipeInformation = new RecipeInformation(String.valueOf(this.id));
        return recipeInformation.getStepRecipeInformation();
    }

    public ArrayList<String> getIngredientsList() {
        ArrayList<String> ingredientsList = new ArrayList<>();
        RecipeInformation recipeInformation = new RecipeInformation(String.valueOf(this.id));
        ArrayList<Map<String, String>> ingredientsInformation = recipeInformation.getIngredientsInformation();

        for (int index = 0; index < ingredientsInformation.size(); index++) {
            Map<String, String> information = ingredientsInformation.get(index);
            String unit = information.get("unit");

            if (unit.equals("pinch") || unit.equals("pinches") || unit.equals("serving") || unit.equals("servings") || unit.equals("")) {
                ingredientsList.add(information.get("fullDescription"));
            } else {
                try {
                    String measureSystem = MeasureSystem.getMeasureSystem().toString();
                    String ingredient = information.get(measureSystem + "Amount")
                            + " "
                            + information.get(measureSystem + "Unit")
                            + " "
                            + information.get("description");
                    ingredientsList.add(ingredient);
                } catch (IOException exception) {
                    ingredientsList.add(information.get("fullDescription"));
                }
            }
        }
        return ingredientsList;
    }

    public float getScore() {
        return 0;
    }
}
