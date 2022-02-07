package app.foodapp.model.dataManipulation.recipe;

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
        return new ArrayList<>();
    }

    public float getScore() {
        return 0;
    }
}
