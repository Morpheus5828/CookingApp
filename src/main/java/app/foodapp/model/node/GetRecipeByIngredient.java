package app.foodapp.model.node;

public class GetRecipeByIngredient extends Node{
    public GetRecipeByIngredient() {
        super();
        addNodes();
        System.out.println("BINGO I AM THE MACHINNNNNNEEEEEEE");
    }

    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.add(NodeName.FAVORITE);
        this.neighborsList.add(NodeName.RECIPE_DETAILS);
        this.neighborsList.add(NodeName.WELCOME);
        this.neighborsList.add(NodeName.MEASURE_SYSTEM);

    }



}
