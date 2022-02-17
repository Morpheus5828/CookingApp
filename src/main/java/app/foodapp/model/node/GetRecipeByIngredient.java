package app.foodapp.model.node;

public class GetRecipeByIngredient extends Node{
    public GetRecipeByIngredient() {
        super();
        addNodes();
    }

    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.put(0, NodeName.WELCOME);
        this.neighborsList.put(2, NodeName.FAVORITE);
        this.neighborsList.put(3, NodeName.MEASURE_SYSTEM);
        this.neighborsList.put(4, NodeName.RECIPE_DETAILS);

    }



}
