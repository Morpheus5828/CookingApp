package app.foodapp.model;

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
}
