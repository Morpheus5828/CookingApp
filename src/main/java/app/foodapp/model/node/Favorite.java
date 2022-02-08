package app.foodapp.model.node;

public class Favorite extends Node{
    public Favorite() {
        super();
        addNodes();
    }

    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.add(NodeName.RECIPE_DETAILS);
        this.neighborsList.add(NodeName.WELCOME);
        this.neighborsList.add(NodeName.GET_RECIPE_BY_INGREDIENT);
        this.neighborsList.add(NodeName.MEASURE_SYSTEM);

    }
}
