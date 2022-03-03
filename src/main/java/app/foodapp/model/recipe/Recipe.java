package app.foodapp.model.recipe;

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

    public String getIngredients() {
        RecipeInformation recipeInfo = new RecipeInformation(String.valueOf(getId()));
        return recipeInfo.getIngredients();
    }

    public String displaySimpleCharacteristics() {
        return "Recipe :" + getTitle() + "\n" +
                "Cooking Time: " + getCookingTime() + "\n" +
                "Serving: " + getServings() + " people(s)" + "\n";
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

    public String displayDetailsCharacteristicsGUI(){
        String details = "\n" +
                getTitle() + "\n" +
                getServings() + " People(s)" + "\t\t" + " • Cooking time: " + getCookingTime() + " min " + "\n\n" +
                "Ingredient(s)" + "\n\n" +
                getIngredients() + "\n" +
                "Step(s) instruction(s): " + "\n" +
                getSteps() + "\n";
        return details;
    }
}
