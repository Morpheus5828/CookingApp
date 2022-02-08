package app.foodapp.model.node;

public class GetRecipeByIngredient extends Node{
    public GetRecipeByIngredient() {
        super();
        // We add current class neighbors
        this.neighborsList.add(NodeName.RECIPE_DETAILS);
    }



}
