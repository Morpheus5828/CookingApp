package app.foodapp.model;

public class Recipe {
    private int id;
    private String image;
    private String title;
    private int servings;
    private int cookingTime;

    public Recipe (final int id, final String image, final String title, final int servings, final int cookingTime) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.servings = servings;
        this.cookingTime = cookingTime;
    }
}
