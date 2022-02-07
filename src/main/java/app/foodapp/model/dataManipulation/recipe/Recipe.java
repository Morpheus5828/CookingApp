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

    public ArrayList<String> getIngredientsList() throws IOException {
        ArrayList<String> ingredientsList = new ArrayList<>();
        RecipeInformation recipeInformation = new RecipeInformation(String.valueOf(this.id));

        ArrayList<Map<String, String>> ingredientsInformation = recipeInformation.getIngredientsInformation();
        MeasureSystem measureSystem = MeasureSystem.getMeasureSystem();

        for (int index = 0; index < ingredientsInformation.size(); index++) {
            Map<String, String> information = ingredientsInformation.get(index);
            String unit = information.get("unit");

            if (unit.equals("pinch") || unit.equals("pinches") || unit.equals("serving") || unit.equals("serving") || unit == null) {
                ingredientsList.add(information.get("fullDescription"));
            } else {
                String ingredient = "";
                switch (measureSystem) {
                    case US:
                        ingredient = ingredient + information.get("usAmount") + " " + information.get("usUnit") + " " + information.get("description");
                        break;
                    case METRIC:
                        ingredient = ingredient + information.get("metricAmount") + " " + information.get("metricUnit") + " " + information.get("description");
                        break;
                }
                ingredientsList.add(ingredient);
            }
        }

        return new ArrayList<>();
    }

    public float getScore() {
        return 0;
    }
}
