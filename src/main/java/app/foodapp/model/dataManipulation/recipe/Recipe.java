package app.foodapp.model.dataManipulation.recipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Recipe {
    private final int id;
    //private final Map<Integer, String> step;
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
        //this.step = new HashMap<>();
    }

    public Recipe (final String title, final double servings, final double cookingTime) {
        this.id = 0; // no value
        this.image = "";   // no value
        this.title = title;
        this.servings = servings;
        this.cookingTime = cookingTime;
        //this.step = step;
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

    public ArrayList<String> getIngredientsList() {
        return new ArrayList<>();
    }

    public float getScore() {
        return 0;
    }

    /*public void bigDisplay() {
        System.out.println(
            "\t" + "Recipe: " + this.title + "\n" +
            "\t" + "Cooking Time: " + this.cookingTime + "\n" +
            "\t" + "Serving: " + this.servings + "\n" +
            "\t" + "Instructions: " + this.step.toString() + "\n" +
            "\t" + "-----------------------------------------------------------" + "\n"
        );
    }*/
}
