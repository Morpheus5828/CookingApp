package app.foodapp.model.dataManipulation.recipe;

import app.foodapp.model.dataManipulation.MeasureSystem;

import java.io.IOException;
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
                    default:
                        ingredient = information.get("fullDescription");
                }
                ingredientsList.add(ingredient);
            }
        }
        return ingredientsList;
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
