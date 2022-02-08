package app.foodapp.model.node;

public class MeasureSystem extends Node{
    public MeasureSystem() {
        super();
        addNodes();
    }

    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.add(NodeName.WELCOME);
        this.neighborsList.add(NodeName.FAVORITE);
        this.neighborsList.add(NodeName.GET_RECIPE_BY_INGREDIENT);
    }
}
