package app.foodapp.model.dataManipulation.recipe;

import java.util.ArrayList;

public class Recipe {
    private final int id;
    private final String image;
    private final String title;
    private final double servings;
    private final double cookingTime;

    public Recipe (final int id, final String image, final String title, final double servings, final double cookingTime) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.servings = servings;
        this.cookingTime = cookingTime;
    }

    public Recipe (final String title, final double servings, final double cookingTime) {
        this.id = 0; // no value
        this.image = "";   // no value
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

    public double getServings() {
        return this.servings;
    }

    public double getCookingTime() {
        return this.cookingTime;
    }

    public ArrayList<String> getSteps() {
        return new ArrayList<>();
    }

    public ArrayList<String> getIngredientsList() {
        return new ArrayList<>();
    }

    public float getScore() {
        return 0;
    }
}
