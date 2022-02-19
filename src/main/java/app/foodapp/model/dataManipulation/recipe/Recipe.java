package app.foodapp.model.dataManipulation.recipe;

public class Recipe {
    private final int id;
    private final String image;
    private final String title;
    private final double servings;
    private final double cookingTime;

    // Constructor for detail display
    public Recipe (final int id, final String image, final String title, final double servings, final double cookingTime) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.servings = servings;
        this.cookingTime = cookingTime;
    }

    // Constructor for simple display
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

    public String getSteps() {
        RecipeInformation recipeInfo = new RecipeInformation(String.valueOf(getId()));
        String result = "";
        for(int i = 0; i < recipeInfo.getStepRecipeInformation().keySet().size(); i++) {
            result += "\t" + "• " + i + " " + recipeInfo.getStepRecipeInformation().get(i) + "\n";
        }
        return result;
    }

    public void getIngredients() {
        RecipeInformation recipeInfo = new RecipeInformation(String.valueOf(getId()));
        recipeInfo.getIngredients();
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
            "------------------------------------------------" + "\n" +
            getTitle() + "\n" +
            "------------------------------------------------" + "\n" +
            "• " + getServings() + " People(s)" + "\t\t" + " • Cooking time: " + getCookingTime() + " min " + "\n\n" +
            "Ingredient(s)" + "\n" +
            //getIngredientsList();
            "Step(s) instruction(s): " + "\n" +
            getSteps() + "\n"

        );
    }
}
