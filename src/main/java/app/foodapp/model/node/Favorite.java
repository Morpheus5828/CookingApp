package app.foodapp.model.node;

public class Favorite extends Node{
    public Favorite() {
        super();
        addNodes();
    }

    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.put(0, NodeName.WELCOME);
        this.neighborsList.put(1, NodeName.GET_RECIPE_BY_INGREDIENT);
        this.neighborsList.put(3, NodeName.MEASURE_SYSTEM);
        this.neighborsList.put(4, NodeName.RECIPE_DETAILS);
    }

    public void launch() {
        System.out.println();
    }
}
