package app.foodapp.model.node;

public class Welcome extends Node{

    public Welcome() {
        super();
        addNodes();

    }

    private void addNodes() {
        // Creation of link with Welcome class
        this.neighborsList.add(NodeName.FAVORITE);
        this.neighborsList.add(NodeName.MEASURE_SYSTEM);
        this.neighborsList.add(NodeName.GET_RECIPE_BY_INGREDIENT);
    }

}
