package app.foodapp.model.dataManipulation.recipe;

import app.foodapp.model.dataManipulation.MeasureSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

//TODO problem with test because I add a new property in constructor
public class Recipe {
    private final String id;
    private final String image;
    private final String title;
    private final double servings;
    private final double cookingTime;

    // Constructor for detail display
    public Recipe (String id, String image, String title, double servings, double cookingTime) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.servings = servings;
        this.cookingTime = cookingTime;
    }

    // Constructor for simple display
    public Recipe (String id, String title, double servings, double cookingTime) {
        this.id = id; // no value
        this.image = "";   // no value
        this.title = title;
        this.servings = servings;
        this.cookingTime = cookingTime;
    }

    public String getId() {
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

    public String getSteps() throws NullPointerException{
        String result = "Steps unavailable";
        try {
            result = "";
            RecipeInformation recipeInfo = new RecipeInformation(String.valueOf(getId()));
            for(int i = 0; i < recipeInfo.getStepRecipeInformation().keySet().size(); i++) {
                result += "\t" + "• " + i + " " + recipeInfo.getStepRecipeInformation().get(i) + "\n";
            }
            return result;
        } catch (NullPointerException e) {
            result = "Steps unavailable";
        }
        return result;
    }

    public ArrayList<String> getStepsGUI() throws NullPointerException{
        ArrayList<String> steps = new ArrayList<>();
        try {
            RecipeInformation recipeInfo = new RecipeInformation(String.valueOf(getId()));
            for(int i = 0; i < recipeInfo.getStepRecipeInformation().keySet().size(); i++) {
                steps.add(recipeInfo.getStepRecipeInformation().get(i));
            }
        } catch (NullPointerException e) {
            return null;
        }
        return steps;
    }

    public String getIngredients() {
        RecipeInformation recipeInfo = new RecipeInformation(String.valueOf(getId()));
        return recipeInfo.getIngredients();
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

    public void displaySimpleCharacteristics() {
        System.out.println(
            "Recipe :" + getTitle() + "\n" +
            "Cooking Time: " + getCookingTime() + "\n" +
            "Serving: " + getServings() + " people(s)" + "\n"
        );
    }

    public void displayDetailsCharacteristics() {
        System.out.println(
            "\n" +
            "------------------------------------------------" + "\n" +
            getTitle() + "\n" +
            "------------------------------------------------" + "\n" +
            "• " + getServings() + " People(s)" + "\t\t" + " • Cooking time: " + getCookingTime() + " min " + "\n\n" +
            "• Ingredient(s)" + "\n\n" +
             getIngredients() + "\n" +
            "• Step(s) instruction(s): " + "\n" +
             getSteps() + "\n"
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id);
    }
}
